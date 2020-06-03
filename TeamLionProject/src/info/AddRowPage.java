package info;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;

public class AddRowPage extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textType;
	private JTextField textRoom;
	private JTextField textTags;
	private JTextField textDate;
	private JLabel lblApplianceType;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton saveButton;
	int rows;

	/**
	 * Create the frame.
	 */
	public AddRowPage(int rows) {
		this.rows = rows;
		setTitle("Add Row");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textName = new JTextField();
		textName.setColumns(20);
		textName.setBounds(226, 48, 86, 20);
		contentPane.add(textName);

		textType = new JTextField();
		textType.setColumns(20);
		textType.setBounds(226, 79, 86, 20);
		contentPane.add(textType);

		textRoom = new JTextField();
		textRoom.setColumns(20);
		textRoom.setBounds(226, 110, 86, 20);
		contentPane.add(textRoom);

		textTags = new JTextField();
		textTags.setColumns(40);
		textTags.setBounds(226, 141, 86, 20);
		contentPane.add(textTags);

		textDate = new JTextField();
		textDate.setColumns(10);
		textDate.setBounds(226, 172, 86, 20);
		contentPane.add(textDate);

		JLabel lblNewLabel = new JLabel("Appliance Name:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel.setBounds(125, 51, 91, 14);
		contentPane.add(lblNewLabel);

		lblApplianceType = new JLabel("Appliance Type:");
		lblApplianceType.setForeground(new Color(255, 255, 255));
		lblApplianceType.setFont(new Font("Arial", Font.BOLD, 11));
		lblApplianceType.setBounds(129, 82, 91, 14);
		contentPane.add(lblApplianceType);

		lblNewLabel_2 = new JLabel("Room:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2.setBounds(181, 113, 35, 14);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Tags(Comma seperated):");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_3.setBounds(75, 144, 141, 14);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Date of Purchase:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_4.setBounds(120, 175, 96, 14);
		contentPane.add(lblNewLabel_4);

		saveButton = new JButton("Submit");
		saveButton.setForeground(new Color(0, 0, 0));
		saveButton.setBackground(new Color(173, 255, 47));
		saveButton.setBounds(223, 211, 89, 23);
		contentPane.add(saveButton);
		addListener();
	}

	public void addListener() { 
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				File file = new File("files/Table.txt");
				String newInput = "\n" + (rows + 1) + "\n" + textName.getText() + "\n" + textType.getText()
						+ "\n" + textRoom.getText() + "\n" + textTags.getText() + "\n"
						+ textDate.getText() + "\n" + "Files Placeholder";
				FileWriter fr = null;
				try {
					fr = new FileWriter(file, true);
					fr.write(newInput);
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
		});
	}
}
