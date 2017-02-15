package ser.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import ser.main.classes.EntityA;
import ser.main.classes.EntityB;

public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Space Game";

	private boolean running = false;
	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	private BufferedImage background1 = null;
	private BufferedImage player = null;
	
	private boolean is_shooting = false;

	private int enemy_count = 5;
	private int enemy_killed = 0;
	

	public int getEnemt_killed() {
		return enemy_killed;
	}

	public void setEnemt_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}

	private Player p;
	private Controller c;
	private Texture tex;
	private Menu menu;

	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	
	public static int HEALTH = 100 * 2;

	public static enum STATE
	{
		MENU,
		GAME
	};
	
	public static STATE State = STATE.MENU;
	
	
	public void init() {
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/sheet.png");
			background = loader.loadImage("/background.gif");
			background1 = loader.loadImage("/background.gif");
			player = loader.loadImage("/space_ship2.gif");

		} catch (IOException e) {
			e.printStackTrace();
		}

		addKeyListener(new KeyInput(this));
		this.addMouseListener(new MoustInput());
		
		tex = new Texture(this);
		
		c = new Controller(tex, this);
		p = new Player(WIDTH, HEIGHT*2, tex, this, c);
		menu = new Menu();
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		c.createEnemy(enemy_count);

	}

	private synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
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
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
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
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println(updates+" Ticks, Fps "+frames);
				updates = 0;
				frames = 0;
			}
			
		}
		stop();
		
	}

	// everything that updates
	private void tick() {
		if(State == STATE.GAME)
		{
		p.tick();
		c.tick();
		}
		if (enemy_killed >= enemy_count)
		{
			enemy_count += 2;
			enemy_killed = 0;
			c.createEnemy(enemy_count);
			
		}
		
		if (HEALTH <=0)
		{
			State = STATE.MENU;
		}
		
	}

	// everything that renders
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		//

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		g.drawImage(background, 0, 0, null);
		
		if(State == STATE.GAME)
		{
		g.drawImage(background1, 0, 0, null);
		p.render(g);
		c.render(g);
		
		int alpha = 150;
		Color grey = new Color(224, 224, 224, alpha);
		Color green = new Color(0, 255, 0, alpha);
		Color white = new Color(255, 225, 225, alpha);


		g.setColor(grey);
		g.fillRect(5, 5, 200, 20);
		
		g.setColor(green);
		g.fillRect(5, 5, HEALTH, 20);
		
		g.setColor(white);
		g.drawRect(5, 5, 200, 20);
		
		
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(grey);
		g.fillRect(WIDTH*15/10, 5, 80, 20);
		
		Font fnt0 = new Font("arial" , Font.BOLD, 13);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Score : " + String.valueOf(enemy_killed), WIDTH*15/10	, 20);
		
		
		}
		else if(State == STATE.MENU )
		{
			menu.render(g);
		}
		

		g.dispose();
		bs.show();
	}


	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(State == STATE.GAME)
		{
		if (key == KeyEvent.VK_RIGHT) {
			p.setVelX(5);
		} else if (key == KeyEvent.VK_LEFT) {
			p.setVelX(-5);

		} else if (key == KeyEvent.VK_DOWN) {
			p.setVelY(5);

		} else if (key == KeyEvent.VK_UP) {
			p.setVelY(-5);
		} else if (key == KeyEvent.VK_SPACE && !is_shooting)
		{
			c.addEntity(new Bullet(p.getX(), p.getY(), tex, this));
			is_shooting = true;
		}
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_LEFT) {
			p.setVelX(0);

		} else if (key == KeyEvent.VK_DOWN) {
			p.setVelY(0);

		} else if (key == KeyEvent.VK_UP) {
			p.setVelY(0);
		} else if (key == KeyEvent.VK_SPACE)
		{
			is_shooting = false;
		}
		
	}
	

	public static void main(String[] args) {
		Game game = new Game();

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
	
	public BufferedImage getPlayer() {
		return player;
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

	
	
}
