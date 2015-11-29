package managers;

import java.awt.Graphics;
import java.awt.Image;

import characters.Sprite;
import mainGame.Game;

public class HUD {
	private boolean wait;
	private Image hud;
	private Image shieldBar, speedBar, fireRateBar;
	private String url, urlShield, urlSpeed, urlFire;
	private int X, Y, Bx, By;
	public  String[] HUD_Live = {
			"/HUD/test0.png",
			"/HUD/test1.png",
			"/HUD/test2.png",
			"/HUD/test3.png",
			"/HUD/test4.png",
			"/HUD/test5.png",
			"/HUD/test6.png",
			"/HUD/test7.png",
			"/HUD/test8.png"
	};
	
	public  String[] HUD_StatsBar = {
			"/HUD/Bar0.png",
			"/HUD/Bar1.png",
			"/HUD/Bar2.png",
			"/HUD/Bar3.png",
			"/HUD/Bar4.png",
			"/HUD/Bar5.png",
			"/HUD/Bar6.png",
			"/HUD/Bar7.png",
			"/HUD/Bar8.png",
			"/HUD/Bar9.png",
			"/HUD/Bar10.png"
	};

	
	public HUD(){
		url= HUD_Live[8];
		set_HUD_Image();
		X = (Game.WIDTH /2) -700 ;
		Y = 0;
		Bx = 290;
		By = 110;
		wait = false;
	}
	
	public Image getHUDImage(){
		return hud;
	}
	public Image getShieldBarImage(){
		return shieldBar;
	}
	public Image getSpeedBarImage(){
		return speedBar;
	}
	public Image getFireRateBarImage(){
		return fireRateBar;
	}
	public void change_HUD_Live(int n){
		url = HUD_Live[n];
		set_HUD_Image();
	}
	
	public void HUD_Wait(){
		wait = true;
	}
	public void HUD_Start(){
		wait = false;
	}
	public void setHUDstats(int shield, int speed, int fire){
		change_HUD_stats(shield,speed,fire);
		set_HUD_StatsImage();
	}
	public void change_HUD_stats(int shield, int speed, int fire){
		urlShield = HUD_StatsBar[shield];
		urlSpeed = HUD_StatsBar[speed];
		urlFire = HUD_StatsBar[fire];
	}
	
	public void set_HUD_Image(){
		hud = Sprite.loadSprite(url, this);
	}
	
	public void set_HUD_StatsImage(){
		shieldBar = Sprite.loadSprite(urlShield, this);
		speedBar = Sprite.loadSprite(urlSpeed, this);
		fireRateBar = Sprite.loadSprite(urlFire, this);
	}   
	
	public void draw(Graphics g){ 
		if(wait){return;
		}else{
		g.drawImage(getHUDImage(), X, Y, null);
		/*g.drawImage(getShieldBarImage(), Bx, By, null);
		g.drawImage(getSpeedBarImage(), Bx, (By + 20), null);
		g.drawImage(getFireRateBarImage(), Bx, (By + 40),null);
		*/
		}   
	}
	
}
