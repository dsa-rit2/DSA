package travelBug.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import travelBug.library.LinkArray;
import travelBug.library.ReadWriteFile;
import travelBug.obj.Company;
import travelBug.obj.TravelLegAccount;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class listTravelLegAccount extends JPanel {

	private final UIControl mainFrame;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private LinkArray<TravelLegAccount> lArray = new LinkArray<TravelLegAccount>();
	private ReadWriteFile<TravelLegAccount> lFile = new ReadWriteFile<TravelLegAccount>("TravelLegAccount.txt", TravelLegAccount.class);
	private LinkArray<Company> cArray = new LinkArray<Company>();
	private ReadWriteFile<Company> cFile = new ReadWriteFile<Company>("Company.txt", Company.class);
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private String shortFormString = null;
	private int indexCompany = 0;
	/**
	 * Launch the application.
	 */

	public listTravelLegAccount(UIControl parent,String companyName) {
		// =================================================Panel Setting====================================//
		super();
		setForeground(Color.RED);
		this.mainFrame = parent;
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));
		setLayout(null);
		// ===============================================Content Component =================================//
		boolean gotCompany = false;
		tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		lArray = lFile.readLinkArray();
		cArray = cFile.readLinkArray();
		for(int i=0;i<cArray.size();i++) {
			if(cArray.getIndexElement(i).getCompanyName().equalsIgnoreCase(companyName)) {
				gotCompany = true;
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
					Vector vector = (Vector) tableModel.getDataVector().elementAt(SelectedRowIndex);
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
		tableModel.addColumn("Username");
		tableModel.addColumn("Password");
		load();

//		table.setShowGrid(false);
		table.setBounds(34, 31, 537, 167);
//		contentPane.add(table);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 60, 876, 330);
		scrollPane.setEnabled(false);
		add(scrollPane);
		
		textField = new JTextField();
		textField.setBounds(76, 34, 335, 22);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(27, 37, 56, 16);
		add(lblSearch);
		
		btnNewButton = new JButton("Add Travelleg Account");
		btnNewButton.addActionListener(event->{
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new AddTravelLegAccount(mainFrame,cArray.getIndexElement(indexCompany).getCompanyName())));
		});
		btnNewButton.setBounds(170, 403, 174, 34);
		add(btnNewButton);
		
		btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(event->{
			//redirect to the page called
//			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new (mainFrame,vector.elementAt(0).toString(),
//					vector.elementAt(1).toString())));
		});
		btnNewButton_1.setBounds(458, 403, 174, 34);
		add(btnNewButton_1);

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
	public void load() {
		tableModel.setRowCount(0);
		for (int i = 0; i < lArray.size(); i++) {
			String[] dataStrings = { lArray.getIndexElement(i).getUsername(),lArray.getIndexElement(i).getPassword()};
//			if()
			//Need to do something 
			tableModel.addRow(dataStrings);
			lArray.getIndexElement(i).print();
		}
	}

}
