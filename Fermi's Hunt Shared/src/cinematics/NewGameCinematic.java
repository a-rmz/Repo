package cinematics;

import java.awt.Component; 
import java.net.URL; 
import javax.media.Manager; 
import javax.media.Player; 
import javax.swing.JPanel;

public class NewGameCinematic extends JPanel {

	private static final long serialVersionUID = 1L;
	String[] url = {
			"/Sounds/two_planets.mpg"
	}; 
	private URL Url = getClass().getResource(url[0]);
	
	public NewGameCinematic(){
	
		
		 
		 Manager.setHint( Manager.LIGHTWEIGHT_RENDERER, true );
		 
		 try{
			 
			Player rMedios = Manager.createRealizedPlayer(Url);
			Component video = rMedios.getVisualComponent();
		
			
			if ( video != null)
				add(video);
			 	
				
			rMedios.start();
			
			 
		 } catch (Exception e ){
			 
		 }
		 
		
	}
}