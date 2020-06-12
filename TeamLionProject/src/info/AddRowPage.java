package info;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.Color;
import javax.swing.UIManager;
/**
 * A method that the rows to the Home Page
 * @author 
 *
 */
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
	int index;
	DefaultTableModel model;

	/**
	 * Create the frame.
	 */ 
	public AddRowPage(int index) {
		this.index = index;
//		this.rows = rows;
		setTitle("Add Row");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textName = new JTextField();
		textName.setColumns(20);
		textName.setBounds(226, 48, 100, 20);
		contentPane.add(textName);

		textType = new JTextField();
		textType.setColumns(20);
		textType.setBounds(226, 79, 100, 20);
		contentPane.add(textType);

		textRoom = new JTextField();
		textRoom.setColumns(20);
		textRoom.setBounds(226, 110, 100, 20);
		contentPane.add(textRoom);

		textTags = new JTextField();
		textTags.setColumns(40);
		textTags.setBounds(226, 141, 100, 20);
		contentPane.add(textTags);

		textDate = new JTextField("yyyy/mm/dd");
		textDate.setColumns(10);
		textDate.setBounds(226, 172, 100, 20);
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
		lblNewLabel_2.setBounds(180, 113, 60, 14);
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
		
		model = (DefaultTableModel) HomePage.table.getModel();
	}
	
	
	/**
	 * Text Field for name
	 * @return textName
	 */
	public JTextField getTextName() {
		return textName;
	}


	/**
	 * Method to set the name to textName textfield
	 * @param textName
	 */
	public void setTextName(JTextField textName) {
		this.textName = textName;
	}


	/**
	 * Meethod to the textType
	 * @return textType
	 */
	public JTextField getTextType() {
		return textType;
	}


	/**
	 * Method so set textType
	 * @param textType
	 */
	public void setTextType(JTextField textType) {
		this.textType = textType;
	}


	/**
	 * Method to get the textRoom
	 * @return
	 */
	public JTextField getTextRoom() {
		return textRoom;
	}


	/**
	 * Method to set text for textRoom
	 * @param textRoom
	 */
	public void setTextRoom(JTextField textRoom) {
		this.textRoom = textRoom;
	}


	/**
	 * Method to get the textTags
	 * @return textTags
	 */
	public JTextField getTextTags() {
		return textTags;
	}


	/**
	 * Method to set the textTags
	 * @param textTags
	 */
	public void setTextTags(JTextField textTags) {
		this.textTags = textTags;
	}


	/**
	 * Method to get the textDate
	 * @return textDate
	 */
	public JTextField getTextDate() {
		return textDate;
	}


	/**
	 * Method to set the TextDate
	 * @param textDate
	 */
	public void setTextDate(JTextField textDate) {
		this.textDate = textDate;
	}


	/**
	 * Method to get lblApplianceType
	 * @return lblApplianceType
	 */
	public JLabel getLblApplianceType() {
		return lblApplianceType;
	}


	/**
	 * Method to set lblAppliance
	 * @param lblApplianceType
	 */
	public void setLblApplianceType(JLabel lblApplianceType) {
		this.lblApplianceType = lblApplianceType;
	}
	
	/**
	 * Method that add listener to all the textfield that belong to this class
	 */
	public void addListener() { 
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent theE) {
				model.insertRow(model.getRowCount(), new Object[]{index, getTextName().getText(), 
						getTextType().getText(), getTextRoom().getText(),
						getTextTags().getText(), getTextDate().getText(), "File Holder"});
				
				//storing in the table file
				File file = new File("TeamLionProject/files/Table.txt");
				int rowNum = model.getRowCount();
				String newInput = index + "\n" + textName.getText() + "\n" + textType.getText()
						+ "\n" + textRoom.getText() + "\n" + textTags.getText() + "\n"
						+ textDate.getText() + "\n" + "Files Placeholder" + "\n";
				
				FileWriter fr = null;
				try {
					fr = new FileWriter(file, true);
					fr.write(newInput);
					fr.close();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
				dispose();
			}
			
		});
	}
}
