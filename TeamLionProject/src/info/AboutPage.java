/*
 * Tcss 360 project 
 * Spring 2020
 * Team Lions
 */
package info;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.*;

public class AboutPage {
	String[] developers;
	String version;
	
	public AboutPage() throws IOException {
		Scanner input = new Scanner(Paths.get("TeamLionProject/files/ProjectInfo.txt"));
		developers = new String[4];
		getInfo(input);
	}
	
	private void getInfo(Scanner input){
		version = input.nextLine();
		int index = 0;
		while(input.hasNextLine()) {
			developers[index] = input.nextLine();
			index++;
		}
	}
	
	public void makePage() {
		var frame = new JFrame();		
		frame.setLayout(new BorderLayout());
		JLabel versionText = new JLabel(version);
		JLabel devs = new JLabel("<html>Developers:<br>");
		devs.setForeground(Color.WHITE);
		for(String s: developers) {	
			devs.setText(devs.getText() + s + "<br>");
		}
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		panel1.setBackground(new Color(173, 255, 47));
		panel2.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));

		panel2.add(devs);
				
		panel1.add(versionText);
		frame.getContentPane().add(panel1, BorderLayout.NORTH);
		frame.getContentPane().add(panel2, BorderLayout.CENTER);
		frame.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		frame.setVisible(true);
		frame.setSize(200, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("About Page");
	}
	
	
}
