package Effects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Explosion  {
	
	private Image explosion = null;
	private String url;
	private int X , Y ;

	
	private String[] resources = {
			"/Sprites/Effects/Explosion.gif"
	};
	
	public Explosion(int x, int y){
		url= resources[0];
		setExplosionImage();
		X = x;
		Y = y;
	}
	
	
	public void setExplosionImage(){
		try {
			// Gets the image from the url defined by the resources array
			explosion = Toolkit.getDefaultToolkit().getImage(getClass().getResource(url));
		} catch (Exception e) {
			explosion = null;
		}
		
	}
	
	public Image getExplosionImage(){
		return this.explosion;
	}
	
	public void draw(Graphics g){
		g.drawImage(getExplosionImage(), X, Y, null);
	}


	

}
