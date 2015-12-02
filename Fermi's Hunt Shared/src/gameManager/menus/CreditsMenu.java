package gameManager.menus;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import characters.Sprite;

@SuppressWarnings("serial")
public class CreditsMenu extends Menu {
	// Graphics
		private Image cAnim;

		private static CreditsMenu instance = new CreditsMenu();
		
		private CreditsMenu() {
			init();
		}
		@Override
		public void init() {
			cAnim = Sprite.loadSprite("/BackgroundImg/Credits_Menu/credits.gif", this);
		}
	
		public static CreditsMenu menu() {
			return instance;
		}

		@Override
		public void update() {
		}

		@Override
		public void draw(Graphics2D g) {
			g.drawImage(cAnim, 0, 0, null);
		}

		@Override
		public void keyPressed(int k) {
			if(k == KeyEvent.VK_ESCAPE) {
				creditsMenu = false;
			}
		}

		@Override
		public void keyReleased(int k) {
			// TODO Auto-generated method stub
			
		}
		@Override
		protected void select() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
}
