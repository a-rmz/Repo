package mainGame;


import java.awt.Graphics; // mostrar imagen
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel; // mostrar imagen
import javax.swing.Timer;

import background.Background;
import characters.Ship;
import characters.SpaceObject;
import managers.EnemyManager;
import navigation.KeyInput;
import soundtracks.Soundtrack;

@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener{
	
	public static Timer timer;
	Ship p1;
	EnemyManager e;
	Background bg;
	Soundtrack st;
		
	public Game(){
		setFocusable(true);
		timer = new Timer(50, this);
		timer.start();
		p1 = new Ship();
		e = new EnemyManager(1, 10); //TODO
		bg = new Background(1); // TODO Modify level
		st = new Soundtrack();
		st.playSoundtrack();
		
		addKeyListener (new KeyInput(p1));
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		int sizeX = (int) SpaceObject.screenSize().getWidth();
		int sizeY = (int) SpaceObject.screenSize().getHeight();
		Graphics2D g2d = (Graphics2D) g;
		// Prints background
		g2d.drawImage(bg.getBackgroundImage(), bg.pbg1.getX(), 0, sizeX, sizeY, null);
		g2d.drawImage(bg.getBackgroundImage(), bg.pbg2.getX(), 0, sizeX, sizeY, null);
		
		// Prints user Ship
		g2d.drawImage(p1.getSpaceObjectImage(), p1.p.getX() , p1.p.getY(), 
				p1.getSpaceObjectImage().getWidth(null)*2,
				p1.getSpaceObjectImage().getHeight(null)*2, null);
		
		// Prints hits on user
		
		p1.hitsOnSelf(g);
		
		// Prints enemies
		e.draw(g);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		update();
				
	}
	
	public void update(){
		bg.update();
		p1.update();
		e.update();
	}
	
	public void pauseBackground(){
		timer.stop();
	}
	
	public void quitPauseBackground(){
		timer.restart();
	}
		
		
}
