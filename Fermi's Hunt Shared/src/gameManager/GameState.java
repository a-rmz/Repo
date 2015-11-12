package gameManager;

import java.awt.event.MouseEvent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class GameState extends JPanel{

	protected GameStateManager gsm;
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	public abstract void mouseMoved(MouseEvent e);
	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	public abstract void mouseClicked(MouseEvent e);
}
