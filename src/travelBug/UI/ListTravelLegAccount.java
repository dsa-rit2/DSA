package travelBug.UI;

//=========================
//	Import Package
//=========================
//=========================

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import travelBug.library.LinkArray;
import travelBug.library.ReadWriteFile;
import travelBug.library.library;
import travelBug.obj.Company;
import travelBug.obj.TravelLegAccount;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class ListTravelLegAccount extends JPanel {
	private static final long serialVersionUID = 1L;	// Serializable purpose
	private final UIControl mainFrame;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private LinkArray<TravelLegAccount> lArray = new LinkArray<TravelLegAccount>();
	private ReadWriteFile<TravelLegAccount> lFile = new ReadWriteFile<TravelLegAccount>("TravelLegAccount.txt",
			TravelLegAccount.class);
	private LinkArray<Company> cArray = new LinkArray<Company>();
	private ReadWriteFile<Company> cFile = new ReadWriteFile<Company>("Company.txt", Company.class);
	private JTextField textField;
	private JButton btnNewButton, btnNewButton_1, btnNewButton_2, btnNewButton_3;
	private int indexCompany = 0;
	private Vector<?> vector;


	public ListTravelLegAccount(UIControl parent, String companyName) {
		// ==================== JPanel Setting =====================
		super();
		setForeground(Color.RED);
		this.mainFrame = parent;
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));
		setLayout(null);
		
		tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		lArray = lFile.readLinkArray();
		cArray = cFile.readLinkArray();
		for (int i = 0; i < cArray.size(); i++) {
			if (cArray.getIndexElement(i).getCompanyName().equalsIgnoreCase(companyName)) {
				cArray.getIndexElement(i).print();
				indexCompany = i;
				break;
			}
		}
		table = new JTable(tableModel);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {

					tableModel = (DefaultTableModel) table.getModel();

					int SelectedRowIndex = table.getSelectedRow();
					vector = (Vector<?>) tableModel.getDataVector().elementAt(SelectedRowIndex);
					if (vector != null) {
//	    				//redirect to the modify travellegaccount
					}
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		tableModel.addColumn("Username");
		tableModel.addColumn("Password");
		load(null);

//		table.setShowGrid(false);
		table.setBounds(34, 31, 537, 167);
//		contentPane.add(table);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 119, 876, 271);
		scrollPane.setEnabled(false);
		add(scrollPane);

		textField = new JTextField();
		textField.setBounds(74, 84, 335, 22);
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

		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(25, 87, 56, 16);
		add(lblSearch);
		
		JLabel lblTravellegAccountList = new JLabel("Travelleg Account List");
		lblTravellegAccountList.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTravellegAccountList.setBounds(298, 13, 382, 42);
		add(lblTravellegAccountList);
		
		for (int i = 0; i < table.getColumnCount(); i++) {
			int j = 0;
			TableColumn column = table.getColumnModel().getColumn(i);
			if (i >= 0) {
				if (i == j)
					column.setPreferredWidth(100);
			}
			j++;
		}
		
		// ==============================================Button =====================================//
		btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(event -> {
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(
					new AddTravelLegAccount(mainFrame, cArray.getIndexElement(indexCompany).getCompanyName())));
		});
		btnNewButton.setBounds(99, 403, 123, 34);
		add(btnNewButton);

		btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(event -> {
			library.dialogMessage("The page will redirect to company list");
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCompany(mainFrame)));
		});
		btnNewButton_1.setBounds(634, 403, 123, 34);
		add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Modify");
		btnNewButton_2.addActionListener(event->{
			tableModel = (DefaultTableModel) table.getModel();
			int SelectedRowIndex = table.getSelectedRow();
			if (SelectedRowIndex >= 0) {
				vector = (Vector<?>) tableModel.getDataVector().elementAt(SelectedRowIndex);
				if (vector != null) {
					library.dialogMessage("The page will redirect to modify travelleg account");
					SwingUtilities.invokeLater(() -> mainFrame.changePanel(new EditTravelLegAccount(mainFrame,companyName,vector.elementAt(0).toString())));
				}
			} else {
				library.dialogMessage("Please choose one location to modify");
			}
		});
		btnNewButton_2.setBounds(284, 403, 123, 34);
		add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Delete");
		btnNewButton_3.addActionListener(event->{
			tableModel = (DefaultTableModel) table.getModel();
			int SelectedRowIndex = table.getSelectedRow();
			if (SelectedRowIndex >= 0) {
				vector = (Vector<?>) tableModel.getDataVector().elementAt(SelectedRowIndex);
				if (vector != null) {
//    			Redirect the thing to modify location
					int choice = JOptionPane.showConfirmDialog(null, "Do you want to delete it?", "Confirm",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (choice == JOptionPane.YES_NO_OPTION) {
						for (int i = 0; i < lArray.size(); i++) {
							if (lArray.getIndexElement(i).getUsername().equalsIgnoreCase(vector.elementAt(0).toString())
									&& lArray.getIndexElement(i).getPassword()
											.equalsIgnoreCase(vector.elementAt(1).toString())) {
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
		});
		btnNewButton_3.setBounds(458, 403, 123, 34);
		add(btnNewButton_3);

	}

	public void load(String anythinString) {
		if (anythinString == null) {
			tableModel.setRowCount(0);			
			for (int i = 0; i < lArray.size(); i++) {
				if (lArray.getIndexElement(i).getUsername()
						.contains(cArray.getIndexElement(indexCompany).getShortForm() + ".")) {
					String[] dataStrings = { lArray.getIndexElement(i).getUsername(),
							lArray.getIndexElement(i).getPassword() };
					tableModel.addRow(dataStrings);
				}
			}
		} else {
			anythinString = anythinString.toUpperCase();
			tableModel.setRowCount(0);
			for (int i = 0; i < lArray.size(); i++) {
				if (lArray.getIndexElement(i).getUsername()
						.contains(cArray.getIndexElement(indexCompany).getShortForm() + ".") && lArray.getIndexElement(i).getUsername().toUpperCase()
						.contains("." + anythinString) ) {
					String[] dataStrings = { lArray.getIndexElement(i).getUsername(),
							lArray.getIndexElement(i).getPassword() };
					tableModel.addRow(dataStrings);
				}
			}
		}
	}
}
