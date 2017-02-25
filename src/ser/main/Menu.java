package ser.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

//Designing the menu here
public class Menu {

	//Menu will have play, help,  quit and mute button
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 150, 100, 50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120, 250, 100, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 350, 100, 50);
	public Rectangle mute = new Rectangle(Game.WIDTH / 4, 370, 30, 30);

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("arial", Font.BOLD, 59);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("SPACE FIGHTER", Game.WIDTH / 4, 100);

		fnt0 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt0);
		g.drawString("Play", playButton.x + 19, playButton.y + 30);
		g2d.draw(playButton);
		g.drawString("Help", helpButton.x + 19, helpButton.y + 30);
		g2d.draw(helpButton);
		g.drawString("Quit", quitButton.x + 19, quitButton.y + 30);
		g2d.draw(quitButton);
		
		//Mute
		fnt0 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt0);
		g.drawString("♫", mute.x+5, mute.y+20);
		g2d.draw(mute);
		
	}

}
