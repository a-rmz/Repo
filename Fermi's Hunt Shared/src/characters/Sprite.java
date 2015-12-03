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
	 * @param url
	 * @param o
	 * @return Sprite image
	 */
	public static Image loadSprite(String url, Object o) {
		Image img;
		try {
			// Gets the image from the url defined by the resources array
			img = Toolkit.getDefaultToolkit().getImage(o.getClass().getResource(url));
		} catch (Exception e) {
			System.out.println("Failed to load sprite: " + url);
			e.printStackTrace();
			System.out.print("Message: "); e.getMessage();
			img = null;
		}
		return img;
	}

}
