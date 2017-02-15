package ser.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import ser.main.classes.EntityA;
import ser.main.classes.EntityB;
import ser.main.lib.Animation;

public class Enemy extends GameObject implements EntityB {

	private Texture tex;
	Random r = new Random();
	
	private Game game;
	private Controller c;

	private int speed = r.nextInt(3) + 1;

	Animation anim;

	public Enemy(double x, double y, Texture tex, Controller c, Game game) {
		super(x, y);
		this.tex = tex;
		this.c = c;
		this.game = game;
		anim = new Animation(5, tex.enemy, tex.enemy, tex.enemy);
	}

	public void tick() {
		y += speed;

		if (y > Game.HEIGHT * Game.SCALE) {
			x = r.nextInt(640);
			y = -5;
		}
		
		for(int i =0;i <game.ea.size();i++)
		{
			EntityA tempEnt = game.ea.get(i);
			
		if(Physics.Collision(this, tempEnt))
		{
			c.removeEntity(tempEnt);
			c.removeEntity(this);
			game.setEnemy_killed(game.getEnemy_killed() + 1);
			
		}
		}
		anim.runAnimation();

	}

	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 0);
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
