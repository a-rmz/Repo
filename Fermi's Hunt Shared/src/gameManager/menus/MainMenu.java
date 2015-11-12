package gameManager.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import background.Background;
import gameManager.GameState;
import gameManager.GameStateManager;
import mainGame.Game;

@SuppressWarnings("serial")
public class MainMenu extends GameState{
	
	// Atributes
	GameStateManager gsm;
	int currentChoice;
	private String[] options = {
			"Continue", "New Game", "Load", "Help", "Credits", "Quit" 
	};

	
	// Graphics
	Background mBG;
	Color FontColor;
	Color SelectedFontColor;
	Font font;
	
	// Constructor
	public MainMenu(GameStateManager gsm) {
		this.gsm = gsm;
		currentChoice = 0;
		init();		
		font = new Font("8BIT WONDER Nominal", Font.PLAIN, 60);
		FontColor = Color.WHITE;
		SelectedFontColor = Color.BLUE;
	}

	@Override
	public void init() {
		mBG = new Background(Background.MAIN_MENU);
	}

	@Override
	public void update() {
		mBG.update();		
	}

	@Override
	public void draw(Graphics2D g) {
		// Draw bg
		mBG.draw(g);
		
		// Draw title
		font = new Font("8BIT WONDER Nominal", Font.PLAIN, 60);
		g.setColor(FontColor);
		g.setFont(font);
		g.drawString("Fermis Hunt", (int) Game.WIDTH/3, 150);
		
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
			// Continue
			break;
		case 1:
			gsm.newGame();
			break;
		case 2:
			// Load
			break;
		case 3:
			// Help
			break;
		case 4: 
			// Credits
			break;
		case 5:
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
	}

	public void keyReleased(int k) { }


	public void mouseOver(MouseEvent e) {
		 
//		if(FontMetrics.this.getStringBounds(options[0], gsm)) {
			
//		}
		
		
		if(e.getX() > 850 && e.getX() < 1090 && e.getY() > 500 && e.getY() < 540) {
			currentChoice = 0;
		}
		if(e.getX() > 850 && e.getX() < 1120 && e.getY() > 540 && e.getY() < 620) {
			currentChoice = 1;
		}
		if(e.getX() > 850 && e.getX() < 1120 && e.getY() > 620 && e.getY() < 680) {
			currentChoice = 2;
		}
		if(e.getX() > 850 && e.getX() < 1120 && e.getY() > 680 && e.getY() < 760) {
			currentChoice = 3;
		}
		if(e.getX() > 850 && e.getX() < 1120 && e.getY() > 760 && e.getY() < 800) {
			currentChoice = 4;
		}
		if(e.getX() > 850 && e.getX() < 1120 && e.getY() > 800 && e.getY() < 860) {
			currentChoice = 5;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseOver(e);		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseOver(e);
		select();
		System.out.println(e.getX() + ", " + e.getY());
		
	}

}
