package info;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

public class HomePage extends JFrame{
	static int rows;
	private List<String> tableData = Files.readAllLines(Paths.get("TeamLionProject/files/Table.txt"));
	private AboutPage about;
	private ProfilePage profile;
	JButton editProf = new JButton("View Profile");
	JPanel panelNorth = new JPanel();
	JPanel panelSouth = new JPanel();
	
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu menuFile = new JMenu("File");
	private final JMenuItem ExitMenuItem = new JMenuItem("Exit");
	private final JMenu menuHelp = new JMenu("Help");
	private final JMenuItem menuInstr = new JMenuItem("Instructions");
	private final JMenuItem menuAbout = new JMenuItem("About");
	private final JPanel panelLeft = new JPanel();
	private final JPanel panelRight = new JPanel();
	static JTable table;
	JFrame homeFrame;
	final static JButton addRowButt = new JButton("Add New Row");
	final static JButton delRowButt = new JButton("Delete Row");
	private final JButton refreshButt = new JButton("Refresh");
	
	//i added
	private final JLabel dateLabel = new JLabel("Date/Time: ");
	private final JLabel dateTF =  new JLabel(" ");
	DateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY   [HH:mm:ss]");
	Date date = new Date();
	DefaultTableModel model;
	static String[] comboBoxSelection = {"Select Sorting Type","Sort By ID", "Sort By Name", "Sort By Type", "Sort By Room", "Sort By Date"};
	private final static JComboBox sortButt = new JComboBox(comboBoxSelection);
	final static JButton updateRowButt = new JButton("Update Row (select row to update)");
//	final static JPanel centerPanel = new JPanel();
	final static JTextField  rowIDTF = new JTextField();
	final static JTextField  rowNameTF = new JTextField();
	final static JTextField  rowTypeTF = new JTextField();
	final static JTextField  rowRoomTF = new JTextField();
	final static JTextField  rowTagsTF = new JTextField();
	final static JTextField  rowDateTF = new JTextField();
	final static JTextField  rowFileTF = new JTextField();

	public HomePage() throws IOException{
		about = new AboutPage();
		profile = new ProfilePage();
		homeFrame = new JFrame();
		makeHomeFrame();
		menuBar.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		menuBar.setOpaque(true);
		menuBar.setBackground(Color.WHITE);
		homeFrame.setJMenuBar(menuBar);
		menuBar.add(menuFile);
		menuFile.add(ExitMenuItem);
		menuBar.add(menuHelp);
		menuHelp.add(menuInstr);
		menuHelp.add(menuAbout);
		homeFrame.setVisible(true);
		addListener();
		makeTable();
		rows = table.getRowCount();
		 
		//i added
		model = (DefaultTableModel) table.getModel();
		
	}
	
	public void makeTable() {
		
		//i changed here
		table.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	            	
//	                {null, null, null, null, null, null, null},
	            },
	            new String [] {
	                "ID", "Appliance Name", "Appliance Type", "Room", "Tags", "Date Added", "Files"
	            }
	            
	        ));
		
	        table.addAncestorListener(new javax.swing.event.AncestorListener() {
	            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
	                tableAncestorAdded(evt);
	            }
	            private void tableAncestorAdded(AncestorEvent evt) {
					// TODO Auto-generated method stub
					
				}
				public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
	            }
	            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
	            }
	        });
	        
	        
		DefaultTableModel savedTable = (DefaultTableModel) table.getModel();
//		tableModel.reset();
		for(int i = 0; i < tableData.size(); i = i + 7) {
			String appID = tableData.get(i);
			String appName = tableData.get(i + 1);
			String appType = tableData.get(i + 2);
			String appRooms = tableData.get(i + 3);
			String appTags = tableData.get(i + 4);
			String appDate = tableData.get(i + 5);
			String appFiles = tableData.get(i + 6);
			//MyTableModel tableModel = (MyTableModel) table.getModel();
            //Object[] newRow = {appID, appName, appType, appRooms, appTags, appDate, appFiles};
	        ((DefaultTableModel) savedTable).insertRow(savedTable.getRowCount(), new Object[] {appID, appName, appType, appRooms, appTags, appDate, appFiles});
		}
	}
	
	public void makeHomeFrame() {
		homeFrame.setTitle("DashBoard Page for Team Lions");
		homeFrame.getContentPane().setLayout(null);
		
		panelSouth.setBounds(0, 706, 1184, 33);
		panelSouth.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		editProf.setBackground(new Color(173, 255, 47));
		panelSouth.add(editProf);homeFrame.getContentPane().add(panelSouth);
		
		panelNorth.setBounds(0, 0, 1184, 61);
		panelNorth.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		homeFrame.getContentPane().add(panelNorth);
		addRowButt.setBackground(new Color(173, 255, 47));
		panelNorth.add(addRowButt);
		delRowButt.setBackground(new Color(173, 255, 47));
		panelNorth.add(delRowButt);
		refreshButt.setBackground(new Color(173, 255, 47));
		
		//i added
		panelNorth.add(sortButt);
		sortButt.setBackground(new Color(173, 255, 47));
		dateLabel.setForeground(Color.GREEN);
		dateTF.setForeground(Color.GREEN);
		panelSouth.add(dateLabel);
		panelSouth.add(dateTF);
		dateTF.setText(dateFormat.format(date));
//		centerPanel.setBounds(0, 424, 1184, 300);
//		homeFrame.getContentPane().add(centerPanel);
//		centerPanel.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
//		centerPanel.add(updateRowButt).setBounds(0, 448, 60, 24);
		homeFrame.add(updateRowButt);
//		updateRowButt.setBackground(Color.RED);
		homeFrame.add(rowIDTF);
		homeFrame.add(rowNameTF);
		homeFrame.add(rowTypeTF);
		homeFrame.add(rowRoomTF);
		homeFrame.add(rowTagsTF);
		homeFrame.add(rowDateTF);
		homeFrame.add(rowFileTF);
		updateRowButt.setBounds(20, 430, 230, 35);
		rowIDTF.setBounds(20, 480, 74, 30);
		rowNameTF.setBounds(20, 510, 148, 30);
		rowTypeTF.setBounds(20, 540, 148, 30);
		rowRoomTF.setBounds(20, 570, 148, 30);
		rowTagsTF.setBounds(20, 600, 148, 30);
		rowDateTF.setBounds(20, 630, 148, 30);
		rowFileTF.setBounds(20, 660, 148, 30);
		
		panelNorth.add(refreshButt);
		panelLeft.setBounds(0, 61, 10, 645);
		panelLeft.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		homeFrame.getContentPane().add(panelLeft);
		panelRight.setBounds(1174, 61, 10, 645);
		panelRight.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		homeFrame.getContentPane().add(panelRight);
		
		//can put model inside the JTable contruction new JTable(model)
		table = new JTable();
		// ENABLE SORTING
//		table.setAutoCreateRowSorter(true);
				
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 72, 1144, 347);
		homeFrame.getContentPane().add(scrollPane);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		homeFrame.setSize(1200, 800);
		homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		pack();
	}
	
	/**
	 * Action Listeners for the buttons
	 */
	public void addListener() { 
		
		//i added
		ExitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				int confirmButton = JOptionPane.showConfirmDialog(null, 
						"Are you sure you want to exit ?", "Comfirm!",
						JOptionPane.YES_NO_OPTION);
				if(confirmButton == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		
		//i added
		sortButt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				//cast to string object for the selection
				String selection = (String) sortButt.getSelectedItem();
				
				//switch cases for combobox
			switch (selection) {
					case "Sort By ID":
						TableRowSorter<TableModel> sorter0 = new TableRowSorter<>(table.getModel());
						table.setRowSorter(sorter0);
						List<RowSorter.SortKey> sortKeys0 = new ArrayList<>();
						int columnIndexForTable0 = 0;
						sortKeys0.add(new RowSorter.SortKey(columnIndexForTable0, SortOrder.ASCENDING));
						sorter0.setSortKeys(sortKeys0);
				
						// DISABLE SORTING FOR A SPECIFIC COLUMN
						sorter0.setSortable(4, false);
						sorter0.setSortable(6, false);
						break;
					case "Sort By Name":
						TableRowSorter<TableModel> sorter1 = new TableRowSorter<>(table.getModel());
						table.setRowSorter(sorter1);
						List<RowSorter.SortKey> sortKeys1 = new ArrayList<>();
						int columnIndexForTable1 = 1;
						sortKeys1.add(new RowSorter.SortKey(columnIndexForTable1, SortOrder.ASCENDING));
						sorter1.setSortKeys(sortKeys1);
						
						// DISABLE SORTING FOR A SPECIFIC COLUMN
						sorter1.setSortable(4, false);
						sorter1.setSortable(6, false);
						break;
						
					case "Sort By Type":
						TableRowSorter<TableModel> sorter2 = new TableRowSorter<>(table.getModel());
						table.setRowSorter(sorter2);
						List<RowSorter.SortKey> sortKeys2 = new ArrayList<>();
						int columnIndexForTable2 = 2;
						sortKeys2.add(new RowSorter.SortKey(columnIndexForTable2, SortOrder.ASCENDING));
						sorter2.setSortKeys(sortKeys2);
						
						// DISABLE SORTING FOR A SPECIFIC COLUMN
						sorter2.setSortable(4, false);
						sorter2.setSortable(6, false);
						break;
						
					case "Sort By Room":
						TableRowSorter<TableModel> sorter3 = new TableRowSorter<>(table.getModel());
						table.setRowSorter(sorter3);
						List<RowSorter.SortKey> sortKeys3 = new ArrayList<>();
						int columnIndexForTable3 = 3;
						sortKeys3.add(new RowSorter.SortKey(columnIndexForTable3, SortOrder.ASCENDING));
						sorter3.setSortKeys(sortKeys3);
						
						// DISABLE SORTING FOR A SPECIFIC COLUMN
						sorter3.setSortable(4, false);
						sorter3.setSortable(6, false);
						break;
						
					case "Sort By Date":
						//TODO
						TableRowSorter<TableModel> sorter4 = new TableRowSorter<>(table.getModel());
						table.setRowSorter(sorter4);
						List<RowSorter.SortKey> sortKeys4 = new ArrayList<>();
						int columnIndexForTable4 = 4;
						sortKeys4.add(new RowSorter.SortKey(columnIndexForTable4, SortOrder.ASCENDING));
						sorter4.setSortKeys(sortKeys4);
						
						// DISABLE SORTING FOR A SPECIFIC COLUMN
						sorter4.setSortable(4, false);
						sorter4.setSortable(6, false);
						
						break;
						
					default:
						System.out.println("nothing selected");
				}	
			}
		});
		
        // get selected row data From table to textfields 
       table.addMouseListener(new MouseAdapter(){
       
       @Override
       public void mouseClicked(MouseEvent theE){
           
           // i = the index of the selected row
           int i = table.getSelectedRow();
           
           rowIDTF.setText(model.getValueAt(i, 0).toString());
           rowNameTF.setText(model.getValueAt(i, 1).toString());
           rowTypeTF.setText(model.getValueAt(i, 2).toString());
           rowRoomTF.setText(model.getValueAt(i, 3).toString());
           rowTagsTF.setText(model.getValueAt(i, 4).toString());
           rowDateTF.setText(model.getValueAt(i, 5).toString());
           rowFileTF.setText(model.getValueAt(i, 6).toString());
       }
       });
       
       // button update row
       updateRowButt.addActionListener(new ActionListener(){

           @Override
           public void actionPerformed(ActionEvent theE) {
            
               // i = the index of the selected row
               int i = table.getSelectedRow();
               
               if(i >= 0) 
               {
                  model.setValueAt(rowIDTF.getText(), i, 0);
                  model.setValueAt(rowNameTF.getText(), i, 1);
                  model.setValueAt(rowTypeTF.getText(), i, 2);
                  model.setValueAt(rowRoomTF.getText(), i, 3);
                  model.setValueAt(rowTagsTF.getText(), i, 4);
                  model.setValueAt(rowDateTF.getText(), i, 5);
                  model.setValueAt(rowFileTF.getText(), i, 6);
                 
                 //update the txt database
                //updating each line from the table to new temp file
					File tempFile = new File("TeamLionProject/files/tempTable.txt");
					int rowM = 1;
					String newInput;
					
					//write each rows and columns to the temp txt file
					for (int row = 0; row < table.getRowCount(); row++) {
						for (int col = 0; col < table.getColumnCount(); col++) {
							    	
							newInput = model.getValueAt(row, col) + "\n";
							    	
							FileWriter fr = null;
							try {
								fr = new FileWriter(tempFile, true);
								fr.write(newInput);
								fr.close();
							} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
							}
						}
					}
					
					//rename the temp txt to Table.txt
					tempFile.renameTo(new File("TeamLionProject/files/Table.txt"));
							
			               
					JOptionPane.showMessageDialog(null, "Selected row updated successfully");
                  
                  
               }
               else{
                   System.out.println("Update Error");
               }
           }
       });
		
		
		
		menuAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				about.makePage();
			}
		});

		editProf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				profile.makePage();
			}
		});
		
		addRowButt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				AddRowPage addRowPage = new AddRowPage();
				addRowPage.setVisible(true);
		    }
		});

		refreshButt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				try {
					tableData = Files.readAllLines(Paths.get("TeamLionProject/files/Table.txt"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				makeTable();
			}
		});
		
		//i added
		delRowButt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				if(table.getSelectedRow() != -1) {
					
					int confirmButton = JOptionPane.showConfirmDialog(null, 
							"Are you sure you want to exit ?", "Comfirm!",
		                    JOptionPane.YES_NO_OPTION);
					if(confirmButton == JOptionPane.YES_OPTION) {
						
						
//						int rowNum = table.getSelectedRow();
//						for (int i = rowNum; i <= 3; i++ ) {
//							model.setValueAt(table.getValueAt(i+1, 0), i+2, 0);
//							model.setValueAt(table.getValueAt(i, 0), i+1, 0);
//						}
						
						// remove selected row from the model
						model.removeRow(table.getSelectedRow());
				               
						//updating each line from the table to new temp file
						File tempFile = new File("TeamLionProject/files/tempTable.txt");
						int rowM = 1;
						String newInput;
						
						//write each rows and columns to the temp txt file
						for (int row = 0; row < table.getRowCount(); row++) {
							for (int col = 0; col < table.getColumnCount(); col++) {
								    	
								newInput = model.getValueAt(row, col) + "\n";
								    	
								FileWriter fr = null;
								try {
									fr = new FileWriter(tempFile, true);
									fr.write(newInput);
									fr.close();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
								}
							}
						}
						
						//rename the temp txt to Table.txt
						tempFile.renameTo(new File("TeamLionProject/files/Table.txt"));
								
				               
						JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
				               
				               
					} else {
						setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					}
				} else {
					System.out.println("delete error. Empty row!");
				}
			}
		});
	}
}