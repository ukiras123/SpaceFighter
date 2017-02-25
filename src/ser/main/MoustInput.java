package ser.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.sampled.LineUnavailableException;

//Mouse listener
public class MoustInput implements MouseListener {

	public Sound sound;

	public MoustInput(Sound sound) {
		this.sound = sound;
	}

	public void mousePressed(MouseEvent e) {

		int mx = e.getX();
		int my = e.getY();

		// Menu Page
		if (Game.State == Game.STATE.MENU) {
			// Play Button
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
				if (my >= 150 && my <= 200) {
					Game.State = Game.STATE.GAME;
				}
			}
			// Menu Button
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
				if (my >= 250 && my <= 300) {
					Game.State = Game.STATE.HELP;
				}
			}
			// Quit Button
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
				if (my >= 350 && my <= 400) {
					System.exit(1);
				}
			}

			// Mute Button
			if (mx >= Game.WIDTH / 4 && mx <= Game.WIDTH / 4 + 50) {
				if (my >= 350 && my <= 400) {
					if (Sound.gameMysicPlaying == true) {
						sound.stopGameMusic();
					} else {
						try {
							sound.playGameMusic();
						} catch (LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		}

		// Help Page
		if (Game.State == Game.STATE.HELP) {
			// Back Button
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
				if (my >= 380 && my <= 430) {
					Game.State = Game.STATE.MENU;
				}
			}
		}

		// Game Over Page
		if (Game.State == Game.STATE.GAMEOVER) {
			// Continue Button
			if ((mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) && !(Game.getTotalContinue() <= 0)) {
				if (my >= 150 && my <= 200) {
					Game.State = Game.STATE.GAME;
					Game.setTotalContinue(Game.getTotalContinue() - 1);
				}
			} // Main Menu Button
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
				if (my >= 250 && my <= 300) {
					Game.State = Game.STATE.RESTART;
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
