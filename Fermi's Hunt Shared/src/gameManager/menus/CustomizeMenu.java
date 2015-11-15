package gameManager.menus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import background.Background;
import characters.Ship;
import characters.Sprite;
import gameManager.GameStateManager;
import mainGame.Game;

@SuppressWarnings("serial")
public class CustomizeMenu extends Menu{
	
	private static class ShipThumb {
		// Members
		private String shipName;
		private Image ship;
		
		// Options
		private static final String names[] = {
			"Star Fighter Alpha Rogue 1",
			"Combat Star Luna",
			"Ship destroyer Commando"
		};

		String[] baseShips = {
				"/Sprites/Ship/thumbs/sprite_ship1_singleShot_thumb.gif",
				"/Sprites/Ship/thumbs/sprite_ship2_singleShot_thumb.gif",
				"/Sprites/Ship/thumbs/sprite_ship3_singleShot_thumb.gif"
			};
		
		public ShipThumb(int type) {
			shipName = names[type];
			ship = Sprite.loadSprite(baseShips[type], this);
		}
		
		public Image getThumb() {
			return ship;
		}
		public String getName() {
			return shipName;
		}
		public static int shipQuant() {
			return names.length;
		}
	}

	// Graphics
	Background cBG;
	
	ShipThumb[] thumbs = new ShipThumb[3];
	Rectangle[] ships;
	
	Rectangle mainShipName;
	
	ShipThumb mainShip;
	
	// Name
	String name = "";
	
	// Player Stats
	private int pointsLeft = 10;
	private int shield;
	private int fireRate;
	private int speed;
	private boolean stats[][]= {{false}, {false}};
	
	// GameStateManager
	GameStateManager gsm;
	
	// Constructor
	public CustomizeMenu(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	@Override
	protected void select() {
		
	}

	@Override
	public void init() {
		gsm.game.showCursor();
		cBG = new Background(Background.CUSTOMIZE_MENU);
		ships = new Rectangle[ShipThumb.shipQuant()];
		stats = new boolean[3][10];
		for(int i = 0; i < ShipThumb.shipQuant(); i++) {
			ships[i] = new Rectangle(
				(i * 200) + 200,
				(int) (3 * Game.HEIGHT / 4),
				150,
				150
			);
		}
		loadThumbs();
		mainShip = thumbs[0];
		Ship.getPlayer().setShipName("Enter Ship name");
	}
	
	private void loadThumbs() {
		for(int i = 0; i < ShipThumb.shipQuant(); i++) {
			thumbs[i] = new ShipThumb(i);
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
		drawMainShip(g);
		drawShipName(g);
		drawShipCustomPoints(g);
	}
	
	public void drawMainShip(Graphics2D g) {
		g.setStroke(new BasicStroke(5));
		g.setColor(Color.BLACK);
		// Draws the frame and background
		Rectangle r = new Rectangle(
				200, 
				150, 
				ships[1].x + ships[2].width, 
				ships[1].x + ships[2].width
				);
		g.draw(r);
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(r.x, r.y, r.width, r.height);
		
		// Draws the ship name
		g.setColor(Color.WHITE);
		Font f = new Font("8BIT WONDER Nominal", Font.PLAIN, 22);
		FontMetrics fm = g.getFontMetrics(f);
		g.setFont(f);
		g.drawString(mainShip.getName(), 
				(int) r.getCenterX() - fm.stringWidth(mainShip.getName())/2, 
				r.y + r.height + 35
				);
		
		// Draws the ship image
		g.drawImage(mainShip.getThumb(),
				r.x,
				r.y,
				r.width,
				r.height,
				this
			);
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
		for(int i = 0; i < ShipThumb.shipQuant(); i++) {
			g.drawImage(thumbs[i].getThumb(), 
					(int) ships[i].getX(),
					(int) ships[i].getY(),
					(int) ships[i].getWidth(),
					(int) ships[i].getHeight(),
					this
				);
		}
	}
	
	private void drawShipName(Graphics2D g) {
		String name = Ship.getPlayer().getShipName();
		
		// Draws the ship name
		g.setColor(Color.WHITE);
		Font f = new Font("8BIT WONDER Nominal", Font.PLAIN, 60);
		g.setFont(f);
		FontMetrics fm = g.getFontMetrics(f);
		
		g.drawString(
				name, 
				(2 * Game.WIDTH / 3) - fm.stringWidth(name)/2 + 30, 
				150
				);
		
		if(mainShipName == null) {
			mainShipName = new Rectangle(
					(int) (2 * Game.WIDTH / 3) - fm.stringWidth(name)/2 + 30, 
					(int) 150 - fm.getHeight(), 
					(int) fm.stringWidth(name), 
					(int) fm.getHeight()
				);
		}
	}
	
	private void drawShipCustomPoints(Graphics2D g) {
		g.setStroke(new BasicStroke(5));
		g.setColor(Color.BLACK);
		// Draws the frame and background
		Rectangle r = new Rectangle(
				ships[1].x + ships[2].width + 225, 
				200, 
				1000, 
				760
				);
		g.draw(r);		
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(r.x, r.y, r.width, r.height);
		
		String points = "Points left: " + pointsLeft;
		// Draws the header
		g.setColor(Color.WHITE);
		Font f = new Font("8-Bit Madness", Font.PLAIN, 60);
		FontMetrics fm = g.getFontMetrics(f);
		g.setFont(f);
		g.drawString(points, 
				(int) (r.getCenterX() - fm.stringWidth(points)/2),
				r.y + 100);
		
		f = new Font("8-Bit Madness", Font.PLAIN, 30);
		fm = g.getFontMetrics(f);
		String buffer = "Shield: ";
		g.drawString(buffer, r.x + 50, r.y + 250);
		buffer = "Speed: ";
		g.drawString(buffer, r.x + 50, r.y + 400);
		buffer = "Fire Rate: ";
		g.drawString(buffer, r.x + 50, r.y + 550);
		drawPoints(g);
	}
	

	
	private void setShipName() {
		// Do magic stuff
	}
	
	private void drawPoints(Graphics2D g) {
		Rectangle r = new Rectangle(
			ships[1].x + ships[2].width + 225, 
			200, 
			1000, 
			760
			);
		Color ctrue = new Color(255, 216, 0, 100);
		Color cfalse = new Color(205, 16, 26, 100);
		
		g.setStroke(new BasicStroke(4));
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 10; j++) {
				if(stats[i][j]) {
					g.setColor(ctrue);
					g.fillRect(r.x + 150 + (j*40), r.y + 280 + (i * 150), 30, 40);
					g.setColor(Color.BLACK);
					g.drawRect(r.x + 150 + (j*40), r.y + 280 + (i * 150), 30, 40);
				} else {
					g.setColor(cfalse);
					g.fillRect(r.x + 150 + (j*40), r.y + 280 + (i * 150), 30, 40);
					g.setColor(Color.BLACK);
					g.drawRect(r.x + 150 + (j*40), r.y + 280 + (i * 150), 30, 40);					
				}
			}
		}
	}

	@Override
	public void keyPressed(int k) {		
		switch(k) {
		case KeyEvent.VK_ESCAPE:
			gsm.setState(GameStateManager.LEVEL1STATE);
			gsm.game.hideCursor();
			break;
		}
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	private boolean mouseOverThumbs(MouseEvent e) {
		for(int i = 0; i < ShipThumb.shipQuant(); i++) {
			if(ships[i].contains(e.getPoint())) {
				mainShip = thumbs[i];
				return true;
			}
		}
		return false;
	}
	private boolean mouseOverName(MouseEvent e) {
		if(mainShipName.contains(e.getPoint())) return true;
		return false;
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
		if(mouseOverThumbs(e)) {
			// Automatically switches between ships.
		} else if (mouseOverName(e)) {
			setShipName();
		}
		
	}

}
