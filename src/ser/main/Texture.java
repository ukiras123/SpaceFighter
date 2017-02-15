package ser.main;

import java.awt.image.BufferedImage;

public class Texture {
	
	public BufferedImage player, missile, enemy;
	
	private SpriteSheet ss ;
	Game game;
	public Texture(Game game)
	{
		ss = new SpriteSheet(game.getSpriteSheet());
		this.game = game;
		getTextures();
	}

	private void getTextures()
	{
		player = game.getPlayer();
		//player = ss.grabImage(3, 1	, 32, 32);
		missile = ss.grabImage(2, 1, 32, 32);
		enemy = ss.grabImage(1, 1, 32, 32);
	}
}
