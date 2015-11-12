package gameManager.levels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import background.Background;
import characters.Ship;
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
	
	// Graphics
	Background bg;
	Soundtrack st;
		
	public Level1(GameStateManager gsm){
		this.gsm = gsm;
		p1 = new Ship();
		e = new EnemyManager(1, 2); //TODO
		bg = new Background(Background.LEVEL_1);
		
	}
	
	public void draw(Graphics2D g2d){
		
		Graphics g = (Graphics2D) g2d;
		
		// Prints the Background
		bg.draw(g2d);
		
		// Prints user Ship
		p1.draw(g);

		// Prints enemies
		e.draw(g);
		
	}


	public void update(){
		bg.update();
		p1.update();
		e.update();
	}

	
	@Override
	public void init() { }

	
	@Override
	public void keyPressed(int k) {
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

}
