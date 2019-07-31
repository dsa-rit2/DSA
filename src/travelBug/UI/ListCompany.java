package travelBug.UI;

//=========================
//	Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListCompany extends JPanel {
	private static final long serialVersionUID = 1L; // Serializable purpose
	private LinkArray<Company> cArray = new LinkArray<Company>();
	private ReadWriteFile<Company> rFile = new ReadWriteFile<Company>("Company.txt", Company.class);
	private JTextField textField;
	private JList displayList;
	DefaultListModel listModel;
	private final UIControl mainFrame;

	public ListCompany(UIControl parent) {
		super();
		this.mainFrame = parent;
		// =================================== Jpanel setting
		// ===========================//
		cArray = rFile.readLinkArray();
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		// =================================== Content component
		// =========================//
		JLabel lblConpanyList = new JLabel("Company List");
		lblConpanyList.setHorizontalAlignment(SwingConstants.CENTER);
		lblConpanyList.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblConpanyList.setBounds(12, 13, 876, 50);
		add(lblConpanyList);

		listModel = new DefaultListModel();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(80, 114, 451, 280);
		add(scrollPane);
		displayList = new JList(listModel);
		displayList.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		scrollPane.setViewportView(displayList);
		displayList.setBorder(new LineBorder(new Color(0, 0, 0)));
		updateList(null);

		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSearch.setBounds(575, 116, 65, 20);
		add(lblSearch);

		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField.setBounds(652, 114, 200, 25);
		textField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateList(textField.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateList(textField.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateList(textField.getText());
			}
		});
		add(textField);
		textField.setColumns(10);

		// ============================= Button =====================================//
		JButton btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnModify.setBounds(620, 300, 208, 35);
		btnModify.addActionListener(event -> {
			if (displayList.getSelectedValue() == null) {
				library.dialogMessage("You must select company first");
			} else {
				String getCompany = displayList.getSelectedValue().toString();
				SwingUtilities.invokeLater(() -> mainFrame.changePanel(new EditCompany(getCompany, mainFrame)));
			}
		});
		add(btnModify);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(event -> {
			// redirect to mainmenu //

		});
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnBack.setBounds(620, 359, 208, 35);
		add(btnBack);

		JButton btnNewButton = new JButton("List Travelleg Account");
		btnNewButton.addActionListener(event -> {
			if (displayList.getSelectedValue() == null) {
				library.dialogMessage("You must select company first");
			} else {
				String getCompany = displayList.getSelectedValue().toString();
				SwingUtilities.invokeLater(() -> mainFrame.changePanel(new listTravelLegAccount(mainFrame,getCompany)));
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnNewButton.setBounds(618, 238, 210, 35);
		add(btnNewButton);
	}

	// ==================== Function ==================== //
	public void updateList(String searchItem) {
		if (searchItem == null) {
			listModel = (DefaultListModel) displayList.getModel();
			listModel.clear();
			for (int i = 0; i < cArray.size(); i++) {
				listModel.addElement(cArray.getIndexElement(i).getCompanyName());
			}
		} else {
			listModel = (DefaultListModel) displayList.getModel();
			listModel.clear();
			for (int i = 0; i < cArray.size(); i++) {
				searchItem = searchItem.toUpperCase();
				if (cArray.getIndexElement(i).getCompanyName().toUpperCase().matches(searchItem + ".*")) {
					listModel.addElement(cArray.getIndexElement(i).getCompanyName());
				}
			}
		}

	}
}
