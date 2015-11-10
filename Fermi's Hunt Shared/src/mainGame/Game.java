package mainGame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import gameManager.GameStateManager;

@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable, KeyListener{

	// Dimensions
	public static final int WIDTH = (int) screenSize().getWidth();
	public static final int HEIGHT = (int) screenSize().getHeight();
	
	
	private static Thread LoopThread;
	private static boolean paused = false;
	private boolean isRunning = false;
	private int FPS = 60;
	private long  LoopTime = 1000 / FPS;
	
	// Image
	private BufferedImage image;
	private Graphics2D g;
	
	// Game State Manager
	private static GameStateManager gsm;
	
	public Game() {
		super();
		setFocusable(true);
		requestFocus();
		init();
		LoopThread = new Thread(this);
		isRunning= true;
		LoopThread.start();
		addKeyListener(this);
  }
	
	
	private static Dimension screenSize() {
		int xMax = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		int yMax = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		Dimension d = new Dimension(xMax, yMax);
		return d;
	}
	
	private void init() {
		
		image = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		isRunning = true;		
		gsm = new GameStateManager();
	}
	
	/**
	 *  Game loop
	 */
	public void run() {
		// TODO Auto-generated method stub
		
		long start, elapsed, wait;
  	// Initializes what is needed for the Game.
  	
		
		start= System.nanoTime();
		elapsed = System.nanoTime() - start;
		wait = LoopTime - elapsed / 1000000;
		
		if(wait < 0) wait =5;
		
		while(isRunning){
    
			
			update();
			repaint();
			
      
			
			if(wait < 0) wait = 5;
			
			try{
				Thread.sleep(wait);
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}
	}
	
	private void update() {
		gsm.update();
		
    // Checks if the Game is paused.
		try {
		    if (paused) {
		        synchronized (this) {
              // While the Game is paused, the thread is waiting.
		            while (paused) wait();
		            }
		        }
		    } catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}

	
	private void paint(Graphics2D g) {
    // Calls the GameStateManager draw method.
		Graphics g2 = getGraphics();
		g2.drawImage(image,  0, 0, Game.WIDTH, Game.HEIGHT, null);
		g2.dispose();
		gsm.draw(g);
	}
	
  // Sets the Game to pause.
	public static void pauseMenu() {
		gsm.setState(GameStateManager.PAUSESTATE);
	}
	
  // Resumes the game.
	public static synchronized void resumeGame() {
		// Sets the paused state to false.
    paused = false;
		synchronized (LoopThread) {
      // Notifies the LoopThread.
			LoopThread.notify();
		}
    // Activates the level where it was.
    // TODO
		gsm.setState(GameStateManager.LEVEL1STATE);
	}
	
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}

	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}
	
	public void keyTyped(KeyEvent key) { 
	}
	}