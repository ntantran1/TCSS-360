package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import info.MyTableModel;

public class MyTableModelTest {
	
	MyTableModel page;
	List<Object[]> data;
	
	@Before
	public void setup() {
		page = new MyTableModel();
		data = new ArrayList<Object[]>();
	}

	@Test
	public void testIsCellEditable() {
		assertTrue(page.isCellEditable(1, 4));
		assertFalse(page.isCellEditable(3, 1));	
	}

	@Test
	public void testGetColumnCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRowCount() {
		Object fridge = null;
		Object storage = null;
		Object kitchen = null;
		page.addRow(1, fridge, storage, kitchen, kitchen, 03/01/2020, null, 1);
		int count = data.size();
		assertEquals(1, count);
	}

	@Test
	public void testGetColumnNameInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetValueAt() {
		fail("Not yet implemented");
	}

	@Test
	public void testReset() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetColumnClassInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddRow() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetValueAtObjectIntInt() {
		fail("Not yet implemented");
	}

}
