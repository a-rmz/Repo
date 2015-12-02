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
	public boolean enemyAppears;
	public boolean levelUp;
	
	private String url;
	private int X, Y, i, r;
	
	Thread t;
	
	String[] resources= {
		"/newGameCM/Text1.png", //0
		"/newGameCM/Text2.png",//1
		"/newGameCM/Text3.png",	//2
		"/newGameCM/animation1.gif",//3
		"/newGameCM/animation2.gif",//4
		"/newGameCM/animation3.gif",//5
		"/newGameCM/animation4.gif",//6
		"/newGameCM/HostileRed.png", // 7
		"/newGameCM/Danger.png",//8
		"/newGameCM/Fire.png", // 9
		
	};

	
	public NewGameCM(){
		t = new Thread(this);
		X= 0;
		Y= (Game.HEIGHT) - 350 ;
		r = 0;
		i= 0;
		isRunning = enemyAppears  = false;
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
		return isRunning;
	}
	public void draw(Graphics g){
		if(i<=9)
		g.drawImage(getActualImage(), X, Y, null);
	}
	
	public void update(){
		if(i == 10 ) {
			return;
		}
		
		url = resources[i];
		
		setActualImage();
	}
	
	public Image getActualImage(){
		return text;
	}
	
	public void setActualImage(){
		text = Sprite.loadSprite(url, this);
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
					
					Thread.sleep(1000); //7
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			case 2:
				
				try {
					
					Thread.sleep(1000); // 2
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
					
					Thread.sleep(1000); // 12
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			case 4:
				update();
				try {
					
					Thread.sleep(1000); // 10
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				i++;
				break;
			case 5:
				
				update();
				try {
					
					Thread.sleep(1000); // 11
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				se.close();
				se2.play();
				
				i++;
				break;
			
			case 6:
				update();
				
				enemyAppears = true;
		
				
				try {
					
					Thread.sleep(1000); // 5
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			case 7:
			case 8:
				se5.close();
				se4.play();
			
				if( (r % 2) == 0){ 
					i = 7; 
				}else{
					i = 8; 
				}
			
				
				
				if(i == 8) {
					X=0; Y = 0;
				}else{ 
					X = (Game.WIDTH / 2 ) - 700  ;  Y = (Game.HEIGHT / 2) - 200 ;
				}
				
				
				update();
				if(r==7) i = 9;
				try {
					
					Thread.sleep(500); // .5
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				r++;
				
				break;
			
			case 9:
				X = (Game.WIDTH / 2 ) - 700;
				Y =  (Game.HEIGHT / 2) - 200 ;
		
				update();
				try {
					
					Thread.sleep(2000); // 2
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				i++;

				break;
			case 10:
				
				try {
					
					Thread.sleep(10000); // 2
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				isRunning = false;
				break;
				
			default:
				
				update();
				
				try {
					
					Thread.sleep(2000); // 2
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return;
			
			} // end Switch
			
			
			
		}
		
		
	}

}
