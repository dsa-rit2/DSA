package travelBug.UI;

//=========================
//		Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================

import java.awt.*;
import javax.swing.DefaultListModel;
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

public class TravelLegMaintenance extends JFrame {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					TravelLegMaintenance frame = new TravelLegMaintenance();
					frame.setVisible(true);
					frame.setTitle("Travel Leg Maintenance");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	private static final long serialVersionUID = 5629499624569369278L;
	private JPanel contentPane;
	private JTextField tfUsername;
	private JTextField txtPassword;
	private LinkArray<TravelLegInfo> tArray = new LinkArray<TravelLegInfo>();
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

	public TravelLegMaintenance() {
		tArray = tFile.readLinkArray();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfSearch = new JTextField();
		tfSearch.setBounds(69, 27, 116, 22);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);

		lblSearchJLabel = new JLabel("Search :");
		lblSearchJLabel.setBounds(8, 30, 49, 16);
		contentPane.add(lblSearchJLabel);

		defaultTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(defaultTableModel);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {

					defaultTableModel = (DefaultTableModel) table.getModel();

					int SelectedRowIndex = table.getSelectedRow();
					vector = (Vector) defaultTableModel.getDataVector().elementAt(SelectedRowIndex);
					if (vector != null) {
						TravelLegModify travelLegModify = new TravelLegModify(vector, vector.elementAt(0).toString());
						dispose();
						travelLegModify.setVisible(true);
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

		updateLabel(null);

		String[] strings;
		// Create the first row
		// Insert a row at position p

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(192, 10, 578, 416);
		getContentPane().add(scrollPane);

		for (int i = 0; i < table.getColumnCount(); i++) {
			int j = 0;
			TableColumn column = table.getColumnModel().getColumn(i);
			if (i >= 0) {
				if (i == j)
					column.setPreferredWidth(100);
			}
			j++;
		}

		JButton btnNewButton = new JButton("Reset");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfSearch.setText("");
				updateLabel(null);
			}
		});
		btnNewButton.setBounds(12, 78, 87, 25);
		contentPane.add(btnNewButton);

		tfSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateLabel(tfSearch.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateLabel(tfSearch.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateLabel(tfSearch.getText());
			}
		});

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
								tArray.getIndexElement(j).gettoTime(), tArray.getIndexElement(j).getMode() });
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
									tArray.getIndexElement(j).getMode() });

				}
			}
		}
	}
}
