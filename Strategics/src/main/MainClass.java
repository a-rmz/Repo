package main;

import javax.swing.JFrame;

public class MainClass {

	public static void main(String[] args) {
		JFrame gameWindow = new JFrame("Strategics 0.1");
		
		gameWindow.add(new Game());
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setPreferredSize(Game.getScreenSize());
		gameWindow.pack();
		gameWindow.setVisible(true);
	}
	
}
