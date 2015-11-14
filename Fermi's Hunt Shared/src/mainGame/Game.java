package mainGame;


import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import gameManager.GameStateManager;

@SuppressWarnings("serial")
public class Game extends JPanel implements 
	Runnable, KeyListener, MouseMotionListener, MouseListener{

	// Dimensions
	public static final int WIDTH = (int) screenSize().getWidth();
	public static final int HEIGHT = (int) screenSize().getHeight();
	
	
	private static Thread LoopThread;
	private static boolean paused = false;
	private boolean isRunning = false;
	private int FPS = 60;
	private long  LoopTime = 1000 / FPS;
	
	
	// Game State Manager
	private static GameStateManager gsm;
	
	public Game() {
		super();
		init();
		setFocusable(true);
		requestFocus();
		LoopThread = new Thread(this);
		isRunning= true;
		LoopThread.start();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
  }
	
	
	private static Dimension screenSize() {
		int xMax = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		int yMax = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		Dimension d = new Dimension(xMax, yMax);
		return d;
	}
	
	private void init() {
		
		isRunning = true;		
		gsm = new GameStateManager(this);
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
		
		if(wait < 0) wait = 5;
		
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

	
	public void paint(Graphics g) {
		super.paint(g);
    // Calls the GameStateManager draw method.
		Graphics2D g2d = (Graphics2D) g;
		gsm.draw(g2d);
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
		gsm.setState(GameStateManager.LEVEL1STATE);
	}
	
	public void hideCursor() {
		//Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
		setCursor(blankCursor);
	}
	public void showCursor() {
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}

	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}
	
	public void keyTyped(KeyEvent key) { 
	}


	public void mouseMoved(MouseEvent e) {
		gsm.mouseMoved(e);
	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseClicked(MouseEvent e) {
		gsm.mouseClicked(e);
	}


	@Override
	public void mouseExited(MouseEvent e) {
	
	}
	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		gsm.mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		gsm.mouseReleased(e);
	}
	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}


	}