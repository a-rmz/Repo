package managers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import characters.BasicEnemy;
import characters.Ship;
import Effects.Explosion;
import Effects.SoundEffects;

public class EnemyManager extends Manager<BasicEnemy> implements Runnable {
	
	// NO LOCAL INSTANCES
	
	// --------------------------------------------------------------------------------
	
	//******* Attributes for effects********
	ArrayList<Explosion> BUUM = new ArrayList<Explosion>();
	SoundEffects se = new SoundEffects();
	
	private Thread thread;
	private boolean isRunning;
	
	private int enemiesOnScreen;
	private int maxEnemies;
	private int totalEnemies;
	private int maxLevel;
	private int level;
	
	// **** CONSTRUCTOR ****
	public EnemyManager(int level, int maxEnemies, int number) {
		this.maxEnemies = maxEnemies;
		maxLevel = number;
		this.level = level;
		enemiesOnScreen = 0;
		// Fills the LinkedList with number Enemies.
		for(int i = 0; i < maxEnemies; i++) {
			// Creates a new Enemy.
			BasicEnemy e = new BasicEnemy(level);
			// Adds the new enemy to the LinkedList.
			add(e);
			enemiesOnScreen++;
		}
		isRunning=true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	
	// ----------------- **** METHODS **** ----------------- 

	// **** GRAPHICAL METHODS ****
	/**
	 *  Uses the BasicEnemy draw method to print all the BasicEnemy on the LinkedList.
	 * @param g
	 */
	public void draw(Graphics g) {
		
		for(int x =0 ; x< BUUM.size(); x++){
			Explosion e = BUUM.get(x);
			e.draw(g);
			
		}
		
		
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
		for(int i = enemiesOnScreen; i < maxEnemies && totalEnemies < maxLevel; i++) {
			add(new BasicEnemy(level));
			enemiesOnScreen++;
		}
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
		if(be.getHP() <= 0) {
			be.dead = true;
			Explosion e = new Explosion(be.p.getX(),be.p.getY());
			BUUM.add(e);
			se.ExplosionSound(0);
			
		
			// Adds +1 to the killedEnemies record of the Player
			Ship.getPlayer().killedEnemy();
			// Sets the position of the former BasicEnemy to null.
		
			be.p = null;
			// Removes the BasicEnemy.
			i.remove();
			// Destroys the object.
		
			be = null;
	
			enemiesOnScreen--;
			return true;
		}
		// If the Enemy was not destroyed, returns false.
		return false;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(isRunning){
		for(int x=0; x<BUUM.size() ; x++){
			try {
				Thread.sleep(550);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			BUUM.remove(x);
		}
	}
		
	}

}
