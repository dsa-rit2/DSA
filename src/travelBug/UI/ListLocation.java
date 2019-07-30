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

public class ListLocation extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private LinkArray<Location> lArray = new LinkArray<Location>();
	private ReadWriteFile<Location> lFile = new ReadWriteFile<Location>("Location.txt", Location.class);
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private Vector vector;
	private JButton btnDelete;
	private JButton btnModify;
	private final UIControl mainFrame;

	public ListLocation(UIControl parent) {
		super();
		this.mainFrame = parent;
		// ======================== Jpanel setting ================================//
//		UIControl.titleName = "Location List";
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));


		tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		lArray = lFile.readLinkArray();

		table = new JTable(tableModel);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {

					tableModel = (DefaultTableModel) table.getModel();

					int SelectedRowIndex = table.getSelectedRow();
					vector = (Vector) tableModel.getDataVector().elementAt(SelectedRowIndex);
					if (vector != null) {
//	    			Redirect the thing to modify location
						ModifyLocation frame = new ModifyLocation(mainFrame,vector.elementAt(0).toString(),
								vector.elementAt(2).toString());
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
		scrollPane.setBounds(12, 60, 876, 330);
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
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(12, 13, 200, 35);
		add(lblNewLabel);

		// ======================== Button ================================//

		JButton btnNewButton = new JButton("Add Location");
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnNewButton.addActionListener(event ->{
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new AddLocation(mainFrame)));
		});
		btnNewButton.setBounds(12, 403, 130, 35);
		add(btnNewButton);

		btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.RED);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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

		btnDelete.setBounds(758, 403, 130, 35);
		add(btnDelete);

		btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel = (DefaultTableModel) table.getModel();

				int SelectedRowIndex = table.getSelectedRow();
				if (SelectedRowIndex >= 0) {
					vector = (Vector) tableModel.getDataVector().elementAt(SelectedRowIndex);
					if (vector != null) {
//	    			Redirect the thing to modify location
						ModifyLocation frame = new ModifyLocation(mainFrame,vector.elementAt(0).toString(),
								vector.elementAt(2).toString());
						frame.setVisible(true);
					}
				} else {
					library.dialogMessage("Please choose one location to modify");
				}

			}
		});
		btnModify.setBounds(616, 403, 130, 35);
		add(btnModify);
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

}
