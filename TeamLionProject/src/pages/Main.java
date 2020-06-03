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
		LoginPage login = new LoginPage();
		login.setVisible(true);
		//HomePage home = new HomePage();
	}
}
