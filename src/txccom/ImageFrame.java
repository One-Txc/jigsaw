package txccom;
import javax.swing.*;
public class ImageFrame extends JFrame {
	public ImageFrame(){
		JComponent Ima = new NewImageComponent();
		add(Ima);
		setSize(500,500);
	}
}
