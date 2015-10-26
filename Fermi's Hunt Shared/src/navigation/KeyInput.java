package navigation;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import characters.Ship;

public class KeyInput extends KeyAdapter {
		
		Ship p1;
		
		public KeyInput(Ship p){
			this.p1= p;
		}
		
		public void keyPressed (KeyEvent e){
			p1.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e){
			p1.keyReleased(e);
		}
		
}
