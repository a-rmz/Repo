package gameManager;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import gameManager.menus.*;
import mainGame.Game;

public class GameStateManager {

	public ArrayList<GameState>  gameStates;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int CUSTOMIZESTATE = 1;
	public static final int PAUSESTATE = 2;
	public static final int ENDGAME = 3;
	public static final int LEVEL1STATE = 4;
	
	public Game game;
	
	public GameStateManager(Game game) {
		this.game = game;
		gameStates = new ArrayList<GameState>();
		currentState = MENUSTATE;
		gameStates.add(new MainMenu(this));	
		gameStates.add(new CustomizeMenu(this));
		gameStates.add(new PauseMenu(this));
		gameStates.add(new EndGameMenu(this));
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
		setState(CUSTOMIZESTATE);
	}
	public void endGame() {
		setState(ENDGAME);
	}
}

