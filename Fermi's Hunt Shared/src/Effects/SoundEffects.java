package Effects;

import javax.sound.sampled.*;

public class SoundEffects {
	// Clip is the object that can play a mp3 file
	public Clip clip;
	
	// ********* Resources ***********
	public  String[] shipShotSound = {
			"/Sounds/ShipShot1.mp3",
			"/Sounds/DoubleShot.mp3",
			"/Sounds/rocket.mp3"
	};
	
	public  String[] enemyShotSound ={
			"/Sounds/ShipShot2.mp3"
	};
	
	public  String[] explosion ={
			"/Sounds/ExplosionSound.mp3"
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
			"/Sounds/control_tower_radio.mp3",
			"/Sounds/Keyboard.mp3",
			"/Sounds/hurt.mp3",
			"/Sounds/levelUp.mp3",
			"/Sounds/BgMusic.mp3",
			"/Sounds/EndGame.mp3",
			"/Sounds/Fyrus.mp3"
	};
	
	/**
	 * Empty constructor.
	 */
	public SoundEffects(){
		
	}
	
	/**
	 *  Loads a sound effect.
	 * @param n
	 */
	public void FXSound(int n){
		
		try{
			// All of this Try catch is only for set the mp3 file in the clip object 
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
			// Open the clip
			clip = AudioSystem.getClip();
			clip.open(dais);													
			// now the clip can be played	
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Loads a ship shot sound effect.
	 * @param n
	 */
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
	
	/**
	 * Loads an enemy shot sound effect.
	 * @param n
	 */
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
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Loads the explosion sound effect.
	 * @param n
	 */
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

	/**
	 * Loads the background music for the Game States.
	 * @param n
	 */
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
					
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Loads the music for the backgrounds.
	 * @param n
	 */
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
	
	} catch(Exception e){
		e.printStackTrace();
	}
	
}
	
	/**
	 *  This method plays the mp3 file, but it doesn't play the file again if the file is running.
	 */
	public void play(){
		if (clip == null || clip.isRunning() ) {return;}
		clip.setFramePosition(0);
		clip.start();
	}
	
	/**
	 * This method plays the mp3 file, but it can play the file again if the file is running.
	 */
	public void playAgain(){
		if (clip == null) {return;}
		clip.setFramePosition(0);
		clip.start();
		
	}
	
	/**
	 * This method stops the clip
	 */
	public void stop() {
		try{
			clip.stop();
		} catch( Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method closes the clip, so then it can be deleted.
	 */
	public void close(){
		clip.close();
	}

	/**
	 * This method pauses the clip.
	 */
	public void pause(){
		try {
			clip.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
