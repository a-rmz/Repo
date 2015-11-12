package gameManager;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import gameManager.levels.Level1;
import gameManager.menus.MainMenu;
import gameManager.menus.PauseMenu;

public class GameStateManager {

	private ArrayList<GameState>  gameStates;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int PAUSESTATE = 1;
	public static final int LEVEL1STATE = 2;
	
	
	public GameStateManager() {
		
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
	
	public void newGame() {
		
		
		gameStates.add(new Level1(this));
		this.setState(LEVEL1STATE);
	}
}

