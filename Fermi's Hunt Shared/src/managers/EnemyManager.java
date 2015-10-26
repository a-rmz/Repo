package managers;

import java.awt.Graphics;

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
		for(BasicEnemy b : l) {
			// Calls the BasicEnemy update method.
			b.update();
		}
	}

}
