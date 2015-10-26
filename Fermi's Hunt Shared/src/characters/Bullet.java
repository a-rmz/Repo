package characters;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;

import managers.Position;

public class Bullet {
	
	// **** RESOURCES ****
	private String[] resources = {
			"/Sprites/Bullet/BasicEnemyAttack.png"
	};
	private String url;
	public Image bullet = null;

	
	// **** BULLET STATS ****
	private int bulletSpeed = -30; 
	
	
	// **** BULLET MODIFIERS ****
	public Position p;
	
	
	// --------------------------------------------------------------------------------
	
	
	// **** CONSTRUCTOR ****
	/**
	 * Receives as a parameter the object who fires the bullet.
	 * Most accurate way to obtain the object data.
	 * @param p
	 */
	public Bullet(Position p) {
		// Sets the url for the Bullet image. 
		url = resources[0];
		// Loads the bullet image.
		setBulletImage();
		// Clones the position of the parameter to the local Position.
		this.p = p.clone();
		// Sets the y-position to the half of the enemy image.
		p.setPosY(p.getY() - (bullet.getHeight(null)/2));
	}

	
	// ----------------- **** METHODS **** ----------------- 
	
	// **** GRAPHICAL METHODS ****
	/**
	 *  Loads the image as a TODO TBD .gif/.png.
	 */
	public void setBulletImage() {
		try {
			// Gets the image from the url defined by the resources array
			bullet = Toolkit.getDefaultToolkit().createImage(getClass().getResource(url));
		} catch (Exception e) {
			bullet = null;
		}
	}

	/**
	 *  Returns the bullet image.
	 * @return Bullet Image
	 */
	public Image getBulletImage() {
		return bullet;
	}
	
	/**
	 *  Draws the bullet image.
	 * @param g
	 */
	public void draw(Graphics g) {
		g.drawImage(bullet, p.getX(), p.getY(), null);
	}

	
	
	// **** LOGICAL METHODS ****
	/**
	 *  Moves the position of the Bullet on bulletSpeed units.
	 */
	public void update() {
		p.increasePosX(bulletSpeed);
		
	}
	
	/**
	 *  Creates a new collider rectangle with the Bullet's actual position to
	 *  determine if it hits the ship.
	 *  @return Rectangle with the dimensions of the Bullet.
	 */
	public Rectangle collider() {
		return new Rectangle(p.getX(), p.getY(), bullet.getWidth(null), bullet.getHeight(null));	
	}
	
	
}
