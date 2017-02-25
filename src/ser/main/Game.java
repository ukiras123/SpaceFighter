package ser.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;

import ser.main.interfaces.EntityA;
import ser.main.interfaces.EntityB;

public class Game extends Canvas implements Runnable {


	//JFrame dimension variables
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;				
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Space Fighter";

	private static int totalContinue = 3; //total number of user continuation
	private int score = 0; 			// default score
	private int enemy_count = 8;	// default enemy
	private int enemy_killed = 0;	//default enemy killed
	private static int level = 1;		// Beginning level of the game
	public static int HEALTH = 200;		// initial health with 200 width
	public static STATE State = STATE.MENU; // default state at the beginning of game

	
	private Thread thread;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	private BufferedImage background1 = null;
	private BufferedImage mainPlayer = null;

	private boolean running = false;  //just to check if game is running
	private boolean is_shooting = false;		//useful for sound purpose

	private Sound sound;
	private HighScoreUtil highScore;	// to access highScore file.
	private Player p;
	private Controller c;
	private Texture tex;
	private Menu menu;

	public LinkedList<EntityA> ea; //Player entity 
	public LinkedList<EntityB> eb; //Enemy entity


	//Creating state for each different STATES in game
	public static enum STATE {
		MENU, GAME, HELP, GAMEOVER, RESTART			
	};

	
	//instantiating all required objects and variables.
	public void init() {
		requestFocus();  //needed to focus JFrame, ActionListenor might not work sometimes without it.
		highScore = new HighScoreUtil();
		sound = new Sound();
		//Loading up all the animation pic
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/sheet1.png");
			background = loader.loadImage("/starbg.png");		//Menu + Game background
			background1 = loader.loadImage("/starbg1.png");	// Game background
			mainPlayer = loader.loadImage("/space.gif");
		} catch (IOException e) {
			e.printStackTrace();
		}


		//Initiating these two to listen mouse and keyboard input
		addKeyListener(new KeyInput(this));
		addMouseListener(new MoustInput(sound));
		
		tex = new Texture(this);
		c = new Controller(tex, this);
		p = new Player(WIDTH, HEIGHT * 2, tex, this, c);
		menu = new Menu();
		

		ea = c.getEntityA();		//Bullets
		eb = c.getEntityB();		//Enemies: enemy + asteroid

		c.createEnemy(enemy_count); //Creating new enemies to start the game
		
		if (Sound.gameMysicPlaying != true )
		{
		try {
			sound.playGameMusic();	//Start game music
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	private synchronized void start() {
		if (running)		//For safety check
			return;

		running = true;
		thread = new Thread(this);		//creating new thread
		thread.start();
	}

	private synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0; // will be used to set the speed of the game, high = more speed
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		//Controlling the FPS in game across all device
		//Main game loop: tick() & render()
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	//Created these two variable to move background image top to bottom
	//Flying illusion
	int ybg = 0;
	int ybg2 = -((HEIGHT * 2));

	// Updating all the variables here
	private void tick() {

		//For background Image animation
		if (ybg2 <= 0) {
			ybg2 += 1;
		} else {
			ybg2 = -((HEIGHT * 2));
		}

		if (ybg <= (HEIGHT * 2)) {
			ybg += 1;
		} else {
			ybg = 0;
		}

		if (State == STATE.GAME) {
			p.tick();
			c.tick();
		}
		
		//Every time all the enemies are killed in one level, more enemies are created
		if (enemy_killed >= enemy_count) {
			ea.clear(); // clearing all bullets from the list
			eb.clear(); // clearing all enemy group from the list
			enemy_count += 2;	//enemy added for new level
			enemy_killed = 0;	//reseting enemy_killed back to 0 for that level
			level++;		//each cycle will result new level
			c.createEnemy(enemy_count);	//new level new enemies
		}

	}

	// All Graphical activities here
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);		// We are rendering images in 3 buffers
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		//two background moving each other from top to bottom to top
		g.drawImage(background, 30, ybg, null);
		g.drawImage(background, 30, ybg2, null);

		//Animations during game time
		if (State == STATE.GAME) {
			//We are literally cycling two images so we can give that feel of flying as in menu
			//changing background image after level 10
			if(level < 10)
			{
			g.drawImage(background, 0, ybg, null);
			g.drawImage(background, 0, ybg2, null);
			}
			else
			{
			g.drawImage(background1, 0, ybg, null);
			g.drawImage(background1, 0, ybg2, null);
			}
			p.render(g);
			c.render(g);

			int alpha = 150;
			Color grey = new Color(224, 224, 224, alpha);
			Color green = new Color(0, 255, 0, alpha);
			Color white = new Color(255, 225, 225, alpha);

			g.setColor(grey);
			g.fillRect(5, 5, 200, 20);

			g.setColor(green);
			g.fillRect(5, 5, HEALTH, 20);	//Health bar

			g.setColor(white);
			g.drawRect(5, 5, 200, 20);

			g.setColor(grey);
			g.fillRect(WIDTH * 2 - 77, 5, 80, 20);

			Font fnt0 = new Font("arial", Font.BOLD, 13);
			g.setFont(fnt0);
			g.setColor(Color.white);
			g.drawString("Score : " + String.valueOf(getScore()), WIDTH * 2 - 75, 20);

			fnt0 = new Font("Monaco", Font.BOLD, 20);
			g.setFont(fnt0);
			g.drawString("Level : " + String.valueOf(level), WIDTH - 30, 20);

		} else if (State == STATE.MENU) {
			menu.render(g);
		} else if (State == STATE.HELP) {
			
			Font fnt0 = new Font("arial", Font.BOLD, 59);
			g.setFont(fnt0);
			g.setColor(Color.white);
			g.drawString("SPACE FIGHTER", Game.WIDTH / 4, 100);

			Font fnt1 = new Font("Impact", Font.CENTER_BASELINE, 22);
			g.setFont(fnt1);

			g.drawString("Up: 		Up Arrow", (Game.WIDTH / 2) + 100, 150);
			g.drawString("Down: 	Down Arrow", (Game.WIDTH / 2) + 100, 200);
			g.drawString("Left: 	Left Arrow", (Game.WIDTH / 2) + 100, 250);
			g.drawString("Right: 	Right Arrow", (Game.WIDTH / 2 ) + 100, 300);
			g.drawString("Shoot: 	Space", (Game.WIDTH / 2) + 50, 350);
			g.drawString("Pause: 	ESC", (Game.WIDTH / 2) + 200, 350);
			
			//Go Back button
			Graphics2D g2d = (Graphics2D) g;
			Rectangle backButton = new Rectangle(Game.WIDTH / 2 + 120, 380, 100, 50);
			g.drawString("Go Back", backButton.x + 19, backButton.y + 30);
			g2d.draw(backButton);

		} else if (State == STATE.GAMEOVER) {
			
			Graphics2D g2d = (Graphics2D) g;
			Rectangle restartGame = new Rectangle(Game.WIDTH / 2 + 120, 250, 100, 50);
			Rectangle continueGame = new Rectangle(Game.WIDTH / 2 + 120, 150, 100, 50);

			Font fnt0 = new Font("arial", Font.BOLD, 59);
			g.setFont(fnt0);
			g.setColor(Color.white);
			g.drawString("GAME OVER", Game.WIDTH / 2, 100);

			fnt0 = new Font("arial", Font.BOLD, 15);
			g.setFont(fnt0);
			
			g.drawString("Cont. : " + totalContinue, continueGame.x + 19, continueGame.y + 30);
			g2d.draw(continueGame);
			g.drawString("Main Menu", restartGame.x + 15, restartGame.y + 30);
			g2d.draw(restartGame);
			
			//Score
			fnt0 = new Font("arial", Font.BOLD, 30);
			g.setFont(fnt0);
			g.drawString("Your Score : " + score, restartGame.x - 50, restartGame.y + 100);
			
			//HighScore
			fnt0 = new Font("arial", Font.BOLD, 50);
			g.setFont(fnt0);
			try {
				g.drawString("High Score : " + highScore.getScore(), restartGame.x - 120, restartGame.y + 150);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		// In case a player dies in the game
		if (HEALTH <= 0) {
			State = STATE.GAMEOVER;
			HEALTH = 200;
			p.setVelX(0);		// Rest Player spped back to 0
			p.setVelY(0);
			try {
				if (highScore.getScore() < score) {
					highScore.setScore(score);		//Always save the user score after he dies in the game
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//In case a player chose to restart the game from the beginning we setting everything default
		if (State == STATE.RESTART) {
			score = 0; // to reset score after game over
			level = 1;	// default level
			enemy_count = 8; // to reset enemy count after game over
			enemy_killed = 0; // to reset enemy killed after game over
			ea.clear(); // clearing all bullets from the list
			eb.clear(); // clearing all enemy group from the list
			c.createEnemy(enemy_count); //Creating new enemies to start the game
			State = STATE.MENU;
			HEALTH = 200;	// Default health
		}
		
		g.dispose();
		bs.show();
	}

	//Keyboard functionality
	public void keyPressed(KeyEvent e) throws LineUnavailableException, IOException {
		int key = e.getKeyCode();
		//Speed of player changes with level
		if (State == STATE.GAME) {
			
			if (key == KeyEvent.VK_RIGHT) {
				p.setVelX(4 + level * 0.5);	
			} else if (key == KeyEvent.VK_LEFT) {
				p.setVelX(-4 - level * 0.5);
			} else if (key == KeyEvent.VK_DOWN) {
				p.setVelY(4 + level * 0.5);
			} else if (key == KeyEvent.VK_UP) {
				p.setVelY(-4 - level * 0.5);
			} 
			
			else if (key == KeyEvent.VK_SPACE && !is_shooting) {
				c.addEntity(new Bullet(p.getX(), p.getY(), tex, this));	//Creating bullet for each space
				sound.playGunSound();			//Every space will produce gun sound
				is_shooting = true;
			} else if (key == KeyEvent.VK_ESCAPE) {
				State = STATE.MENU;		// Just go back to Menu (Pause the game) 
			}
		} else if (State == STATE.MENU) {
			if (key == KeyEvent.VK_ESCAPE) {		
				State = STATE.GAME;	// start new game or go back to game if it was paused
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (State == STATE.GAME) {
			// setting velX an velY back to 0, so it wont continue to move
			if (key == KeyEvent.VK_RIGHT) {
				p.setVelX(0);
			} else if (key == KeyEvent.VK_LEFT) {
				p.setVelX(0);
			} else if (key == KeyEvent.VK_DOWN) {
				p.setVelY(0);
			} else if (key == KeyEvent.VK_UP) {
				p.setVelY(0);
			} else if (key == KeyEvent.VK_SPACE) {
				sound.stopGunSound();
				is_shooting = false;
			}
		}

	}
	
	//Main starting point of the game
	//Creating new JFrame with pre-created dimension
	public static void main(String[] args) {
		Game game = new Game();
		//Game dimension : 640 x 480
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();

	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public BufferedImage getPlayerBuffer() {
		return mainPlayer;
	}

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}

	public Player getPlayer() {
		return p;
	}

	public int getLevel() {
		return level;
	}

	public static int getTotalContinue() {
		return totalContinue;
	}

	public static void setTotalContinue(int totalContinue) {
		Game.totalContinue = totalContinue;
	}
	
	public void updateScore() {		//updating score for enemy killed 
		this.score += 1;
	}

	public int getScore() {
		return score;
	}
	
	public Sound getSound()
	{
		return sound;
	}
	
}
