package ser.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import ser.main.interfaces.EntityA;
import ser.main.interfaces.EntityB;

//Main controller to create/remove entities, implemented as linkedList
public class Controller {

	private LinkedList<EntityA> ea = new LinkedList<EntityA>();	//Bullet
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();	//Enemy (enemy + asteroid)

	EntityA enta; // For bullet
	EntityB entb; // For enemy, asteroid

	private Random r = new Random();
	private Texture tex;
	private Game game;

	public Controller(Texture tex, Game game) {
		this.tex = tex;
		this.game = game;
	}

	public void createEnemy(int enemy_count) {
		for (int i = 0; i < enemy_count / 2; i++) {
			addEntity(new Enemy(r.nextInt(640), -10, tex, this, game));	//enemy entity
			addEntity(new Asteroid(r.nextInt(640), -10, tex, this, game));	//asteroid entity
		}	
	}

	public void tick() {

		// bullet entities
		for (int i = 0; i < ea.size(); i++) {
			enta = ea.get(i);
			enta.tick();
		}

		// Enemy group i.e enemy and asteroid
		for (int i = 0; i < eb.size(); i++) {
			entb = eb.get(i);
			entb.tick();
		}

	}

	public void render(Graphics g) {

		// bullet entities
		for (int i = 0; i < ea.size(); i++) {
			enta = ea.get(i);
			enta.render(g);
		}
		
		// Enemy group i.e enemy and asteroid
		for (int i = 0; i < eb.size(); i++) {
			entb = eb.get(i);
			entb.render(g);
		}

	}

	public void addEntity(EntityA block) {
		ea.add(block);
	}

	public void removeEntity(EntityA block) {
		ea.remove(block);

	}

	public void addEntity(EntityB block) {
		eb.add(block);
	}

	public void removeEntity(EntityB block) {
		eb.remove(block);
	}

	public LinkedList<EntityA> getEntityA() {
		return ea;
	}

	public LinkedList<EntityB> getEntityB() {
		return eb;
	}
}
