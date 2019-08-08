package travelBug.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.color.ICC_ColorSpace;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.omg.CORBA.portable.ValueBase;

import travelBug.library.LinkArray;
import travelBug.library.ReadWriteFile;
import travelBug.library.SinglyLinkedList;
import travelBug.library.library;
import travelBug.obj.Location;
import travelBug.obj.SourceDest;
import travelBug.obj.sourceDest2;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class AdminReport extends JPanel {
	private static final long serialVersionUID = 1L;
	private final UIControl mainFrame;
	private JTable table;
	private DefaultTableModel tableModel;
	private Vector<?> vector;
	private JScrollPane scrollPane;
//	private singlyLinkedList<SourceDest> hahalLinkedList = new singlyLinkedList<SourceDest>();
//	private ReadWriteFile<SourceDest> lFile = new ReadWriteFile<SourceDest>("SourceDes.txt", SourceDest.class);
	private SinglyLinkedList<sourceDest2> hahalLinkedList2 = new SinglyLinkedList<sourceDest2>();
	private ReadWriteFile<sourceDest2> lFile2 = new ReadWriteFile<sourceDest2>("sourceDes.txt", sourceDest2.class);
	private LinkArray<sourceDest2> linkArray = new LinkArray<sourceDest2>();
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JButton btnBack;

	public AdminReport(UIControl parent) {
		super();
		this.mainFrame = parent;
		SourceDest dest = new SourceDest("asd", "pp");
		dest.addCount();
		// read txtile
		linkArray = lFile2.readLinkArray();
		hahalLinkedList2 = library.Convertion(linkArray);

//		hahalLinkedList2.add(new sourceDest2("aaa", "ppqqq"));
//		hahalLinkedList2.add(new sourceDest2("wwwww", "ppqqq"));
//		hahalLinkedList2.add(new sourceDest2("qqqq", "ppqqq"));
//		hahalLinkedList2.add(new sourceDest2("rrrrr", "ppqqq"));
//		hahalLinkedList2.add(new sourceDest2("qqqq", "ppqqq"));
//		hahalLinkedList2.add(new sourceDest2("qqqq", "ppqqq"));
//		hahalLinkedList2.add(new sourceDest2("qqqq", "ppqqq"));
//		hahalLinkedList2.add(new sourceDest2("qqqq", "ppqqq"));
//		hahalLinkedList2.add(new sourceDest2("qqqq", "ppqqq"));
//		linkArray = library.Converted(hahalLinkedList2);
//	    lFile2.writeLinkArray(linkArray);
		for (sourceDest2 s : hahalLinkedList2) {
			System.out.println(s.getL1() + " " + s.getL2() + " " + s.getLocalDate());
		}
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
		table.getTableHeader().setBackground(Color.MAGENTA);
		table.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				if (arg0.getClickCount() == 2) {
//					tableModel = (DefaultTableModel) table.getModel();
//					int SelectedRowIndex = table.getSelectedRow();
//					vector = (Vector<?>) tableModel.getDataVector().elementAt(SelectedRowIndex);
//					if (vector != null) {
////	    			Redirect the thing to modify location
//						SwingUtilities.invokeLater(() -> mainFrame
//								.changePanel(new ModifyLocation(mainFrame, vector.elementAt(0).toString())));
//					}
//				}
//			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		table.getTableHeader().setReorderingAllowed(false);
//		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setSize(1000, 500);
		table.setBounds(34, 31, 537, 167);
		table.setRowHeight(100);
		table.getTableHeader().setPreferredSize(new Dimension(100,40));
		Font f = new Font("Arial", Font.BOLD, 25);
		table.getTableHeader().setFont(f);
//		contentPane.add(table);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 93, 876, 297);
		scrollPane.setEnabled(false);
		add(scrollPane);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Month>", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBox.setBounds(12, 58, 130, 22);
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("44");
			}
		});
		add(comboBox);

		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"<Year>", "2018", "2019", "2020", "2021", "2022"}));
		comboBox_1.setBounds(169, 58, 195, 22);
		comboBox_1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("7774");
			}
		});
		add(comboBox_1);
		
		btnBack = new JButton("Back");
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

	public void load(int month, int year) {
		String[] location = new String[1000];
		String[] destination = new String[1000];

		tableModel.setRowCount(0);

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
				tableModel.addRow(new String[] { currVal, currVal2, Integer.toString(count2) });
//				System.out.print("Source : " + currVal + " Dest: " + currVal2 + " Count : " + count2 + "\n");
			}
		}
	}

	// put the function below to the search button
	public void addRecord(String source, String destination) {
//		Get the thing from the database into link list
		hahalLinkedList2.add(new sourceDest2(source, destination));
		// write the thing to the database
	}
//	public void compare(String source,String destionation) {
//		//read data from txt file
//		boolean found = false;
//		for(int i =1 ; i<= hahalLinkedList2.getNumberOfEntries(); i ++) {
//			if(hahalLinkedList2.getEntry(i).getL1()== source && hahalLinkedList2.getEntry(i).getL2() == destionation) {
////				hahalLinkedList2.getEntry(i).addCount();
////				found = true;
//			}
//		}
//		if(!found) {
//			hahalLinkedList2.add(new sourceDest2(source,destionation));
//		}
//		// write into text field
//	}
}
