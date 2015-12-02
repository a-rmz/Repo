package Effects;

import java.awt.Graphics;
import java.awt.Image;

import characters.Sprite;
import mainGame.Game;

public class LevelUp implements Runnable {
	
	public String[] levelUpImage = {
			"/newGameCM/LevelUP.png"
		};
		
		private SoundEffects se;
		private Image levelUP;
		private Thread thread;
		private boolean print, listen;
		private String url;
		
		public LevelUp(){
			se = new SoundEffects();
			se.FXSound(4);
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
				g.drawImage(getDamageImage(), (Game.WIDTH / 2 ) - 700 , (Game.HEIGHT / 2) - 200, null);
		}
		
		public void update(){
			
		}
		
		public void setDamegeImage(){
			url = levelUpImage[0];
			levelUP = Sprite.loadSprite(url, this);
		}
		
		public Image getDamageImage(){
			return levelUP;
		}

		@Override
		public void run() {
			
			while(listen){
			update();
			
			try {
				
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			print = false;
			
			}
		}

}