package ser.main;

import java.io.IOException;
import java.util.LinkedList;

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
			sound.playExplosion();

			return true;
		}
		return false;
	}
}
