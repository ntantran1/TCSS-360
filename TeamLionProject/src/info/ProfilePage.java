package info;

import java.awt.BorderLayout;

//Testing for the boys

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class ProfilePage {

	private static final JFrame frame = new JFrame();
	private List<String> settings = Files.readAllLines(Paths.get("TeamLionProject/files/Settings.txt"));
	private String email;
	private String firstName;
	JButton changeData = new JButton("Change personal info");
	JButton exportData = new JButton("Export Profile");
	JButton importData = new JButton("Import Profile");
	JButton submitData = new JButton("Submit");
	JTextField firstNameText;
	JTextField emailText;
	JPanel panelNorth = new JPanel();
	JPanel panelCenter = new JPanel();
	JPanel panelSouth = new JPanel();
	String file;

	public ProfilePage() throws IOException{
		firstName = settings.get(1);
		email = settings.get(3);
		frame.setLayout(new BorderLayout());
		panelSouth.add(changeData);
		panelSouth.add(submitData);
		panelNorth.add(exportData);
		panelNorth.add(importData);
		firstNameText = new javax.swing.JTextField(firstName);
		emailText = new javax.swing.JTextField(email);
		JLabel nameLabel = new JLabel("<html>Name:<br>");
		JLabel emailLabel = new JLabel("<html>Email:<br>");
		panelCenter.add(nameLabel);
		panelCenter.add(firstNameText);
		panelCenter.add(emailLabel);
		panelCenter.add(emailText);
		frame.getContentPane().add(panelNorth, BorderLayout.NORTH);
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		frame.getContentPane().add(panelCenter, BorderLayout.CENTER);
		firstNameText.setEditable(false);
		emailText.setEditable(false);
		submitData.setEnabled(false);
		firstNameText.setColumns(20);
		emailText.setColumns(20);
		addListener();
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Profile Page");
	}

	public void makePage() {
		frame.setVisible(true);
	}

	public void addListener() { 

		firstNameText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				firstName = firstNameText.getText();
				firstNameText.setEditable(false);
			}
		});

		emailText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				email = emailText.getText();
				emailText.setEditable(false);
			}
		});

		changeData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				//
				firstNameText.setEditable(true);
				emailText.setEditable(true);
				submitData.setEnabled(true);
				changeData.setEnabled(false);
			}
		});

		submitData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				emailText.setVisible(true);
				emailText.setEditable(false);
				firstNameText.setEditable(false);
				email = emailText.getText();
				firstName = firstNameText.getText();
				settings.set(1, firstName);
				settings.set(3, email);
				try {
					Files.write(Paths.get("TeamLionProject/files/Settings.txt"),
							settings, StandardCharsets.UTF_8);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				changeData.setEnabled(true);
				submitData.setEnabled(false);
			}
		});

		exportData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				UserProfile myProfile = new UserProfile(firstName, email);
				myProfile.export();
			}
		});
	} 
	
	private class UserProfile implements Serializable
	{
		private String tagName;
		private String email;
		
		public UserProfile(String tagName, String email)
		{   
			this.tagName = tagName;
			this.email = email;
		}
		public String toString()
		{
			return tagName + " " + email;
		}

		public void export ()
		{
			ObjectOutputStream oos = null;
			FileOutputStream fout = null;
			file = "files/";

			try{ 
				fout = new FileOutputStream("c:\\profile.ser", true);
				oos = new ObjectOutputStream(fout);
				oos.writeObject(this);
			} catch (Exception ex) {}
			finally {
				if(oos != null){
					try {
						oos.close();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}

	}
}