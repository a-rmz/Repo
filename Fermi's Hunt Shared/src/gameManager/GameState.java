package gameManager;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class GameState extends JPanel{

	protected GameStateManager gsm;
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	public abstract void mouseClicked(int k);
	
}
