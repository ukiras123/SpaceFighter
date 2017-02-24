package ser.main;

import ser.main.interfaces.EntityA;
import ser.main.interfaces.EntityB;

public class Physics {
	private static Sound sound = new Sound();;

	public static boolean Collision(EntityA enta, EntityB entb) {

		if (enta.getBounds().intersects(entb.getBounds())) {
			//sound.playExplosion();
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
