package gameManager.levels;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Timer;

import background.Background;
import characters.Ship;
import gameManager.GameState;
import managers.EnemyManager;
import soundtracks.Soundtrack;

public class Level1 extends GameState {
	
	public static Timer timer;
	Ship p1;
	public static EnemyManager e;
	Background bg;
	Soundtrack st;
		
	public Level1(){
		init();		
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
	public void init() {
		p1 = new Ship();
		e = new EnemyManager(1, 10); //TODO
		bg = new Background(1); // TODO Modify level
		st = new Soundtrack();
		st.playSoundtrack();		
	}

	
	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
