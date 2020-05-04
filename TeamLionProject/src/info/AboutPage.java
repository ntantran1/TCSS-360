package info;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.*;

public class AboutPage {
	String[] developers;
	String version;
	String file;
	
	public AboutPage() throws IOException {
		file = "files/";
		Scanner input = new Scanner(Paths.get(file + "ProjectInfo.txt"));
		developers = new String[4];
		getInfo(input);
	}
	
	private void getInfo(Scanner input){
		version = input.nextLine();
		for(int i = 0; i < 4; i++) {
			developers[i] = input.nextLine();
		}
	}
	
	public void makePage() {
		var frame = new JFrame();		
		frame.setLayout(new BorderLayout());
		JLabel versionText = new JLabel("Version: " + version);
		JLabel devs = new JLabel("<html>Developers:<br>" +
				developers[0] + "<br>" +
				developers[1] + "<br>" +
 				developers[2] + "<br>" +
				developers[3]);
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("About Page");
	}
	
	
}
