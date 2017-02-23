package ser.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

//Whenever we press key, this class is called
public class KeyInput extends KeyAdapter {

	Game game;

	public KeyInput(Game game) {
		this.game = game;
	}

	public void keyPressed(KeyEvent e) {
		try {
			game.keyPressed(e);
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}

}
