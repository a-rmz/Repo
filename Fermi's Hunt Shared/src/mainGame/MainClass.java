package mainGame;

import java.awt.Frame;
import javax.swing.JFrame;

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
