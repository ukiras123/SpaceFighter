package ser.main.interfaces;

import java.awt.Graphics;
import java.awt.Rectangle;

//All player group (player, bullet) use this.
public interface EntityA {

	public void tick();

	public void render(Graphics g);

	public Rectangle getBounds();

	public double getX();

	public double getY();

}
