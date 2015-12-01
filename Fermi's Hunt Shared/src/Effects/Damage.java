package Effects;

import java.awt.Graphics;
import java.awt.Image;

import characters.Sprite;

public class Damage implements Runnable {
	
	public String[] damageImage = {
		"/newGameCM/Danger.png"
	};
	
	private SoundEffects se;
	private Image damage;
	private Thread thread;
	private boolean print, listen;
	private String url;
	
	public Damage(){
		se = new SoundEffects();
		se.FXSound(0);
		thread = new Thread(this);
		listen = true;
		setDamegeImage();
		thread.start();
	}
	
	public void start(){
		print = true;
		se.playAgain();
	}
	
	public void draw(Graphics g){
		if(print)
			g.drawImage(getDamageImage(), 0, 0, null);
	}
	
	public void update(){
		
	}
	
	public void setDamegeImage(){
		url = damageImage[0];
		damage = Sprite.loadSprite(url, this);
	}
	
	public Image getDamageImage(){
		return damage;
	}

	@Override
	public void run() {
		
		while(listen){
		update();
		
		try {
			
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		print = false;
		
		}
	}

}
