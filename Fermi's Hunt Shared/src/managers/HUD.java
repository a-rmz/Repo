package managers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import characters.Sprite;
import mainGame.Game;

public class HUD {
	
	private Font f = new Font("8BIT WONDER Nominal", Font.PLAIN, 15);
	private boolean wait;
	private Image hud, hudDown, hudWeapon;
	private Image shieldBar, speedBar, fireRateBar;
	private String url, urlHudDown, urlWeapon, urlShield, urlSpeed, urlFire, Sscore;
	private int X, Y, bX, bY, wX, wY, sX, sY,  score;

	
	public  String[] HUD_Live = {
			"/HUD/test0.png",
			"/HUD/test1.png",
			"/HUD/test2.png",
			"/HUD/test3.png",
			"/HUD/test4.png",
			"/HUD/test5.png",
			"/HUD/test6.png",
			"/HUD/test7.png",
			"/HUD/test8.png",
			"/HUD/HUD_down.png"
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
	
	public String[] HUD_Weapon = {
			"/HUD/SimpleShot.png",
			"/HUD/DoubleShot.png",
			"/HUD/RocketLauncher.png"
	};
	
	String[] levelUpResource = {
			"/newGameCM/LevelUp.png"
	};

	
	public HUD(){
		
		url= HUD_Live[8];
		urlHudDown = HUD_Live[9];
		urlWeapon = HUD_Weapon[0];
		score = 0;
		
		
		set_HUD_Image();
		setHUD_downImage();
		setHUDWeapon();
		ScoreAndXPToString();
		
		X = (Game.WIDTH /2) -700 ;
		Y = 0;
		bX = ((Game.WIDTH / 2) - 700) + 33;
		bY = (Game.HEIGHT - 200) + 70;
		wX =((Game.WIDTH / 2) - 700) + 1027;
		wY = (Game.HEIGHT - 200) + 44;
		sX = ((Game.WIDTH / 2) - 700) + 483;
		sY = (Game.HEIGHT - 200) + 65;
		
		
		wait = false;
	}
	
	public Image getHUDImage(){
		return hud;
	}
	public Image getHUDDownImage(){
		return hudDown;
	}
	
	public Image getHUDWeapon(){
		return hudWeapon;
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
	
	public void changeHUDWeapon(int n){
		urlWeapon = HUD_Weapon[n];	
		setHUDWeapon();
	}
	
	public void HUD_Wait(){
		wait = true;
	}
	public void HUD_Start(){
		wait = false;
	}
	public void setHUDstats(int shield, int speed, int fire){
		
		if (speed > 10) speed = 10;
		if (shield > 10) shield = 10;
		if (fire> 10) fire = 10;
		
		
		urlShield = HUD_StatsBar[shield];
		urlSpeed = HUD_StatsBar[speed];
		urlFire = HUD_StatsBar[fire];
		set_HUD_StatsImage();
	}
	
	
	public void set_HUD_Image(){
		hud = Sprite.loadSprite(url, this);
	}
	public void setHUD_downImage(){
		hudDown = Sprite.loadSprite(urlHudDown, this);
	}
	
	public void setScoreAndXP(int score){
		this.score = score;
		ScoreAndXPToString();
		
	}
	
	public void ScoreAndXPToString(){
		Sscore = "" + score;
		
	}
	
	public void setHUDWeapon(){
		hudWeapon = Sprite.loadSprite(urlWeapon, this);
	}
	
	public void set_HUD_StatsImage(){
		shieldBar = Sprite.loadSprite(urlShield, this);
		speedBar = Sprite.loadSprite(urlSpeed, this);
		fireRateBar = Sprite.loadSprite(urlFire, this);
	}   
	
	public void drawDamage(){
		
	}
	
	public void draw(Graphics g){ 
		g.setColor(Color.WHITE);
		g.setFont(f);
		if(wait){return;
		}else{
		g.drawImage(getHUDImage(), X, Y, null);
		g.drawImage(getHUDDownImage(), (Game.WIDTH / 2) - 700, (Game.HEIGHT - 200), null);
		g.drawImage(getHUDWeapon(), wX, wY, null);
		g.drawString(Sscore, sX, sY);
		g.drawImage(getShieldBarImage(), bX, bY, null);
		g.drawImage(getSpeedBarImage(), bX, (bY + 50), null);
		g.drawImage(getFireRateBarImage(), bX, (bY + 100),null);
		
		}   
	}
	
}
