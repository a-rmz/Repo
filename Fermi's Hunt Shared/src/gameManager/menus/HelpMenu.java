package gameManager.menus;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import background.Background;
import characters.Sprite;

@SuppressWarnings("serial")
public class HelpMenu extends Menu {
	// Graphics
		Background hBG;
		private Image hlp;

		private static HelpMenu instance = new HelpMenu();
		
		private HelpMenu() {
			init();
		}
		@Override
		public void init() {
			hBG = new Background(Background.HELP_MENU);	
			hlp = Sprite.loadSprite("/BackgroundImg/Help_Menu/hlp.png", this);
		}
		
		public static HelpMenu menu() {
			return instance;
		}

		@Override
		public void update() {
			hBG.update();
		}

		@Override
		public void draw(Graphics2D g) {
			hBG.draw(g);
			g.drawImage(hlp, 300, 300, null);
		}

		@Override
		public void keyPressed(int k) {
			if(k == KeyEvent.VK_ESCAPE) {
				helpMenu = false;
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
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
}
