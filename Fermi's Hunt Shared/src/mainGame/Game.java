package mainGame;


import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import gameManager.GameStateManager;

@SuppressWarnings("serial")
public class Game extends JPanel implements 
	Runnable, KeyListener, MouseMotionListener, MouseListener{

	private class Highscore {
		
		// Class player for the highscore map
		private class Player implements java.io.Serializable{
			private String name;
			private Integer score;
			
			public Player(String name, Integer score) {
				this.name = name;
				this.score = score;
			}
			public String getName() {
				return this.name;
			}
			public Integer getScore() {
				return this.score;
			}
		}
		
		private LinkedHashMap<String, Integer> scores = new LinkedHashMap<String, Integer>(5);
		private String dir;
		private String fName = "hscr.hsc";
		private Path mkd;
		private Path mkf;
		private File hsFile;
		
		public Highscore() {			
			char os;
			os = (System.getProperty("os.name").contains("Win")) ? 'w' : 'o';
			if(os == 'w') {
				dir = System.getenv("AppData");
				dir += "/Fermi/";
			}
			if(os == 'o') {
				dir = System.getProperty("user.home");
				dir += "/Library/Application Support/Fermi/";
			}
			mkd = Paths.get(dir);
			mkf = Paths.get(dir + fName);
			hsFile = mkf.toFile();
			if(Files.notExists(mkd)) {
				mkdir();
			} else
			if(Files.notExists(mkf)) {
				mkfil();
				initHS();
			} else lfil();
		}
		
		private void mkdir() {
			try {
				Files.createDirectories(mkd);
				System.out.println("Directorio creado.");
			} catch (Exception e) {
				System.out.println("Error al crear el directorio.");
				e.printStackTrace();
			}
		}
		private void mkfil() {
			try {
				System.out.println("Archivo creado.");
				Files.createFile(mkf);
			} catch (Exception e) {
				System.out.println("Error al crear el archivo.");
				e.printStackTrace();
			}
		}
		private void initHS() {
			scores.put("Javier D�valos", 9999);
			scores.put("John Cena", 9999);
			scores.put("Chuck Norris", 9999);
			scores.put(":v", 9999);
			scores.put("Yo", 9999);
			save();
		}
		
		private void lfil() {
			FileInputStream fis;
			ObjectInputStream ois;
			
			try {
				fis = new FileInputStream(hsFile);
				ois = new ObjectInputStream(fis);
				
				scores = (LinkedHashMap<String, Integer>) ois.readObject();
				
				System.out.println(scores.toString());
				ois.close();
			} catch (Exception e) {
				System.out.println("Hashtable not loaded");
				e.printStackTrace();
			}
		}
		
		private void save() {
			FileOutputStream fos;
			ObjectOutputStream oos;
			
			try {
				fos = new FileOutputStream(hsFile);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(scores);
				oos.close();
				fos.close();
			} catch (Exception e) {
				System.out.println("Hashtable not saved");
				e.printStackTrace();
			}
			
		}
	
		public void addScore(String name, int score) {
			
			
		}

		private void sort() {
			List<Map.Entry<String, Integer>> unsorted =
					  new ArrayList<Map.Entry<String, Integer>>(scores.entrySet());
			
			Collections.sort(unsorted, new Comparator<Map.Entry<String, Integer>>() {
				public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
					return -a.getValue().compareTo(b.getValue());
				}
			});
			
			scores.clear();
			for(Map.Entry<String, Integer> entry : unsorted) {
				scores.put(entry.getKey(), entry.getValue());
			}
		}
			
		
		public String toString() {
			return scores.toString();
		}
	}
	
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
		Highscore hs = new Highscore();
		System.out.println(hs.toString());
		// Font load
		try {
			String url = "resources/Fonts/8-BIT MADNESS.ttf";
			File ff = new File(url);
			Font f = Font.createFont(Font.TRUETYPE_FONT, ff);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(f);
			
			url = "resources/Fonts/8-BIT WONDER.ttf";
			ff = new File(url);
			f = Font.createFont(Font.TRUETYPE_FONT, ff);
			ge.registerFont(f);
		} catch (IOException|FontFormatException e) {
			e.printStackTrace();
		     System.out.println("Fonts not loaded.");
		}
		
		// Initialize game
		isRunning = true;		
		gsm = new GameStateManager(this);
	}
	
	/**
	 *  Game loop
	 */
	@Override
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

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
    // Calls the GameStateManager draw method.
		
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
	
	@Override
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}
	
	@Override
	public void keyTyped(KeyEvent key) { 
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		gsm.mouseMoved(e);
	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		gsm.mouseClicked(e);
	}


	@Override
	public void mouseExited(MouseEvent e) {
	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		gsm.mousePressed(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		gsm.mouseReleased(e);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}


	}