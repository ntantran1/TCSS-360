package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import info.AboutPage;

public class AboutPageTest {
	
	AboutPage page;

	
	@Test
	public void testAboutPage() throws IOException{
		page = new AboutPage();
	}

	@Test
	public void testMakePage() throws IOException{
		page = new AboutPage();
		page.makePage();
	}

}