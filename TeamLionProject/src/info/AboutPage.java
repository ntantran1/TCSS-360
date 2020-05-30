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
	String file;
	
	public AboutPage() throws IOException {
		File file = new File("TeamLionProject/files/ProjectInfo.txt");
		Scanner input = new Scanner(file);
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
		for(String s: developers) {	
			devs.setText(devs.getText() + s + "<br>");
		}
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		panel2.add(devs);
		
		JButton home = new JButton("Home");
		panel3.add(home);
		
		panel1.add(versionText);
		frame.getContentPane().add(panel1, BorderLayout.NORTH);
		frame.getContentPane().add(panel2, BorderLayout.CENTER);
		frame.getContentPane().add(panel3, BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("About Page");
	}
	
	
}
