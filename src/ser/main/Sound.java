
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
	private AudioClip gun, boom, hit;

	private Clip gameClip;
	private AudioInputStream game; // for Game Music we have to do this way so that we
									// can use loop for music.

	public static boolean gameMysicPlaying;

	public Sound() {
		gameMysicPlaying = false;
		url1 = Sound.class.getResource("/laserSound.wav");
		url2 = Sound.class.getResource("/boom.wav");
		url3 = Sound.class.getResource("/adventure.wav");
		url4 = Sound.class.getResource("/hit.wav");

		gun = Applet.newAudioClip(url1);
		boom = Applet.newAudioClip(url2);
		hit = Applet.newAudioClip(url4);

		try {
			game = AudioSystem.getAudioInputStream(url3);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			gameClip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			gameClip.open(game);
		} catch (LineUnavailableException e) {
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

	public void playHitSound() {
		hit.play();
	}

	public void stopHitSound() {
		hit.stop();
	}
	public void playExplosion() {
		boom.stop();
		boom.play();
	}

	public void stopExplosion() {
		boom.stop();
	}

	public void playGameMusic() throws LineUnavailableException {
		if (gameMysicPlaying == false) { // Validation check
			//gameClip.start();
			gameClip.loop(Clip.LOOP_CONTINUOUSLY);
			gameMysicPlaying = true;
		}
	}

	public void stopGameMusic() {
		gameClip.stop();
		gameMysicPlaying = false;
	}

}
