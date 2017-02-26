package ser.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import ser.main.interfaces.EntityA;
import ser.main.interfaces.EntityB;

public class Asteroid extends GameObject implements EntityB {

	private Random r = new Random();	//to randomize speed and coordinates
	
	private Texture tex;
	private Game game;
	private Controller c;
	private Sound sound;
	private int speed = r.nextInt(3) + 1;

	public Asteroid(double x, double y, Texture tex, Controller c, Game game) {
		super(x, y);
		this.tex = tex;
		this.c = c;
		this.game = game;
		this.sound = game.getSound();
	}

	//updating all variables, speed depends on game Level
	public void tick() {
		y += speed + (game.getLevel() / 3.5);
		
		//After an asteroid goes out of frame, getting it back to top
		if (y > Game.HEIGHT * Game.SCALE) {
			x = r.nextInt(640);
			y = -3;
		}

		//in case of collision occurred removing entity
		for (int i = 0; i < game.ea.size(); i++) {
			EntityA playerGroup = game.ea.get(i);
			//Detecting if enemy collides with and player group (bullet or player)
			if (Physics.Collision(this, playerGroup)) {
				sound.playExplosion();	//Playing explosion sound in case asteroid collides with bullet
				c.removeEntity(playerGroup);	//removing bullet
				c.removeEntity(this);		//removing asteroid
				game.updateScore(10*game.getLevel()); // adding score in case enemy is killed
				game.setEnemy_killed(game.getEnemy_killed() + 1);

			}
		}

	}

	public void render(Graphics g) {
		g.drawImage(tex.asteroid[0], (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

}
