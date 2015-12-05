package mainGame;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import characters.Sprite;
import gameManager.GameStateManager;

@SuppressWarnings("serial")
public class Game extends JPanel implements 
	Runnable, KeyListener, MouseMotionListener, MouseListener{

	/**
	 * Inner class to controll the high scores.
	 * @author alex
	 *
	 */
	public class Highscore {
		
		private LinkedHashMap<String, Integer> scores = new LinkedHashMap<String, Integer>(5);
		private String dir;
		private String fName = "hscr.hsc";
		private Path mkd;
		private Path mkf;
		private File hsFile;
		
		/**
		 * Constructor of the Highscore
		 */
		public Highscore() {			
			char os;
			// Verifies the operating System.
			os = (System.getProperty("os.name").contains("Win")) ? 'w' : 'o';
			// OS is Windows.
			if(os == 'w') {
				dir = System.getenv("AppData");
				dir += "/Fermi/";
			}
			// OS is Other (MacOS, any Linux distro)
			if(os == 'o') {
				dir = System.getProperty("user.home");
				dir += "/Library/Application Support/Fermi/";
			}
			// Path for the directory.
			mkd = Paths.get(dir);
			// Path for the file.
			mkf = Paths.get(dir + fName);
			// Creates a new file on the file path.
			hsFile = mkf.toFile();
			if(Files.notExists(mkd)) {
				// Creates the directory.
				mkdir();
			}
			if(Files.notExists(mkf)) {
				// Creates the file.
				mkfil();
				// Initializes the High scores.
				initHS();
			} else lfil(); // Loads the file.
		}
		
		/**
		 * Creates the directory for the high score save.
		 */
		private void mkdir() {
			try {
				Files.createDirectories(mkd);
				System.out.println("Directorio creado.");
			} catch (Exception e) {
				System.out.println("Error al crear el directorio.");
				e.printStackTrace();
			}
		}
		/**
		 * Creates the file for the high score save.
		 */
		private void mkfil() {
			try {
				System.out.println("Archivo creado.");
				Files.createFile(mkf);
			} catch (Exception e) {
				System.out.println("Error al crear el archivo.");
				e.printStackTrace();
			}
		}
		/**
		 * Creates the default values for the high scores and puts them into the file.
		 */
		private void initHS() {
			scores.put("Javier Davalos", 1000);
			scores.put("John Cena", 1000);
			scores.put("Chuck Norris", 1000);
			scores.put(":v", 1000);
			scores.put("MC Dinero", 1000);
			save();
		}
		
		/**
		 * Loads the table from the file.
		 */
		@SuppressWarnings("unchecked")
		private void lfil() {
			FileInputStream fis;
			ObjectInputStream ois;
			
			try {
				fis = new FileInputStream(hsFile);
				ois = new ObjectInputStream(fis);
				
				// Necesary cast to LinkedHashMap.
				scores = (LinkedHashMap<String, Integer>) ois.readObject();
				ois.close();
			} catch (Exception e) {
				System.out.println("Hashtable not loaded");
				e.printStackTrace();
			}
		}
		/**
		 * Saves the table on the file.
		 */
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
	
		/** 
		 * Every time the game ends, adds a score and check if it has to go to the table.
		 * @param name
		 * @param score
		 */
		public void addScore(String name, int score) {
			boolean mustReplace = false;
			for(Integer value : scores.values()) {
				if(score > value) {
					mustReplace = true;
					break;
				}
			}
			
			if(mustReplace) {
				LinkedHashMap<String, Integer> newScores = 
						new LinkedHashMap<String, Integer>(5);
				// Copies the first 4 elements (Excludes only the last).
				Iterator<Map.Entry<String, Integer>> i = scores.entrySet().iterator();
				for(int ctr = 0; ctr < 4; ctr++) {
					Map.Entry<String, Integer> tmp = i.next();
					newScores.put(tmp.getKey(), tmp.getValue());
				}
				// Adds the new score on the last position.
				newScores.put(name, score);
				scores = newScores;
				newScores = null;
				System.out.println("Score added: " + name + " || " + score);
				// Sorts and saves the table.
				sort();
				save();
			}
		}

		/**
		 * Sorts the table with a Comparator, from the highest to the lowest.
		 */
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
			
		/**
		 * Draw method for the High scores.
		 * @param g
		 * @param x -> position on x
		 * @param y -> position on y
		 */
		public void draw(java.awt.Graphics2D g, int x, int y) {
			Font f = new Font("8-Bit Madness", Font.PLAIN, 80);
			g.setFont(f);
			g.setColor(Color.WHITE);
			
			// If the list, for any reason, is empty, loads it.
			if(scores.isEmpty()) lfil();
			Iterator<Map.Entry<String, Integer>> it = scores.entrySet().iterator();
			for(int i = 0; i < 5; i++) {
				Map.Entry<String, Integer> tmp = it.next();
				g.drawString((i+1) + ". " + tmp.getKey(), x, (y + i*85));
				g.drawString("" + tmp.getValue(), x + Game.WIDTH/3 + 100, (y + i*85));
							
			}
		}
		
	}
	
	// Dimensions
	public static final int WIDTH = (int) screenSize().getWidth();
	public static final int HEIGHT = (int) screenSize().getHeight();
	
	// Thread
	private static Thread LoopThread;
	private static boolean paused = false;
	private boolean isRunning = false;
	private int FPS = 60;
	private long  LoopTime = 1000 / FPS;
	
	// Scores
	public static Highscore hs;
	
	// Game State Manager
	private static GameStateManager gsm;
	
	
	// **** CONSTRUCTOR ****
	/**
	 * Constructor for the Game.
	 */
	public Game() {
		super();
		init();
		setFocusable(true);
		requestFocus();
		LoopThread = new Thread(this);
		isRunning = true;
		LoopThread.start();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		
}

	/**
	 * Gets the actual dimensions of the screen.
	 * @return Dimension of the screen
	 */
	private static Dimension screenSize() {
		int xMax = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		int yMax = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		Dimension d = new Dimension(xMax, yMax);
		return d;
	}
	
	/**
	 * Icon list for the main game.
	 * @return Icon list
	 */
	public List<Image> iconList() {
		List<Image> i = new ArrayList<Image>(2);
		i.add(Sprite.loadSprite("/icon/icon16.png", this));
		i.add(Sprite.loadSprite("/icon/icon32.png", this));
		return i;
	}
	
	/**
	 * Initializes the game.
	 */
	private void init() {
		// Creates the High Score.
		hs = new Highscore();
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
			
			url = "resources/Fonts/MINECRAFT.ttf";
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
	
	/**
	 * Update method called every iteration of the Game Loop.
	 */
	private void update() {
		// Updates the selected state of the GSM.
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

	/**
	 * Draws all the components.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		// Calls the GameStateManager draw method.
		gsm.draw(g2d);
	}
	
	
	/**
	 *  Sets the Game to pause.
	 */
	public static void pauseMenu() {
		gsm.setState(GameStateManager.PAUSESTATE);
			
	}
	
  	/**
  	 * Resumes the game.
  	 */
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
	
	/** 
	 * Hides the cursor
	 */
	public void hideCursor() {
		//Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
		setCursor(blankCursor);
	}
	/**
	 * Shows the cursor.
	 */
	public void showCursor() {
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	
	// **** LISTENERS ****
	/*
	 * Listen the action performed by the event.
	 */
	// ** KEYLISTENER **
	@Override
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}

	// ** MOUSEMOTIONLISTENER **
	@Override
	public void mouseMoved(MouseEvent e) {
		gsm.mouseMoved(e);
	}
	
	// ** MOUSELISTENER **
	@Override
	public void mouseClicked(MouseEvent e) {
		gsm.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gsm.mousePressed(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		gsm.mouseReleased(e);
	}
	

	// **** UNUSED METHODS ****
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void keyTyped(KeyEvent key) {}



	}