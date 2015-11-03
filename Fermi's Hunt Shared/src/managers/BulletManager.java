package managers;


import java.awt.Graphics;
import java.util.Iterator;

import characters.Bullet;
import characters.SpaceObject;

public class BulletManager extends Manager <Bullet> {

	// ***** BULLETMANAGER MODIFIERS *****
	
	private int direction;
	
	// --------------------------------------------------------------------------------
	
	
	// **** CONSTRUCTOR ****
	public BulletManager(int direction) {
		this.direction = direction;
	}
	
	/**
	 * This constructor makes it easier for the BasicEnemy BulletManager
	 * If there's no parameter, automatically is set to an EnemyBulletManager
	 */
	public BulletManager() {
		this.direction = 1;
	}
	
	
	// ----------------- **** METHODS **** ----------------- 
	
	// **** GRAPHICAL METHODS ****
	/**
	 *  Draws every Bullet.
	 * @param g
	 */
	public void draw(Graphics g) {
		// For-each loop to draw every Bullet.
		for(Bullet b : l) {
			// Calls the Bullet draw method.
			b.draw(g);
		}
	}
	
	
	
	// **** LOGICAL METHODS ****
	/**
	 *  Adds a new Bullet to the LinkedList in the Position p.
	 * @param p
	 */
	public void add(Position p) {
		// Creates a new Bullet.
		if(p != null) {
			Bullet b = new Bullet(p, direction);
			// Adds the Bullet to the LinkedList.
			l.add(b);
		}
		return;
	}

	
	/**
	 *  Updates every Bullet in the LinkedList.
	 */
	public void update() {
		// This for loop avoids the java.util.ConcurrentModificationException
		// If you use a foreach loop, you can't modify the list.
		for(Iterator<Bullet> i = l.iterator(); i.hasNext();) {
			// Sets the Bullet b to the next Bullet in the List.
			Bullet b = i.next();
			// Calls the Bullet updated method.
			b.update();
			// Checks if the Bullet has to be destroyed.
			destroy(i, b);			
		} 
	}
	
	/**
	 *  Checks if a Bullet has gone off bounds and destroys it.
	 * @param i Bullet Iterator
	 * @param b Bullet Object
	 * @return boolean true if the object is successfully destroyed.
	 */
	public boolean destroy(Iterator<Bullet> i, Bullet b) {
		// Checks if the Bullet is off bounds.
		if(b.p.getX() < -(b.getBulletImage().getWidth(null)) || 
				b.p.getX() > (SpaceObject.screenSize().getWidth())) {
			// If you use the Manager remove method, there's a Exception
			// Use this remove.
			try {
				i.remove();
			} catch (Exception e) {
				System.out.println("Bullet not removed");
			}
			// Destroys the object
			//b = null;
			return true;
		}
		// If the Bullet was not destroyed, returns false.
		return false;
	}
	

	
}
