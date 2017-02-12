package ser.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import ser.main.classes.EntityB;

public class Enemy extends GameObject implements EntityB {
		
	private Texture tex;
	Random r = new Random();
	
	private int speed = r.nextInt(3)+1;
	
	public Enemy(double x, double y, Texture tex)
	{
	super(x,y);	
	this.tex = tex;
	}

	public void tick()
	{
		y += speed;
		
		if(y>Game.HEIGHT*Game.SCALE)
		{
			x = r.nextInt(640);
			y = -10;
		}
	
	}
	
	public void render(Graphics g)
	{
		g.drawImage(tex.enemy, (int) x, (int) y, null);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y,32,32);
	}
	
	public double getY()
	{
		return y;
	}
	public void setY(double y)
	{
		this.y = y;
	}

	public double getX() {
		return x;
	}
	
}
