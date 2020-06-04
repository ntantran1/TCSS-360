package info;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField email;
	private List<String> settings;
	JButton loginButton = new JButton("LET ME IN");

	/** 
	 * Create the frame.
	 * @throws IOException 
	 */
	public LoginPage() throws IOException {
		BufferedImage image = ImageIO.read(new File("resource/LimeLogin.jpg"));
		//contentPane.add(image);
		
		settings = Files.readAllLines(Paths.get("files/Settings.txt"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 369);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HOWDY FELLA");
		lblNewLabel.setForeground(new Color(173, 255, 47));
		lblNewLabel.setBounds(49, 42, 381, 85);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 93));
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setFont(new Font("Arial", Font.BOLD, 11));
		username.setForeground(Color.BLACK);
		username.setBackground(new Color(192, 192, 192));
		username.setBounds(283, 151, 147, 20);
		contentPane.add(username);
		username.setColumns(20);
		
		email = new JTextField();
		email.setFont(new Font("Arial", Font.BOLD, 11));
		email.setForeground(Color.BLACK);
		email.setBackground(new Color(192, 192, 192));
		email.setBounds(283, 182, 147, 20);
		contentPane.add(email);
		email.setColumns(20);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1.setBounds(189, 151, 84, 17);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2.setBounds(222, 184, 51, 12);
		contentPane.add(lblNewLabel_2);
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		loginButton.setForeground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		
		//JButton btnNewButton = new JButton("LET ME IN");
		loginButton.setBackground(new Color(173, 255, 47));
		loginButton.setBounds(283, 227, 147, 30);
		contentPane.add(loginButton);
		
		JLabel lbl = new JLabel("New label");
		ImageIcon newIcon = new ImageIcon("resource/LimeLogin.jpg");
		lbl.setIcon(newIcon);
		lbl.setBounds(451, -4, 425, 345);
		contentPane.add(lbl);
		addListener();
	}
	
	public void addListener() { 
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				String storedName = settings.get(1);
				String storedEmail = settings.get(3);
				if(username.getText().equals(storedName) && email.getText().equals(storedEmail)) {
					try {
						HomePage home = new HomePage();
						Frame[] get = LoginPage.getFrames();
						get[0].dispose();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					System.out.println(storedName);
					System.out.println(storedEmail);
					JOptionPane.showMessageDialog(getComponent(0), "Yo we got a big phony over here");
				}
			}
		});

	}
}
