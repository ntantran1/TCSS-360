/*
 * Team Lions Project
 * spring 2020
 */
package info;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Instruction page
 *
 */
public class InstrPage {
	
	public InstrPage() throws IOException {
		var frame = new JFrame();		
		frame.setLayout(new BorderLayout());
		JPanel panel1 = new JPanel();
		
		panel1.setBackground(new Color(173, 255, 47));
		panel1.setSize(600,400);
		JLabel headerLabel = new JLabel("<html><h1>Instructions for this App</h1><br>");
		JLabel paragraph = new JLabel("<html> <h4>--Login with user and pass: for now the username is: oh pass: no </h4><br> "
				+ "<h4>--It will take you to the dashboard page if the user and pass are correct.<br>"
				+ "<h4>--In the dashboard page, you can create/delete/update/read the applicance information.</h4> <br>"
				+ "<h4>--You can also wriete notes, tags, dates or anyother informations regarding the homeappliances.</h4> <br>"
				+ "<h4>--You can also upload an pdf file, image file to keep informations about the appliances. </h4>-<br>"
				+ "--------Team Lions---------");
		panel1.add(headerLabel);
		JPanel panel2 = new JPanel();
		panel2.setSize(600,400);
		frame.add(panel2);
		panel2.setBackground(new Color(173, 255, 47));
		panel2.add(paragraph);
		
		frame.getContentPane().add(panel1, BorderLayout.NORTH);
		frame.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		frame.setVisible(true);
		frame.setBounds(100, 100, 607, 369);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Instruction Page");
		frame.setVisible(true);
	}

}
