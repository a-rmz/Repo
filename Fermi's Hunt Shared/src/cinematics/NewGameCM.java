package cinematics;

import java.awt.Graphics;
import java.awt.Image;

import Effects.SoundEffects;
import characters.Sprite;
import mainGame.Game;


public class NewGameCM implements Runnable {
	
	// Attributes of newGameCinematic
	private Image text;
	public SoundEffects se, se2, se3, se4, se5;
	public boolean isRunning;
	public boolean enemyAppears;
	public boolean levelUp;
	
	private String url;
	
	// Variables to position and counters 
	private int X, Y, i, r;
	// A thread that control the time of the cinematic
	Thread t;
	
	// ******* Resources *********** 
	String[] resources = {
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

	/*
	 * The constructor of NewGameCinematic create instances 
	 * of the thread and soundEffects and set the cinematic position 
	 */
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
		// Assigns a sound to each instance of sound effects 
		se.backGroundMenuMusic(0);
		se2.backGroundMusic(1);
		se3.FXSound(2);
		se4.FXSound(0);
		se5.FXSound(1);
	}
	
	public void startAnimation(){
		// Start the cinematic 
		isRunning = true;
		t.start();	
	}

	public boolean endCinematic(){
		// Ask if the cinematic still running
		return isRunning;
	}
	
	// This method draw the cinematic 
	public void draw(Graphics g){
		if(i<=9)
		g.drawImage(getActualImage(), X, Y, null);
	}
	
	// This method refresh the status of the cinematic 
	public void update(){
		if(i == 10 ) {
			return;
		}
		
		url = resources[i];
		
		setActualImage();
		// This method call the method "draw" at the end
	}
	
	public Image getActualImage(){
		return text;
	}
	
	public void setActualImage(){
		text = Sprite.loadSprite(url, this);
	}


	/*
	 * This method is controlled by the thread, 
	 * the thread determines the time on the screen of each image in the cinematic
	 */
	public void run() {
	
	
		while(isRunning){
			
			switch (i){
			case 0:
				// Start the sounds and update the cinematic
				se3.play();
				se.play();
				update();
				
				// The thread wait 14 seconds before to change to other case in the "switch"
				try {
					
					Thread.sleep(14000); //7
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
				// increase "i", "i" is the position of resources[], resources[] contains the relative directions of the images
				i++;
				break;
				
				/*
				 * It�s the same actions for each  "switch case"
				 */
				
			case 1:
			
				update();
				try {
					
					Thread.sleep(7000); //7
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
				i++;
				break;
			case 2:
				
				try {
					
					Thread.sleep(2000); // 2
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
				update();
				i++;
				break;
			case 3:
				se5.play();
				update();
				
				
				try {
					
					Thread.sleep(12000); // 12
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
				i++;
				break;
			case 4:
				update();
				try {
					
					Thread.sleep(10000); // 10
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			
				i++;
				break;
			case 5:
				
				update();
				try {
					
					Thread.sleep(11000); // 11
				} catch (InterruptedException e) {
				
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
					
					Thread.sleep(5000); // 5
				} catch (InterruptedException e) {
				
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
					
					e.printStackTrace();
				}
				
				i++;

				break;
			case 10:
				
				try {
					
					Thread.sleep(10000); // 2
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
				isRunning = false;
				break;
				
			default:
				
				update();
				
				try {
					
					Thread.sleep(2000); // 2
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
				
				
				return;
			
			} // end Switch
			
			
			
		}
		
		
	}

}
