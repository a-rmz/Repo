package characters;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public abstract class SpaceObject {
	
	// **** SPACEOBJECT STATS ****
	// All SpaceObjects must inherit the modifier.
	public int level;
	
	// ----------------- **** METHODS **** ----------------- 
	
	// **** GRAPHICAL METHODS ****
	
	public abstract void setSpaceObjectImage();
	public abstract Image getSpaceObjectImage();
	
	
	
	// **** LOGICAL METHODS ****
	public abstract void update();
	public abstract void collidesWithBorders(Dimension d);
	public abstract Rectangle collider();
	
	
	
	// **** CONCRETE METHODS ****
	/**
	 * Gets the screen size.
	 * @return Dimension with screen size
	 */
	public static Dimension screenSize() {
		// Gets the Screen dimensions.
		int xMax = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		int yMax = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		// Creates a new Dimension object with the screen size.
		Dimension d = new Dimension(xMax, yMax);
		return d;
	}
	

}
