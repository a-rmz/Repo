package gameManager.menus.popups;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Keyboard {
	
	/**
	 * Private inner class for Keyboard.
	 * @author alex
	 *
	 */
	private class Key {
		public char keyVal;
		public boolean use;
		protected Rectangle keyForm;
		
		/**
		 * Adds a key by its char value.
		 * @param keyVal
		 */
		public Key(char keyVal) {
			this.keyVal = keyVal;
			this.use = false;
		}
		/**
		 * Adds a key by the int representation of its char value.
		 * @param i
		 */
		public Key(int i) {
			keyVal = (char) i;
			this.use = false;
		}

		/**
		 * Returns the char value of the key.
		 * @return value of the key
		 */
		public char getKeyVal() {
			return keyVal;
		}
		/**
		 * Returns if the key is being used.
		 * @return
		 */
		public boolean used() {
			return use;
		}
		/**
		 * Sets the key to using.
		 */
		public void using() {
			use = true;
		}
		/**
		 * Sets the key to not using.
		 */
		public void unused() {
			use = false;
		}
		/**
		 * Sets the rectangle of the key with the given one.
		 * @param keyForm
		 */
		public void setForm(Rectangle keyForm) {
			this.keyForm = keyForm;
		}
		/**
		 * Returns the form of the rectangle.
		 * @return rectangle with the key shape
		 */
		public Rectangle getForm() {
			return keyForm;
		}
		
		/**
		 * Draws the key on the given coordinates.
		 * @param g
		 * @param x
		 * @param y
		 */
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

	/**
	 * Private inner class for Keyboard. For keys with non-character direct representation: ENTER, SPACE, BACKSPACE.
	 * @author alex
	 *
	 */
	private class SpecialKey extends Key {
		private String kName;
		
		/**
		 * Calls the Super constructor with the char value of the key.
		 * @param keyVal
		 * @param name
		 */
		public SpecialKey(char keyVal, String name) {
			super(keyVal);
			this.kName = name;
		}
		/**
		 * Calls the Super constructor with the int representation of the char value of the key.
		 * @param keyVal
		 * @param name
		 */
		public SpecialKey(int keyVal, String name) {
			super(keyVal);
			this.kName = name;
		}
		
		/**
		 * Draws the special key.
		 */
		@Override
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
	
	/**
	 * Initializes the keyboard.
	 */
	public Keyboard() {
		initKeyboard();
	}
	
	/**
	 * Sets the keys, and their shape rectangles.
	 */
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

	/**
	 * Returns the shape Rectangle of the given key.
	 * @param i
	 * @param j
	 * @return
	 */
	public Rectangle getKeyShape(int i, int j) {
		return keyboard[i][j].getForm();
	}
	/**
	 * Returns the char value of the given key.
	 * @param i
	 * @param j
	 * @return
	 */
	public char getKeyValue(int i, int j) {
		return keyboard[i][j].getKeyVal();
	}
	/**
	 * Returns the shape Rectangle of the given special key.
	 * @param i
	 * @return
	 */
	public Rectangle getSpecialKeyShape(int i) {
		return specialKeys[i].getForm();
	}
	/**
	 * Returns the char value of the given special key.
	 * @param i
	 * @return
	 */
	public char getSpecialKeyValue(int i) {
		return specialKeys[i].getKeyVal();
	}
	/**
	 * Changes the used key to the new one.
	 * @param i
	 * @param j
	 */
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
	
	/**
	 * Draws the keys on the given coordinates.
	 * @param g
	 * @param x
	 * @param y
	 */
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
	
	/**
	 * Draws the whole keyboard, including the keys.
	 * @param g
	 */
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
