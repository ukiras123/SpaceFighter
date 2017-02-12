package ser.main;

import java.awt.Graphics;

public class Bullet implements Entity {
	
	private double x;
	private double y;
	
	
	private Texture tex;

	
	public Bullet(double x, double y, Texture tex) {
		this.x = x;
		this.y = y;	
		
		this.tex = tex;

	}
	
	public void tick()
	{
		y -= 6;
		
	}
	
	public void render(Graphics g)
	{
		g.drawImage(tex.missile, (int) x, (int) y, null);
	}
	
	public double getY()
	{
		return y;
	}
	public double getX()
	{
		return x;
	}

}
