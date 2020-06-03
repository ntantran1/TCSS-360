package info;

import java.awt.*;

//Testing for the boys

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.JTable;

import java.awt.Dimension;

public class HomePage extends JFrame{
	static int rows;
	private List<String> tableData = Files.readAllLines(Paths.get("files/Table.txt"));
	private AboutPage about;
	private ProfilePage profile;
	JButton editProf = new JButton("View Profile");
	JPanel panelNorth = new JPanel();
	JPanel panelSouth = new JPanel();
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu menuFile = new JMenu("File");
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
		menuBar.add(menuHelp);
		menuHelp.add(menuInstr);
		menuHelp.add(menuAbout);
		homeFrame.setVisible(true);
		addListener();
		makeTable();
		rows = table.getRowCount();
	}
	
	public void makeTable() {
		MyTableModel tableModel = (MyTableModel) table.getModel();
		tableModel.reset();
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
	        ((MyTableModel) tableModel).addRow(appID, appName, appType, appRooms, appTags, appDate, appFiles, i%7);
		}
	}

	public void makeHomeFrame() {
		homeFrame.setTitle("Home Page");
		homeFrame.getContentPane().setLayout(null);
		panelSouth.setBounds(0, 706, 1184, 33);
		panelSouth.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		editProf.setBackground(new Color(173, 255, 47));
		panelSouth.add(editProf);
		homeFrame.getContentPane().add(panelSouth);
		panelNorth.setBounds(0, 0, 1184, 61);
		panelNorth.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		homeFrame.getContentPane().add(panelNorth);
		addRowButt.setBackground(new Color(173, 255, 47));
		
		panelNorth.add(addRowButt);
		delRowButt.setBackground(new Color(173, 255, 47));
		
		panelNorth.add(delRowButt);
		refreshButt.setBackground(new Color(173, 255, 47));
		
		panelNorth.add(refreshButt);
		panelLeft.setBounds(0, 61, 10, 645);
		panelLeft.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		homeFrame.getContentPane().add(panelLeft);
		panelRight.setBounds(1174, 61, 10, 645);
		panelRight.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));
		homeFrame.getContentPane().add(panelRight);
		table = new JTable(new MyTableModel());
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 72, 1144, 347);
		homeFrame.getContentPane().add(scrollPane);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		homeFrame.setSize(1200, 800);
		homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void addRow(Object name, Object type,
			Object rooms, Object tags, Object date) {
		MyTableModel tableModel = (MyTableModel) table.getModel();
        //Object[] newRow = {rows + 1, name, type, rooms, tags, date, "File Placeholder"};
        ((MyTableModel) tableModel).addRow(rows + 1, name, type, rooms, tags, date, "File Placeholder", rows);
        rows++;
	}
	
	public void addListener() { 
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
				AddRowPage newRowPage = new AddRowPage(rows);
				newRowPage.setVisible(true);
			}
		});

		refreshButt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				try {
					tableData = Files.readAllLines(Paths.get("files/Table.txt"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				makeTable();
			}
		});
		
		delRowButt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theE) {
				profile.makePage();
			}
		});
		

	}
}