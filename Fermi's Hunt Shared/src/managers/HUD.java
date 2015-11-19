package managers;

import java.awt.Graphics;
import java.awt.Image;

import characters.Sprite;

public class HUD {
	
	private Image hud;
	private String url;
	private int X, Y;
	public  String[] HUDResources = {
			"/HUD/HUD8bits.png",
			"/HUD/HUD8bits2.png"
			
		};

	
	public HUD(){
		url= HUDResources[1];
		set_HUD_Image();
		X = 0;
		Y = 0;
	}
	
	public Image getHUDImage(){
		return hud;
	}
	public void change_HUD_Values(int n){
		url = HUDResources[n];
		set_HUD_Image();
	}
	
	public void set_HUD_Image(){
		hud = Sprite.loadSprite(url, this);
	}
	
	public void draw(Graphics g){
		g.drawImage(getHUDImage(), X, Y, null);
	}
	 
}
