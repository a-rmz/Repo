package Effects;

import java.awt.Graphics;
import java.awt.Image;

import characters.Sprite;
import mainGame.Game;


public class LevelUp implements Runnable {
	
	// String of relative direction 
	public String[] levelUpImage = {
			"/newGameCM/LevelUP.png"
		};
		
		// Attributes of LevelUp
		private SoundEffects se;
		private Image levelUP;
		private Thread thread;
		private boolean print, listen;
		private String url;
		
		/**
		 *  The constructor creates instances of soundEffect, thread and sets initial attributes.
		 */
		public LevelUp(){
			
			se = new SoundEffects();
			// Sets a specific sound to Damage effect
			se.FXSound(4);
			
			thread = new Thread(this);
			// The Thread is constantly listening 
			listen = true;
			// Loads the levelup image
			setLevelUpImage();
			thread.start();
		}
		
		/**
		 *  When the player levelUp, this method is called 
		 */
		public void start(){
			print = true;
			// PLay the Damage sound effect
			se.playAgain();
		}
		
		/**
		 *  This method draw a green level up string in all the screen
		 * @param g
		 */
		public void draw(Graphics g){
			if(print)
				g.drawImage(getLevelUpImage(), (Game.WIDTH / 2 ) - 700 , (Game.HEIGHT / 2) - 200, null);
		}
		
		/**
		 *  This Method is only to re-call the method "draw" automatically 
		 */
		public void update(){
			
		}
		
		/**
		 * Sets the image from the URL defined by the resources array
		 */
		public void setLevelUpImage(){
			url = levelUpImage[0];
			levelUP = Sprite.loadSprite(url, this);
		}
		
		/**
		 *  Gets the explosion image loaded.
		 * @return levelup image
		 */
		public Image getLevelUpImage(){
			return levelUP;
		}

		/**
		 *  This method controls the time of the "levelUp" effect on screen
		 */
		public void run() {
			
			while(listen){
			update();
			
			try {
				
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			print = false;
			
			}
		}

}
