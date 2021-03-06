package characters;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.Timer;

import Effects.Damage;
import Effects.LevelUp;
import Effects.SoundEffects;
import mainGame.Game;
import managers.BulletManager;
import managers.HUD;
import managers.Position;

public class Ship extends SpaceObject implements MouseListener{

	// **** RESOURCES ****
	private String[][][] resources = {
			// Ship TYPE 1
		{
			{
			"/Sprites/Ship/Ship_1/Single_shot/sprite_ship1_singleShot.gif",
			"/Sprites/Ship/Ship_1/Single_shot/sprite_ship1_singleShot_Up.gif",
			"/Sprites/Ship/Ship_1/Single_Shot/sprite_ship1_singleShot_Down.gif",
			"/Sprites/Ship/Ship_1/Single_shot/sprite_ship1_singleShot_shoot.gif",
			},
			// Ship level 2
			{
			"/Sprites/Ship/Ship_1/Double_shot/sprite_ship1_doubleShot.gif",
			"/Sprites/Ship/Ship_1/Double_shot/sprite_ship1_doubleShot_Up.gif",
			"/Sprites/Ship/Ship_1/Double_shot/sprite_ship1_doubleShot_Down.gif",
			"/Sprites/Ship/Ship_1/Double_shot/sprite_ship1_doubleShot_shoot.gif",
			},
			// Ship level 3
			{
			"/Sprites/Ship/Ship_1/Missile/sprite_ship1_missile.gif",
			"/Sprites/Ship/Ship_1/Missile/sprite_ship1_missile_Up.gif",
			"/Sprites/Ship/Ship_1/Missile/sprite_ship1_missile_Down.gif",
			"/Sprites/Ship/Ship_1/Missile/sprite_ship1_missile_Shoot.gif",
			}
		}, 
			// Ship TYPE 2
		{
			{
			"/Sprites/Ship/Ship_2/Single_shot/sprite_ship2_singleShot.gif",
			"/Sprites/Ship/Ship_2/Single_shot/sprite_ship2_singleShot_Up.gif",
			"/Sprites/Ship/Ship_2/Single_shot/sprite_ship2_singleShot_Down.gif",
			"/Sprites/Ship/Ship_2/Single_shot/sprite_ship2_singleShot_shoot.gif",
			},
			// Ship level 2
			{
			"/Sprites/Ship/Ship_2/Double_shot/sprite_ship2_doubleShot.gif",
			"/Sprites/Ship/Ship_2/Double_shot/sprite_ship2_doubleShot_Up.gif",
			"/Sprites/Ship/Ship_2/Double_shot/sprite_ship2_doubleShot_Down.gif",
			"/Sprites/Ship/Ship_2/Double_shot/sprite_ship2_doubleShot_shoot.gif",
			},
			// Ship level 3
			{
			"/Sprites/Ship/Ship_2/Missile/sprite_ship2_missile.gif",
			"/Sprites/Ship/Ship_2/Missile/sprite_ship2_missile_Up.gif",
			"/Sprites/Ship/Ship_2/Missile/sprite_ship2_missile_Down.gif",
			"/Sprites/Ship/Ship_2/Missile/sprite_ship2_missile_Shoot.gif",
			}
		}, 
		// Ship TYPE 3
		{
			{
			"/Sprites/Ship/Ship_3/Single_shot/sprite_ship3_singleShot.gif",
			"/Sprites/Ship/Ship_3/Single_shot/sprite_ship3_singleShot_Up.gif",
			"/Sprites/Ship/Ship_3/Single_shot/sprite_ship3_singleShot_Down.gif",
			"/Sprites/Ship/Ship_3/Single_shot/sprite_ship3_singleShot_shoot.gif",
			},
			// Ship level 2
			{
			"/Sprites/Ship/Ship_3/Double_shot/sprite_ship3_doubleShot.gif",
			"/Sprites/Ship/Ship_3/Double_shot/sprite_ship3_doubleShot_Up.gif",
			"/Sprites/Ship/Ship_3/Double_shot/sprite_ship3_doubleShot_Down.gif",
			"/Sprites/Ship/Ship_3/Double_shot/sprite_ship3_doubleShot_shoot.gif",
			},
			// Ship level 3
			{
			"/Sprites/Ship/Ship_3/Missile/sprite_ship3_missile.gif",
			"/Sprites/Ship/Ship_3/Missile/sprite_ship3_missile_Up.gif",
			"/Sprites/Ship/Ship_3/Missile/sprite_ship3_missile_Down.gif",
			"/Sprites/Ship/Ship_3/Missile/sprite_ship3_missile_Shoot.gif",
			}
		} 
	};
	public int shipType = 0;
	private final int BASIC_SHIP = 0;
	private final int SHIP_UP = 1;
	private final int SHIP_DOWN = 2;
	private final int SHIP_SHOOT = 3;
	
	// Initializes the basic Ship.
	private String url = resources[shipType][0][BASIC_SHIP];
	Image ship = null; 

	
	// ***** HUD ***** 
	HUD hud = new HUD();
	int hpCounter = 8;
	
	// ***** Effects ***** 
	// control instances of shot sounds
	HashMap<String, SoundEffects> effects = new HashMap<>(); 
	Damage damage;
	LevelUp levelUp;
	
	
	// **** PLAYER STATS ****
	public String shipName;
	public int hp;
	private int level;
	public int killedEnemies;
	private int xp;
	public int shield;
	private int shieldTop;
	public int fireRate;
	public int speed;
	private int weaponNumber;
	
	
	// **** SHIP MODIFIERS ****
	private Timer FireRateTimer;
	private boolean canShoot = true;
	private Timer ShieldRegenTimer;
	private boolean shieldOff = true;
	
	public Position p = new Position(50, (Game.HEIGHT / 2));
	// New BulletManager to handle the bullets
	private static BulletManager bm = new BulletManager(BulletManager.SHIP);
	// Imports the list of Enemy bullets
	private List<Bullet> b = BasicEnemy.getEnemyBullets();

	
	// ***** SINGLETON INSTANCE *****
	private static Ship instance = new Ship();
	
	
	// --------------------------------------------------------------------------------
	
	
	// **** CONSTRUCTOR ****
	private Ship() {
		// Initializes the Ship stats.
		init();
	}
	
	/**
	 *  Initialization of the Ship values, stats, and objects.
	 */
	public void init() {
		// Initialize ship stats
		hp = 5;
		level = 1;
		killedEnemies = 0;
		shield = 0;
		fireRate = 0;
		speed = 0;
		weaponNumber = 0;
		// Loads the ship image
		setSpaceObjectImage();
		// Creates a new instance of SoundEffects.
		SoundEffects ShotSound = new SoundEffects();
		// Adds the "shot" sound to the ship SoundEffects.
		effects.put("shot", ShotSound);	
		// ship damage and LevelUp effect
		damage = new Damage();
		levelUp = new LevelUp();
	}
	
	
	// ----------------- **** METHODS **** ----------------- 
	
	// ***** SINGLETON *****
	
	/**
	 *  Returns the instance of the Singleton.
	 * @return Ship.
	 */
	public static Ship getPlayer() {
		return instance;
	}
	
	// **** GRAPHICAL METHODS ****
	/**
	 *  Loads the image as a .gif.
	 */
	@Override
	public void setSpaceObjectImage() {
		ship = Sprite.loadSprite(url, this);
	}
	
	/**
	 *  Returns the ship image.
	 *  @return Ship image.
	 */
	@Override
	public Image getSpaceObjectImage() {
		return ship;
	}
	
	
	/**
	 *  Draws the Ship, the Bullets, the HUD, the damage received, and the LevelUp animation.
	 * @param
	 */
	public void draw(Graphics g){
		// Draws the Ship image.
		g.drawImage(getSpaceObjectImage(), p.getX(), p.getY(),
				getSpaceObjectImage().getWidth(null)*2,
				getSpaceObjectImage().getHeight(null)*2,  null);
		
		// Draw method from BulletManager.
		bm.draw(g);
		
		//Draw method for the HUD.
		hud.draw(g);
		
		// Draw method for the damage and LevelUp.
		damage.draw(g);
		levelUp.draw(g);
	}
	
	
	
	// **** LOGICAL METHODS ****
	/**
	 * Updates the position and ship stats.
	 */
	@Override
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
		// Checks if the Player has leveled up.
		levelUp();
		// Modifies the HUD to fit the new stats.
		changeScoreAndXpInHUD();
	}
	
	/**
	 *  Modifies the HUD to fit the XP.
	 */
	private void changeScoreAndXpInHUD() {
		hud.setScoreAndXP(xp);
	}

	/**
	 *  Sets the values selected on the Customization menu and passes them to the HUD.
	 */
	public void initPlayer() {
		// Constructor of HUD to set a visual stats and shipname  
		hud.setHUDstats(shield , speed, fireRate, shipName);
		
		initTimers();
		if(speed == 0) {
			speed = 5;
		}
		speed *= 2.5;
	}
	
	/**
	 * Starts the timers for the player, such as FireRate timer, and the ShieldRegen.
	 */
	private void initTimers() {
		// To avoid /0 division, the fire rate is set to 1 if it is 0.
		if(fireRate == 0) fireRate = 1;
		// Initializes the FireRateTimer.
		FireRateTimer = new Timer(
				// The fire rate wait time is defined by this.
				1_000 / fireRate,
				// Creates a new ActionListener that allows the Ship to fire.
				new ActionListener() {
					@Override
					// Every time the action is performed, the ship is allowed to shoot. 
					public void actionPerformed(ActionEvent e) {
						canShoot = true;
					}
				});
		// To have a top on the shield regen, the shieldTop equals the original shield value.
		shieldTop = shield;
		// If the ship has no shield, then the timer is not initialized.
		if(shield > 0) {
			shieldOff = false;
			// Initializes the ShieldRegenTimer.
			ShieldRegenTimer = new Timer(
				// The shield regen time is given by this.
				15_000 / shield,
				// Creates a new ActionListener that regenerates the shield.
				new ActionListener() {
					@Override
					// Every time the action is performed, the shield regens.
					public void actionPerformed(ActionEvent e) {
						shieldRegen();
					}
				});
			// Starts the ShieldRegenRimer.
			ShieldRegenTimer.start();
		}
		// Starts the FireRateTimer.
		FireRateTimer.start();
		
	}
	
	/**
	 *  According to the dimensions of the screen, determines if
	 *  the ship collided with the screen borders and modifies its
	 *  position.
	 */
	@Override
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
	
	/**
	 *  Regenerates the shield of the ship. Called by the ShieldRegenTimer.
	 */
	private void shieldRegen() {
		// If the shield is lower than the top, increases the shield on one.
		if(shield < shieldTop) {
			shield++;
		}
	}

	/**
	 *  The attack method from Ship.
	 */
	public void attack() {
		// First, checks if the ship can shoot determined by the FireRateTimer.
		if(canShoot) {
			// Selects the image of the shooting ship.
			url = resources[shipType][level-1][SHIP_SHOOT];
			
			if(level == 1) {
				// Adds a bullet to the BulletManager.
				bm.add(p);
				// Play the "shot" sound
				effects.get("shot").shipShotSound(0);
			} else 
			if(level == 2) {
				// Duplicates the original position of the ship for the bullets to come out from the same x.
				Position bp = p.clone();
				for(int i = 0; i < level; i++) {
					// Adds the bullet i * 30 pixels under the ship's position.
					bp.setPosY(p.getY() + (i * 30));
					// Adds the bullet to the BulletManager.
					bm.add(bp);
					// Discards the temporal position.
					bp = null;
					// Play the "shot" sound
					effects.get("shot").shipShotSound(1);
				}
			} else
			if(level == 3) {
				bm.add(p);
				// Play the "shot" sound
				effects.get("shot").shipShotSound(2);
				
			} else
			if(level >= 4){
				// Restarts the ship type.
				shipType = 2;
				// Restarts the level.
				level = 2;
				// Changes the image to display.
				url = resources[3][3][3];
				// Adds a bullet to the BulletManager.
				bm.add(p);
				// Play the "shot" sound
				effects.get("shot").shipShotSound(3);
			}
			// Disables the canShoot flag.
			canShoot = false;
			// Restarts the timer.
			FireRateTimer.restart();
		}
	}
	
	/**
	 *  This method is called from the EnemyManager every time an enemy is 
	 *  deleted from the manager.
	 */
	public void killedEnemy() {
		// Increases the killedEnemies.
		killedEnemies++;
		// Increases the xp.
		xp += 100;
	}
	
	private void levelUp() {
		if(xp == (300 * Math.pow(level, 3))) {
			this.level++;
			
			this.hp = 8 + (level * 2); 
			
			if(hp >= 8){
				hud.change_HUD_Live(8);
			}else{
				hud.change_HUD_Live(hp);
			}
			
			// Change the weapon sprite in the HUD
			this.weaponNumber++;
			
			levelUp.start();
			
			if(weaponNumber > 2) weaponNumber = 2;
			hud.changeHUDWeapon(weaponNumber);
			
		}
	}

	/**
	 *  Creates a new collider rectangle with the ship's actual position to
	 *  determine when it gets hit.
	 *  @return Rectangle with the dimensions of the ship.
	 */
	@Override
	public Rectangle collider() {
		return new Rectangle(p.getX(), p.getY(), ship.getWidth(null), ship.getHeight(null));
	}
	
	/**
	 *  Analyzes if any of the BasicEnemy fired Bullets hit the ship and makes
	 *  the corresponding stats modifications.
	 */
	public void gotHit() {
		// For-each to analyze every Bullet object.
		synchronized(b) {for(Iterator<Bullet> a = b.iterator(); a.hasNext(); ) {
			Bullet hitter = a.next();
			// Checks if the actual Bullet's collider rectangle intersects with this'.
			if(collider().intersects(hitter.collider())) {
				// If the ship has no shield, the attack enters straight.
				if(shieldOff) {
					// Stat modifiers.
					hp -= 1;
					// To avoid problems with the display of the HUD.
					if(hp >= 8){
						// Sets the HUD life image to the highest. 
						hud.change_HUD_Live(8);
					}else if(hp >= 0){
						// Sets the HUD life image to the current hp.
						hud.change_HUD_Live(hp);
					}
				// The bullet lowers the shield.
				} else {
					shield -= 1;
				}
				// Starts the damage animation.
				damage.start();
				} // End if collider.
			} // End for.
		} // End synchronized.
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
	public synchronized List<Bullet> getShipBullets() {
		return bm.returnManager();
	}
	
	/**
	 *  Setter for the ship name to be displayed on the HUD.
	 * @param shipName
	 */
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	
	/**
	 *  Getter for the ship name displayed on the HUD.
	 * @return Ship name
	 */
	public String getShipName() {
		return this.shipName;
	}
	
	/**
	 *  Changes the type of ship (1, 2, or 3) on the Customization menu.
	 * @param shipType
	 */
	public void changeType(int shipType) {
		this.shipType = shipType;
		// Sets the Ship image according to the type. 
		url = resources[shipType][0][BASIC_SHIP];
	}
	
	/**
	 *  Getter for the ship level.
	 * @return Ship level
	 */
	public int getLevel() {
		return this.level;
	}
	
	/**
	 *  Getter for the ship score.
	 * @return Ship xp
	 */
	public int getScore() {
		return this.xp;
	}

	
	
	// **** USER KEY INTERACTION ****
	
	/**
	 * On key pressed: Up arrow key
	 */
	public void up(){
		// Changes the actual ship image
		url = resources[shipType][level-1][SHIP_UP];
		// Changes the ship speed and allows it to move diagonally.
		p.setVelY(-speed);
	}
	
	/**
	 * On key pressed: Down arrow key
	 */
	public void down() {
		// Changes the actual ship image
		url = resources[shipType][level-1][SHIP_DOWN];
		// Changes the ship speed and allows it to move diagonally.
		p.setVelY(speed);
	}
	
	/**
	 * On key pressed: Left arrow key
	 */
	public void left() {
		// Changes the actual ship image
		url = resources[shipType][level-1][BASIC_SHIP];
		// Changes the ship speed and allows it to move diagonally.
		p.setVelX(-speed);
	}
	
	/**
	 * On key pressed: Right arrow key
	 */
	public void right() {
		// Changes the actual ship image
		url = resources[shipType][level-1][BASIC_SHIP];
		// Changes the ship speed and allows it to move diagonally.
		p.setVelX(speed);
	}
	
	
	
	// **** INHERITED FROM KEYLISTENER ****
	/* These methods are NOT IMPLEMENTING the KeyListener ones.
	 * These are independent, getting called by the Game class.
	 */
	
	public void keyPressed(int code) {
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
		if(code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) {
			attack();
		}
		// If the key pressed is ESC, exits the game.
		if(code == KeyEvent.VK_ESCAPE) {
			Game.pauseMenu();
		}
		
	}

	public void keyReleased(int code) {
		// When releasing UP key, the y-velocity is set to 0.
		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W)
			p.setVelY(0);
		// When releasing DOWN key, the y-velocity is set to 0.
		if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S)
			p.setVelY(0);
		// When releasing LEFT key, the x-velocity is set to 0.
		if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A )
			p.setVelX(0);
		// When releasing RIGHT key, the x-velocity is set to 0.
		if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D)
			p.setVelX(0);
		// The ship image is set to the basic image again according to the level.
		url = resources[shipType][level-1][BASIC_SHIP];
	}


	// **** INHERITED FROM MOUSELISTENER AND MOUSEACTIONLISTENER ****
	
	/* These methods are NOT IMPLEMENTING the MouseListener and MouseActionListener ones.
	 * These are independent, getting called by the Game class.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// If the left mouse button is clicked, calls the attack method.
		if(e.getButton() == MouseEvent.BUTTON1) {
			attack();
		}
		
	}

	public void mouseReleased(MouseEvent e) {
		// When the mouse is released, the ship image goes back to the normal.
		url = resources[shipType][level-1][BASIC_SHIP];
	}	
	
	
	
	// **** UNUSED INHERITED METHODS ****
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	
}