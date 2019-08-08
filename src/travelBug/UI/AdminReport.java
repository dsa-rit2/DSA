package travelBug.UI;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.omg.CORBA.portable.ValueBase;

import travelBug.library.ReadWriteFile;
import travelBug.library.SinglyLinkedList;
import travelBug.obj.Location;
import travelBug.obj.SourceDest;
import travelBug.obj.sourceDest2;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

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
	private ReadWriteFile<sourceDest2> lFile2 = new ReadWriteFile<sourceDest2>("sourceDes.txt2", sourceDest2.class);
	
	public AdminReport(UIControl parent) {
		super();
		this.mainFrame = parent;
		SourceDest dest = new SourceDest("asd", "pp");
		dest.addCount();
		//read txtile
		hahalLinkedList2.add(new sourceDest2("aaa", "ppqqq"));
		hahalLinkedList2.add(new sourceDest2("wwwww", "ppqqq"));
		hahalLinkedList2.add(new sourceDest2("qqqq", "ppqqq"));
		hahalLinkedList2.add(new sourceDest2("rrrrr", "ppqqq"));
		hahalLinkedList2.add(new sourceDest2("qqqq", "ppqqq"));
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));
		setLayout(null);

		JLabel lblReport = new JLabel("Report");
		lblReport.setFont(new Font("Tahoma", Font.BOLD, 14));
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
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
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setBounds(34, 31, 537, 167);
		table.setRowHeight(100);
//		contentPane.add(table);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 93, 876, 297);
		scrollPane.setEnabled(false);
		add(scrollPane);

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

//		load(null);
		for (int i = 0; i < table.getColumnCount(); i++) {
			int j = 0;
			TableColumn column = table.getColumnModel().getColumn(i);
			if (i >= 0) {
				if (i == j)
					column.setPreferredWidth(100);
				
			}
			j++;
		}
	}

//	public void load(int month , int year,String Tdestination,String Tsource) {
//		int count = 1;
//		String [] location = new String[1000];
//		String []destination = new String[1000];
//		
//		tableModel.setRowCount(0);
//		
//		for(int j=1; j <= hahalLinkedList2.getNumberOfEntries() ; j++) {
//			 location [j]=hahalLinkedList2.getEntry(j).getL1();
//			 destination[j] = hahalLinkedList2.getEntry(j).getL2();
//		}
//		
		
//		for(int i =1; i<= hahalLinkedList2.getNumberOfEntries() ; i++) {
//			          
//			if(month == hahalLinkedList2.getEntry(i).getLocalDate().getMonth() && year == hahalLinkedList2.getEntry(i).getLocalDate().getYear() ) {
//				if(location[i]= Tsource && destionation[i] = Tdestination) {
//					location[i] = null;
//					destionation[i]= null;
//					count++;
//				}
//			}
//		}
//		if (anyString == null) {
//			for (int i = 0; i < hahalLinkedList.getNumberOfEntries(); i++) {
//				String[] dataStrings = {hahalLinkedList.getEntry(i).getL1(),hahalLinkedList.getEntry(i).getL2(),Integer.toString(hahalLinkedList.getEntry(i).getCount())
//						};
//				tableModel.addRow(dataStrings);
//			}
//		} else {
//			anyString = anyString.toUpperCase();
//			for (int i = 0; i < hahalLinkedList.getNumberOfEntries(); i++) {
//				if (hahalLinkedList.getEntry(i).getL1().toUpperCase().matches(anyString + ".*")) {
//					String[] dataStrings = {hahalLinkedList.getEntry(i).getL1(),
//							hahalLinkedList.getEntry(i).getL2(),Integer.toString(hahalLinkedList.getEntry(i).getCount())};
//					tableModel.addRow(dataStrings);
//				}
//
//			}
//		}
		//
//		for (int i = 1; i <= hahalLinkedList.getNumberOfEntries(); i++) {
//			String[] dataStrings = { hahalLinkedList.getEntry(i).getL1(), hahalLinkedList.getEntry(i).getL2(),
//					Integer.toString(hahalLinkedList.getEntry(i).getCount()) };
//			tableModel.addRow(dataStrings);
//		}
//	}
//	
	// put the function below to the search button
	public void compare(String source,String destionation) {
		//read data from txt file
		boolean found = false;
		for(int i =1 ; i<= hahalLinkedList2.getNumberOfEntries(); i ++) {
			if(hahalLinkedList2.getEntry(i).getL1()== source && hahalLinkedList2.getEntry(i).getL2() == destionation) {
//				hahalLinkedList2.getEntry(i).addCount();
//				found = true;
			}
		}
		if(!found) {
			hahalLinkedList2.add(new sourceDest2(source,destionation));
		}
		// write into text field
	}
}
