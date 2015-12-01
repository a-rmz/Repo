package gameManager.menus;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import background.Background;
import characters.Sprite;
import mainGame.Game;

@SuppressWarnings("serial")
public class HighscoreMenu extends Menu {

	Background hBG;
	private Image over;
	
	private static HighscoreMenu instance = new HighscoreMenu();
	
	private HighscoreMenu() {
		hBG = new Background(Background.SCORE_MENU, 3);
		over = Sprite.loadSprite("/BackgroundImg/Highscore_Menu/high_scores_bg.png", this);
	}
	
	public static HighscoreMenu menu() {
		return instance;
	}
	
	@Override
	protected void select() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		hBG.update();	
	}

	@Override
	public void draw(Graphics2D g) {
		hBG.draw(g);
		g.drawImage(over, 0, 0, null);
		Game.hs.draw(g, Game.WIDTH / 3 - 100, Game.HEIGHT / 3);
		
	}

	@Override
	public void keyPressed(int k) {	
		if(k == KeyEvent.VK_ESCAPE) {
			highscoreMenu = false;
		}
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
