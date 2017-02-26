package ser.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import ser.main.interfaces.EntityA;
import ser.main.interfaces.EntityB;

public class Player extends GameObject implements EntityA {

	private double velX = 0; //speed towards X
	private double velY = 0; //speed towards Y
	
	private Random rand = new Random();
	private Texture tex;
	private Game game;
	private Controller controller;
	private int randomPlayer;
	private Sound sound;
	public Player(double x, double y, Texture tex, Game game, Controller controller) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.controller = controller;
		randomPlayer = rand.nextInt(5);	//to choose random player, we have 5 players 
		this.sound = game.getSound();
	}

	// updating variables
	public void tick() {
		x += velX;
		y += velY;
		
		//Controlling player just inside the frame
		if (x <= 0)
			x = 0;
		if (x >= 640 - 32)
			x = 640 - 32;
		if (y < 0)
			y = 0;
		if (y >= 480 - 40)
			y = 480 - 40;

		for (int i = 0; i < game.eb.size(); i++) {
			EntityB enemyGroup = game.eb.get(i);
			//Detecting if player collides with and enemy group (asteroid or enemy)
			if (Physics.Collision(this, enemyGroup)) {
				controller.removeEntity(enemyGroup);
				Game.HEALTH -= 20;			// This will decrease heal bar. Total player life available = 200/20 = 10
				game.setEnemy_killed(game.getEnemy_killed() + 1);
				sound.playHitSound();		// Sound when player gets hit by enemy
			}

		}
	}

	public void render(Graphics g) {
		//Random player each game
		if (randomPlayer == 4) {
			g.drawImage(tex.mainPlayer, (int) x, (int) y, null);
		} else {
			g.drawImage(tex.players[randomPlayer], (int) x, (int) y, null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

}
