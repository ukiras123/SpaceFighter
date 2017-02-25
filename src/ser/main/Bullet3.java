package ser.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import ser.main.interfaces.EntityA;

public class Bullet3 extends GameObject implements EntityA {

	private Texture tex;
	private int level;

	public Bullet3(double x, double y, Texture tex, Game game) {
		super(x, y);
		this.tex = tex;
		level = game.getLevel();
	}

	// updating all variables
	// speed of bulled increases with level
	public void tick() {
		if (level >= 1 && level <= 3) {
			y -= 6;
			x -= 1.5;
		} else if (level > 3 && level <= 6) {
			y -= 9;
			x -= 1.5;
		} else {
			y -= 12;
			x -= 1.5;
		}
	}

	// controlling bullet pic and level
	public void render(Graphics g) {
		if (level >= 1 && level <= 3) {
			g.drawImage(tex.missile[0], (int) x, (int) y, null);
		} else if (level > 3 && level <= 5) {
			g.drawImage(tex.missile[1], (int) x, (int) y, null);
		} else if (level > 5 && level <= 7) {
			g.drawImage(tex.missile[2], (int) x, (int) y, null);
		} else if (level > 7 && level <= 9){
			g.drawImage(tex.missile[3], (int) x, (int) y, null);
		}else{
			g.drawImage(tex.missile[4], (int) x, (int) y, null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public double getY() {
		return y;
	}

	public double getX() {
		return x;
	}

}
