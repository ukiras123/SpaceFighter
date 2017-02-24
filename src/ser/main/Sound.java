
package ser.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private URL url1, url2, url3, url4;
	private AudioClip gun, boom;

	private Clip menuClip, gameClip;
	private AudioInputStream menu, game;

	public static boolean menuPlaying, gamePlaying, explosionOn;

	public Sound() {
		menuPlaying = false;
		gamePlaying = false;
		url1 = Sound.class.getResource("/laserSound.wav");

		url2 = Sound.class.getResource("/boom.wav");

		url3 = Sound.class.getResource("/adventure.wav");

		url4 = Sound.class.getResource("/adventure.wav");

		gun = Applet.newAudioClip(url1);

		boom = Applet.newAudioClip(url2);

		try {
			game = AudioSystem.getAudioInputStream(url4);
			menu = AudioSystem.getAudioInputStream(url3);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void playGunSound() {
		gun.play();
	}

	public void stopGunSound() {
		gun.stop();
	}

	public void playExplosion() {
		boom.stop();
		boom.play();
	}

	public void stopExplosion() {
		boom.stop();
	}
/*
	public void playMenu() throws LineUnavailableException, IOException {
		if (gamePlaying == false && menuPlaying == false) {
			menuClip = AudioSystem.getClip();

			try {
				menuClip.open(menu);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			menuClip.start();
			menuClip.loop(Clip.LOOP_CONTINUOUSLY);
			menuPlaying = true;
		}
	}

	public void stopMenu() {
		if (gamePlaying == true) {
			menuClip.stop();
			menuPlaying = false;
		}
	}
*/
	public void playGame() throws LineUnavailableException {
		if (gamePlaying == false && menuPlaying == false) {
			gameClip = AudioSystem.getClip();
			try {
				gameClip.open(game);
			} catch (

			LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			gameClip.start();
			gameClip.loop(Clip.LOOP_CONTINUOUSLY);
			gamePlaying = true;
		}
	}

	public void stopGame() {
		
			gameClip.stop();
			gamePlaying = false;
		
	}

}

/*
 * package ser.main;
 * 
 * import java.io.IOException; import java.net.URL;
 * 
 * import javax.sound.sampled.AudioInputStream; import
 * javax.sound.sampled.AudioSystem; import javax.sound.sampled.Clip; import
 * javax.sound.sampled.LineUnavailableException; import
 * javax.sound.sampled.UnsupportedAudioFileException;
 * 
 * class Sound {
 * 
 * private URL url1, url2, url3, url4; private Clip gunClip, menuClip, boomClip,
 * gameClip; private AudioInputStream gun, boom, menu, game; public static
 * boolean menuPlaying, gamePlaying;
 * 
 * public Sound() { menuPlaying = false; gamePlaying = false; url1 =
 * Sound.class.getResource("/laserSound.wav");
 * 
 * url2 = Sound.class.getResource("/boom.wav");
 * 
 * url3 = Sound.class.getResource("/ad.wav");
 * 
 * url4 = Sound.class.getResource("/ad.wav"); try { game =
 * AudioSystem.getAudioInputStream(url4); menu =
 * AudioSystem.getAudioInputStream(url3); boom =
 * AudioSystem.getAudioInputStream(url2); gun =
 * AudioSystem.getAudioInputStream(url1); } catch (UnsupportedAudioFileException
 * e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
 * 
 * }
 * 
 * public void playGunSound() throws LineUnavailableException, IOException {
 * gunClip = AudioSystem.getClip(); gunClip.open(gun); gunClip.start(); }
 * 
 * public void stopGunSound() { gunClip.stop(); }
 * 
 * public void playExplosion() throws LineUnavailableException, IOException {
 * boomClip = AudioSystem.getClip(); boomClip.open(boom); boomClip.start();
 * boomClip.stop(); }
 * 
 * public void stopExplosion() { boomClip.stop(); }
 * 
 * public void playMenu() throws LineUnavailableException, IOException { if
 * (gamePlaying == false) { menuClip = AudioSystem.getClip();
 * 
 * try { menuClip.open(menu); } catch (LineUnavailableException e) {
 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
 * menuClip.start(); menuClip.loop(Clip.LOOP_CONTINUOUSLY); menuPlaying = true;
 * } }
 * 
 * public void pauseMenu() {
 * 
 * }
 * 
 * public void stopMenu() { if (gamePlaying == true) { menuClip.stop();
 * menuPlaying = false; } }
 * 
 * public void playGame() throws LineUnavailableException { if (gamePlaying ==
 * false) { gameClip = AudioSystem.getClip(); try { gameClip.open(game); } catch
 * (
 * 
 * LineUnavailableException e) { e.printStackTrace(); } catch (IOException e) {
 * e.printStackTrace(); } gameClip.start();
 * gameClip.loop(Clip.LOOP_CONTINUOUSLY); gamePlaying = true; } }
 * 
 * public void stopGame() { if (gamePlaying == true) { gameClip.stop();
 * gamePlaying = false; } }
 * 
 * public void pauseGame() {
 * 
 * } }
 */