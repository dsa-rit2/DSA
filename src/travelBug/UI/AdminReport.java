package travelBug.UI;

//=========================
//	Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================

import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableColumn;


import travelBug.library.LinkArray;
import travelBug.library.ReadWriteFile;
import travelBug.library.SinglyLinkedList;
import travelBug.library.library;
import travelBug.obj.SourceDest;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import javax.swing.table.TableColumn;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminReport extends JPanel {
	private static final long serialVersionUID = 1L;
	private final UIControl mainFrame;
	private JTable table;
	private DefaultTableModel tableModel;
	private Vector<?> vector;
	private JScrollPane scrollPane;
//	private singlyLinkedList<SourceDest> hahalLinkedList = new singlyLinkedList<SourceDest>();
//	private ReadWriteFile<SourceDest> lFile = new ReadWriteFile<SourceDest>("SourceDes.txt", SourceDest.class);
	private SinglyLinkedList<SourceDest> hahalLinkedList2 = new SinglyLinkedList<SourceDest>();
	private ReadWriteFile<SourceDest> lFile2 = new ReadWriteFile<SourceDest>("sourceDes.txt", SourceDest.class);
	private LinkArray<SourceDest> linkArray = new LinkArray<SourceDest>();

	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JButton btnBack;

	public AdminReport(UIControl parent) {
		super();
		this.mainFrame = parent;
		// read txtile

//		hahalLinkedList2.add(new SourceDest("aaa", "ppqqq"));
//		hahalLinkedList2.add(new SourceDest("wwwww", "ppqqq"));
//		hahalLinkedList2.add(new SourceDest("qqqq", "ppqqq"));
//		hahalLinkedList2.add(new SourceDest("rrrrr", "ppqqq"));
//		hahalLinkedList2.add(new SourceDest("qqqq", "ppqqq"));
//		hahalLinkedList2.add(new SourceDest("qqqq", "ppqqq"));
//		hahalLinkedList2.add(new SourceDest("qqqq", "ppqqq"));
//		hahalLinkedList2.add(new SourceDest("qqqq", "ppqqq"));
//		hahalLinkedList2.add(new SourceDest("qqqq", "ppqqq"));
//		linkArray = library.Converted(hahalLinkedList2);
//		lFile2.writeLinkArray(linkArray);
//		for (SourceDest s : hahalLinkedList2) {
//			System.out.println(s.getL1() + " " + s.getL2() + " " + s.getLocalDate());
//		}
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));
		setLayout(null);

		JLabel lblReport = new JLabel("Report");
		lblReport.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblReport.setBounds(420, 11, 102, 45);
		add(lblReport);

		tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		table = new JTable(tableModel);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		Color ivory = new Color(255, 255, 208);
		table.setBackground(ivory);
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(Color.white);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setSize(1000, 500);
		table.setBounds(34, 31, 537, 167);
		table.setRowHeight(100);
		table.getTableHeader().setPreferredSize(new Dimension(100, 40));
		Font f = new Font("Arial", Font.BOLD, 25);
		table.getTableHeader().setFont(f);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 93, 876, 297);
		scrollPane.setEnabled(false);
		add(scrollPane);

		
		//Month dropdownlist
		comboBox = new JComboBox(new String[] { "<Month>", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" });
		comboBox.setBounds(12, 58, 130, 22);
		comboBox.addActionListener(event -> {
			if (comboBox.getSelectedIndex() != 0) {
				comboBox_1.setEnabled(true);
				if (comboBox_1.getSelectedIndex() != 0)
					load(Integer.parseInt(comboBox.getSelectedItem().toString()),
							Integer.parseInt(comboBox_1.getSelectedItem().toString()));
			} else {
				comboBox_1.setEnabled(false);
			}
		});
		add(comboBox);

		//Year dropdown List
		comboBox_1 = new JComboBox(new String[] { "<Year>", "2018", "2019", "2020", "2021", "2022" });
		comboBox_1.setEnabled(false);
		comboBox_1.setBounds(169, 58, 195, 22);
		comboBox_1.addActionListener(event -> {
			if (comboBox_1.getSelectedIndex() != 0) {
				load(Integer.parseInt(comboBox.getSelectedItem().toString()),
						Integer.parseInt(comboBox_1.getSelectedItem().toString()));
			} else {
				load(0, 0);
			}
		});
		add(comboBox_1);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> mainFrame.changePanel(new MainMenu(mainFrame)));
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnBack.setBounds(771, 397, 97, 25);
		add(btnBack);

		for (int i = 0; i < table.getColumnCount(); i++) {
			int j = 0;
			TableColumn column = table.getColumnModel().getColumn(i);
			if (i >= 0) {
				if (i == j) {
					column.setPreferredWidth(100);
				}
			}
			j++;
		}

		tableModel.addColumn("Source");
		tableModel.addColumn("Destionation");
		tableModel.addColumn("Number of Visit");

		load(0, 0);

		for (int i = 0; i < table.getColumnCount(); i++) {
			int j = 0;
			TableColumn column = table.getColumnModel().getColumn(i);
			if (i >= 0) {
				if (i == j)
					column.setPreferredWidth(50);
			}
			j++;
		}
	}
//load the table 
	public void load(int month, int year) {
		String[] location = new String[1000];
		String[] destination = new String[1000];
		linkArray = lFile2.readLinkArray();
		hahalLinkedList2 = library.Convertion(linkArray);
		tableModel.setRowCount(0);
		if (month == 0 && year == 0) {//if the month and year = 0
			int size = hahalLinkedList2.getNumberOfEntries();
			for (int i = 1; i <= size; i++) { // for entire array
				String currVal = hahalLinkedList2.getEntry(i).getL1();
				String currVal2 = hahalLinkedList2.getEntry(i).getL2();// select current value
				int count2 = 1; // and set count to 1
				if (currVal != null) { // if value not seen
					for (int j = i + 1; j <= size; j++) { // for rest of array
						if (hahalLinkedList2.getEntry(j).getL1().equalsIgnoreCase(currVal)
								&& hahalLinkedList2.getEntry(j).getL2().equalsIgnoreCase(currVal2)) { // if same as
																										// current Value
							hahalLinkedList2.remove(j);// mark as seen
							count2++; // and count it
							size--;
						}
					}
					System.out.println(currVal + currVal2 + count2);
					tableModel.addRow(new String[] { currVal, currVal2, Integer.toString(count2) });
//					System.out.print("Source : " + currVal + " Dest: " + currVal2 + " Count : " + count2 + "\n");
				}
			}
		} else {
			int size = hahalLinkedList2.getNumberOfEntries();
			for (int i = 1; i <= size; i++) { // for entire array
				if (hahalLinkedList2.getEntry(i).getLocalDate().getMonthValue() == month
						&& hahalLinkedList2.getEntry(i).getLocalDate().getYear() == year) {
					String currVal = hahalLinkedList2.getEntry(i).getL1();
					String currVal2 = hahalLinkedList2.getEntry(i).getL2();// select current value
					int count2 = 1; // and set count to 1
					if (currVal != null) { // if value not seen
						for (int j = i + 1; j <= size; j++) { // for rest of array
							if (hahalLinkedList2.getEntry(j).getL1().equalsIgnoreCase(currVal)
									&& hahalLinkedList2.getEntry(j).getL2().equalsIgnoreCase(currVal2)) { // if
								// current Value
								hahalLinkedList2.remove(j);// mark as seen
								count2++; // and count it
								size--;
							}
						}
						System.out.println(currVal + currVal2 + count2);
						tableModel.addRow(new String[] { currVal, currVal2, Integer.toString(count2) });
					}
				}
			}

		}

	}

	// put the function below to the search button
	

}
