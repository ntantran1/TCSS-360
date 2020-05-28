package info;

import java.awt.BorderLayout;

//Testing for the boys

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import java.lang.Math;
import java.io.Serializable;
import java.io.*;

public class HomePage {

	private static final JFrame frame = new JFrame("Home Page");
	private AboutPage about;
	private ProfilePage profile;
	JButton editProf = new JButton("View Profile");
	JButton aboutButton = new JButton("About Info");
	JPanel panelNorth = new JPanel();
	JPanel panelSouth = new JPanel();

	public HomePage() throws IOException{
		about = new AboutPage();
		profile = new ProfilePage();
		var frame = new JFrame();
		frame.setLayout(new BorderLayout());
		panelSouth.add(aboutButton);
		panelNorth.add(editProf);
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		frame.getContentPane().add(panelNorth, BorderLayout.NORTH);
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		editProf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				profile.makePage();
			}
		});

	}

}