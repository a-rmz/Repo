package gameManager.menus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import background.Background;
import characters.Ship;
import characters.Sprite;
import gameManager.GameStateManager;
import gameManager.levels.Level1;
import gameManager.menus.popups.NamePopup;
import mainGame.Game;

@SuppressWarnings("serial")
public class CustomizeMenu extends Menu{
	
	private static class ShipThumb {
		// Members
		private String shipName;
		private Image ship;
		private int id;
		
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
			id = type;
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
		public int getID() {
			return id;
		}
	}

	// Graphics
	private Image frame;
	Background cBG;
	
	ShipThumb[] thumbs = new ShipThumb[3];
	Rectangle[] ships;
	
	Rectangle mainShipName;
	NamePopup np;
	ShipThumb mainShip;
	
	// Name
	String name = "";
	
	// Player Stats
	private static int pointsLeft = 15;
	private boolean stats[][]= {{false}, {false}};
	private boolean flag[] = {false, false, false};
	private int ptsPos;
	private Rectangle statsRect[][];
	Rectangle oR = new Rectangle(
			775, 
			200, 
			1000, 
			760);
	private boolean readyToLaunch = false;
	
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
		frame = Sprite.loadSprite("/BackgroundImg/Customize_Menu/frame.png", this);
		stats = new boolean[3][10];
		for(int i = 0; i < ShipThumb.shipQuant(); i++) {
			ships[i] = new Rectangle(
				(i * 200) + 200,
				3 * Game.HEIGHT / 4,
				150,
				150
			);
		}
		loadThumbs();
		initPoints();
		mainShip = thumbs[0];
		
		name = "Enter Ship name";
		Ship.getPlayer().setShipName(name);
		np = new NamePopup(gsm, name);
	}
	
	private void initPoints() {
		statsRect = new Rectangle[3][10];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 10; j++) {
				statsRect[i][j] = new Rectangle(oR.x + 150 + (j*40), oR.y + 280 + (i * 150), 30, 40);
			}
		}
	}
	
	private void loadThumbs() {
		for(int i = 0; i < ShipThumb.shipQuant(); i++) {
			thumbs[i] = new ShipThumb(i);
		}
	}

	@Override
	public void update() {
		cBG.update();
		if(pointsLeft == 0) {
			readyToLaunch = true;
		} else {
			readyToLaunch = false;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		// Draws the Background
		cBG.draw(g);
		drawShipOptions(g);
		drawMainShip(g);
		drawShipName(g);
		drawShipCustomPoints(g);
		if(readyToLaunch) drawLaunch(g);
		if(np.visible()) np.drawNamePopup(g);
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
		Font f = new Font("8-Bit Madness", Font.PLAIN, 120);
		g.setFont(f);
		FontMetrics fm = g.getFontMetrics(f);
		
		g.drawString(
				name, 
				(2 * Game.WIDTH / 3) - fm.stringWidth(name)/2 + 30, 
				150
				);
		
		if(mainShipName == null) {
			mainShipName = new Rectangle(
					2 * Game.WIDTH / 3 - fm.stringWidth(name)/2 + 30, 
					150 - fm.getHeight(), 
					fm.stringWidth(name), 
					fm.getHeight()
				);
		}
	}
	
	private void drawShipCustomPoints(Graphics2D g) {
		g.setStroke(new BasicStroke(5));
		g.setColor(Color.BLACK);
		// Draws the frame and background
		g.drawImage(frame, oR.x, oR.y, null);
		
		String points = "Points left: " + pointsLeft;
		// Draws the header
		g.setColor(Color.WHITE);
		Font f = new Font("8-Bit Madness", Font.PLAIN, 60);
		FontMetrics fm = g.getFontMetrics(f);
		g.setFont(f);
		g.drawString(points, 
				(int) (oR.getCenterX() - fm.stringWidth(points)/2),
				oR.y + 200);
		
		f = new Font("8-Bit Madness", Font.PLAIN, 30);
		fm = g.getFontMetrics(f);
		String buffer = "Shield: ";
		g.drawString(buffer, oR.x + 100, oR.y + 250);
		buffer = "Speed: ";
		g.drawString(buffer, oR.x + 100, oR.y + 400);
		buffer = "Fire Rate: ";
		g.drawString(buffer, oR.x + 100, oR.y + 550);
		drawPoints(g);
	}
	
	private void drawPoints(Graphics2D g) {
		Color ctrue = new Color(255, 216, 0, 100);
		Color cfalse = new Color(205, 16, 26, 100);
		
		g.setStroke(new BasicStroke(4));
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 10; j++) {
				if(stats[i][j]) {
					g.setColor(ctrue);
					g.fillRect(oR.x + 150 + (j*40), oR.y + 280 + (i * 150), 30, 40);
					g.setColor(Color.BLACK);
					g.draw(statsRect[i][j]);
				} else {
					g.setColor(cfalse);
					g.fillRect(oR.x + 150 + (j*40), oR.y + 280 + (i * 150), 30, 40);
					g.setColor(Color.BLACK);
					g.draw(statsRect[i][j]);					
				}
			}
		}
	}

	private void drawLaunch(Graphics2D g) {
		
		Image launchBtn = Sprite.loadSprite(
				"/BackgroundImg/Customize_Menu/launch_btn.png", this);
		g.drawImage(launchBtn,
				(int) (oR.x + oR.getWidth() - 50 - launchBtn.getWidth(this)),
				(int) (oR.y + oR.getHeight() - 50 - launchBtn.getHeight(this)), 
				launchBtn.getWidth(this), launchBtn.getHeight(this), this);
	}
	
	

	
	private void setShipName() {
		Ship.getPlayer().setShipName("");
		np.setVisible(true);
	}
	
	private void setPoints() {
		if(pointsLeft <= 0) {
			subtractPoints();
			return;
		}
		for(int i = 0; i < 3; i++) {
			if(!flag[i]) continue;
			for(int j = 0; j <= ptsPos && pointsLeft > 0; j++) {
				if(stats[i][ptsPos]) {
					subtractPoints();
					flag[i] = false;
					return;
				}
				if(!stats[i][j]) {
					stats[i][j] = true;
					pointsLeft--;
				}
				switch(i) {
				case 0:
					Ship.getPlayer().shield++; break;
				case 1:
					Ship.getPlayer().speed++; break;
				case 2:
					Ship.getPlayer().fireRate++; break;
				}
			}
			flag[i] = false;
		}
	}
	
	private void subtractPoints() {
		for(int i = 0; i < 3; i++) {
			if(!flag[i]) continue;
			for(int j = ptsPos; stats[i][j] && pointsLeft <= 10; j++) {
				if(stats[i][j]) {
					stats[i][j] = false;
					pointsLeft++;
				}
				switch(i) {
				case 0:
					Ship.getPlayer().shield--; break;
				case 1:
					Ship.getPlayer().speed--; break;
				case 2:
					Ship.getPlayer().fireRate--; break;
				}
			}
			flag[i] = false;
		}
		
	}
	
	@Override
	public void keyPressed(int k) {	}

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
	private boolean mouseOverPoints(MouseEvent e) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 10; j++) {
				if(statsRect[i][j].contains(e.getPoint())) {
					ptsPos = j;
					flag[i] = true;
					return true;
				}
			}
		}
		return false;
	}
	private boolean mouseOverLaunch(MouseEvent e) {
		if(!readyToLaunch) return false;

		Image launchBtn = Sprite.loadSprite(
				"/BackgroundImg/Customize_Menu/launch_btn.png", this);
		
		Rectangle launch = new Rectangle(
				(int) (oR.x + oR.getWidth() - 50 - launchBtn.getWidth(this)),
				(int) (oR.y + oR.getHeight() - 50 - launchBtn.getHeight(this)), 
				launchBtn.getWidth(this), launchBtn.getHeight(this));
		
		if(launch.contains(e.getPoint())) {
			return true;
		}		
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
		if(np.visible()) return;
		if(mouseOverThumbs(e)) {
			// Automatically switches between ships.
			Ship.getPlayer().changeType(mainShip.getID());
		} else if (mouseOverName(e)) {
			setShipName();
		} else if (mouseOverPoints(e)) {
			setPoints();
		} else if (mouseOverLaunch(e)) {
			se.stop();
			Ship.getPlayer().initPlayer();
			gsm.game.hideCursor();
			Level1 l1 = new Level1(gsm);
			l1.initCinematic();
			gsm.gameStates.add(l1);
			gsm.setState(GameStateManager.LEVEL1STATE);
			
			
		}
		
	}

}
