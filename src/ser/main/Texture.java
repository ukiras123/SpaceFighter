package ser.main;

import java.awt.image.BufferedImage;

public class Texture {

	public BufferedImage mainPlayer;
	public BufferedImage[] missile = new BufferedImage[3];
	public BufferedImage[] enemy = new BufferedImage[3];
	public BufferedImage[] asteroid = new BufferedImage[3];
	public BufferedImage[] players = new BufferedImage[4];

	private SpriteSheet ss;
	Game game;

	public Texture(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		this.game = game;

		getTextures();
	}

	private void getTextures() {
		missile[0] = ss.grabImage(2, 1, 32, 32);
		missile[1] = ss.grabImage(2, 2, 32, 32);
		missile[2] = ss.grabImage(2, 3, 32, 32);
		asteroid[0] = ss.grabImage(4, 1, 32, 32);
		enemy[0] = ss.grabImage(1, 1, 32, 32);
		enemy[1] = ss.grabImage(1, 2, 32, 32);
		enemy[2] = ss.grabImage(1, 3, 32, 32);
		players[0] = ss.grabImage(1, 4, 32, 32);
		players[1] = ss.grabImage(2, 4, 32, 32);
		players[2] = ss.grabImage(3, 4, 32, 32);
		players[3] = ss.grabImage(4, 4, 32, 32);
		mainPlayer = game.getPlayerBuffer();
	}
}
