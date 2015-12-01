package gameManager.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import background.Background;
import gameManager.GameStateManager;
import mainGame.Game;

@SuppressWarnings("serial")
public class MainMenu extends Menu{
	
	// Atributes
	GameStateManager gsm;
	int currentChoice;
	
	String[] options = { 
			"New Game", "High Scores", "Help", "Credits", "Quit" 
	};
	private Rectangle[] optionsRect;
	
	// Graphics
	Background mBG;
	Color FontColor;
	Color SelectedFontColor;
	Font font;
	
	
	// SubMenus
	HelpMenu hM;
	HighscoreMenu hsM;
	
	// Constructor
	public MainMenu(GameStateManager gsm) {
		this.gsm = gsm;
		currentChoice = 0;
		init();		
		font = new Font("8BIT WONDER Nominal", Font.PLAIN, 60);
		FontColor = Color.WHITE;
		SelectedFontColor = Color.BLUE;
		se.backGroundMenuMusic(1);
		se.play();
		
	}

	@Override
	public void init() {
		mBG = new Background(Background.MAIN_MENU);
		optionsRect = new Rectangle[options.length];
		hM = HelpMenu.menu();
		hsM = HighscoreMenu.menu();
		
	}

	@Override
	public void update() {
		if(helpMenu) {
			hM.update();
			return;
		}
		if(highscoreMenu) {
			hsM.update();
			return;
		}
		mBG.update();		
	}

	@Override
	public void draw(Graphics2D g) {
		if(helpMenu) {
			hM.draw(g);
			return;
		}
		if(highscoreMenu) {
			hsM.draw(g);
			return;
		}
		// Draw bg
		mBG.draw(g);
		
		// Draw title
		font = new Font("8BIT WONDER Nominal", Font.PLAIN, 60);
		g.setColor(FontColor);
		g.setFont(font);
		g.drawString("Fermis Hunt", Game.WIDTH/3, 150);
		
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
					Game.WIDTH/2 - fm.stringWidth(options[i])/2, 
					Game.HEIGHT/2 + (i * 60));
			
			// Fills the array with the collision rectangles.n
			if (!(optionsRect[options.length-1] instanceof Rectangle)) {
				optionsRect[i] = createOptionBorder(options[i], i, g, fm);
			}
		}
	}

	@Override
	protected void select() {
		switch(currentChoice) {
		case 0:
			gsm.newGame();
//			se.Stop();
			break;
		case 1:
			// High Scores
			highscoreMenu = true;
			break;
		case 2:
			// Help
			helpMenu = true;
			hM.restart();
			currentChoice = 0;
			break;
		case 3: 
			// Credits
			break;
		case 4:
			System.exit(0);
			break;
		}
	}
	
	@Override
	public void keyPressed(int k) {
		if(helpMenu) {
			hM.keyPressed(k);
			return;
		}
		if(highscoreMenu) {
			hsM.keyPressed(k);
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
	}

	@Override
	public void keyReleased(int k) { }


	public boolean mouseOver(MouseEvent e) {
		if(helpMenu) {
			// HelpMenu stuff here
			return false;
		}
		if(highscoreMenu) {
			// hsM stuff here
			return false;
		}
		for(int i = 0; i < options.length; i++) {
			if(optionsRect[i].contains(e.getPoint())) {
				currentChoice = i;
				return true;
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
		} else if(helpMenu) {
			hM.mouseClicked(e);
		}
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
