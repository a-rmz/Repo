package Effects;

import java.awt.Graphics;
import java.awt.Image;

import characters.Sprite;

public class Explosion  {
	
	// Attributes of the explosion 
	private Image explosion = null;
	private String url;
	private int X , Y ;

	// String of the relative direction 
	private String[] resources = {
			"/Sprites/Effects/Explosion.gif"
	};
	
	/**
	 *  The constructor sets the GIF to explosion effect and the position
	 * @param x position
	 * @param y position
	 */
	public Explosion(int x, int y){
		url= resources[0];
		setExplosionImage();
		
		// the x an y position are the same than enemy's position 
		X = x;
		Y = y;
	}
	
	
	/**
	 * Sets the image from the URL defined by the resources array
	 */
	public void setExplosionImage(){
		explosion = Sprite.loadSprite(url, this);
	}
	
	/**
	 *  Gets the explosion image loaded.
	 * @return explosion image
	 */
	public Image getExplosionImage(){
		return this.explosion;
	}
	
	// This method draw the explosion 
	public void draw(Graphics g){
		g.drawImage(getExplosionImage(), X, Y, null);
	}

}
