package ser.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {

	private double x;
	private double y;

	private double velX = 0;
	private double velY = 0;
	

	private Texture tex;
	
	public Player(double x, double y, Texture tex) {
		this.x = x;
		this.y = y;
		this.tex = tex;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(x <=0)
			x = 0;
		if(x >= 640 - 32)
			x=640 - 32;
		if(y<0)
			y=0;
		if(y >=480 -40)
			y=480 -40;
	}

	public void render(Graphics g) {
		g.drawImage(tex.player, (int) x, (int) y, null);
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

	public void setVelX(double velX)
	{
		this.velX = velX;
	}
	public void setVelY(double velY)
	{
		this.velY = velY;
	}
	
}
