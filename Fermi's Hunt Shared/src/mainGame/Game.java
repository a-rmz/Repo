package mainGame;


import java.awt.Dimension;
import java.awt.Graphics; // mostrar imagen
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel; // mostrar imagen

import gameManager.GameStateManager;

@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable, KeyListener{

	// Dimensions
	public static final int WIDTH = (int) screenSize().getWidth();
	public static final int HEIGHT = (int) screenSize().getHeight();
	
	
	// Thread
	private static Thread thread;
	private boolean running = false;
	private static boolean paused = false;
	private int FPS = 60;
	private long targetTime = 1_000 / FPS;
	
	// Image
	private BufferedImage image;
	private Graphics2D g;
	
	// Game State Manager
	private static GameStateManager gsm;
	
	public Game() {
		super();
		setFocusable(true);
		requestFocus();
		
	}
	
	
	private static Dimension screenSize() {
		int xMax = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		int yMax = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		Dimension d = new Dimension(xMax, yMax);
		return d;
	}
	
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this, "GameThread");
			addKeyListener(this);
			thread.start();
		}
	}
	
	private void init() {
		
		image = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
		
		gsm = new GameStateManager();
	}
	
	/**
	 *  Game loop
	 */
	public void run() {
		
		init();
		
		long start;
		long elapsed;
		long wait;
		
		// GameLoop
		
		while(running) {
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1_000_000;
			if(wait < 0) wait = 5;
			
			try {
				Thread.sleep(wait);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void update() {
		gsm.update();
		
		try {
		    if (paused) {
		        synchronized (this) {
		            while (paused) {
		                wait();
		            }
		        }
		    }
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}
	
	private void draw() {
		gsm.draw(g);
	}
	private void drawToScreen() { 
		Graphics g2 = getGraphics();
		g2.drawImage(image,  0, 0, Game.WIDTH, Game.HEIGHT, null); //TODO
		g2.dispose();
	}
	
	public static void pauseMenu() {
		gsm.setState(GameStateManager.PAUSESTATE);
	}
	
	public static synchronized void resumeGame() {
		paused = false;
		synchronized (thread) {
			thread.notify();
		}
		gsm.setState(GameStateManager.LEVEL1STATE);
	}
	
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}

	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}
	
	public void keyTyped(KeyEvent key) { }
		
		
}
