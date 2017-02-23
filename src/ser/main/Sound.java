package ser.main;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Sound {
	URL url1 = Sound.class.getResource("/laserSound.wav");
	AudioClip gun = Applet.newAudioClip(url1);
	
	public void playGunSound()
	{
		gun.play();
	}
	public void stopGunSound()
	{
		gun.stop();
	}

}
