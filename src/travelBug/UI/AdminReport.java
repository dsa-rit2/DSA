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

	public AdminReport(UIControl parent) {
		super();
		this.mainFrame = parent;
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

		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setBounds(127, 358, 633, -242);
		add(table);
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
		
		tableModel.addColumn("Location");
		tableModel.addColumn("Continent");
		tableModel.addColumn("State");
		tableModel.addColumn("Country");
		tableModel.addColumn("Type");
		tableModel.addColumn("Number of Choosen");

	}
}
