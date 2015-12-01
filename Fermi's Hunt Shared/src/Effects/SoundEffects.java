package Effects;

import javax.sound.sampled.*;

public class SoundEffects {
	
	public Clip clip;
	
	
	public  String[] shipShotSound = {
			"/Sounds/ShipShot1.mp3",
			"/Sounds/ShipShot3.mp3"
	};
	
	public  String[] enemyShotSound ={
			"/Sounds/ShipShot2.mp3"
	};
	
	public  String[] explosion ={
			"/Sounds/ExplosionSound.mp3"
	};
	
	public  String[] bossBattleTheme = {
			"/Sounds/Fyrus.mp3"
	};
	
	public  String[] backGroundMusic ={
			"/Sounds/sectorZ.mp3",
			"/Sounds/warning.mp3"
	};
	public  String[] backGroundMenuMusic ={
			"/Sounds/MenuMusic.mp3",
			"/Sounds/sectorZ.mp3"
	};
	public  String[] EffectsSounds ={
			"/Sounds/Alarm.mp3",
			"/Sounds/Control_tower_radio.mp3",
			"/Sounds/Keyboard.mp3",
			"/Sounds/hurt.mp3",
			"/Sounds/levelUp.mp3"
	};
	
	public SoundEffects(){
		
	}
public void FXSound(int n){
	
		
		try{
			
			AudioInputStream ais =
					AudioSystem.getAudioInputStream(
						getClass().getResourceAsStream(EffectsSounds[n])	
					);
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(),
					16,
					baseFormat.getChannels(),
					baseFormat.getChannels() * 2,
					baseFormat.getSampleRate(),
					false
					);
			AudioInputStream dais = AudioSystem.getAudioInputStream(
					decodeFormat, ais);
			
			clip = AudioSystem.getClip();
			clip.open(dais);													
					
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void shipShotSound(int n){
	
		
		try{
			
			AudioInputStream ais =
					AudioSystem.getAudioInputStream(
						getClass().getResourceAsStream(shipShotSound[n])	
					);
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(),
					16,
					baseFormat.getChannels(),
					baseFormat.getChannels() * 2,
					baseFormat.getSampleRate(),
					false
					);
			AudioInputStream dais = AudioSystem.getAudioInputStream(
					decodeFormat, ais);
			
			clip = AudioSystem.getClip();
			clip.open(dais);
			clip.start();
				
					
					
					
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void enemyShotSound(int n){
	
		
		try{
			
			AudioInputStream ais =
					AudioSystem.getAudioInputStream(
						getClass().getResourceAsStream(enemyShotSound[n])	
					);
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(),
					16,
					baseFormat.getChannels(),
					baseFormat.getChannels() * 2,
					baseFormat.getSampleRate(),
					false
					);
			AudioInputStream dais = AudioSystem.getAudioInputStream(
					decodeFormat, ais);
			
			clip = AudioSystem.getClip();
			clip.open(dais);
			clip.start();
				
					
					
					
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void ExplosionSound(int n){
	
		
		try{
			
			AudioInputStream ais =
					AudioSystem.getAudioInputStream(
						getClass().getResourceAsStream(explosion[0])	
					);
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(),
					16,
					baseFormat.getChannels(),
					baseFormat.getChannels() * 2,
					baseFormat.getSampleRate(),
					false
					);
			AudioInputStream dais = AudioSystem.getAudioInputStream(
					decodeFormat, ais);
			
			clip = AudioSystem.getClip();
			clip.open(dais);
			clip.start();
				
					
					
					
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void backGroundMusic(int n){
	
		
		try{
			
			AudioInputStream ais =
					AudioSystem.getAudioInputStream(
						getClass().getResourceAsStream(backGroundMusic[n])	
					);
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(),
					16,
					baseFormat.getChannels(),
					baseFormat.getChannels() * 2,
					baseFormat.getSampleRate(),
					false
					);
			AudioInputStream dais = AudioSystem.getAudioInputStream(
					decodeFormat, ais);
			
			clip = AudioSystem.getClip();
			clip.open(dais);
			//clip.start();
				
					
					
					
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void backGroundMenuMusic(int n){
	
	
	try{
		
		AudioInputStream ais =
				AudioSystem.getAudioInputStream(
					getClass().getResourceAsStream(backGroundMenuMusic[n])	
				);
		AudioFormat baseFormat = ais.getFormat();
		AudioFormat decodeFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				baseFormat.getSampleRate(),
				16,
				baseFormat.getChannels(),
				baseFormat.getChannels() * 2,
				baseFormat.getSampleRate(),
				false
				);
		AudioInputStream dais = AudioSystem.getAudioInputStream(
				decodeFormat, ais);
		
		clip = AudioSystem.getClip();
		clip.open(dais);
		//clip.start();
			
				
				
				
	} catch(Exception e){
		e.printStackTrace();
	}
	
}
	
	public void play(){
		if (clip == null || clip.isRunning() ) {return;}
		clip.setFramePosition(0);
		clip.start();
		
	}
	public void playAgain(){
		if (clip == null) {return;}
		clip.setFramePosition(0);
		clip.start();
		
	}

	public void stop() {
		try{
		clip.stop();
		} catch( Exception e){
			e.printStackTrace();
		}
	}
	
	public void close(){
		stop();
		clip.close();
	}
	public void pause(){
		try {
			clip.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
