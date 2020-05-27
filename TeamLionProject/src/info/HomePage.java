package info;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class HomePage {
	
	private static final JFrame frame = new JFrame("Home Page");
	
	private AboutPage about;
	
	private String email = "ntantra@uw.edu";
	
	private String firstName = "Nhan";
	
	JButton editProf = new JButton("Edit Profile");
	
	JButton aboutButton = new JButton("About Info");
	
	JPanel panel = new JPanel();
	
	public HomePage() throws IOException{
		about = new AboutPage();
		panel.add(aboutButton);
		panel.add(editProf);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(panel);		
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
	            	 JButton changeFirst = new JButton("Change First Name");
	            	 JButton changeEmail = new JButton("Change Email");
	            	 
	            	 JButton getFirst = new JButton("Get First Name");
	            	 JButton getEmail = new JButton("Get email");
	            	 
	            	 JPanel p = new JPanel();
	            	 
	            	 JTextField first = new javax.swing.JTextField("first name");
	            	 JTextField emailText = new javax.swing.JTextField("email");
	            	 

	            	 p.add(first);
	            	 p.add(emailText);
	            	 
	            	 p.add(getFirst);
	            	 p.add(getEmail);
	            	 
	            	 first.setEditable(false);
	            	 emailText.setEditable(false);
	            	 
	            	 p.add(changeEmail);
	            	 p.add(changeFirst);
	            	 getFirst.addActionListener(new ActionListener() {

	            		 @Override
	        	         public void actionPerformed(final ActionEvent theE) {
	            			 JOptionPane.showMessageDialog( null, firstName);
	        	         }
	        	                
	        	     });
	            	 
	            	 getEmail.addActionListener(new ActionListener() {

	            		 @Override
	        	         public void actionPerformed(final ActionEvent theE) {
	            			 JOptionPane.showMessageDialog( null, email);
	        	         }
	        	                
	        	     });
	           
	            	 
	            	 first.addActionListener(new ActionListener() {

	                     @Override
	                     public void actionPerformed(final ActionEvent theE) {
	                    	 
	                    	 firstName = first.getText();
	                    	 first.setEditable(false);
	                    	 
	                     }
	            	 });
	            	 
	            	 emailText.addActionListener(new ActionListener() {

	                     @Override
	                     public void actionPerformed(final ActionEvent theE) {
	                    	 
	                    	 email = emailText.getText();
	                    	 emailText.setEditable(false);
	                    	 
	                    	 
	                     }
	            	 });
	            	 
	            	 changeFirst.addActionListener(new ActionListener() {

	            		 @Override
	        	         public void actionPerformed(final ActionEvent theE) {
	            			 //
	            			 first.setEditable(true);
	            			 
	        	         }
	        	                
	        	     });
	            	 
	            	 changeEmail.addActionListener(new ActionListener() {

	            		 @Override
	        	         public void actionPerformed(final ActionEvent theE) {
	            			 
	    	            	 emailText.setVisible(true);
	            			 emailText.setEditable(true);
	            			 
	        	         }
	        	                
	        	     });
	            	
	            	 JFrame f = new JFrame("Profile Info");
	            	 
	            	 
	            	
	            	 
	            	 f.setSize(500, 500);
	            	 f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	         		 f.getContentPane().add(p);		
	                 f.setVisible(true);
	            	 
	                
	            }
	                
	        });

	        

	        
	        
	} 



}
 