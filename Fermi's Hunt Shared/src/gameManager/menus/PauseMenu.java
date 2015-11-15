package gameManager.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Effects.SoundEffects;
import background.Background;
import gameManager.*;
import mainGame.Game;

@SuppressWarnings("serial")
public class PauseMenu extends Menu implements Runnable{

	//private GameStateManager gsm;
	
	int currentChoice;
	private String[] options = {
			"Resume", "Save", "Load", "Help", "Quit"
		};
	private Rectangle[] optionsRect;
	
	//backGround Sound
	SoundEffects se = new SoundEffects();
	
	// Thread
	Thread pauseMenu;
	private boolean running;
	
	// Graphics
	Background pBG;
	Color FontColor;
	Color SelectedFontColor;
	Font font;
	// Image
	private BufferedImage image;
	private Graphics2D g;
	
	// GameStateManager
	GameStateManager gsm;
	
	
	public PauseMenu(GameStateManager gsm) {
		this.gsm = gsm;
		init();
		font = new Font("8BIT WONDER Nominal", Font.PLAIN, 60);
		FontColor = Color.WHITE;
		SelectedFontColor = Color.BLUE;
		se.backGroundMenuMusic(0);
	}
	
	@Override
	public void init() {
		currentChoice = 0;
		pBG = new Background(Background.PAUSE_MENU);
		image = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		gsm.game.showCursor();
		optionsRect = new Rectangle[options.length];
		se.play();
		
	}
	
	public void addNotify() {
		super.addNotify();
		if(pauseMenu == null) {
			pauseMenu = new Thread(this, "PauseMenu");
			pauseMenu.start();
		}
	}

	@Override
	public void update() {
		if(helpMenu) {
			HelpMenu.menu().update();
			return;
		}
		pBG.update();
	}

	@Override
	public void draw(Graphics2D g) {
		if(helpMenu) {
			HelpMenu.menu().draw(g);
			return;
		}
		// Draw bg
		pBG.draw(g);
		
		// Draw title
		font = new Font("8BIT WONDER Nominal", Font.PLAIN, 60);
		g.setColor(FontColor);
		g.setFont(font);
		g.drawString("Pause", (int) Game.WIDTH/3, 150);
		
		// Draw menu options
		font = new Font("8BIT WONDER Nominal", Font.PLAIN, 30);
		g.setFont(font);
		// Helps to center the text.
		FontMetrics fm = g.getFontMetrics(font);
		for(int i = 0; i < options.length; i++) {
			
			if(i == currentChoice) {
				g.setColor(SelectedFontColor);
			} else {
				g.setColor(FontColor);
			}
			
			g.drawString(options[i], 
					(int) Game.WIDTH/2 - fm.stringWidth(options[i])/2, 
					(int) Game.HEIGHT/2 + (i * 60));
			
			// Fills the array with the collision rectangles.
			if (!(optionsRect[options.length-1] instanceof Rectangle)) {
				optionsRect[i] = createOptionBorder(options[i], i, g, fm);
			}
			
		}
	}


	protected void select() {
		switch(currentChoice) {
		case 0:
			// Resume
			gsm.game.hideCursor();
			Game.resumeGame();
			se.Stop();
			break;
		case 1:
			// Save
			break;
		case 2:
			// Load
			break;
		case 3:
			// Help
			helpMenu = true;
			currentChoice = 0;
			break;
		case 4:
			// Quit
			System.exit(0);
			break;
		}
	}
	
	public void keyPressed(int k) {
		if(helpMenu) {
			HelpMenu.menu().keyPressed(k);
			return;
		}
		if(k == KeyEvent.VK_ENTER) {
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
		if(k == KeyEvent.VK_ESCAPE) {
			currentChoice = 0;
			select();
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	// Thread
	public void run() {
		while(running) {	
			draw(g);
			update();
			drawToScreen();
		}
	}
	
	private void drawToScreen() { 
		Graphics g2 = getGraphics();
		g2.drawImage(image,  0, 0, Game.WIDTH, Game.HEIGHT, null);
		g2.dispose();
	}

	public boolean mouseOver(MouseEvent e) {
		if(helpMenu) {
			// HelpMenu stuff here
			return false;
		}

		for(int i = 0; i < options.length; i++) {
			try {
				if(optionsRect[i].contains(e.getPoint())) {
				currentChoice = i;
				return true;
				}
			} catch (Exception ex) {
				System.out.println("Error madafaca: \n" + ex	);
			}
			
		}
		return false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseOver(e);		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// Only admits clicking if the mouse is over the option.
		if(mouseOver(e)) {
			select();
		}
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
	}
}
