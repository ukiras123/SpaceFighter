package ser.main.interfaces;

import java.awt.Graphics;
import java.awt.Rectangle;

//All enemy group (enemy, asteroid) use this.
public interface EntityB {

	public void tick();

	public void render(Graphics g);

	public Rectangle getBounds();

	public double getX();

	public double getY();

}
