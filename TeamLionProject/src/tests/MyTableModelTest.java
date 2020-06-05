package tests;

import static org.junit.Assert.*;
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
	}

	@Test
	public void testIsCellEditable() {
		assertTrue(page.isCellEditable(1, 4));
		assertFalse(page.isCellEditable(3, 1));	
	}

	@Test
	public void testGetColumnCount() {
		int colLength = page.getColumnCount();
		assertEquals(7, colLength);
	}

	@Test
	public void testGetRowCount() {
		Object fridge = null;
		Object storage = null;
		Object kitchen = null;
		page.addRow(1, fridge, storage, kitchen, kitchen, 03/01/2020, null, 1);
		int rowCount = page.getRowCount();
		assertEquals(1, rowCount);
	}

	@Test
	public void testGetColumnName() {
		String colName = page.getColumnName(1);
		assertEquals("Name",colName);
	}

	@Test
	public void testGetValueAt() {
		Object fridge = null;
		Object storage = "storage";
		Object kitchen = null;
		page.addRow(1, fridge, storage, kitchen, kitchen, 03/01/2020, null, 1);
		assertEquals(storage, page.getValueAt(0,2));
	}

	@Test
	public void testReset() {
		Object fridge = null;
		Object storage = "storage";
		Object kitchen = null;
		page.addRow(1, fridge, storage, kitchen, kitchen, 03/01/2020, null, 1);
		page.reset();
		assertEquals(0, page.getRowCount());
	}

}
