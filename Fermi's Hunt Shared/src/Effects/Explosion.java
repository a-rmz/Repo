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
	
	// The constructor set the gif to explosion effect and the position
	public Explosion(int x, int y){
		url= resources[0];
		setExplosionImage();
		
		// the x an y position are the same that enemy position 
		X = x;
		Y = y;
	}
	
	
	public void setExplosionImage(){
		try {
			// Gets the image from the url defined by the resources array
			explosion = Sprite.loadSprite(url, this);
		} catch (Exception e) {
			explosion = null;
		}
		
	}
	
	public Image getExplosionImage(){
		return this.explosion;
	}
	
	// This method draw the explosion 
	public void draw(Graphics g){
		g.drawImage(getExplosionImage(), X, Y, null);
	}


	

}
