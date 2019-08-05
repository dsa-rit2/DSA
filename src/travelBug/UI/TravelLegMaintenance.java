package travelBug.UI;

//=========================
//		Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.border.MatteBorder;

public class TravelLegMaintenance extends JPanel {

	private static final long serialVersionUID = 5629499624569369278L;
	private JTextField tfUsername;
	private JTextField txtPassword;
	private LinkArray<TravelLegInfo> tArray = new LinkArray<TravelLegInfo>();
	private SortedLinkedList<TravelLeg> sArray = new SortedLinkedList<TravelLeg>();
	private ReadWriteFile<TravelLegInfo> tFile = new ReadWriteFile<TravelLegInfo>("TravelLeg.txt", TravelLegInfo.class);
	private JTextField txtUserNameNum;
	private JTextField textField;
	private JTextField tfSearch;
	private DocumentListener dListener;
	private JLabel lblSearchJLabel;
	private JList lsitJList;
	private DefaultListModel model;
	private DefaultTableModel defaultTableModel;
	private JTable table;
	private Vector vector;
	private final UIControl mainFrame;
	private JButton btnDelete;

	public TravelLegMaintenance(UIControl parent) {
		super();
		this.mainFrame = parent;
		// ========================== Jpanel setting ===========================//
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		// ========================== Content component =========================//
		tArray = tFile.readLinkArray();
		tfSearch = new JTextField();
		tfSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfSearch.setBounds(88, 15, 177, 36);
		add(tfSearch);
		tfSearch.setColumns(10);

		lblSearchJLabel = new JLabel("Search :");
		lblSearchJLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSearchJLabel.setBounds(12, 13, 76, 36);
		add(lblSearchJLabel);

		defaultTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(defaultTableModel);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {

					defaultTableModel = (DefaultTableModel) table.getModel();

					int SelectedRowIndex = table.getSelectedRow();
					vector = (Vector) defaultTableModel.getDataVector().elementAt(SelectedRowIndex);
					if (vector != null) {
						String iDString = vector.elementAt(0).toString();

						SwingUtilities.invokeLater(() -> mainFrame
								.changePanel(new TravelLegModify(vector, vector.elementAt(0).toString(), mainFrame)));

					}

				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		defaultTableModel.addColumn("ID.");
		defaultTableModel.addColumn("Source");
		defaultTableModel.addColumn("Destination");
		defaultTableModel.addColumn("From Date");
		defaultTableModel.addColumn("To Date");
		defaultTableModel.addColumn("From Time");
		defaultTableModel.addColumn("To Time");
		defaultTableModel.addColumn("Transport");
		defaultTableModel.addColumn("Price (RM)");
		defaultTableModel.addColumn("Distance (km)");

		updateLabel(null);

		String[] strings;
		// Create the first row
		// Insert a row at position p

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(8, 59, 837, 323);
		add(scrollPane);

		for (int i = 0; i < table.getColumnCount(); i++) {
			int j = 0;
			TableColumn column = table.getColumnModel().getColumn(i);
			if (i >= 0) {
				if (i == j)
					column.setMinWidth(100);;
			}
			j++;
		}

		tfSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateLabel(tfSearch.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateLabel(tfSearch.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateLabel(tfSearch.getText());
			}
		});

		// =============================== Button ===================================//
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfSearch.setText("");
				updateLabel(null);
			}
		});
		btnNewButton.setBounds(373, 15, 109, 38);
		add(btnNewButton);
		
		JButton btnAddTravelleg = new JButton("Add TravelLeg");
		btnAddTravelleg.addActionListener(event -> {
			JOptionPane.showMessageDialog(null, "The page will redirect to Add New TravelLeg\n");
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new AddTravelLeg(mainFrame)));
		});
		btnAddTravelleg.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddTravelleg.setBounds(8, 395, 157, 42);
		add(btnAddTravelleg);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				defaultTableModel = (DefaultTableModel) table.getModel();
				int SelectedRowIndex = table.getSelectedRow();
				if (SelectedRowIndex > 0) {
					vector = (Vector) defaultTableModel.getDataVector().elementAt(SelectedRowIndex);
					if (vector != null) {
//	    			Redirect the thing to modify location
						int choice = JOptionPane.showConfirmDialog(null, "Do you want to delete it?", "Confirm",
								JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if (choice == JOptionPane.YES_NO_OPTION) {
							for (int i = 0; i < tArray.size(); i++) {
								if (tArray.getIndexElement(i).getrecordNo().equalsIgnoreCase(vector.elementAt(0).toString())){
									tArray.deleteIndexItem(i);
								}
							}
							tFile.writeLinkArray(tArray);
							library.dialogMessage("Record deleted successful!\n");
							SwingUtilities.invokeLater(() -> mainFrame.changePanel(new TravelLegMaintenance(mainFrame)));
						}
					}
				} else {
					library.dialogMessage("Please choose record to delete");
				}

			}
		});
		btnDelete.setForeground(Color.RED);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(513, 395, 157, 42);
		add(btnDelete);
		
		JButton btnSortPrice = new JButton("Cheapest");
		btnSortPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				defaultTableModel = (DefaultTableModel) table.getModel();
				defaultTableModel.setNumRows(0);
				sArray.clear();
				
				for (int j = 1; j <= tArray.size(); j++) {
//					System.out.println(tArray.getIndexElement(j - 1).getrecordNo());
//					System.out.println(tArray.getIndexElement(j - 1).getSource());
//					System.out.println(tArray.getIndexElement(j - 1).getDest());
//					System.out.println(tArray.getIndexElement(j - 1).getMode());
//					System.out.println(tArray.getIndexElement(j - 1).getfromDate());
//					System.out.println(tArray.getIndexElement(j - 1).gettoDate());
//					System.out.println(tArray.getIndexElement(j - 1).getfromTime());
//					System.out.println(tArray.getIndexElement(j - 1).gettoTime());
//					System.out.println(tArray.getIndexElement(j - 1).getDistance());
//					System.out.println(tArray.getIndexElement(j - 1).getPrice());
					
					sArray.add(new TravelLeg(tArray.getIndexElement(j - 1).getMode(),
											tArray.getIndexElement(j - 1).getSource(),
											tArray.getIndexElement(j - 1).getDest(),
											tArray.getIndexElement(j - 1).getPrice(),
											tArray.getIndexElement(j - 1).getDistance(),
											tArray.getIndexElement(j - 1).getfromDate(),
											tArray.getIndexElement(j - 1).gettoDate(),
											tArray.getIndexElement(j - 1).getfromTime(),
											tArray.getIndexElement(j - 1).gettoTime()));				
					}
				for(int i = 1; i <= sArray.getLength(); i++) {
					sArray.getEntry(i).setrecordNo(tArray.getIndexElement(i - 1).getrecordNo());	
					defaultTableModel.insertRow(defaultTableModel.getRowCount(),
							new Object[] { sArray.getEntry(i).getrecordNo(),
									sArray.getEntry(i).getSource(), sArray.getEntry(i).getDest(),
									sArray.getEntry(i).getfromDate(), sArray.getEntry(i).gettoDate(),
									sArray.getEntry(i).getfromTime(), sArray.getEntry(i).gettoTime(),
									sArray.getEntry(i).getMode(), sArray.getEntry(i).getPrice(),
									sArray.getEntry(i).getDistance()});
				}
				}
				
		});
		btnSortPrice.setBounds(641, 23, 97, 25);
		add(btnSortPrice);
		
		JButton btnSortDistance = new JButton("Fastest");
		btnSortDistance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSortDistance.setBounds(736, 23, 97, 25);
		add(btnSortDistance);
	}

	public void updateLabel(String searchItem) {
		if (searchItem == null) {
			defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setNumRows(0);
			for (int j = 0; j < tArray.size(); j++) {
				defaultTableModel.insertRow(j,
						new Object[] { tArray.getIndexElement(j).getrecordNo(), tArray.getIndexElement(j).getSource(),
								tArray.getIndexElement(j).getDest(), tArray.getIndexElement(j).getfromDate(),
								tArray.getIndexElement(j).gettoDate(), tArray.getIndexElement(j).getfromTime(),
								tArray.getIndexElement(j).gettoTime(), tArray.getIndexElement(j).getMode(),
								tArray.getIndexElement(j).getPrice(),tArray.getIndexElement(j).getDistance()});
			}
		} else {
			searchItem = searchItem.toLowerCase();
			defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setNumRows(0);
			for (int j = 0; j < tArray.size(); j++) {
				if (tArray.getIndexElement(j).getSource().toLowerCase().contains(searchItem)
						|| tArray.getIndexElement(j).getDest().toLowerCase().contains(searchItem)
						|| tArray.getIndexElement(j).getfromDate().toString().toLowerCase().contains(searchItem)
						|| tArray.getIndexElement(j).gettoDate().toString().toLowerCase().contains(searchItem)
						|| tArray.getIndexElement(j).getfromTime().toString().toLowerCase().contains(searchItem)
						|| tArray.getIndexElement(j).gettoTime().toString().toLowerCase().contains(searchItem)
						|| tArray.getIndexElement(j).getMode().toLowerCase().contains(searchItem)) {
					defaultTableModel.insertRow(defaultTableModel.getRowCount(),
							new Object[] { tArray.getIndexElement(j).getrecordNo(),
									tArray.getIndexElement(j).getSource(), tArray.getIndexElement(j).getDest(),
									tArray.getIndexElement(j).getfromDate(), tArray.getIndexElement(j).gettoDate(),
									tArray.getIndexElement(j).getfromTime(), tArray.getIndexElement(j).gettoTime(),
									tArray.getIndexElement(j).getMode(), tArray.getIndexElement(j).getPrice(),
									tArray.getIndexElement(j).getDistance()});

				}
			}
		}
	}
}
