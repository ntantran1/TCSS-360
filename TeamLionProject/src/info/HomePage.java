package info;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class HomePage {
	private static final JFrame frame = new JFrame("Home Page");
	private AboutPage about;
	final JButton b = new JButton();
	public HomePage() throws IOException{	
		about = new AboutPage();
		frame.pack();
        frame.setVisible(true);
	}


}
 