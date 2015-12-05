package gameManager.levels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Effects.SoundEffects;
import background.Background;
import characters.Ship;
import cinematics.NewGameCM;

import gameManager.GameState;
import gameManager.GameStateManager;
import managers.EnemyManager;


@SuppressWarnings("serial")
public class Level1 extends GameState {
	
	
	// **** LEVEL1 ATTRIBUTES ****
	GameStateManager gsm;
	Ship p1;
	public EnemyManager e;
	public NewGameCM cinematic;
	SoundEffects se ;
	
	// Graphics
	Background bg;
	
	// ----------------------------------------------
	
	// **** CONSTRUCTOR ****
	/**
	 * Creates a new instance of Level1.
	 * @param gsm
	 */
	public Level1(GameStateManager gsm){
		this.gsm = gsm;
		// Loads the player from the Ship's singleton.
		p1 = Ship.getPlayer();
		// Creates a new Background.
		bg = new Background(Background.LEVEL_1);
		// Creates a new EnemyManager.
		e = new EnemyManager(2, 4, 20); //TODO
		// Creates a new Cinematic.
		cinematic = new NewGameCM();
		// Creates a new SoundEffects.
		se = new SoundEffects();
		// Loads the Sound effect 7.
		se.FXSound(7);
	}
	
	/**
	 * Starts playing the cinematic.
	 */
	public void  initCinematic(){
		cinematic.startAnimation();
	}
	
	/**
	 * Prints the elements of the Level.
	 */
	@Override
	public void draw(Graphics2D g2d){
		
		Graphics g = g2d;
		
		// Prints the Background
		bg.draw(g2d);
		
		// Prints enemies
		if(cinematic.enemyAppears)  e.draw(g);
		
		// Prints user Ship
		p1.draw(g);

		// Prints cinematic
		if(cinematic.isRunning){ 
			cinematic.draw(g);
		}
		
	}

	/**
	 * Updates the elements of the Level.
	 */
	public void update(){
		bg.update();
		p1.update();
		
		// AS long as the cinematic is playing, plays the sound effect.
		if(!cinematic.endCinematic()){
			se.play();
		}
		
		// When the enemies appear, starts updating them.
		if(cinematic.enemyAppears) e.update();
		
		// Evaluates if the player is dead.
		if(!Ship.getPlayer().isAlive()) {
			// Stops playing the sound.
			se.stop();
			// Calls the endGame method.
			gsm.endGame();
		}
		
	}

		
	@Override
	public void keyPressed(int k) {
		// When pressed the ESC key, stops the sound.
		if(k == KeyEvent.VK_ESCAPE) se.stop();
		// Listens to the Ship keyPressed.
		p1.keyPressed(k);		
	}

	@Override
	public void keyReleased(int k) {
		// Listens to the Ship keyReleased.
		p1.keyReleased(k);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Listens to the Ship mousePressed.
		p1.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Listens to the Ship mouseReleased.
		p1.mouseReleased(e);
	}
	
	
	// **** UNUSED METHODS ****
	@Override
	public void init() {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {}

}
