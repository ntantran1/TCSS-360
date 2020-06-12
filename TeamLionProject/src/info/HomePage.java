/*
 * TCSS 360 Team Lion Project
 * Spring 2020
 */
package info;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import javax.swing.JToggleButton;
import javax.swing.JList;

/**
 * Home page class for team lion.
 * This is also our dashboard board page.
 * This class is reposible for most of out app functions.
 * 
 * @ author Kevin
 * @ author rabin
 * @ author aman
 * @ author nhan
 *
 */
public class HomePage extends JFrame{
	static int rows;
	private int index;
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
	private final JButton refreshButt = new JButton("Refresh/Home");
	private final JLabel dateLabel = new JLabel("Date/Time: ");
	private final JLabel dateTF =  new JLabel(" ");
	DateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY   [HH:mm:ss]");
	Date date = new Date();
	DefaultTableModel model;
	static String[] comboBoxSelection = {"Select Sorting Type","Sort By ID", "Sort By Name", "Sort By Type", "Sort By Room", "Sort By Date"};
	private final static JComboBox sortButt = new JComboBox(comboBoxSelection);
	final static JButton updateRowButt = new JButton("Update Row (select row to update)");
	final static JTextField  rowNameTF = new JTextField();
	final static JTextField  rowTypeTF = new JTextField();
	final static JTextField  rowRoomTF = new JTextField();
	final static JTextField  rowTagsTF = new JTextField();
	final static JTextField  rowDateTF = new JTextField();
	final static JLabel  rowNameLabel = new JLabel("Selected Name:");
	final static JLabel  rowTypeLabel = new JLabel("Selected Type:");
	final static JLabel  rowRoomLabel = new JLabel("Selected Room:");
	final static JLabel  rowTagsLabel = new JLabel("Selected Room:");
	final static JLabel  rowDateLabel = new JLabel("Selected Date:");

	List<Vector<Object>> fileList = new ArrayList<Vector<Object>>();
	private JButton addFileButt = new JButton("Add File to row");
	private JButton addLinkButt = new JButton("Add Link to row");
	JToggleButton deleteButt;
	boolean deleteMode;
	JList list;
	private JButton openButt = new JButton("Open selected file");
	private JTextField linkText;
	
	/**
	 * A constructor to start the home page
	 * @throws IOException
	 */
	public HomePage() throws IOException{
		index = 1;
		deleteMode = false;
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
		model = (DefaultTableModel) table.getModel();

	}
	
	/** 
	 * Method to make the table for the homepage
	 * @throws IOException
	 */
	public void makeTable() throws IOException {
		table.setModel(new javax.swing.table.DefaultTableModel(
				new Object [][] {
					//{null, null, null, null, null, null, null},
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

			}
			public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
			}
			public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
			}
		});


		DefaultTableModel savedTable = (DefaultTableModel) table.getModel();

		for(int i = 0; i < tableData.size(); i = i + 7) {
			String appID = tableData.get(i);
			String appName = tableData.get(i + 1);
			String appType = tableData.get(i + 2);
			String appRooms = tableData.get(i + 3);
			String appTags = tableData.get(i + 4);
			String appDate = tableData.get(i + 5);
			String appFiles = tableData.get(i + 6);
			int appIndex = Integer.parseInt(appID);
			while(appIndex >= fileList.size())
				fileList.add(new Vector<Object>());
			fileList.add(appIndex, new Vector<Object>());
			index++;
			File tempFile = new File("files/app" + appID + "Links.txt");
			String newInput;
			if(tempFile.exists()) {
				List<String> linkData = Files.readAllLines(Paths.get("files/app" + appID + "Links.txt"));
				for(int j = 1; j <= linkData.size(); j++) {
					for(String s: linkData) {
						fileList.get(appIndex - 1).add(s);
					}
				}
			} else {
				tempFile.createNewFile();
			}
			((DefaultTableModel) savedTable).insertRow(savedTable.getRowCount(), new Object[] {appID, appName, appType, appRooms, appTags, appDate, appFiles});
		}
	}
	
	/**
	 * Method that create the frame for the homepage
	 */
	public void makeHomeFrame() {
		homeFrame.setTitle("Team Lions Dashboard Page");
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
		homeFrame.getContentPane().add(updateRowButt);
		updateRowButt.setBackground(new Color(173, 255, 47));
		homeFrame.getContentPane().add(rowNameTF);
		homeFrame.getContentPane().add(rowTypeTF);
		homeFrame.getContentPane().add(rowRoomTF);
		homeFrame.getContentPane().add(rowTagsTF);
		homeFrame.getContentPane().add(rowDateTF);
		homeFrame.getContentPane().add(rowNameLabel);
		homeFrame.getContentPane().add(rowTypeLabel);
		homeFrame.getContentPane().add(rowRoomLabel);
		homeFrame.getContentPane().add(rowTagsLabel);
		homeFrame.getContentPane().add(rowDateLabel);
		updateRowButt.setBounds(20, 430, 230, 35);
		rowNameLabel.setBounds(20, 476, 120, 30);
		rowTypeLabel.setBounds(20, 523, 120, 30);
		rowRoomLabel.setBounds(20, 570, 120, 30);
		rowTagsLabel.setBounds(20, 620, 120, 30);
		rowDateLabel.setBounds(20, 665, 120, 30);
		rowNameTF.setBounds(125, 476, 148, 30);
		rowTypeTF.setBounds(125, 523, 148, 30);
		rowRoomTF.setBounds(125, 570, 148, 30);
		rowTagsTF.setBounds(125, 620, 148, 30);
		rowDateTF.setBounds(125, 665, 148, 30);

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

		JScrollPane scrollPaneFile = new JScrollPane((Component) null);
		scrollPaneFile.setBounds(295, 430, 641, 267);
		homeFrame.getContentPane().add(scrollPaneFile);

		DefaultListModel listmodel = new DefaultListModel();
		list = new JList(listmodel);
		scrollPaneFile.setViewportView(list);

		homeFrame.setSize(1200, 800);
		homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addLinkButt.setBackground(new Color(173, 255, 47));
		addLinkButt.setBounds(946, 499, 218, 35);

		homeFrame.getContentPane().add(addLinkButt);
		addFileButt.setBackground(new Color(173, 255, 47));
		addFileButt.setBounds(946, 430, 218, 35);

		homeFrame.getContentPane().add(addFileButt);

		deleteButt = new JToggleButton("Delete selected file");
		deleteButt.setBounds(946, 660, 218, 35);
		homeFrame.getContentPane().add(deleteButt);
		openButt.setBackground(new Color(173, 255, 47));
		openButt.setBounds(946, 555, 218, 35);

		homeFrame.getContentPane().add(openButt);

		linkText = new JTextField();
		linkText.setText("(Type link here)");
		linkText.setBounds(946, 481, 218, 20);
		homeFrame.getContentPane().add(linkText);
		linkText.setColumns(10);

		//pack();
	}
	/**
	 * Method that add listener for the homepage components
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
					TableRowSorter<TableModel> sorter4 = new TableRowSorter<>(table.getModel());
					table.setRowSorter(sorter4);
					List<RowSorter.SortKey> sortKeys4 = new ArrayList<>();
					int columnIndexForTable4 = 5;
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

				int modelRow = table.getSelectedRow();
				int i = table.convertRowIndexToModel(modelRow);
				//table.getModel().getValueAt(modelRow, column);
				if(i >= 0) {
					//rowIDTF.setText(table.getModel().getValueAt(i, 0).toString());
					rowNameTF.setText(table.getModel().getValueAt(i, 1).toString());
					rowTypeTF.setText(table.getModel().getValueAt(i, 2).toString());
					rowRoomTF.setText(table.getModel().getValueAt(i, 3).toString());
					rowTagsTF.setText(table.getModel().getValueAt(i, 4).toString());
					rowDateTF.setText(table.getModel().getValueAt(i, 5).toString());
					list.setListData(fileList.get(i));
					//rowFileTF.setText(table.getModel().getValueAt(i, 6).toString());
				} else {
					//rowIDTF.setText("");
					rowNameTF.setText("");
					rowTypeTF.setText("");
					rowRoomTF.setText("");
					rowTagsTF.setText("");
					rowDateTF.setText("");
					DefaultListModel listmodel = new DefaultListModel();
					list = new JList(listmodel);
					//rowFileTF.setText("");
				}

			}
		});

		addFileButt.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent theE) {

				JFileChooser files = new JFileChooser();
				File workingDirectory = new File(System.getProperty("user.dir"));
				files.setCurrentDirectory(workingDirectory);
				int modelRow = table.getSelectedRow();
				int i = table.convertRowIndexToModel(modelRow);
				int id = Integer.parseInt((String) model.getValueAt(i, 0));
				if(i >= 0) {
					int rVal = files.showOpenDialog(null);
					if(rVal == JFileChooser.APPROVE_OPTION)
					{
						fileList.get(i).add(files.getSelectedFile().toString());
						list.setListData(fileList.get(i));
					}
					File tempFile = new File("files/app" + id + "Links.txt");
					String newInput = "";
					try {
						//Flush file
						PrintWriter writer = new PrintWriter(tempFile);
						writer.print("");
						writer.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for(Object s: fileList.get(i)) {
						newInput += (String) s;
						newInput += "\n";
					}
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
				else {
					JOptionPane.showMessageDialog(getComponent(0), "No row selected");
				}
			}
		});

		addLinkButt.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent theE) {
				int modelRow = table.getSelectedRow();
				int i = table.convertRowIndexToModel(modelRow);
				int id = Integer.parseInt((String) model.getValueAt(i, 0));
				if(linkText.getText().contains(" "))
					JOptionPane.showMessageDialog(getComponent(0), "Invalid link");
				else if(i >= 0) {
					if(!linkText.getText().contains("http://"))
						fileList.get(i).add("http://" + linkText.getText());	
					else
						fileList.get(i).add(linkText.getText());
					list.setListData(fileList.get(i));
					File tempFile = new File("files/app" + id + "Links.txt");
					String newInput = "";
					try {
						//Flush file
						PrintWriter writer = new PrintWriter(tempFile);
						writer.print("");
						writer.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for(Object s: fileList.get(i)) {
						newInput += (String) s;
						newInput += "\n";
					}
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
				else {
					JOptionPane.showMessageDialog(getComponent(0), "No row selected");
				}
			}
		});

		openButt.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent theE) {
				String select = (String) list.getSelectedValue();
				if(select.substring(0,  8).equals("https://")) {
					try {
						Desktop.getDesktop().browse(new URI((String) select));
					} catch (IOException | URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					//File htmlFile = new File(select);
					try {
						Desktop.getDesktop().open(new File(select));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		// button update row
		updateRowButt.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent theE) {
				// i = the index of the selected row
				int i = table.getSelectedRow();

				if(i >= 0) {
					//model.setValueAt(rowIDTF.getText(), i, 0);
					model.setValueAt(rowNameTF.getText(), i, 1);
					model.setValueAt(rowTypeTF.getText(), i, 2);
					model.setValueAt(rowRoomTF.getText(), i, 3);
					model.setValueAt(rowTagsTF.getText(), i, 4);
					model.setValueAt(rowDateTF.getText(), i, 5);
					//model.setValueAt(rowFileTF.getText(), i, 6);

					//update the txt database
					File tempFile = new File("files/Table.txt");
					String newInput;

					try {
						//Flush file
						PrintWriter writer = new PrintWriter(tempFile);
						writer.print("");
						writer.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

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
					//tempFile.renameTo(new File("files/Table.txt"));
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
				AddRowPage addRowPage = new AddRowPage(index);
				addRowPage.setVisible(true);
				index++;
				fileList.add(new Vector<Object>());
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
				try {
					makeTable();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		menuInstr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				try {
					InstrPage instrPage = new InstrPage();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
		});
		//i added
		delRowButt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {

				if(table.getSelectedRow() != -1) {
					int confirmButton = JOptionPane.showConfirmDialog(null, 
							"Are you sure you want to delete this Row ?", "Comfirm!",
							JOptionPane.YES_NO_OPTION);
					if(confirmButton == JOptionPane.YES_OPTION) {

						//						int rowNum = table.getSelectedRow();
						//						for (int i = rowNum; i <= 3; i++ ) {
						//							model.setValueAt(table.getValueAt(i+1, 0), i+2, 0);
						//							model.setValueAt(table.getValueAt(i, 0), i+1, 0);
						//						}

						// remove selected row from the model
						int selected = table.convertRowIndexToModel(table.getSelectedRow());
						model.removeRow(selected);
						fileList.remove(selected);
						//int modelRow = table.getSelectedRow();
						//int i = table.convertRowIndexToModel(modelRow);
						//table.getModel().getValueAt(modelRow, column);
						//updating each line from the table to new temp file
						File tempFile = new File("files/Table.txt");
						String newInput;

						try {
							//Flush file
							PrintWriter writer = new PrintWriter(tempFile);
							writer.print("");
							writer.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

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
						//tempFile.renameTo(new File("files/Table.txt"));
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
