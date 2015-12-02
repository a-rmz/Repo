package gameManager.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import background.Background;
import characters.Ship;
import gameManager.GameStateManager;
import gameManager.menus.popups.NamePopup;
import mainGame.Game;

@SuppressWarnings("serial")
public class EndGameMenu extends Menu {

	Background egBG;
	NamePopup np;
	Font f;
	FontMetrics fm;
	GameStateManager gsm;
	
	private boolean cont = false;
	private boolean nameSet = false;
	private String contStr = "Press ENTER to continue";
	
	public EndGameMenu(GameStateManager gsm) {
		this.gsm = gsm;
		egBG = new Background(Background.ENDGAME, 0);
	}
	
	@Override
	protected void select() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		cont = false;
		nameSet = false;
		np = new NamePopup(gsm, "");
	}

	@Override
	public void update() {
		if(np.ended() && !nameSet) {
			System.out.println(np.getName());
			nameSet = true;
			end();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		egBG.draw(g);
		g.setColor(Color.WHITE);
		f = new Font("8-Bit Madness", Font.PLAIN, 70);
		g.setFont(f);
		fm = g.getFontMetrics(f);
		if(!cont) {
			g.drawString(contStr,
					Game.WIDTH/2 - fm.stringWidth(contStr)/2,
					Game.HEIGHT - 250);
		} else if(!nameSet) {
			np.drawNamePopup(g);
		}
	}

	
	private void end() {
		Game.hs.addScore(np.getName(), Ship.getPlayer().getScore());
		gsm.restart();
	}
	
	
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			cont = true;
		}
	}

	@Override
	public void keyReleased(int k) {
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
