package mainGame;


import java.awt.*; // mostrar imagen


import javax.swing.JPanel; // mostrar imagen

import background.Background;
import characters.Ship;
import characters.SpaceObject;
import managers.EnemyManager;
import navigation.KeyInput;
import soundtracks.Soundtrack;
 

@SuppressWarnings("serial")
public class Game extends JPanel implements  Runnable{
	
	Ship p1;
	public static EnemyManager e;
	Background bg;
	Soundtrack st;
	
	private Thread LoopThread;
	
	private boolean isRunning;
	private int FPS = 60;
	private long  LoopTime = 1000 / FPS;
	
		
	public Game(){
		setFocusable(true);
		p1 = new Ship();
		e = new EnemyManager(1, 10); //TODO
		bg = new Background(1); // TODO Modify level
		st = new Soundtrack();
		
		
		LoopThread = new Thread(this);
		
		isRunning= true;
		LoopThread.start();
	
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
	public void run() {
		// TODO Auto-generated method stub
		
		long start, elapsed, wait;
		
		start= System.nanoTime();
		elapsed = System.nanoTime()-start;
		
		wait = LoopTime - elapsed / 1000000;
		
		if(wait < 0) wait =5;
		
		while(isRunning){
			
			repaint();
			update();
			
			
			if(wait < 0) wait =5;
			
			try{
				Thread.sleep(wait);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
	}
		
		
}
