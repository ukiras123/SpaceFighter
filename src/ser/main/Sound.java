
package ser.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

@SuppressWarnings("unused")
public class Sound {
	URL url1 = Sound.class.getResource("/laserSound.wav");

	URL url2 = Sound.class.getResource("/boom.wav");

	URL url3 = Sound.class.getResource("/ad.wav");

	URL url4 = Sound.class.getResource("/ad.wav");

	AudioClip gun = Applet.newAudioClip(url1);

	AudioClip boom = Applet.newAudioClip(url2);

	AudioClip menu = Applet.newAudioClip(url3);

	AudioClip game = Applet.newAudioClip(url4);

	public void playGunSound() {
		gun.play();
	}

	public void stopGunSound() {
		gun.stop();
	}

	public void playExplosion() {
		boom.play();
	}

	public void stopExplosion() {
		boom.stop();
	}

	public void playMenu() {
		menu.play();
	}

	public void stopMenu() {
		menu.stop();
	}

	public void playGame() {
		game.play();
	}

	public void stopGame() {
		game.stop();
	}

}

// Ignore for now, m planing to implement in better way.
/*
 * package ser.main;
 * 
 * import java.applet.Applet; import java.applet.AudioClip; import
 * java.io.IOException; import java.net.URL;
 * 
 * import javax.sound.sampled.AudioInputStream; import
 * javax.sound.sampled.AudioSystem; import javax.sound.sampled.Clip; import
 * javax.sound.sampled.LineUnavailableException; import
 * javax.sound.sampled.UnsupportedAudioFileException;
 * 
 * public class Sound {
 * 
 * private URL url1,url2,url3,url4; private Clip gunClip, menuClip, boomClip,
 * gameClip; private AudioInputStream gun, boom, menu, game;
 * 
 * public Sound() { url1 = Sound.class.getResource("/laserSound.wav");
 * 
 * url2 = Sound.class.getResource("/boom.wav");
 * 
 * url3 = Sound.class.getResource("/ad.wav");
 * 
 * url4 = Sound.class.getResource("/ad.wav");
 * 
 * 
 * 
 * try { game = AudioSystem.getAudioInputStream(url4); menu =
 * AudioSystem.getAudioInputStream(url3); boom =
 * AudioSystem.getAudioInputStream(url2); gun =
 * AudioSystem.getAudioInputStream(url1); } catch (UnsupportedAudioFileException
 * e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
 * 
 * }
 * 
 * public void playGunSound() throws LineUnavailableException, IOException {
 * gunClip.open(gun); gunClip.start(); }
 * 
 * public void stopGunSound() { gunClip.stop(); }
 * 
 * public void playExplosion() throws LineUnavailableException, IOException {
 * boomClip.open(boom); boomClip.start(); }
 * 
 * public void stopExplosion() { boomClip.stop(); }
 * 
 * public void playMenu() throws LineUnavailableException, IOException { try {
 * menuClip.open(menu); } catch (LineUnavailableException e) {
 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
 * menuClip.start(); menuClip.loop(Clip.LOOP_CONTINUOUSLY); }
 * 
 * public void pauseMenu() {
 * 
 * }
 * 
 * public void stopMenu() { menuClip.stop(); }
 * 
 * public boolean playing = false; public void playGame() { //game.play(); try {
 * gameClip.open(game); } catch (LineUnavailableException e) {
 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
 * gameClip.start(); gameClip.loop(Clip.LOOP_CONTINUOUSLY); playing = true; }
 * 
 * public void stopGame() { //game.stop(); if (playing = true) {
 * gameClip.stop(); playing = false; }
 * 
 * }
 * 
 * public void pauseGame() {
 * 
 * } }
 */