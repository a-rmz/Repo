package gameManager.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import background.Background;
import gameManager.GameState;
import gameManager.GameStateManager;
import mainGame.Game;

@SuppressWarnings("serial")
public class PauseMenu extends GameState implements Runnable{

	//private GameStateManager gsm;
	
	int currentChoice;
	private String[] options = {
			"Resume", "Save", "Load", "Help", "Quit"
		};
	
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
	
	
	public PauseMenu(GameStateManager gsm) {
		this.gsm = gsm;
		init();
		font = new Font("8BIT WONDER Nominal", Font.PLAIN, 60);
		FontColor = Color.WHITE;
		SelectedFontColor = Color.BLUE;
	}
	
	public void addNotify() {
		super.addNotify();
		if(pauseMenu == null) {
			pauseMenu = new Thread(this, "PauseMenu");
			pauseMenu.start();
		}
	}
	
	@Override
	public void init() {
		pBG = new Background(Background.PAUSE_MENU);
		image = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
	}

	@Override
	public void update() {
		pBG.update();
	}

	@Override
	public void draw(Graphics2D g) {
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
		for(int i = 0; i < options.length; i++) {
			
			if(i == currentChoice) {
				g.setColor(SelectedFontColor);
			} else {
				g.setColor(FontColor);
			}
			
			g.drawString(options[i], 
					(int) Game.WIDTH/2 - 100, 
					(int) Game.HEIGHT/2 + (i * 60));
		}
	}


	private void select() {
		switch(currentChoice) {
		case 0:
			Game.resumeGame();
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			System.exit(0);
			break;
		}
	}
	
	public void keyPressed(int k) {
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
			Game.resumeGame();
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

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}