package characters;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import managers.Position;

public class Bullet {
	
	// **** RESOURCES ****
	private String[] resources = {
			"/Sprites/Bullet/BasicEnemyAttack.png",
			"/Sprites/Bullet/ShipBullet.png",
			"/Sprites/Bullet/ShipMissile.png",
	};
	private String url;
	public Image bullet = null;

	
	// **** BULLET STATS ****
	private int bulletSpeed = -30; 
	private int bulletDamage = 0;
	
	
	// **** BULLET MODIFIERS ****
	public Position p;
	private boolean ship = false;
	
	
	// --------------------------------------------------------------------------------
	
	
	// **** CONSTRUCTOR ****
	/**
	 * Receives as a parameter the object who fires the bullet.
	 * Most accurate way to obtain the object data.
	 * The direction (1 or -1) mutliplies the velX to direct the Bullet
	 * @param p
	 * @param direction
	 */
	public Bullet(Position p, int direction) {
		if(direction < 0 ) {
			// Sets the url for the Bullet image. 
			url = resources[1];
			bulletDamage = Ship.getPlayer().getLevel();
			ship = true;
			System.out.println(bulletDamage);
		} else {
			// Sets the url for the Bullet image. 
			url = resources[0];
			bulletDamage = 1;
		}
		
		// Loads the bullet image.
		setBulletImage();
		// Clones the position of the parameter to the local Position.
		this.p = p.clone();
		this.p.setPosX(this.p.getX());
		// Sets the y-position to the half of the enemy image.
		this.p.setPosY(this.p.getY()/* - (bullet.getHeight(null)/2)*/);
		// Defines the Bullet's direction
		bulletSpeed = bulletSpeed * direction;
	}

	
	// ----------------- **** METHODS **** ----------------- 
	
	// **** GRAPHICAL METHODS ****
	/**
	 *  Loads the image as a .png.
	 */
	public void setBulletImage() {
		try {
			// Gets the image from the url defined by the resources array
			bullet = Toolkit.getDefaultToolkit().getImage(getClass().getResource(url));
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
		if(ship) {
			bulletDamage = Ship.getPlayer().getLevel();
			bulletDamage = (bulletDamage * 2) - 1;
			if(Ship.getPlayer().getLevel() == 3) {
				url = resources[2];
			}
		}
		setBulletImage();
	}
	
	public int getDamage() {
		return this.bulletDamage;
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
