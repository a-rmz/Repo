package Effects;

import java.awt.Graphics;
import java.awt.Image;

import characters.Sprite;

public class Damage implements Runnable {
	
	// String of relative direction 
	public String[] damageImage = {
		"/newGameCM/Danger.png"
	};
	
	// Attributes of Damage
	private SoundEffects se;
	private Image damage;
	private Thread thread;
	private boolean print, listen;
	private String url;
	
	// The constructor create instances of soundEffect, thread and set initial attributes
	public Damage(){
		
		se = new SoundEffects();
		// Set a specific sound to Damage effect
		se.FXSound(3);
		
		thread = new Thread(this);
		// The Thread is constantly listening 
		listen = true;
		
		setDamegeImage();
		// Start the thread
		thread.start();
	}
	
	// When the player get damage, this method is called 
	public void start(){
		print = true;
		// PLay the Damage sound effect
		se.playAgain();
	}
	
	// This method draw a red image in all the screen
	public void draw(Graphics g){
		if(print)
			g.drawImage(getDamageImage(), 0, 0, null);
	}
	
	// This Method is only to re-call the method "draw" automatically 
	public void update(){
		
	}
	
	public void setDamegeImage(){
		url = damageImage[0];
		damage = Sprite.loadSprite(url, this);
	}
	
	public Image getDamageImage(){
		return damage;
	}

	// This method control the time of the Damage effect on screen
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
