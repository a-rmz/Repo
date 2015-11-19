package gameManager.menus.popups;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import characters.Ship;
import gameManager.GameStateManager;

public class NamePopup implements KeyListener, MouseListener, MouseMotionListener{
	
	private String name;
	private String buffer;
	private char charBuffer;
	Keyboard keyboard;
	private Rectangle textFrame;
	private Rectangle popupFrame;
	
	
	private boolean isVisible = false;
	
	public NamePopup(GameStateManager gsm, String name) {
		this.name = name;
		keyboard = new Keyboard();
		init();
		gsm.game.addKeyListener(this);
		gsm.game.addMouseListener(this);
		gsm.game.addMouseMotionListener(this);
	}	
	
	private void init() {
		name = "";
		popupFrame = new Rectangle(200, 170, 1440, 300);
		textFrame = new Rectangle(275, 245, 1290, 150);
	}
	
	public void drawNamePopup(Graphics2D g) {
		keyboard.draw(g);
		drawNameFrames(g);
		drawName(g);
	}
	
	private void drawNameFrames(Graphics2D g) {
		g.setStroke(new BasicStroke(6));
		g.setColor(new Color(85, 30, 0, 210));
		g.fill(popupFrame);
		g.setColor(Color.WHITE);
		g.draw(popupFrame);
		
		g.setStroke(new BasicStroke(4));
		g.setColor(new Color(50, 20, 0, 200));
		g.fill(textFrame);
		g.setColor(Color.WHITE);
		g.draw(textFrame);
	}
	
	private void drawName(Graphics2D g) {
		Font f = new Font("8-Bit Madness", Font.PLAIN, 120);
		FontMetrics nFm = g.getFontMetrics(f);
		
		g.setFont(f);
		g.setColor(Color.WHITE);
		
		g.drawString(name, 
				(int) textFrame.getCenterX() - (nFm.stringWidth(name)/2),
				(int) textFrame.getCenterY() + (nFm.getHeight()/2) - 10);
	}
	
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	public boolean visible() {
		return isVisible;
	}
	private void finish() {
		Ship.getPlayer().setShipName(name);
		setVisible(false);
	}


	public boolean mouseOverKey(MouseEvent e) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 10; j++) {
				if(keyboard.getKeyShape(i, j).contains(e.getPoint())) {
					keyboard.usedKey(i, j);
					buffer = "" + keyboard.getKeyValue(i, j);
					return true;
				}
			}
		}
		return false;
	}
	private boolean mouseOverSpecialKey(MouseEvent e) {
		for(int i = 0; i < 3; i++) {
			if(keyboard.getSpecialKeyShape(i).contains(e.getPoint())) {
				keyboard.usedKey(i, -1);
				charBuffer = keyboard.getSpecialKeyValue(i);
				return true;
			}
		}
		return false;
	}
	private void specialKeyAction() {
		if(name.length() > 15) {
			if(charBuffer == KeyEvent.VK_ENTER) {
				finish();
			} else if(charBuffer == KeyEvent.VK_BACK_SPACE) {
				if(name.length() <= 0) return;
				String newName = name.substring(0, name.length() - 1);
				name = newName;
			}
			else return;
		}
		if(charBuffer == KeyEvent.VK_SPACE) {
			name += " ";
		} else
		if(charBuffer == KeyEvent.VK_BACK_SPACE) {
			if(name.length() <= 0) return;
			String newName = name.substring(0, name.length() - 1);
			name = newName;
		} else 
		if(charBuffer == KeyEvent.VK_ENTER && name.length() > 0) {
			finish();
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if(mouseOverKey(e)) {
			// Magic
		} else
		if(mouseOverSpecialKey(e)) {
			// More magic
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(mouseOverKey(e)) {
			if(name.length() > 15) return;
			name += buffer;
			buffer = "";
		} else
		if(mouseOverSpecialKey(e)) {
			specialKeyAction();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(name.length() > 15) {
			// If the name length is bigger than 15, only accepts ENTER 
			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
				finish();
			} else if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
				if(name.length() <= 0) return;
				String newName = name.substring(0, name.length() - 1);
				name = newName;
			}
			else {
				return;
			}
		}
		if(e.getKeyChar() >= KeyEvent.VK_A && e.getKeyChar() <= KeyEvent.VK_Z) {
			name += e.getKeyChar();
		} else if(e.getKeyChar() >= 97 && e.getKeyChar() <= 122) {
			buffer = "" + e.getKeyChar();
			buffer = buffer.toUpperCase();
			name += buffer;
		} else if(e.getKeyChar() >= KeyEvent.VK_0 && e.getKeyChar() <= KeyEvent.VK_9) {
			name += e.getKeyChar();
		} else if(e.getKeyChar() == KeyEvent.VK_SPACE) {
			name += " ";
		} else if(e.getKeyChar() == KeyEvent.VK_PERIOD) {
			name += ".";
		} else if(e.getKeyChar() == KeyEvent.VK_EXCLAMATION_MARK) {
			name += "!";
		} else if(e.getKeyChar() == KeyEvent.VK_MINUS) {
			name += "-";
			// Backspace
		} else if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
			if(name.length() <= 0) return;
			String newName = name.substring(0, name.length() - 1);
			name = newName;
		} else if(e.getKeyChar() == KeyEvent.VK_ENTER) {
			if(name.length() > 0) finish();
			else return;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	// Unused
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
