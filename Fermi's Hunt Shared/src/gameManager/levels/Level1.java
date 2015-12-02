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
import soundtracks.Soundtrack;

@SuppressWarnings("serial")
public class Level1 extends GameState {
	
	
	// Atributes
	GameStateManager gsm;
	Ship p1;
	public static EnemyManager e;
	public NewGameCM cinematic;
	SoundEffects se ;
	
	// Graphics
	Background bg;
	Soundtrack st;
		
	public Level1(GameStateManager gsm){
		this.gsm = gsm;
		p1 = Ship.getPlayer();
		e = new EnemyManager(2, 3, 20); //TODO
		bg = new Background(Background.LEVEL_1);
		cinematic = new NewGameCM();
		se = new SoundEffects();
		se.FXSound(5);
		
	}
	
	public void  initCinematic(){
		cinematic.startAnimation();
	}
	
	@Override
	public void draw(Graphics2D g2d){
		
		Graphics g = g2d;
		
		// Prints the Background
		bg.draw(g2d);
		
		// Prints enemies
		if(cinematic.enemyAppears)  e.draw(g);
		
		// Prints user Ship
		p1.draw(g);
		
		
//*******************************************************************//
		
		// Prints enemies
		
		if(cinematic.isRunning){ 
			cinematic.draw(g);
		}
		
	}


	@Override
	public void update(){
		bg.update();
		p1.update();
		
		if(cinematic.endCinematic())	
			se.play();
		
		if(cinematic.enemyAppears) e.update();

		if(!Ship.getPlayer().isAlive()) {
			
			gsm.endGame();
		}
		
	}

	
	@Override
	public void init() { }

	
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ESCAPE) se.stop();
		p1.keyPressed(k);		
	}

	@Override
	public void keyReleased(int k) {
		p1.keyReleased(k);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		p1.mouseMoved(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		p1.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		p1.mouseReleased(e);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
