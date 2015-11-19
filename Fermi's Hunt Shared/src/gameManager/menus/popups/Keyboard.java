package gameManager.menus.popups;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class Keyboard {
	
	private class Key {
		public char keyVal;
		public boolean use;
		protected Rectangle keyForm;
		
		public Key(char keyVal) {
			this.keyVal = keyVal;
			this.use = false;
		}
		public Key(int i) {
			keyVal = (char) i;
			this.use = false;
		}

		public char getKeyVal() {
			return keyVal;
		}
		public boolean used() {
			return use;
		}
		public void using() {
			use = true;
		}
		public void unused() {
			use = false;
		}
		public void setForm(Rectangle keyForm) {
			this.keyForm = keyForm;
		}
		public Rectangle getForm() {
			return keyForm;
		}
		
		public void drawKey(Graphics2D g, int x, int y) {
			String kVal = "" + keyVal;
			Font ft = new Font("8-Bit Madness", Font.PLAIN, 80);
			if(used()) {
				g.setColor(Color.RED);
			} else { 
				g.setColor(Color.WHITE);
			}
			g.setFont(ft);
			FontMetrics kfm = g.getFontMetrics(ft);
			g.setStroke(new BasicStroke(4));
			g.draw(keyForm);
			g.drawString(kVal, 
					(int) (keyForm.getCenterX() - kfm.stringWidth(kVal)/2), 
					(int) (keyForm.getY() + (5 * kfm.getHeight() / 4))
							);
		}
	}
	
	private class SpecialKey extends Key {
		private String kName;
		
		public SpecialKey(char keyVal, String name) {
			super(keyVal);
			this.kName = name;
		}
		public SpecialKey(int keyVal, String name) {
			super(keyVal);
			this.kName = name;
		}
		
		public void drawKey(Graphics2D g, int x, int y) {
			Font ft = new Font("8-Bit Madness", Font.PLAIN, 60);
			if(used()) {
				g.setColor(Color.RED);
			} else { 
				g.setColor(Color.WHITE);
			}
			g.setFont(ft);
			FontMetrics kfm = g.getFontMetrics(ft);
			g.setStroke(new BasicStroke(4));
			g.draw(keyForm);
			g.drawString(kName, 
					(int) (keyForm.getCenterX() - kfm.stringWidth(kName)/2), 
					(int) (keyForm.getY() + (3 * kfm.getHeight() / 2))
							);
		}
	}
	

	private Key[][] keyboard;
	private SpecialKey[] specialKeys;
	private Key previousUsed;
	// Image
	private BufferedImage image;
	private Graphics2D g;
	
	public Keyboard() {
		init();
	}
	
	private void init() {
		initKeyboard();
		
	}
	
	private void initKeyboard() {
		keyboard = new Key[4][10];
		specialKeys = new SpecialKey[3];
		for(int i = 0; i < 10; i++) {
			keyboard[0][i] = new Key('0' + i);
		}
		for(int i = 0; i < 10; i++) {
			keyboard[1][i] = new Key('A' + i);
		}
		for(int i = 0; i < 10; i++) {
			keyboard[2][i] = new Key('K' + i);
	}
		for(int i = 0; i < 7; i++) {
			keyboard[3][i] = new Key('T' + i);
		}
		keyboard[3][7] = new Key('-');
		keyboard[3][8] = new Key('!');
		keyboard[3][9] = new Key('.');
		keyboard[0][0].using();
		previousUsed = keyboard[0][0];
		
		int x = 230, y = 500;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 10; j++) {
				keyboard[i][j].setForm(
					new Rectangle(x + (j * 120), y + (i * 120), 100, 100));
			}
		}
		
		specialKeys[0] = new SpecialKey(' ', "Space");
		specialKeys[1] = new SpecialKey(8, "<-"); // Backspace
		specialKeys[2] = new SpecialKey(13, "ENTER"); // Enter
		
		for(int i = 0; i < 3; i++) {
			specialKeys[i].setForm(new Rectangle(x + 1210, y + 10 + (170 * i), 175, 100));
		}
	}

	public Rectangle getKeyShape(int i, int j) {
		return keyboard[i][j].getForm();
	}
	public char getKeyValue(int i, int j) {
		return keyboard[i][j].getKeyVal();
	}
	public Rectangle getSpecialKeyShape(int i) {
		return specialKeys[i].getForm();
	}
	public char getSpecialKeyValue(int i) {
		return specialKeys[i].getKeyVal();
	}
	public void usedKey(int i, int j) {
		previousUsed.unused();
		if(j == -1) {
			specialKeys[i].using();
			previousUsed = specialKeys[i];
		} else {
			keyboard[i][j].using();
			previousUsed = keyboard[i][j];
		}
	}
	
	public void drawKeyboard(Graphics2D g, int x, int y) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 10; j++) {
				keyboard[i][j].drawKey(g, x + (j * 120), y + (i * 120));
			}
		}
		for(int i = 0; i < 3; i++) {
			specialKeys[i].drawKey(g, x + 1210, y + 10 + (170 * i));
		}
		
	}
	
	public void draw(Graphics2D g){
		int x = 200, y = 470;
		g.setStroke(new BasicStroke(6));
		g.setColor(new Color(85, 30, 0, 200));
		g.fillRect(x, y, 1440, 520);
		g.setColor(Color.WHITE);
		g.drawRect(x, y, 1440, 520);
		drawKeyboard(g, x + 30, y + 30);
	}
	
	
}
