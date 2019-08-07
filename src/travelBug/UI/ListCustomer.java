package travelBug.UI;

//=========================
//	Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class ListCustomer extends JPanel {
	private static final long serialVersionUID = 1L; // Serializable purpose
	private final UIControl mainFrame;
	private LinkArray<Customer> cArray = new LinkArray<Customer>();
	private ReadWriteFile<Customer> cFile = new ReadWriteFile<Customer>("Customer.txt", Customer.class);
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textField;

	public ListCustomer(UIControl parent) {
		// ======================== Jpanel setting ========================//
		super();
		this.mainFrame = parent;
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		// ====================== Content Component =======================//
		tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		cArray = cFile.readLinkArray();

		JLabel lblNewLabel = new JLabel("List Customer");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel.setBounds(341, 13, 221, 36);
		add(lblNewLabel);

		table = new JTable(tableModel);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {

					tableModel = (DefaultTableModel) table.getModel();

					int SelectedRowIndex = table.getSelectedRow();
					Vector<?> vector = (Vector<?>) tableModel.getDataVector().elementAt(SelectedRowIndex);
					if (vector != null) {
//	    			Redirect the thing to modify location
//						SwingUtilities.invokeLater(() -> mainFrame.changePanel(new (mainFrame,vector.elementAt(0).toString(),
//								vector.elementAt(1).toString())));
					}
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		tableModel.addColumn("Name");
		tableModel.addColumn("Username");
		tableModel.addColumn("Password");
		tableModel.addColumn("Phone Number");
		load(null);

		table.setBounds(34, 31, 537, 167);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 119, 876, 271);
		scrollPane.setEnabled(false);
		add(scrollPane);

		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSearch.setBounds(35, 81, 56, 16);
		add(lblSearch);

		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField.setBounds(101, 76, 212, 27);
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

		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(event -> {
			// redirect to the main menu
		});
		btnNewButton_1.setBounds(457, 401, 127, 36);
		add(btnNewButton_1);
	}

	public void load(String anyString) {
		tableModel.setRowCount(0);
		if (anyString == null) {
			for (int i = 0; i < cArray.size(); i++) {
				String[] dataStrings = { cArray.getIndexElement(i).getName(), cArray.getIndexElement(i).getUsername(),
						cArray.getIndexElement(i).getPassword(), cArray.getIndexElement(i).getPhoneNumber() };
				tableModel.addRow(dataStrings);
			}
		} else {
			anyString = anyString.toUpperCase();
			for (int i = 0; i < cArray.size(); i++) {
				if (cArray.getIndexElement(i).getName().toUpperCase().matches(anyString + ".*")) {
					String[] dataStrings = { cArray.getIndexElement(i).getName(),
							cArray.getIndexElement(i).getUsername(), cArray.getIndexElement(i).getPassword(),
							cArray.getIndexElement(i).getPhoneNumber() };
					tableModel.addRow(dataStrings);
				}
			}
		}
	}
}
