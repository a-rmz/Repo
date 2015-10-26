package soundtracks;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Soundtrack {
	
	// **** RESOURCES ****
	private String[] resources = {
			"C:/Users/Alex/Documents/ITESO/POO/Fermi's Hunt/resources/Corneria.wav"
	};
	private String url;
	Clip sound;
	InputStream st;
	
	public Soundtrack(){
		// Defines the url to the soundtrack to be loaded.
		url = resources[0];
		setSoundtrack();
	}
	
	
	public void setSoundtrack() {
		try {
			sound = AudioSystem.getClip();
			st = new FileInputStream(url);
		} catch (Exception e) {
			
		}
	}
	
	public void playSoundtrack(){
		try {
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(st);
			sound.open(inputStream);
		} catch(Exception e) {
			
		}
		// Plays the soundtrack.
		sound.start();
	}
	
	public void stopSoundtrack(){
		sound.stop();
	}
	
	
}
