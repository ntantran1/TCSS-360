//package info;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.swing.table.AbstractTableModel;
//
//public class MyTableModel extends AbstractTableModel  {
//	private String[] columnNames = {"Object ID",
//			"Name", 
//			"Appliance Type",
//			"Room(s)",
//			"Tags",
//			"Date Added",
//			"Files"};
//
//	List<Object[]> data = new ArrayList<Object[]>();
//
//	public int getColumnCount() {
//		return columnNames.length;
//	}
//
//	public int getRowCount() {
//		return data.size();
//	}
//
//	public String getColumnName(int col) {
//		return columnNames[col];
//	}
//
//	public Object getValueAt(int row, int col) {
//		return data.get(row)[col];
//	}
//	
//	public void reset() {
//		data = new ArrayList<Object[]>();
//	}
//
//	/*
//	 * JTable uses this method to determine the default renderer/
//	 * editor for each cell.  If we didn't implement this method,
//	 * then the last column would contain text ("true"/"false"),
//	 * rather than a check box.
//	 */
//	public Class getColumnClass(int c) {
//		return getValueAt(0, c).getClass();
//	}
//
//	/*
//	 * Don't need to implement this method unless your table's
//	 * editable.
//	 */
//	public boolean isCellEditable(int row, int col) {
//		//Note that the data/cell address is constant,
//		//no matter where the cell appears onscreen.
//		if (col == 1) {
//			return false;
//		} else {
//			return true;
//		}
//	}
//
//	/*
//	 * Don't need to implement this method unless your table's
//	 * data can change.
//	 */
//	public void addRow(Object ID, Object name, Object type,
//			Object rooms, Object tags, Object date, Object files, int row) {
//		Object[] newRow = {ID, name, type, rooms, tags, date, files, row};
//		data.add(newRow);
//		fireTableDataChanged();
//	}
//
//	public void setValueAt(Object value, int row, int col) {
//		data.get(row)[col] = value;
//		fireTableCellUpdated(row, col);
//	}
//
//}
