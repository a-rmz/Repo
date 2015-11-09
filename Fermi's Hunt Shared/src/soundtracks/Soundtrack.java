package soundtracks;
import java.awt.MediaTracker;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Soundtrack implements Runnable {
	
	// **** RESOURCES ****
	private String[] resources = {
			"Fyrus.mp3"
	};
	
	private String url;
	Clip sound;
	InputStream st;
	
	public Soundtrack(){
		/* // Defines the url to the soundtrack to be loaded.
		url = resources[0];
		Media hit = new Media(url);
		MediaPlayer mediaplayer = new MediaTracker(hit);*/
	}
	
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
}
