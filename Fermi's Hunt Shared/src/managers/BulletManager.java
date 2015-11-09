package managers;


import java.awt.Graphics;
import java.util.Iterator;

import characters.BasicEnemy;
import characters.Bullet;
import characters.Ship;
import characters.SpaceObject;

public class BulletManager extends Manager <Bullet> {

	// ***** BULLETMANAGER MODIFIERS *****
	
	private int direction;
	
	// --------------------------------------------------------------------------------
	
	
	// **** CONSTRUCTOR ****
	public BulletManager(SpaceObject s) {
		if(s instanceof Ship) {
			direction = -1;
		}
		if(s instanceof BasicEnemy) {
			direction = 1;
		}
	}

	
	
	// ----------------- **** METHODS **** ----------------- 
	
	// **** GRAPHICAL METHODS ****
	/**
	 *  Draws every Bullet.
	 * @param g
	 */
	public void draw(Graphics g) {
		
		synchronized (l) {for(Iterator<Bullet> b = l.iterator(); b.hasNext();) {
			Bullet bDraw = b.next();
			bDraw.draw(g);
			}
		}
	}
	
	
	
	// **** LOGICAL METHODS ****
	/**
	 *  Adds a new Bullet to the LinkedList in the Position p.
	 * @param p
	 */
	public void add(Position p) {
		// When the position is null, is because the BasicEnemy was destroyed
		if(p != null) {
			// Creates a new Bullet.
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
		synchronized(l) { for(Iterator<Bullet> i = l.iterator(); i.hasNext();) {
			// Sets the Bullet b to the next Bullet in the List.
			Bullet b = i.next();
			// Calls the Bullet updated method.
			b.update();
			// Checks if the Bullet has to be destroyed.
			destroy(i, b);			
			}// End for 
		}// End synchronized
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
