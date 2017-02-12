package ser.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

	private LinkedList<Bullet> b = new LinkedList<Bullet>();
	private LinkedList<Enemy> e = new LinkedList<Enemy>();
	
	Random r = new Random();
	
	Bullet TempBullet;
	Enemy TempEnemy;
	
	Game game;
	Texture tex;
	
	public Controller(Game game, Texture tex) {
		this.game = game;
		addEnemy(new Enemy(r.nextInt(Game.WIDTH * Game.SCALE),0,tex));
		
		}

	public void tick() {
		for (int i = 0; i < b.size(); i++) {
			TempBullet = b.get(i);
				if (TempBullet.getY() < 0)
					removeBullet(TempBullet);
			TempBullet.tick();
		}
		for (int i = 0; i < e.size(); i++) {
			TempEnemy = e.get(i);
			
			if (TempEnemy.getY() > (Game.HEIGHT * Game.SCALE))
			{
				TempEnemy.setY(0);
			}
			
			TempEnemy.tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < b.size(); i++) {
			TempBullet = b.get(i);
			
			TempBullet.render(g);
		}
		for (int i = 0; i < e.size(); i++) {
			TempEnemy = e.get(i);
			
			TempEnemy.render(g);
		}
	}

	public void addBullet(Bullet block) {
		b.add(block);
	}

	public void removeBullet(Bullet block) {
		b.remove(block);
	}
	
	public void addEnemy(Enemy block) {
		e.add(block);
	}

	public void removeEnemy(Enemy block) {
		e.remove(block);
	}
	
}
