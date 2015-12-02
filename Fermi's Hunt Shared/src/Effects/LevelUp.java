package Effects;

import java.awt.Graphics;
import java.awt.Image;

import characters.Sprite;
import mainGame.Game;


public class LevelUp implements Runnable {
	
	public String[] levelUpImage = {
			"/newGameCM/LevelUp.png"
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
			setLevelUpImage();
			thread.start();
		}
		
		public void start(){
			print = true;
			se.playAgain();
		}
		
		public void draw(Graphics g){
			if(print)
				g.drawImage(getLevelUpImage(), (Game.WIDTH / 2 ) - 700 , (Game.HEIGHT / 2) - 200, null);
		}
		
		public void update(){
			
		}
		
		public void setLevelUpImage(){
			url = levelUpImage[0];
			levelUP = Sprite.loadSprite(url, this);
		}
		
		public Image getLevelUpImage(){
			return levelUP;
		}

		@Override
		public void run() {
			
			while(listen){
			update();
			
			try {
				
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			print = false;
			
			}
		}

}
