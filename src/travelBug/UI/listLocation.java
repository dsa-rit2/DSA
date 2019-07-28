package travelBug.UI;

//=========================
//	Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================

import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class listLocation extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private LinkArray<Location> lArray = new LinkArray<Location>();
	private ReadWriteFile<Location> lFile = new ReadWriteFile<Location>("Location.txt", Location.class);
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private Vector vector;
	private JButton btnDelete;
	private JButton btnModify;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					listLocation frame = new listLocation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public listLocation() {
		// ======================== Jpanel setting ================================//
		setBounds(100, 100, 620, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		// ======================== Content component ================================//
		JTableHeader header = new JTableHeader();
		tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		lArray = lFile.readLinkArray();

		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {

					tableModel = (DefaultTableModel) table.getModel();

					int SelectedRowIndex = table.getSelectedRow();
					vector = (Vector) tableModel.getDataVector().elementAt(SelectedRowIndex);
					if (vector != null) {
//	    			Redirect the thing to modify location
						ModifyLocation frame = new ModifyLocation(vector.elementAt(0).toString(), vector.elementAt(2).toString());
						frame.setVisible(true);
					}

				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableModel.addColumn("Location");
		tableModel.addColumn("Continent");
		tableModel.addColumn("State");
		tableModel.addColumn("Country");
		tableModel.addColumn("Type");
		load();

//		table.setShowGrid(false);
		table.setBounds(34, 31, 537, 167);
//		contentPane.add(table);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(29, 31, 561, 193);
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

		JLabel lblNewLabel = new JLabel("List Country");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(34, 2, 236, 25);
		contentPane.add(lblNewLabel);

		// ======================== Button ================================//

		JButton btnNewButton = new JButton("Add Location");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddLocation iAddLocation = new AddLocation();
				iAddLocation.setVisible(true);
			}
		});
		btnNewButton.setBounds(53, 237, 121, 25);
		contentPane.add(btnNewButton);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel = (DefaultTableModel) table.getModel();
				int SelectedRowIndex = table.getSelectedRow();
				if (SelectedRowIndex > 0) {
					vector = (Vector) tableModel.getDataVector().elementAt(SelectedRowIndex);
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
							load();
						}
					}
				} else {
					library.dialogMessage("Please choose one location to delete");
				}

			}
		});

		btnDelete.setBounds(313, 237, 97, 25);
		contentPane.add(btnDelete);

		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel = (DefaultTableModel) table.getModel();

				int SelectedRowIndex = table.getSelectedRow();
				if (SelectedRowIndex >= 0) {
					vector = (Vector) tableModel.getDataVector().elementAt(SelectedRowIndex);
					if (vector != null) {
//	    			Redirect the thing to modify location
						ModifyLocation frame = new ModifyLocation(vector.elementAt(0).toString(), vector.elementAt(2).toString());
						frame.setVisible(true);
					}
				} else {
					library.dialogMessage("Please choose one location to modify");
				}

			}
		});
		btnModify.setBounds(193, 237, 97, 25);
		contentPane.add(btnModify);
	}

	// =============================== Additional function
	// ============================//
	public void load() {
		tableModel.setRowCount(0);
		for (int i = 0; i < lArray.size(); i++) {
			String[] dataStrings = { lArray.getIndexElement(i).getName(), lArray.getIndexElement(i).getContinent(),
					lArray.getIndexElement(i).getState(), lArray.getIndexElement(i).getCountry(),
					lArray.getIndexElement(i).getType() };
			tableModel.addRow(dataStrings);
			lArray.getIndexElement(i).print();
		}
	}

//	    public boolean isCellEditable(int row, int column) {
//	       //all cells false
//	       return false;
//	    }
}
