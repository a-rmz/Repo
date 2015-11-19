package characters;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public abstract class SpaceObject {
	
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
	public static Dimension screenSize() {
		int xMax = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		int yMax = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		Dimension d = new Dimension(xMax, yMax);
		return d;
	}
	

}
