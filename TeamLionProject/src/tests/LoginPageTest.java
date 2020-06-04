package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import info.LoginPage;

public class LoginPageTest {
	
	LoginPage page;
	

	@Test
	public void testLoginPage() throws IOException {
		page = new LoginPage();
	}

	@Test
	public void testAddListener() throws IOException {
		final List<String> set = Files.readAllLines(Paths.get("TeamLionProject/files/Settings.txt"));
		String storedName = set.get(1);
		String storedEmail = set.get(3);
		assertEquals("oh", storedName);
		assertEquals("no", storedEmail);
	}

}
