package background;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image; // mostrar imagen
import java.awt.Toolkit;

import mainGame.Game;
import managers.Position;

// TODO Tranquilo shiquito, yo te cubro
public class Background {

	// **** RESOURCES ****
	private String[] backgrounds = {
			"/BackgroundImg/Level 1/fondo8Bits.png"
	};
	private String url;
	public Image bg1, bg2;
	
	
	// **** PLAYER STATS ****
	public int level = 1;

	
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
	public Background(int level){
		// Defines the image to be loaded.
		url = backgrounds[(level - 1)];
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
		pbg1.increasePosX(-1);
		pbg2.increasePosX(-1);
		
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
		// Creates a temporal image to load the background.
		Image i = null;
		try {
			// Gets the image from the url defined by the resources array
			i = Toolkit.getDefaultToolkit().getImage(getClass().getResource(url));
		} catch (Exception e) {

			System.out.println("Aqui pasa algo");
			i = null;
		}
		System.out.println();
		return i;
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
				(int) Game.screenSize().getWidth(), 
				(int) Game.screenSize().getHeight(), null);
		g.drawImage(getBackgroundImage(), pbg2.getX(), 0, 
				(int) Game.screenSize().getWidth(), 
				(int) Game.screenSize().getHeight(), null);
	}
	
}






