package ser.main;

import java.awt.Graphics;
import java.util.Random;

public class Enemy implements Entity {
	
	private double x, y;
	
	private Texture tex;
	Random r = new Random();
	
	private int speed = r.nextInt(3)+1;
	
	public Enemy(double x, double y, Texture tex)
	{
	this.x = x;
	this.y = y;		
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
