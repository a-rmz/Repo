package mainGame;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cinematics.NewGameCinematic;

 public class MainClass {

	 public static void main(String[] args) {
		Game g = new Game();	
		JFrame frame = new JFrame();
		
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setContentPane(g);
		frame.setVisible(true);
		
	
		
}
	 
	 
	 
}
