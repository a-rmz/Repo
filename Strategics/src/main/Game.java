package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel{
	
	
	public static Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
}
