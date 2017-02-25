package ser.main;

import ser.main.interfaces.EntityA;
import ser.main.interfaces.EntityB;

//Collision Detection
public class Physics {

	// For player group colliding with enemy group
	public static boolean Collision(EntityA enta, EntityB entb) {
		if (enta.getBounds().intersects(entb.getBounds())) {
			return true;
		}
		return false;
	}

	// For enemy group colliding with player group
	public static boolean Collision(EntityB entb, EntityA enta) {
		if (entb.getBounds().intersects(enta.getBounds())) {
			return true;
		}
		return false;
	}
}
