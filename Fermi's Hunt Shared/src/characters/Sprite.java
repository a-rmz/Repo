package characters;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * Toolkit for sprites.
 * Methods such as loadSprite.
 * @author Alex
 *
 */
public class Sprite {
	
	/**
	 *  Loads the sprite to the indicated image.
	 * @param url -> of the image
	 * @param o -> object calling the method
	 * @return Sprite image
	 */
	public static Image loadSprite(String url, Object o) {
		Image img;
		try {
			// Gets the image from the URL given.
			img = Toolkit.getDefaultToolkit().getImage(o.getClass().getResource(url));
		} catch (Exception e) {
			System.out.println("Failed to load sprite: " + url);
			e.printStackTrace();
			// The image is set to null.
			img = null;
		}
		return img;
	}

}
