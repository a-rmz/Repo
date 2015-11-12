package gameManager;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gameManager.levels.Level1;
import gameManager.menus.MainMenu;
import gameManager.menus.PauseMenu;
import mainGame.Game;

public class GameStateManager {

	private ArrayList<GameState>  gameStates;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int PAUSESTATE = 1;
	public static final int LEVEL1STATE = 2;
	
	private Game game;
	
	public GameStateManager(Game game) {
		this.game = game;
		gameStates = new ArrayList<GameState>();
		
		currentState = MENUSTATE;
		gameStates.add(new MainMenu(this));	
		gameStates.add(new PauseMenu(this));
		
	}
	
	
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
		
	}
	
	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
	
	
	public void mouseMoved(MouseEvent e) {
		gameStates.get(currentState).mouseMoved(e);
	}

	
	public void mousePressed(MouseEvent e){
		gameStates.get(currentState).mousePressed(e);
		
	}

	public void mouseReleased(MouseEvent e){
		gameStates.get(currentState).mouseReleased(e);
	}
	
	public void mouseClicked(MouseEvent e) {
		gameStates.get(currentState).mouseClicked(e);
	}
	
	public void newGame() {
//		 Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
		game.setCursor(blankCursor);
		
		gameStates.add(new Level1(this));
		this.setState(LEVEL1STATE);
	}
}

