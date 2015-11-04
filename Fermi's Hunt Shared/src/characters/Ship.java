package characters;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import managers.BulletManager;
import managers.Position;

public class Ship extends SpaceObject{

	// **** RESOURCES ****
	private String[] resources = {
		"/Sprites/Ship/Single Shot/sprite_ship1_singleShot.gif",
		"/Sprites/Ship/Single Shot/sprite_ship1_singleShot_Up.gif",
		"/Sprites/Ship/Single Shot/sprite_ship1_singleShot_Down.gif",
		"/Sprites/Ship/Single Shot/sprite_ship1_singleShot_shoot.gif"
	};
	private String url = resources[0];
	private Font f = new Font("8BIT WONDER Nominal", Font.PLAIN, 20);
	Image ship = null;
	
	
	// **** PLAYER STATS ****
	private int hp;
//	private int level;
//	private int ammo;
//	private int killedEnemies;
	
	
	// **** SHIP MODIFIERS ****
	private boolean gotHit = false;
	public Position p = new Position(50, (screenSize().height / 2));
	// New BulletManager to handle the bullets
	private static BulletManager bm = new BulletManager(-5);
	// Imports the list of Enemy bullets
	private LinkedList<Bullet> b = BasicEnemy.getEnemyBullets();
	
	
	// --------------------------------------------------------------------------------
	
	
	// **** CONSTRUCTOR ****
	public Ship() {
		// Initialize ship stats
		hp = 5;
		// Loads the ship image
		setSpaceObjectImage();
	}
	
	
	// ----------------- **** METHODS **** ----------------- 
	
	// **** GRAPHICAL METHODS ****
	/**
	 *  Loads the image as a .gif.
	 */
	public void setSpaceObjectImage() {
		try {
			// Gets the image from the url defined by the resources array
			ship = Toolkit.getDefaultToolkit().getImage(getClass().getResource(url));
		} catch (Exception e) {
			ship = null;
		}
	}
	
	/**
	 *  Returns the ship image.
	 *  @return Ship image.
	 */
	public Image getSpaceObjectImage() {
		return ship;
	}
	
	
	/**
	 *  Draws the enemy ship image and the Bullets fired.
	 * @param
	 */
	public void draw(Graphics g){
		// Draws the enemy image.
		g.drawImage(getSpaceObjectImage(), p.getX(), p.getY(),
				getSpaceObjectImage().getWidth(null)*2,
				getSpaceObjectImage().getHeight(null)*2,  null);
		// Draw method from BulletManager.
		bm.draw(g);
		// Draws the hits the player gets
		hitsOnSelf(g);
	}
	
	/**
	 *  Every time the ship gets hit, prints the string "HIT". Activated by
	 *  the gotHit switch. Is called directly by the TODO Game draw class.
	 * @param g
	 */
	public void hitsOnSelf(Graphics g) {
		// Graphics previous modifiers.
		g.setColor(Color.WHITE);
		g.setFont(f);
		// If the ship got hit, prints the "HIT" string.
		// TODO Implement to a random position near the ship.
		if(gotHit) {
			g.drawString("Hit", p.getX()+10, p.getY()+15);
		}
		// Again the gotHit switch is set to false.
		gotHit = false;
	}
	
	
	
	// **** LOGICAL METHODS ****
	/**
	 * Updates the position and ship stats.
	 */
	public void update() {
		// Re-loads the ship image
		setSpaceObjectImage();
		// Modifies the ship's position on getVel units.
		p.increasePosX(p.getVelX());
		p.increasePosY(p.getVelY());
		// Determines if the ship collided with the screen borders
		collidesWithBorders(screenSize());
		// Determines if the ship got hit
		gotHit();
		// Updates the BulletManager
		bm.update();
		// If the player died, stops the game
		if(!isAlive()) {
			System.out.println("Ship is dead");
		}
	}
	
	/**
	 *  According to the dimensions of the screen, determines if
	 *  the ship collided with the screen borders and modifies its
	 *  position.
	 */
	public void collidesWithBorders(Dimension d) {
		// Gets the x/y size of the image.
		int sizeX = getSpaceObjectImage().getWidth(null);
		int sizeY = getSpaceObjectImage().getHeight(null);
		
		// Collision with the left border.
		if ( p.getX() < 0){	
				p.setPosX(0);
		}else
			// Collision with the right border.
			if (p.getX() > d.getWidth()){
				// The position is set to the screen size minus the image width.
				p.setPosX(d.getWidth() - sizeX);
		}else 
			// Collision with the top border.
			if (p.getY() < 0 ){
				p.setPosY(0);
		}else 
			// Collision with the bottom border.
			if (p.getY() > d.getHeight()){
				// The position is set to the screen size minus the image height.
				p.setPosY(d.getHeight() - sizeY);
		}
	}

	// TODO
	public void attack() {
		url = resources[3];
		bm.add(p);
	}

	/**
	 *  Creates a new collider rectangle with the ship's actual position to
	 *  determine when it gets hit.
	 *  @return Rectangle with the dimensions of the ship.
	 */
	public Rectangle collider() {
		return new Rectangle(p.getX(), p.getY(), ship.getWidth(null), ship.getHeight(null));
	}
	
	/**
	 *  Analyzes if any of the BasicEnemy fired Bullets hit the ship and makes
	 *  the corresponding stats modifications.
	 */
	public void gotHit() {
		// For-each to analyze every Bullet object.
		for(Bullet a : b) {
			// Checks if the actual Bullet's collider rectangle intersects with this'.
			if(collider().intersects(a.collider())) {
				// If the ship got hit, activates the gotHit switch.
				// The gotHit switch affects the graphic representation of the hits.
				gotHit = true;
				// Stat modifiers.
				hp -= 1;
				// Only as a console verifier.
				System.out.println("Hit");
			}
		}
	}
	
	/**
	 *  Determines whether the player still alive or not.
	 * @return boolean true or false
	 */
	public boolean isAlive() {
		if(hp > 0) return true;
		return false;
	}
	
	/**
	 * Returns the Bullet list from the BulletManager.
	 * @return LinkedList of Bullets
	 */
	public static LinkedList<Bullet> getShipBullets() {
		return bm.returnManager();
	}
	
	
	
	// **** USER KEY INTERACTION ****
	
	/**
	 * On key pressed: Up arrow key
	 */
	public void up(){
		// Changes the actual ship image
		url = resources[1];
		// Changes the ship speed and allows it to move diagonally.
		p.setVelY(-15);
	}
	
	/**
	 * On key pressed: Down arrow key
	 */
	public void down() {
		// Changes the actual ship image
		url = resources[2];
		// Changes the ship speed and allows it to move diagonally.
		p.setVelY(15);
	}
	
	/**
	 * On key pressed: Left arrow key
	 */
	public void left() {
		// Changes the actual ship image
		url = resources[1];
		// Changes the ship speed and allows it to move diagonally.
		p.setVelX(-15);
	}
	
	/**
	 * On key pressed: Right arrow key
	 */
	public void right() {
		// Changes the actual ship image
		url = resources[2];
		// Changes the ship speed and allows it to move diagonally.
		p.setVelX(15);
	}
	
	
	
	// **** INHERITED FROM KEYLISTENER ****
	/* These methods are NOT IMPLEMENTING the KeyListener ones.
	 * These are independent, getting called by the TODO Game class.
	 */
	
	public void keyPressed(KeyEvent e) {
		// Gets the int representation of the KeyEvent e.
		int code = e.getKeyCode();
		// If the key pressed is UP, executes up method.
		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
			up();
		}
		// If the key pressed is DOWN, executes down method.
		if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
			down();
		}
		// If the key pressed is LEFT, executes left method.
		if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
			left();
		}
		// If the key pressed is RIGHT, executes right method.
		if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
			right();
		}
		// If the key pressed is SPACE BAR, executes attack method.
		if(code == KeyEvent.VK_SPACE) {
			attack();
		}
		// If the key pressed is ESC, exits the game.
		if(code == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		
	}

	public void keyReleased(KeyEvent e) {
		// Gets the int representation of the KeyEvent e.
		int code = e.getKeyCode();
		// When releasing UP key, the y-velocity is set to 0.
		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W)
			p.setVelY(0);
		// When releasing DOWN key, the y-velocity is set to 0.
		if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S)
			p.setVelY(0);
		// When releasing LEFT key, the x-velocity is set to 0.
		if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A)
			p.setVelX(0);
		// When releasing RIGHT key, the x-velocity is set to 0.
		if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D)
			p.setVelX(0);
		// The ship image is set to the basic image again.
		url = resources[0];
	}	
	
	
	
	
}