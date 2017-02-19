package ser.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MoustInput implements MouseListener {


	public void mousePressed(MouseEvent e) {

		int mx = e.getX();
		int my = e.getY();

		if(Game.State == Game.STATE.MENU)
		{
		// Play Button
		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
			if (my >= 150 && my <= 200) {
				// Play button
				Game.State = Game.STATE.GAME;
			}
		}
		
		// Menu Button
		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
			if (my >= 250 && my <= 300) {
				// Play button
				Game.State = Game.STATE.HELP;
			}
		}

		// Quit Button
		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
			if (my >= 350 && my <= 400) {
				// Play button
				System.exit(1);
			}
		}
		}
		if (Game.State == Game.STATE.HELP)
		{
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
				if (my >= 380 && my <= 430) {
					// Back Button
					Game.State = Game.STATE.MENU;
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
