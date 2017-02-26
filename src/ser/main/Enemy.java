package ser.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import ser.main.interfaces.EntityA;
import ser.main.interfaces.EntityB;

public class Enemy extends GameObject implements EntityB {

	private Random r = new Random();
	private Texture tex;
	private Game game;
	private Controller c;
	private int level;
	private int speed = r.nextInt(3) + 4 / 3;
	private Player p;
	private Sound sound;
	
	public Enemy(double x, double y, Texture tex, Controller c, Game game) {
		super(x, y);
		this.tex = tex;
		this.c = c;
		this.game = game;
		p = game.getPlayer();
		level = game.getLevel();
		this.sound = game.getSound();	// passing same sound object from game
	}

	// updating variables, speed of enemy depends on level
	public void tick() {
		y += speed + (game.getLevel() / 2);
		setX(p.getX());	// basic AI logic

		//in case enemy goes out of frame, again getting it back to the top
		if (y > Game.HEIGHT * Game.SCALE) {
			setX(r.nextInt(640));	// basic AI logic
			y = -5;
		}

		for (int i = 0; i < game.ea.size(); i++) {
			EntityA playerGroup = game.ea.get(i);
			//Detecting if enemy collides with and player group (bullet or player)
			if (Physics.Collision(this, playerGroup)) {
				sound.playExplosion(); //Playing explosion sound in case enemy collides with bullet
				c.removeEntity(playerGroup);	//remove bullet
				c.removeEntity(this);	//remove enemy
				game.updateScore(); // adding score in case enemy is killed
				game.setEnemy_killed(game.getEnemy_killed() + 1);
			}
		}
	}

	//Different enemies with different levels
	public void render(Graphics g) {
		if (level >= 1 && level <= 3) {
			g.drawImage(tex.enemy[5], (int) x, (int) y, null);
		} else if (level > 3 && level <= 6) {
			g.drawImage(tex.enemy[1], (int) x, (int) y, null);
		} else if (level > 6 && level <= 9) {
			g.drawImage(tex.enemy[2], (int) x, (int) y, null);
		} else if (level > 9 && level <= 11) {
			g.drawImage(tex.enemy[3], (int) x, (int) y, null);
		} else if (level > 11 && level <= 15){
			g.drawImage(tex.enemy[4], (int) x, (int) y, null);
		} else{
			g.drawImage(tex.enemy[5], (int) x, (int) y, null);
		}
	}
	

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y * 0.8;
	}

	public double getX() {
		return x;
	}

	//AI piece of the code, enemy will follow player
	public void setX(double x) {
		if (x < this.x) {
			this.x -= 0.5;
		} else {
			this.x += 0.5;
		}
	}

}
