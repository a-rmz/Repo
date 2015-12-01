package gameManager.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import background.Background;
import mainGame.Game;

public class HighscoreMenu extends Menu {

	Background hBG;
	
	private static HighscoreMenu instance = new HighscoreMenu();
	
	private HighscoreMenu() {
		hBG = new Background(Background.SCORE_MENU);
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

		Game.hs.draw(g, Game.WIDTH / 3 - 100, Game.HEIGHT / 3);
		
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		
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
