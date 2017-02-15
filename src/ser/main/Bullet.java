package ser.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import ser.main.classes.EntityA;
import ser.main.lib.Animation;

public class Bullet extends GameObject implements EntityA {
	
	private Texture tex;
private Game game;
	
Animation anim;
	public Bullet(double x, double y, Texture tex, Game game) {
		super(x,y);
		this.tex = tex;

		this.game = game;
		
		anim = new Animation(5, tex.missile,tex.missile,tex.missile );
	}
	
	public void tick()
	{
		y -= 6;
	
		anim.runAnimation();
	}
	
	public void render(Graphics g)
	{
anim.drawAnimation(g, x, y, 0);
}
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y,32,32);
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
