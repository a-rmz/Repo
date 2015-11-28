package cinematics;

import java.awt.Graphics;
import java.awt.Image;

import Effects.SoundEffects;
import characters.Sprite;
import mainGame.Game;


public class NewGameCM implements Runnable {
	
	private Image text;
	public SoundEffects se, se2, se3, se4, se5;
	public boolean isRunning;
	public boolean  enemyAppears;
	private String url;
	private int X, Y, i, r;
	
	Thread t;
	
	String[] resources= {
		"/newGameCM/Text1.png", //0
		"/newGameCM/Text2.png",//1
		"/newGameCM/Text3.png",	//2
		"/newGameCM/Text4.png",//3
		"/newGameCM/Text5.png",//4
		"/newGameCM/Text6.png",//5
		"/newGameCM/HostileRed.png", // 6
		"/newGameCM/Danger.png",//7
		"/newGameCM/Fire.png", // 8
		
	};
	
	public NewGameCM(){
		t = new Thread(this);
		X= 0;
		Y= (Game.HEIGHT) - 350 ;
		r = 0;
		i= 0;
		isRunning = enemyAppears = false;
		se = new SoundEffects();
		se2 = new SoundEffects();
		se3 = new SoundEffects();
		se4 = new SoundEffects();
		se5 = new SoundEffects();
		se.backGroundMenuMusic(0);
		se2.backGroundMusic(1);
		se3.FXSound(2);
		se4.FXSound(0);
		se5.FXSound(1);
		
		
	}
	
	public void startAnimation(){
		isRunning = true;
		t.start();	
	}

	public boolean endCinematic(){
		return isRunning = false;
	}
	public void draw(Graphics g){
		g.drawImage(getActualImage(), X, Y, null);
	}
	
	public void update(){
		if(i == 9 ) {isRunning = false; return;}
		
		url = resources[i];
		
		setActualImage();
	}
	
	public Image getActualImage(){
		return text;
	}
	
	public void setActualImage(){
		text = Sprite.loadSprite(url, this);
	}
	
	public void changeFrame(){
		
	}
	
	public void initCinematic(){
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	
		while(isRunning){
			
	
			switch (i){
			case 0:
			case 1:
				se3.play();
				se.play();
				update();
				try {
					
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			case 2:
				
				try {
					
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update();
				i++;
				break;
			case 3:
				se5.play();
				update();
				
				
				try {
					
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			case 4:
				update();
				try {
					
					Thread.sleep(4500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				i++;
				break;
			case 5:
				enemyAppears = true;
				update();
				try {
					
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				se.close();
				se2.play();
				
				i++;
				break;
			
			case 6:
			case 7:
			
				se5.close();
				se4.play();
				
				if( (r % 2) == 0){ 
					i = 6; 
				}else{
					i = 7; 
				}
			
				
				
				if(i == 7) {
					X=0; Y = 0;
				}else{ 
					X = (Game.WIDTH / 2 ) - 700  ;  Y = (Game.HEIGHT / 2) - 200 ;
				}
				
				
				update();
				if(r==7) i = 8;
				try {
					
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				r++;
				
				break;
			
			case 8:
				X = (Game.WIDTH / 2 ) - 700;
				Y =  (Game.HEIGHT / 2) - 200 ;
		
				update();
				try {
					
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				i++;

				break;
			default:
				update();
				
				return;
			
			} // end Switch
			
		
			
		}
		
	}

}
