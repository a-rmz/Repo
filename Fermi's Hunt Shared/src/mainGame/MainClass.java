package mainGame;

import java.awt.Frame;

import javax.swing.JFrame;

 public class MainClass {

	 public static void main(String[] args) {
		Game g = new Game();		
		JFrame frame = new JFrame();

		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.add(g);
		frame.setVisible(true);
}
	 
	 
	 
}
