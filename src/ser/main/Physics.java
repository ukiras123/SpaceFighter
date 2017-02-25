package ser.main;

import ser.main.interfaces.EntityA;
import ser.main.interfaces.EntityB;

//Collision Detection
public class Physics {
	
	//all the Physics accessor :> player and enemy and asteroid will have to initialize it
	//so we do not have to create new Sound object. Alternative was public static Sound sound = new Sound();
	public static Sound sound; 
								

	//For player colliding with enemy
	public static boolean Collision(EntityA enta, EntityB entb) {
		
		if (enta.getBounds().intersects(entb.getBounds())) {
			//sound.playExplosion();	
			return true;
		}
		return false;
	}

	//For enemy colliding with player
	public static boolean Collision(EntityB entb, EntityA enta) {

		if (entb.getBounds().intersects(enta.getBounds())) {
			sound.playExplosion();

			return true;
		}
		return false;
	}
}
