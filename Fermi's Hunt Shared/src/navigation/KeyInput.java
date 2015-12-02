package navigation;

import java.awt.event.KeyAdapter;

import characters.Ship;

public class KeyInput extends KeyAdapter {
		
		Ship ship;
		
		public KeyInput(Ship p){
			this.ship = p;
		}
		
		public void keyPressed (int e){
			ship.keyPressed(e);
		}
		
		public void keyReleased(int e){
			ship.keyReleased(e);
		}
		
}
