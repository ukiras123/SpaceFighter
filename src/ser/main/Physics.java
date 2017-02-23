package ser.main;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import ser.main.classes.EntityA;
import ser.main.classes.EntityB;

public class Physics {
	private static Sound sound = new Sound();;

	public static boolean Collision(EntityA enta, EntityB entb) {

		if (enta.getBounds().intersects(entb.getBounds())) {
			// sound.playExplosion();
			return true;
		}
		return false;
	}

	public static boolean Collision(EntityB entb, EntityA enta) {

		if (entb.getBounds().intersects(enta.getBounds())) {
			try {
				sound.playExplosion();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return true;
		}
		return false;
	}
}
