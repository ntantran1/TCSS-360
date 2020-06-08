/*
 * TCSS 360 project
 * Team Lions
 */
package pages;
import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;

import info.HomePage;
import info.LoginPage;

public class Main {
	public static void main(String[] args) throws IOException {
		
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	LoginPage login;
				try {
					login = new LoginPage();
					login.setVisible(true);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
	}
}
