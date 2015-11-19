package background;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image; // mostrar imagen
import java.awt.Toolkit;

import characters.Sprite;
import mainGame.Game;
import managers.Position;


public class Background {

	// **** RESOURCES ****
	private String[] backgrounds = {
			"/BackgroundImg/Main_Menu/mm_img.png",
			"/BackgroundImg/Pause_Menu/pm_img.png",
			"/BackgroundImg/Help_Menu/hm_img.png",
			"/BackgroundImg/Customize_Menu/cm_img.png",
			"/BackgroundImg/Level_1/l1_img.png"
	};
	private String url;
	public Image bg1, bg2;
	
	
	// **** PLAYER STATS ****
	public int level = 1;
	public static final int MAIN_MENU = 0;
	public static final int PAUSE_MENU = 1;
	public static final int HELP_MENU = 2;
	public static final int CUSTOMIZE_MENU = 3;
	public static final int LEVEL_1= 4;

	
	// **** SHIP MODIFIERS ****
	// Since this class doesn't extends SpaceObject, you need another Dimension
	// to get the screen size.
	public Dimension sSize = new Dimension(
			(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
			(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	public Position pbg1 = new Position(0, 0);
	public Position pbg2 = new Position(sSize.getWidth(), 0);
	
	
	// --------------------------------------------------------------------------------
	
	
	// **** CONSTRUCTOR ****
	/**
	 * Constructor. Receives as parameter the actual level (or enemy level).
	 * For further level assistance, consult level manager. 
	 * @param level
	 */
	public Background(int index){
		// Defines the image to be loaded.
		url = backgrounds[index];
		// Loads the image to both background images.
		bg1 = setBackgroundImage();
		bg2 = setBackgroundImage();
	}

	
	
	// **** LOGICAL METHODS ****
	/**
	 *  Updates the position of the bg1/bg2 images.
	 */
	public void update(){
		// Moves each background -1 unit at a time.
		pbg1.increasePosX(-10);
		pbg2.increasePosX(-10);
		
		if(pbg1.getX() == (-sSize.getWidth())){
			pbg1.setPosX(sSize.getWidth());
		}
		if(pbg2.getX() == (-sSize.getWidth())){
			pbg2.setPosX(sSize.getWidth());
		}
	
	}
	
	/**
	 * Exception case. Different from all other setImage methods.
	 * This DOES return the image to make easier the assign to
	 * various Background elements.
	 */
	public Image setBackgroundImage() {
		return Sprite.loadSprite(url, this);
	}
	
	/**
	 * Only returns the image, the logic is done by update.
	 * @return
	 */
	public Image getBackgroundImage() {
		return bg1;
	}
	
	public void draw(Graphics2D g) {
		// Prints background
		g.drawImage(getBackgroundImage(), pbg1.getX(), 0, 
				Game.WIDTH, Game.HEIGHT, null);
		g.drawImage(getBackgroundImage(), pbg2.getX(), 0, 
				Game.WIDTH, Game.HEIGHT, null);
	}
	
}






