package txccom;

import java.awt.*;

import javax.swing.*;

public class ImageTest {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new ImageFrame();
				frame.setTitle("TTxXXxCC");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setSize(510, 510);
				frame.repaint();
			}
		});
	}
}
