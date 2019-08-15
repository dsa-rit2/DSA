package travelBug.UI;

//=========================
//	Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================

import java.awt.*;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListLocation extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private LinkArray<Location> lArray = new LinkArray<Location>();
	private ReadWriteFile<Location> lFile = new ReadWriteFile<Location>("Location.txt", Location.class);
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private Vector<?> vector;
	private JButton btnDelete, btnContinent, btnCountry, btnState, btnReset;
	private JButton btnModify;
	private final UIControl mainFrame;
	private JButton btnBack;
	private JTextField textField;
	private JLabel lblNewLabel_1;

	public ListLocation(UIControl parent) {
		super();
		this.mainFrame = parent;
		// ======================== Jpanel setting ==============================
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));
		
		// ==========================Get the data==================================
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
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					tableModel = (DefaultTableModel) table.getModel();
					int SelectedRowIndex = table.getSelectedRow();
					vector = (Vector<?>) tableModel.getDataVector().elementAt(SelectedRowIndex);
					if (vector != null) {
//	    			Redirect the thing to modify location
						SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ModifyLocation(mainFrame, vector.elementAt(0).toString())));
					}
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		tableModel.addColumn("Location");
		tableModel.addColumn("Continent");
		tableModel.addColumn("State");
		tableModel.addColumn("Country");
		tableModel.addColumn("Type");
		
		lArray = lFile.readLinkArray();
		SinglyLinkedList<Location> oArray = library.Convertion(lArray);
		SortedLinkedList<Location> tArrayLinkedList = new SortedLinkedList<Location>(oArray,
				Comparator.comparing(Location::getName));
		lArray.makeEmpty();
		for (Location location : tArrayLinkedList) {
			lArray.addItem(location);
		}

		load(null);

		table.setBounds(34, 31, 537, 167);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 93, 876, 297);
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

		JLabel lblNewLabel = new JLabel("List Location");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(360, 13, 200, 35);
		add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField.setBounds(124, 55, 299, 27);
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				load(textField.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				load(textField.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				load(textField.getText());
			}
		});
		add(textField);
		textField.setColumns(10);

		lblNewLabel_1 = new JLabel("Search:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(50, 52, 78, 32);
		add(lblNewLabel_1);

		// ======================== Button ===========================
		JButton btnNewButton = new JButton("Add Location");
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnNewButton.addActionListener(event -> SwingUtilities.invokeLater(() -> mainFrame.changePanel(new AddLocation(mainFrame))));
		btnNewButton.setBounds(12, 403, 130, 35);
		add(btnNewButton);

		btnContinent = new JButton("Continent");
		btnContinent.addActionListener(event -> {
			btnCountry.setEnabled(true);
			btnState.setEnabled(true);
			btnContinent.setEnabled(false);
			load(textField.getText());
			textField.requestFocus();
		});
		btnContinent.setBounds(494, 55, 97, 25);
		add(btnContinent);

		btnCountry = new JButton("Country");
		btnCountry.addActionListener(event -> {
			btnCountry.setEnabled(false);
			btnState.setEnabled(true);
			btnContinent.setEnabled(true);
			load(textField.getText());
			textField.requestFocus();
		});
		btnCountry.setBounds(590, 55, 97, 25);
		add(btnCountry);

		btnState = new JButton("State");
		btnState.addActionListener(event -> {
			btnCountry.setEnabled(true);
			btnState.setEnabled(false);
			btnContinent.setEnabled(true);
			load(textField.getText());
			textField.requestFocus();
		});
		btnState.setBounds(687, 55, 97, 25);
		add(btnState);

		btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.RED);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel = (DefaultTableModel) table.getModel();
				int SelectedRowIndex = table.getSelectedRow();
				if (SelectedRowIndex >= 0) {
					vector = (Vector<?>) tableModel.getDataVector().elementAt(SelectedRowIndex);
					if (vector != null) {
//	    			Redirect the thing to modify location
						int choice = JOptionPane.showConfirmDialog(null, "Do you want to delete it?", "Confirm",
								JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if (choice == JOptionPane.YES_NO_OPTION) {
							for (int i = 0; i < lArray.size(); i++) {
								if (lArray.getIndexElement(i).getName().equalsIgnoreCase(vector.elementAt(0).toString())
										&& lArray.getIndexElement(i).getState()
												.equalsIgnoreCase(vector.elementAt(2).toString())) {
									lArray.deleteIndexItem(i);
								}
							}
							lFile.writeLinkArray(lArray);
							load(null);
						}
					}
				} else {
					library.dialogMessage("Please choose one location to delete");
				}

			}
		});

		btnDelete.setBounds(596, 403, 130, 35);
		add(btnDelete);

		btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel = (DefaultTableModel) table.getModel();

				int SelectedRowIndex = table.getSelectedRow();
				if (SelectedRowIndex >= 0) {
					vector = (Vector<?>) tableModel.getDataVector().elementAt(SelectedRowIndex);
					if (vector != null) {
//	    			Redirect the thing to modify location
						SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ModifyLocation(mainFrame, vector.elementAt(0).toString())));
					}
				} else {
					library.dialogMessage("Please choose one location to modify");
				}

			}
		});
		btnModify.setBounds(443, 403, 130, 35);
		add(btnModify);

		btnBack = new JButton("Back");
		btnBack.addActionListener(event -> SwingUtilities.invokeLater(() -> mainFrame.changePanel(new MainMenu(mainFrame))));
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnBack.setBounds(758, 403, 130, 34);
		add(btnBack);

		btnReset = new JButton("Reset");
		btnReset.addActionListener(event -> {
			btnContinent.setEnabled(true);
			btnCountry.setEnabled(true);
			btnState.setEnabled(true);
			load(null);
			textField.requestFocus();
		});
		btnReset.setForeground(new Color(0, 0, 255));
		btnReset.setBounds(787, 55, 85, 25);
		add(btnReset);
	}

	// ========================= Additional function ============================
	public void load(String anyString) {
		tableModel.setRowCount(0);
		if (anyString == null) {
			for (int i = 0; i < lArray.size(); i++) {
				String[] dataStrings = { lArray.getIndexElement(i).getName(), lArray.getIndexElement(i).getContinent(),
						lArray.getIndexElement(i).getState(), lArray.getIndexElement(i).getCountry(),
						library.getTypeString(lArray.getIndexElement(i).getType()) };
				tableModel.addRow(dataStrings);
			}
		} else {
			anyString = anyString.toUpperCase();
			if (!btnContinent.isEnabled()) {
				for (int i = 0; i < lArray.size(); i++) {
					if (lArray.getIndexElement(i).getContinent().toUpperCase().matches(anyString + ".*")) {
						String[] dataStrings = { lArray.getIndexElement(i).getName(),
								lArray.getIndexElement(i).getContinent(), lArray.getIndexElement(i).getState(),
								lArray.getIndexElement(i).getCountry(),
								library.getTypeString(lArray.getIndexElement(i).getType()) };
						tableModel.addRow(dataStrings);
					}
				}
			} else if (!btnCountry.isEnabled()) {
				for (int i = 0; i < lArray.size(); i++) {
					if (lArray.getIndexElement(i).getCountry().toUpperCase().matches(anyString + ".*")) {
						String[] dataStrings = { lArray.getIndexElement(i).getName(),
								lArray.getIndexElement(i).getContinent(), lArray.getIndexElement(i).getState(),
								lArray.getIndexElement(i).getCountry(),
								library.getTypeString(lArray.getIndexElement(i).getType()) };
						tableModel.addRow(dataStrings);
					}
				}
			} else if (!btnState.isEnabled()) {
				for (int i = 0; i < lArray.size(); i++) {
					if (lArray.getIndexElement(i).getState().toUpperCase().matches(anyString + ".*")) {
						String[] dataStrings = { lArray.getIndexElement(i).getName(),
								lArray.getIndexElement(i).getContinent(), lArray.getIndexElement(i).getState(),
								lArray.getIndexElement(i).getCountry(),
								library.getTypeString(lArray.getIndexElement(i).getType()) };
						tableModel.addRow(dataStrings);
					}
				}
			} else {
				for (int i = 0; i < lArray.size(); i++) {
					if (lArray.getIndexElement(i).getName().toUpperCase().matches(anyString + ".*")) {
						String[] dataStrings = { lArray.getIndexElement(i).getName(),
								lArray.getIndexElement(i).getContinent(), lArray.getIndexElement(i).getState(),
								lArray.getIndexElement(i).getCountry(),
								library.getTypeString(lArray.getIndexElement(i).getType()) };
						tableModel.addRow(dataStrings);
					}
				}
			}

		}

	}
}
