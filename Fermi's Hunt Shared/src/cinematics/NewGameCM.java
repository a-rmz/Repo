package cinematics;

import java.awt.Graphics;
import java.awt.Image;

import Effects.SoundEffects;
import characters.Sprite;


public class NewGameCM implements Runnable {
	
	private Image text;
	public SoundEffects se, se2;
	public boolean isRunning;
	public boolean  enemyAppears;
	private String url;
	private int X, Y, i;
	int rr = 0;
	int r = 0;
	Thread t;
	
	String[] resources= {
		"/newGameCM/Text1.png", //0
		"/newGameCM/Text2.png",//1
		"/newGameCM/Text3.png",	//2
		"/newGameCM/TextK4.png",//3
		"/newGameCM/TextK5.png",//4
		"/newGameCM/TextK6.png",//5
		"/newGameCM/HostileRed.png", // 6
		"/newGameCM/HostileNone.png",//7
		"/newGameCM/Fire.png", // 8
		
	};
	
	public NewGameCM(){
		t = new Thread(this);
		X= 300;
		Y= 670;
		i= 0;
		isRunning = true;
		enemyAppears = false;
		se = new SoundEffects();
		se2 = new SoundEffects();
		se.backGroundMenuMusic(0);
		se2.backGroundMusic(1);
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
			case 2:
			case 3:
				se.play();
				i++;
				update();
				try {
					
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				break;
			case 4:
			case 5:
				
				i++;
				
				try {
					
					Thread.sleep(2500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				enemyAppears = true;
				se.close();
				se2.play();
				update();
				break;
			
			case 6:
			case 7:
				
				
				if( r % 2 == 0){ i = 6;}
				else{i = 7;}
				r++;
				
				try {
					
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(r==7) i = 8;
				update();
				break;
			
			case 8:
				X = 300;
				Y = 400;
				
		
			
				try {
					
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update();
				i++;

				break;
			default:
				update();
				
				return;
			
			} // end Switch
			
		
			
		}
		
	}

}
