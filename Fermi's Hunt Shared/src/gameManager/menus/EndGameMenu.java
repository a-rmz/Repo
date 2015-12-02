package gameManager.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Effects.SoundEffects;
import background.Background;
import characters.Ship;
import characters.Sprite;
import gameManager.GameStateManager;
import gameManager.menus.popups.NamePopup;
import mainGame.Game;

public class EndGameMenu extends Menu {

	Background egBG;
	NamePopup np;
	Font f;
	FontMetrics fm;
	GameStateManager gsm;
	SoundEffects se;
	private Image insertCoin;
	
	private boolean cont = false;
	private boolean nameSet = false;
	private String contStr = "Press SPACE to continue";
	
	public EndGameMenu(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	@Override
	protected void select() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		np = new NamePopup(gsm, "");
		cont = false;
		nameSet = false;
		egBG = new Background(Background.ENDGAME, 0);
		insertCoin = Sprite.loadSprite("/BackgroundImg/EndGame/endgame_insertcoin.gif", this);		
		se = new SoundEffects();
		se.FXSound(6);
		se.play();
		
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
		} else {
			g.drawImage(insertCoin, 0, 0, null);
		}
	}

	
	private void end() {
		Game.hs.addScore(np.getName(), Ship.getPlayer().getScore());
	}
	
	
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_SPACE) {
			cont = true;
		} 
		if(k > 0 && nameSet) {
			System.exit(0);
		}
	}
	public void keyTyped(int k) {
		
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
