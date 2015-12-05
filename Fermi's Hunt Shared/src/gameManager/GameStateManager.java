package gameManager;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import gameManager.menus.*;
import mainGame.Game;

public class GameStateManager {

	// **** GAMESTATEMANAGER MEMBERS ****
	public ArrayList<GameState>  gameStates;
	private int currentState;
	public Game game;
	
	// **** CONSTS ****
	public static final int MENUSTATE = 0;
	public static final int CUSTOMIZESTATE = 1;
	public static final int PAUSESTATE = 2;
	public static final int ENDGAME = 3;
	public static final int LEVEL1STATE = 4;
	
	// --------------------------------------------------
	
	// **** CONSTRUCTOR ****
	
	/**
	 *  Creates a new instance of GameStateManager.
	 * @param game
	 */
	public GameStateManager(Game game) {
		this.game = game;
		// Creates a new ArrayList of GameStates.
		gameStates = new ArrayList<GameState>();
		// Sets the default state.
		currentState = MENUSTATE;
		gameStates.add(new MainMenu(this));	
		gameStates.add(new CustomizeMenu(this));
		gameStates.add(new PauseMenu(this));
		gameStates.add(new EndGameMenu(this));
	}
	
	/**
	 *  Sets the selected state.
	 * @param state
	 */
	public void setState(int state) {
		currentState = state;
		// Calls the initializing method of the selected GameState.
		gameStates.get(currentState).init();
	}
	
	/**
	 * Returns the actual state of the GameStateManager.
	 * @return current state
	 */
	public int getState() {
		return currentState;
	}
	
	/**
	 * Updates the current state.
	 */
	public void update() {
		gameStates.get(currentState).update();
	}
	
	/**
	 * Draws the current state.
	 * @param g
	 */
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	/**
	 * Listens the keyPressed on the current state.
	 * @param k
	 */
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	/**
	 * Listens the keyReleased on the current state.
	 * @param k
	 */
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
	
	/**
	 * Listens the mouseMoved on the current state.
	 * @param e
	 */
	public void mouseMoved(MouseEvent e) {
		gameStates.get(currentState).mouseMoved(e);
	}

	/**
	 * Listens the mousePressed on the current state.
	 * @param e
	 */
	public void mousePressed(MouseEvent e){
		gameStates.get(currentState).mousePressed(e);
		
	}

	/**
	 * Listens the mouseReleased on the current state.
	 * @param e
	 */
	public void mouseReleased(MouseEvent e){
		gameStates.get(currentState).mouseReleased(e);
	}
	
	/**
	 * Listens the mouseClicked on the current state.
	 * @param e
	 */
	public void mouseClicked(MouseEvent e) {
		gameStates.get(currentState).mouseClicked(e);
	}
	
	/**
	 * Creates a new game.
	 */
	public void newGame() {
		setState(CUSTOMIZESTATE);
	}
	/**
	 * Sets the end of the game.
	 */
	public void endGame() {
		setState(ENDGAME);
	}
}

