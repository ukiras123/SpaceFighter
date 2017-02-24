package ser.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import ser.main.interfaces.EntityA;

public class Bullet extends GameObject implements EntityA {

	private Texture tex;
	private Game game;
	private int level;

	public Bullet(double x, double y, Texture tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		level = game.getLevel();
	}

	public void tick() {
		if (level >= 1 && level <= 3) {
			y -= 6;
		} else if (level > 3 && level <= 6) {
			y -= 9;
		} else {
			y -= 12;
		}
	}

	public void render(Graphics g) {
		if (level >= 1 && level <= 3) {
			g.drawImage(tex.missile[0], (int) x, (int) y, null);
		} else if (level > 3 && level <= 6) {
			g.drawImage(tex.missile[1], (int) x, (int) y, null);
		} else {
			g.drawImage(tex.missile[2], (int) x, (int) y, null);
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
