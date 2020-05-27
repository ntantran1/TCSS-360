package info;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HomePage {
	private static final JFrame frame = new JFrame("Home Page");
	private AboutPage about;
	JButton aboutButton = new JButton("About Info");
	JPanel panel = new JPanel();
	public HomePage() throws IOException{
		about = new AboutPage();
		panel.add(aboutButton);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(panel);		
        frame.setVisible(true);
        addListener();
	}
	
	 public void addListener() { 
	        aboutButton.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(final ActionEvent theE) {
	                about.makePage();
	            }
	                
	        });
	} 



}
 