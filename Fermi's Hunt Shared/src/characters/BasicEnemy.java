package characters;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.Timer;

import managers.BulletManager;
import managers.Position;

public class BasicEnemy extends SpaceObject implements ActionListener{
	
	// **** RESOURCES ****
	private String[] resources = {
			"/Sprites/Enemies/BasicEnemy.png"
	};
	private String url;
	public Image enemy = null;
	
	
	// **** ENEMY STATS ****
	public int enemyLevel;
	public int attackSpeed =  15;
	
	
	// **** ENEMY MODIFIERS ****
	// The timer is for the BasicEnemy to fire every certain time.
	Timer enemyFire = new Timer(attackSpeed * 100, this);
	private Random r = new Random();
	public Position p;
	// New BulletManager to handle shots.
	private static BulletManager bm = new BulletManager();

	
	// --------------------------------------------------------------------------------
	
	
	// **** CONSTRUCTOR ****
	/**
	 * Constructor. Receives as parameter the actual level (or enemy level).
	 * For further level assistance, consult level manager. 
	 * @param enemyLevel
	 */
	public BasicEnemy(int enemyLevel){
		// Sets the position to a random int within the screen bounds.
		p = new Position(r.nextInt(screenSize().width-100), r.nextInt(screenSize().height-100));
		// Sets the url for the enemy image according to the enemyLevel.
		url = resources[enemyLevel - 1];
		// Loads the enemy image.
		setSpaceObjectImage();
		// Sets the enemy level to the parameter enemyLevel.
		this.enemyLevel = enemyLevel;
		// Starts the enemyFire timer.
		enemyFire.start();
		// Sets the enemy velocity to a number between 10 and 20.
		// TODO to be defined by the enemyLevel.
		p.setVelX((r.nextInt(2)+1)*10);
		p.setVelY((r.nextInt(2)+1)*10);
	}

	
	// ----------------- **** METHODS **** ----------------- 
	
	// **** GRAPHICAL METHODS ****
	/**
	 *  Loads the image as a .png.
	 */
	public void setSpaceObjectImage(){
		try {
			// Gets the image from the url defined by the resources array
			enemy = Toolkit.getDefaultToolkit().createImage(getClass().getResource(url));
		} catch (Exception e) {
			enemy = null;
		}
	}	

	/**
	 *  Returns the enemy image.
	 *  @return Enemy image.
	 */
	public Image getSpaceObjectImage() {
		return enemy;
	}
	
	/**
	 *  Draws the enemy ship image and the Bullets fired.
	 * @param
	 */
	public void draw(Graphics g){
		// Draw method from BulletManager.
		bm.draw(g);
		// Draws the enemy image.
		g.drawImage(getSpaceObjectImage(), p.getX(), p.getY(), null);
	}

	
	
	// **** LOGICAL METHODS ****
	/**
	 * Updates the position, the enemy stats, and the BulletManager.
	 */		
	public void update(){
		// Modifies the enemy's position on getVel units.
		p.increasePosY(p.getVelY());
		p.increasePosX(p.getVelX());
		// Determines if the ship collided with the screen borders
		collidesWithBorders(screenSize());
		// Update method from the BulletManager.
		bm.update();
		// Determines if the enemy's velocity has to change.
		changeVelocity();
	}
	
	/**
	 *  Changes the velocity of the enemy every certain time determined
	 *  by a random number.
	 */
	public void changeVelocity() {
		// Generates a random number to be mod by 30.
		// If the mod is 0, it is time to change velocity direction.
		if((r.nextInt(10000) % 30) == 0) {
			// The nextBoolean determines if the x-velocity has to change.
			if(r.nextBoolean()) {
				p.setVelX(-p.getVelX());
			}
			// The nextBoolean determines if the x-velocity has to change.
			if(r.nextBoolean()) {
				p.setVelY(-p.getVelY());
			}
			
			// The nextBoolean determines if the x-velocity has to change in +-5.
			if(r.nextBoolean()) {
				p.setVelX(p.getVelX()+ (r.nextInt(5)-10));
			}
			// The nextBoolean determines if the y-velocity has to change in +-5.
			if(r.nextBoolean()) {
				p.setVelY(p.getVelY()+ (r.nextInt(5)-10));
			}
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
				// Changes the direction to avoid going off bounds.
				p.setVelX(-p.getVelX());
		}else 
			// Collision with the right border.
			if ((p.getX() + sizeX) > d.getWidth()){
				// The position is set to the screen size minus the image width.
				p.setPosX((int) (d.getWidth()- sizeX));
				// Changes the direction to avoid going off bounds.
				p.setVelX(-p.getVelX());
		}else
			// Collision with the upper border.
			if (p.getY() < 0 ){
				p.setPosY(0);
				// Changes the direction to avoid going off bounds.
				p.setVelY(-p.getVelY());
		}else 
			// Collision with the bottom border.
			if ((p.getY() + sizeY) > d.getHeight()){
				// The position is set to the screen size minus the image height.
				p.setPosY((int) (d.getHeight() - sizeY));
				// Changes the direction to avoid going off bounds.
				p.setVelY(-p.getVelY());
		}
	}
	
	/**
	 * Enemy ship's attack.
	 */
	public void attack() {
		// Adds a new Bullet to the BulletManager with the actual enemy position.
		bm.add(p);
	}
	
	/**
	 * Returns the Bullet list from the BulletManager.
	 * @return LinkedList of Bullets
	 */
	public static LinkedList<Bullet> getEnemyBullets() {
		return bm.returnManager();
	}
	
	/**
	 *  Attacks every T time, according to the timer.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		attack();		
	}

	/**
	 *  Returns a collider Rectangle with the dimensions of the enemy image.
	 */
	@Override
	// TODO Integrate with the player fire.
	public Rectangle collider() {
		return new Rectangle(p.getX(), p.getY(), enemy.getWidth(null), enemy.getHeight(null));
	}


}
