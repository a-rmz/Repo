package gameManager.menus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import background.Background;
import characters.Sprite;
import gameManager.GameStateManager;
import mainGame.Game;

@SuppressWarnings("serial")
public class CustomizeMenu extends Menu{

	// Graphics
	Background cBG;
	String[] baseShips = {
		"/Sprites/Ship/thumbs/sprite_ship1_singleShot_thumb.gif",
		"/Sprites/Ship/thumbs/sprite_ship1_singleShot_thumb.gif",
		"/Sprites/Ship/thumbs/sprite_ship1_singleShot_thumb.gif"
	};
	Image[] shipThumbs = new Image[3];
	
	Rectangle[] ships;
	
	// GameStateManager
	GameStateManager gsm;
	
	// Constructor
	public CustomizeMenu(GameStateManager gsm) {
		this.gsm = gsm;
		init();
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
			shipThumbs[i] = Sprite.loadSprite(baseShips[i], this);
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
		g.setStroke(new BasicStroke(3));
		Color back = new Color(0, 0, 0, 125);
		// Draw a Rectangle for each frame.
		for(Rectangle r : ships) {
			g.setColor(Color.BLACK);
			g.draw(r);
			g.setColor(back);
			g.fillRect(r.x, r.y, r.width, r.height);
		}
		
		// Draw the ships
		for(int i = 0; i < 3; i++) {
			g.drawImage(shipThumbs[0], 
					(int) ships[i].getX(),
					(int) ships[i].getY(),
					(int) ships[i].getWidth(),
					(int) ships[i].getHeight(),
					this
				);
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
