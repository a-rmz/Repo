package managers;

import java.awt.Graphics;
import java.util.Iterator;

import characters.BasicEnemy;
public class EnemyManager extends Manager<BasicEnemy>{
	
	// NO LOCAL INSTANCES
	
	// --------------------------------------------------------------------------------
	
	
	// **** CONSTRUCTOR ****
	public EnemyManager(int level, int number) {
		// Fills the LinkedList with number Enemies.
		for(int i = 0; i < number; i++) {
			// Creates a new Enemy.
			BasicEnemy e = new BasicEnemy(level);
			// Adds the new enemy to the LinkedList.
			add(e);
		}
	}
	
	
	// ----------------- **** METHODS **** ----------------- 

	// **** GRAPHICAL METHODS ****
	/**
	 *  Uses the BasicEnemy draw method to print all the BasicEnemy on the LinkedList.
	 * @param g
	 */
	public void draw(Graphics g) {
		// For-each loop to draw the BasicEnemy.
		for(BasicEnemy b : l) {
			// Calls the BasicEnemy draw method.
			b.draw(g);
		}
	}

	
	
	// **** LOGICAL METHODS ****
	/**
	 *  Uses the BasicEnemy update method to update every BasicEnemy on the LinkedList.
	 */
	public void update() {
		// For-each loop to update the BasicEnemy.
		for(Iterator<BasicEnemy> i = l.iterator(); i.hasNext();) {
			// Sets the Bullet b to the next Bullet in the List.
			BasicEnemy be = i.next();
			// Calls the Bullet updated method.
			be.update();
			// Checks if the Bullet has to be destroyed.
			destroy(i, be);
		}
	}
	
	/**
	 *  Checks if a Bullet has gone off bounds and destroys it.
	 * @param i Bullet Iterator
	 * @param b Bullet Object
	 * @return boolean true if the object is successfully destroyed.
	 */
	public boolean destroy(Iterator<BasicEnemy> i, BasicEnemy be) {
		// Checks if the Enemy is hit.
		if(be.hit) {
			// Sets the position of the former BasicEnemy to null.
			be.p = null;
			// Removes the BasicEnemy.
			i.remove();
			// Destroys the object.
			be = null;
			return true;
		}
		// If the Bullet was not destroyed, returns false.
		return false;
	}

}
