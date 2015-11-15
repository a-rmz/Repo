package gameManager.menus;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import gameManager.GameState;
import mainGame.Game;

@SuppressWarnings("serial")
public abstract class Menu extends GameState {
	
	protected static boolean helpMenu = false;

	Rectangle createOptionBorder(String option, int i, Graphics2D g, FontMetrics fm) {

		Rectangle2D bounds = fm.getStringBounds(option, g);
		return new Rectangle(
				(int) Game.WIDTH/2 - fm.stringWidth(option)/2 - 10, 
				(int) Game.HEIGHT/2 + (i * 60) - 30, 
				(int) bounds.getWidth() + 15, 
				(int) bounds.getHeight() + 10
			);
	}	
		
	protected abstract void select();

}
