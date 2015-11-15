package gameManager.menus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import background.Background;
import gameManager.GameStateManager;
import mainGame.Game;

@SuppressWarnings("serial")
public class CustomizeMenu extends Menu{

	// Graphics
	Background cBG;
	String[] baseShips = {
		"/Sprites/Ship/thumbs/sprite_ship1_singleShot_thumb.gif"	
	};
	Image[] shipThumbs = new Image[3];
	
	Rectangle[] ships;
	
	// GameStateManager
	GameStateManager gsm;
	
	// Constructor
	public CustomizeMenu(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	@Override
	protected void select() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		gsm.game.showCursor();
		cBG = new Background(Background.CUSTOMIZE_MENU);
		ships = new Rectangle[3];
		for(int i = 0; i < 3; i++) {
			ships[i] = new Rectangle(
				(i * 200) + 200,
				(int) (2 * Game.HEIGHT / 3),
				150,
				150
			);
		}
		loadThumbs();
	}
	
	private void loadThumbs() {
		for(int i = 0; i < 3; i++) {
			
		}
	}

	@Override
	public void update() {
		cBG.update();
		
	}

	@Override
	public void draw(Graphics2D g) {
		// Draws the Background
		cBG.draw(g);
		drawShipOptions(g);
		
	}
	
	private void drawShipOptions(Graphics2D g) {
		// Draw the ships
		Graphics gr = (Graphics) g;
		gr.drawImage(shipThumbs[0], 
					ships[0].getX(),
					ships[0].getY()
					
				);
		
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.BLACK);
		// Draw a Rectangle for each frame.
		for(Rectangle r : ships) {
			//g.draw(r);
			g.drawRect(r.x, r.y, r.width, r.height);
		}
	}

	@Override
	public void keyPressed(int k) {
		switch(k) {
		case KeyEvent.VK_ESCAPE:
			gsm.setState(GameStateManager.LEVEL1STATE);
			break;
		}
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
