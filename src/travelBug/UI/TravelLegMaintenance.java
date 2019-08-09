package travelBug.UI;

//=========================
//		Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================
import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.border.MatteBorder;

public class TravelLegMaintenance extends JPanel {

	private static final long serialVersionUID = 5629499624569369278L;
	private LinkArray<TravelLegInfo> tArray = new LinkArray<TravelLegInfo>();
	private ReadWriteFile<TravelLegInfo> tFile = new ReadWriteFile<TravelLegInfo>("TravelLeg.txt", TravelLegInfo.class);
	private SinglyLinkedList<ComparePrice> rArray = new SinglyLinkedList<ComparePrice>();
	private JTextField tfSearch;
	private JScrollPane scrollPane;
	private JLabel lblSearchJLabel;
	private DefaultTableModel defaultTableModel;
	private JTable table;
	private Vector<?> vector;
	private final UIControl mainFrame;
	private JButton btnDelete;
	private JButton btnAddTravelleg;
	private JTextField tfSearch1;
	public String companyString;
	private JLabel lblNewLabel;
	public JLabel lblUsername;

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
		tfSearch.setBounds(210, 0, 177, 36);
		add(tfSearch);
		tfSearch.setColumns(10);
		tfSearch.setText(null);

		tfSearch1 = new JTextField();
		tfSearch1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfSearch1.setColumns(10);
		tfSearch1.setBounds(210, 35, 177, 36);
		add(tfSearch1);
		tfSearch1.setText(null);

		lblSearchJLabel = new JLabel("Source Location       :");
		lblSearchJLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSearchJLabel.setBounds(12, 0, 177, 36);
		add(lblSearchJLabel);

		defaultTableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

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
		defaultTableModel.addColumn("Duration");

		table.setBounds(34, 31, 537, 167);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 84, 876, 306);
		scrollPane.setEnabled(false);
		add(scrollPane);

		for (int i = 0; i < table.getColumnCount(); i++) {
			int j = 0;
			TableColumn column = table.getColumnModel().getColumn(i);
			if (i >= 0) {
				if (i == j)
					column.setPreferredWidth(100);
			}
			j++;
		}
//========================Search by Source Location===========================//
		tfSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateLabel(tfSearch.getText(), tfSearch1.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateLabel(tfSearch.getText(), tfSearch1.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateLabel(tfSearch.getText(), tfSearch1.getText());
			}
		});

//========================Search by Destination Location=========================//		
		tfSearch1.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateLabel(tfSearch.getText(), tfSearch1.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateLabel(tfSearch.getText(), tfSearch1.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateLabel(tfSearch.getText(), tfSearch1.getText());
			}
		});
		// =============================================================================//
		companyString = parent.authUser.getUsername().toString();
		companyString = library.getUsernameShortForm(companyString);
		// =============================== Button ===================================//
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfSearch.setText("");
				updateLabel(null, null);
			}
		});
		btnNewButton.setBounds(513, 46, 109, 38);
		add(btnNewButton);

		btnAddTravelleg = new JButton("Add TravelLeg");
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
				if (SelectedRowIndex >= 0) {
					vector = (Vector) defaultTableModel.getDataVector().elementAt(SelectedRowIndex);
					if (vector != null) {
//	    			Redirect the thing to modify location
						int choice = JOptionPane.showConfirmDialog(null, "Do you want to delete it?", "Confirm",
								JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if (choice == JOptionPane.YES_NO_OPTION) {
							for (int i = 0; i < tArray.size(); i++) {
								if (tArray.getIndexElement(i).getrecordNo()
										.equalsIgnoreCase(vector.elementAt(0).toString())) {
									tArray.deleteIndexItem(i);
								}
							}
							tFile.writeLinkArray(tArray);
							library.dialogMessage("Record deleted successful!\n");
							SwingUtilities
									.invokeLater(() -> mainFrame.changePanel(new TravelLegMaintenance(mainFrame)));
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

				SinglyLinkedList<TravelLegInfo> oArray = library.Convertion(tArray);
				SortedLinkedList<TravelLegInfo> tArrayLinkedList = new SortedLinkedList<TravelLegInfo>(oArray,
						Comparator.comparing(TravelLegInfo::getPrice));

				for (int i = 1; i <= tArrayLinkedList.getLength(); i++) {

					defaultTableModel.insertRow(defaultTableModel.getRowCount(), new Object[] {
							tArrayLinkedList.getEntry(i).getrecordNo(), tArrayLinkedList.getEntry(i).getSource(),
							tArrayLinkedList.getEntry(i).getDest(), tArrayLinkedList.getEntry(i).getfromDate(),
							tArrayLinkedList.getEntry(i).gettoDate(), tArrayLinkedList.getEntry(i).getfromTime(),
							tArrayLinkedList.getEntry(i).gettoTime(), tArrayLinkedList.getEntry(i).getMode(),
							tArrayLinkedList.getEntry(i).getPrice(), tArrayLinkedList.getEntry(i).getDistance(),
							library.convertString((tArrayLinkedList.getEntry(i).getDuration())) });
				}
			}

		});
		btnSortPrice.setBounds(634, 55, 97, 25);
		add(btnSortPrice);

		JButton btnSortDistance = new JButton("Fastest");
		btnSortDistance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defaultTableModel = (DefaultTableModel) table.getModel();
				defaultTableModel.setNumRows(0);

				SinglyLinkedList<TravelLegInfo> oArray = library.Convertion(tArray);
				SortedLinkedList<TravelLegInfo> tArrayLinkedList = new SortedLinkedList<TravelLegInfo>(oArray,
						Comparator.comparing(TravelLegInfo::getDuration));

				for (int i = 1; i <= tArrayLinkedList.getLength(); i++) {
					tArrayLinkedList.getEntry(i);

					defaultTableModel.insertRow(defaultTableModel.getRowCount(), new Object[] {
							tArrayLinkedList.getEntry(i).getrecordNo(), tArrayLinkedList.getEntry(i).getSource(),
							tArrayLinkedList.getEntry(i).getDest(), tArrayLinkedList.getEntry(i).getfromDate(),
							tArrayLinkedList.getEntry(i).gettoDate(), tArrayLinkedList.getEntry(i).getfromTime(),
							tArrayLinkedList.getEntry(i).gettoTime(), tArrayLinkedList.getEntry(i).getMode(),
							tArrayLinkedList.getEntry(i).getPrice(), tArrayLinkedList.getEntry(i).getDistance(),
							library.convertString((tArrayLinkedList.getEntry(i).getDuration())) });
				}

			}
		});
		btnSortDistance.setBounds(728, 55, 97, 25);
		add(btnSortDistance);

		JLabel lblDestinationLocation = new JLabel("Destination Location :");
		lblDestinationLocation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDestinationLocation.setBounds(12, 35, 177, 36);
		add(lblDestinationLocation);

		lblNewLabel = new JLabel("Hello :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(513, 12, 62, 16);
		add(lblNewLabel);

		lblUsername = new JLabel("");
		lblUsername.setForeground(Color.BLUE);
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(587, 12, 91, 16);
		add(lblUsername);
		lblUsername.setText(mainFrame.authUser.getUsername());

		updateLabel(null, null);

	}

	public void updateLabel(String searchItem, String searchString) {
		if (lblUsername.getText().equalsIgnoreCase("Admin")) {
			btnAddTravelleg.setEnabled(false);
			if (searchItem == null && searchString == null) {
				defaultTableModel = (DefaultTableModel) table.getModel();
				defaultTableModel.setNumRows(0);
				SinglyLinkedList<TravelLegInfo> oArray = library.Convertion(tArray);
				SortedLinkedList<TravelLegInfo> tArrayLinkedList = new SortedLinkedList<TravelLegInfo>(oArray,
						Comparator.comparing(TravelLegInfo::getSource));

				for (int j = 1; j <= tArrayLinkedList.getLength(); j++) {
					if (tArrayLinkedList.getEntry(j).getfromDate().isEqual(LocalDate.now())
							|| (tArrayLinkedList.getEntry(j).getfromDate().isAfter(LocalDate.now()))
									&& (tArrayLinkedList.getEntry(j).getfromDate()
											.isBefore(tArrayLinkedList.getEntry(j).gettoDate()))) {
						defaultTableModel.insertRow(defaultTableModel.getRowCount(), new Object[] {
								tArrayLinkedList.getEntry(j).getrecordNo(), tArrayLinkedList.getEntry(j).getSource(),
								tArrayLinkedList.getEntry(j).getDest(), tArrayLinkedList.getEntry(j).getfromDate(),
								tArrayLinkedList.getEntry(j).gettoDate(), tArrayLinkedList.getEntry(j).getfromTime(),
								tArrayLinkedList.getEntry(j).gettoTime(), tArrayLinkedList.getEntry(j).getMode(),
								tArrayLinkedList.getEntry(j).getPrice(), tArrayLinkedList.getEntry(j).getDistance(),
								library.convertString((tArrayLinkedList.getEntry(j).getDuration())) });
					}
				}
			} else if (searchItem != null && searchString == null) {
				searchItem = searchItem.toLowerCase();
				SinglyLinkedList<TravelLegInfo> oArray = library.Convertion(tArray);
				SortedLinkedList<TravelLegInfo> tArrayLinkedList = new SortedLinkedList<TravelLegInfo>(oArray,
						Comparator.comparing(TravelLegInfo::getPrice).thenComparing(TravelLegInfo::getDuration));

				defaultTableModel = (DefaultTableModel) table.getModel();
				defaultTableModel.setNumRows(0);

				for (int j = 1; j <= tArrayLinkedList.getLength(); j++) {
					if (tArrayLinkedList.getEntry(j).getSource().contains(searchItem)) {
						if (tArrayLinkedList.getEntry(j).getfromDate().isEqual(LocalDate.now())
								|| (tArrayLinkedList.getEntry(j).getfromDate().isAfter(LocalDate.now()))
										&& (tArrayLinkedList.getEntry(j).getfromDate()
												.isBefore(tArrayLinkedList.getEntry(j).gettoDate()))) {
							defaultTableModel.insertRow(defaultTableModel.getRowCount(), new Object[] {
									tArrayLinkedList.getEntry(j).getrecordNo(),
									tArrayLinkedList.getEntry(j).getSource(), tArrayLinkedList.getEntry(j).getDest(),
									tArrayLinkedList.getEntry(j).getfromDate(),
									tArrayLinkedList.getEntry(j).gettoDate(),
									tArrayLinkedList.getEntry(j).getfromTime(),
									tArrayLinkedList.getEntry(j).gettoTime(), tArrayLinkedList.getEntry(j).getMode(),
									tArrayLinkedList.getEntry(j).getPrice(), tArrayLinkedList.getEntry(j).getDistance(),
									library.convertString((tArrayLinkedList.getEntry(j).getDuration())) });
						}
					}
				}
			} else if (searchItem == null && searchString != null) {
				searchString = searchString.toLowerCase();
				SinglyLinkedList<TravelLegInfo> oArray = library.Convertion(tArray);
				SortedLinkedList<TravelLegInfo> tArrayLinkedList = new SortedLinkedList<TravelLegInfo>(oArray,
						Comparator.comparing(TravelLegInfo::getPrice).thenComparing(TravelLegInfo::getDuration));

				defaultTableModel = (DefaultTableModel) table.getModel();
				defaultTableModel.setNumRows(0);

				for (int j = 1; j <= tArrayLinkedList.getLength(); j++) {
					if (tArrayLinkedList.getEntry(j).getDest().contains(searchString)) {
						if (tArrayLinkedList.getEntry(j).getfromDate().isEqual(LocalDate.now())
								|| (tArrayLinkedList.getEntry(j).getfromDate().isAfter(LocalDate.now()))
										&& (tArrayLinkedList.getEntry(j).getfromDate()
												.isBefore(tArrayLinkedList.getEntry(j).gettoDate()))) {
							defaultTableModel.insertRow(defaultTableModel.getRowCount(), new Object[] {
									tArrayLinkedList.getEntry(j).getrecordNo(),
									tArrayLinkedList.getEntry(j).getSource(), tArrayLinkedList.getEntry(j).getDest(),
									tArrayLinkedList.getEntry(j).getfromDate(),
									tArrayLinkedList.getEntry(j).gettoDate(),
									tArrayLinkedList.getEntry(j).getfromTime(),
									tArrayLinkedList.getEntry(j).gettoTime(), tArrayLinkedList.getEntry(j).getMode(),
									tArrayLinkedList.getEntry(j).getPrice(), tArrayLinkedList.getEntry(j).getDistance(),
									library.convertString((tArrayLinkedList.getEntry(j).getDuration())) });
						}
					}
				}
			} else {
				searchItem = searchItem.toLowerCase();
				searchString = searchString.toLowerCase();
				SinglyLinkedList<TravelLegInfo> oArray = library.Convertion(tArray);
				SortedLinkedList<TravelLegInfo> tArrayLinkedList = new SortedLinkedList<TravelLegInfo>(oArray,
						Comparator.comparing(TravelLegInfo::getPrice).thenComparing(TravelLegInfo::getDuration));

				defaultTableModel = (DefaultTableModel) table.getModel();
				defaultTableModel.setNumRows(0);

				for (int j = 1; j <= tArrayLinkedList.getLength(); j++) {
					if (tArrayLinkedList.getEntry(j).getDest().toLowerCase().contains(searchString)
							&& tArrayLinkedList.getEntry(j).getSource().toLowerCase().contains(searchItem)) {
						if (tArrayLinkedList.getEntry(j).getfromDate().isEqual(LocalDate.now())
								|| (tArrayLinkedList.getEntry(j).getfromDate().isAfter(LocalDate.now()))
										&& (tArrayLinkedList.getEntry(j).getfromDate()
												.isBefore(tArrayLinkedList.getEntry(j).gettoDate()))) {
							defaultTableModel.insertRow(defaultTableModel.getRowCount(), new Object[] {
									tArrayLinkedList.getEntry(j).getrecordNo(),
									tArrayLinkedList.getEntry(j).getSource(), tArrayLinkedList.getEntry(j).getDest(),
									tArrayLinkedList.getEntry(j).getfromDate(),
									tArrayLinkedList.getEntry(j).gettoDate(),
									tArrayLinkedList.getEntry(j).getfromTime(),
									tArrayLinkedList.getEntry(j).gettoTime(), tArrayLinkedList.getEntry(j).getMode(),
									tArrayLinkedList.getEntry(j).getPrice(), tArrayLinkedList.getEntry(j).getDistance(),
									library.convertString((tArrayLinkedList.getEntry(j).getDuration())) });

						}
					}

				}
			}
		} else {
			if (searchItem == null && searchString == null) {
				defaultTableModel = (DefaultTableModel) table.getModel();
				defaultTableModel.setNumRows(0);
				SinglyLinkedList<TravelLegInfo> oArray = library.Convertion(tArray);
				SortedLinkedList<TravelLegInfo> tArrayLinkedList = new SortedLinkedList<TravelLegInfo>(oArray,
						Comparator.comparing(TravelLegInfo::getSource));

				for (int j = 1; j <= tArrayLinkedList.getLength(); j++) {
					if (tArrayLinkedList.getEntry(j).getCompany().equalsIgnoreCase(companyString)) {
						if (tArrayLinkedList.getEntry(j).getfromDate().isEqual(LocalDate.now())
								|| (tArrayLinkedList.getEntry(j).getfromDate().isAfter(LocalDate.now()))
										&& (tArrayLinkedList.getEntry(j).getfromDate()
												.isBefore(tArrayLinkedList.getEntry(j).gettoDate()))) {
							defaultTableModel.insertRow(defaultTableModel.getRowCount(), new Object[] {
									tArrayLinkedList.getEntry(j).getrecordNo(),
									tArrayLinkedList.getEntry(j).getSource(), tArrayLinkedList.getEntry(j).getDest(),
									tArrayLinkedList.getEntry(j).getfromDate(),
									tArrayLinkedList.getEntry(j).gettoDate(),
									tArrayLinkedList.getEntry(j).getfromTime(),
									tArrayLinkedList.getEntry(j).gettoTime(), tArrayLinkedList.getEntry(j).getMode(),
									tArrayLinkedList.getEntry(j).getPrice(), tArrayLinkedList.getEntry(j).getDistance(),
									library.convertString((tArrayLinkedList.getEntry(j).getDuration())) });
						}
					}
				}
			} else if (searchItem != null && searchString == null) {
				searchItem = searchItem.toLowerCase();
				SinglyLinkedList<TravelLegInfo> oArray = library.Convertion(tArray);
				SortedLinkedList<TravelLegInfo> tArrayLinkedList = new SortedLinkedList<TravelLegInfo>(oArray,
						Comparator.comparing(TravelLegInfo::getPrice).thenComparing(TravelLegInfo::getDuration));

				defaultTableModel = (DefaultTableModel) table.getModel();
				defaultTableModel.setNumRows(0);

				for (int j = 1; j <= tArrayLinkedList.getLength(); j++) {
					if (tArrayLinkedList.getEntry(j).getSource().contains(searchItem)
							&& tArrayLinkedList.getEntry(j).getCompany().toString().equalsIgnoreCase(companyString)) {
						if (tArrayLinkedList.getEntry(j).getfromDate().isEqual(LocalDate.now())
								|| (tArrayLinkedList.getEntry(j).getfromDate().isAfter(LocalDate.now()))
										&& (tArrayLinkedList.getEntry(j).getfromDate()
												.isBefore(tArrayLinkedList.getEntry(j).gettoDate()))) {
							defaultTableModel.insertRow(defaultTableModel.getRowCount(), new Object[] {
									tArrayLinkedList.getEntry(j).getrecordNo(),
									tArrayLinkedList.getEntry(j).getSource(), tArrayLinkedList.getEntry(j).getDest(),
									tArrayLinkedList.getEntry(j).getfromDate(),
									tArrayLinkedList.getEntry(j).gettoDate(),
									tArrayLinkedList.getEntry(j).getfromTime(),
									tArrayLinkedList.getEntry(j).gettoTime(), tArrayLinkedList.getEntry(j).getMode(),
									tArrayLinkedList.getEntry(j).getPrice(), tArrayLinkedList.getEntry(j).getDistance(),
									library.convertString((tArrayLinkedList.getEntry(j).getDuration())) });
						}
					}
				}
			} else if (searchItem == null && searchString != null) {
				searchString = searchString.toLowerCase();
				SinglyLinkedList<TravelLegInfo> oArray = library.Convertion(tArray);
				SortedLinkedList<TravelLegInfo> tArrayLinkedList = new SortedLinkedList<TravelLegInfo>(oArray,
						Comparator.comparing(TravelLegInfo::getPrice).thenComparing(TravelLegInfo::getDuration));

				defaultTableModel = (DefaultTableModel) table.getModel();
				defaultTableModel.setNumRows(0);

				for (int j = 1; j <= tArrayLinkedList.getLength(); j++) {
					if (tArrayLinkedList.getEntry(j).getDest().contains(searchString)
							&& tArrayLinkedList.getEntry(j).getCompany().equalsIgnoreCase(companyString)) {
						if (tArrayLinkedList.getEntry(j).getfromDate().isEqual(LocalDate.now())
								|| (tArrayLinkedList.getEntry(j).getfromDate().isAfter(LocalDate.now()))
										&& (tArrayLinkedList.getEntry(j).getfromDate()
												.isBefore(tArrayLinkedList.getEntry(j).gettoDate()))) {
							defaultTableModel.insertRow(defaultTableModel.getRowCount(), new Object[] {
									tArrayLinkedList.getEntry(j).getrecordNo(),
									tArrayLinkedList.getEntry(j).getSource(), tArrayLinkedList.getEntry(j).getDest(),
									tArrayLinkedList.getEntry(j).getfromDate(),
									tArrayLinkedList.getEntry(j).gettoDate(),
									tArrayLinkedList.getEntry(j).getfromTime(),
									tArrayLinkedList.getEntry(j).gettoTime(), tArrayLinkedList.getEntry(j).getMode(),
									tArrayLinkedList.getEntry(j).getPrice(), tArrayLinkedList.getEntry(j).getDistance(),
									library.convertString((tArrayLinkedList.getEntry(j).getDuration())) });
						}
					}
				}
			} else {
				searchItem = searchItem.toLowerCase();
				searchString = searchString.toLowerCase();
				SinglyLinkedList<TravelLegInfo> oArray = library.Convertion(tArray);
				SortedLinkedList<TravelLegInfo> tArrayLinkedList = new SortedLinkedList<TravelLegInfo>(oArray,
						Comparator.comparing(TravelLegInfo::getPrice).thenComparing(TravelLegInfo::getDuration));

				defaultTableModel = (DefaultTableModel) table.getModel();
				defaultTableModel.setNumRows(0);

				for (int j = 1; j <= tArrayLinkedList.getLength(); j++) {
					if (tArrayLinkedList.getEntry(j).getDest().toLowerCase().contains(searchString)
							&& tArrayLinkedList.getEntry(j).getSource().toLowerCase().contains(searchItem)
							&& tArrayLinkedList.getEntry(j).getCompany().equalsIgnoreCase(companyString)) {
						if (tArrayLinkedList.getEntry(j).getfromDate().isEqual(LocalDate.now())
								|| (tArrayLinkedList.getEntry(j).getfromDate().isAfter(LocalDate.now()))
										&& (tArrayLinkedList.getEntry(j).getfromDate()
												.isBefore(tArrayLinkedList.getEntry(j).gettoDate()))) {
							defaultTableModel.insertRow(defaultTableModel.getRowCount(), new Object[] {
									tArrayLinkedList.getEntry(j).getrecordNo(),
									tArrayLinkedList.getEntry(j).getSource(), tArrayLinkedList.getEntry(j).getDest(),
									tArrayLinkedList.getEntry(j).getfromDate(),
									tArrayLinkedList.getEntry(j).gettoDate(),
									tArrayLinkedList.getEntry(j).getfromTime(),
									tArrayLinkedList.getEntry(j).gettoTime(), tArrayLinkedList.getEntry(j).getMode(),
									tArrayLinkedList.getEntry(j).getPrice(), tArrayLinkedList.getEntry(j).getDistance(),
									library.convertString((tArrayLinkedList.getEntry(j).getDuration())) });

						}
					}

				}
			}
		}
	}
}
