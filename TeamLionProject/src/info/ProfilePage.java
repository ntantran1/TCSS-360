package info;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;

public class ProfilePage extends JFrame implements Serializable{

	private static final long serialVersionUID = 7623978707456111660L;
	private static final JFrame frame = new JFrame();
	private List<String> settings = Files.readAllLines(Paths.get("files/Settings.txt"));
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
		frame.getContentPane().setLayout(new BorderLayout());
		changeData.setBackground(new Color(173, 255, 47));
		changeData.setForeground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		panelSouth.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		panelSouth.add(changeData);
		submitData.setForeground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		submitData.setBackground(new Color(173, 255, 47));
		panelSouth.add(submitData);
		exportData.setForeground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		exportData.setBackground(new Color(173, 255, 47));
		panelNorth.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		panelNorth.add(exportData);
		importData.setForeground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		importData.setBackground(new Color(173, 255, 47));
		panelNorth.add(importData);
		firstNameText = new javax.swing.JTextField(firstName);
		firstNameText.setBounds(65, 5, 166, 20);
		emailText = new javax.swing.JTextField(email);
		emailText.setBounds(65, 30, 166, 20);
		JLabel nameLabel = new JLabel("<html>Name:<br>");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setBounds(26, 8, 34, 14);
		JLabel emailLabel = new JLabel("<html>Email:<br>");
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setBounds(29, 33, 31, 14);
		panelCenter.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		panelCenter.setLayout(null);
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
				writeProfile();
				changeData.setEnabled(true);
				submitData.setEnabled(false);
			}
		});

		exportData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				UserProfile myProfile = new UserProfile(firstName, email);
				myProfile.exportProfile();
			}
		});

		importData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				UserProfile myProfile = new UserProfile();
				myProfile.importProfile();
				email = myProfile.email;
				firstName = myProfile.firstName;
				writeProfile();
				firstNameText.setText(firstName);
				emailText.setText(email);
			}
		});
		
		
	} 
	public void writeProfile() {
		settings.set(1, firstName);
		settings.set(3, email);
		try {
			Files.write(Paths.get("files/Settings.txt"),
					settings, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}