package ser.main;

import java.awt.image.BufferedImage;

public class Texture {
	
	public BufferedImage player, missile, enemy;
	
	private SpriteSheet ss ;
	
	public Texture(Game game)
	{
		ss = new SpriteSheet(game.getSpriteSheet());
		getTextures();
	}

	private void getTextures()
	{
		player = ss.grabImage(3, 1	, 32, 32);
		missile = ss.grabImage(2, 1, 32, 32);
		enemy = ss.grabImage(1, 1, 32, 32);
	}
}
