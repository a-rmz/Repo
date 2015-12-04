package background;

import java.awt.Graphics2D;
import java.awt.Image; // mostrar imagen

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
			"/BackgroundImg/Highscore_Menu/hsm_img.png",
			"/BackgroundImg/Level_1/l1_img.png",
			"/BackgroundImg/EndGame/endgame_anim.gif"
	};
	private String url;
	public Image bg1, bg2;
	
	private int rate = -10;
	private int imgSize = 4000;
	
	
	// **** PLAYER STATS ****
	public int level = 1;
	public static final int MAIN_MENU = 0;
	public static final int PAUSE_MENU = 1;
	public static final int HELP_MENU = 2;
	public static final int CUSTOMIZE_MENU = 3;
	public static final int SCORE_MENU = 4;
	public static final int LEVEL_1 = 5;
	public static final int ENDGAME = 6;

	
	// **** SHIP MODIFIERS ****
	public Position pbg1, pbg2;
	
	
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
		// Defines the position of both backgrounds.
		pbg1 = new Position(0, 0);
		pbg2 = new Position(imgSize, 0);
	}


	/**
	 * Constructor. Receives as parameter the actual level (or enemy level) and the
	 * speed rate for the Background movement POSITIVE.
	 * @param level
	 */
	public Background(int index, int rate){
		// Sets the speed rate for the background.
		this.rate = -rate;
		// If the rate is 0, there's only going to be one static image.
		if(rate == 0) imgSize = Game.WIDTH;
		// Defines the image to be loaded.
		url = backgrounds[index];
		// Loads the image to both background images.	
		bg1 = setBackgroundImage();
		bg2 = setBackgroundImage();
		// Defines the position of both backgrounds.
		pbg1 = new Position(0, 0);
		pbg2 = new Position(imgSize, 0);
	}

	
	
	// **** LOGICAL METHODS ****
	/**
	 *  Updates the position of the bg1/bg2 images.
	 */
	public void update(){
		// Moves each background rate units at a time.
		pbg1.increasePosX(rate);
		pbg2.increasePosX(rate);
		
		// Sets the image one after another after they have gone off-screen.
		if(pbg1.getX() + imgSize <= 0){
			pbg1.setPosX(imgSize);
		}
		if(pbg2.getX() + imgSize <= 0){
			pbg2.setPosX(imgSize);
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
		// Prints background on the given position.
		g.drawImage(getBackgroundImage(), pbg1.getX(), 0, 
				imgSize + 10,
				Game.HEIGHT,
				null);
		g.drawImage(getBackgroundImage(), pbg2.getX(), 0, 
				imgSize + 10,
				Game.HEIGHT,
				null);
	}
	
}






