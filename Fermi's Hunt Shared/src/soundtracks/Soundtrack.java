package soundtracks;
import java.io.InputStream;

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
