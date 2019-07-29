package travelBug.UI;

//=========================
//Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListCompany extends JPanel {

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
		JLabel lblConpanyList = new JLabel("Conpany List");
		lblConpanyList.setFont(new Font("Arial", Font.BOLD, 15));
		lblConpanyList.setBounds(20, 11, 125, 14);
		add(lblConpanyList);

		listModel = new DefaultListModel();
		displayList = new JList(listModel);
		displayList.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scrollPane = new JScrollPane(displayList);
		scrollPane.setBounds(20, 36, 300, 300);
		displayList.setBounds(20, 36, 300, 300);
		add(scrollPane);
		updateList(null);

		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearch.setBounds(20, 387, 66, 14);
		add(lblSearch);

		textField = new JTextField();
		textField.setBounds(96, 386, 185, 20);
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
		JButton btnModify = new JButton("Select");
		btnModify.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnModify.setBounds(462, 385, 89, 23);
		btnModify.addActionListener(event -> {
			if (displayList.getSelectedValue() == null) {
				library.dialogMessage("You must select company first");
			} else {
				String getCompany = displayList.getSelectedValue().toString();
				SwingUtilities.invokeLater(() -> mainFrame.changePanel(new EditCompany(getCompany,mainFrame)));
			}
		});
		add(btnModify);

		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(event-> {
			//redirect to mainmenu //
			
		});
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnBack.setBounds(596, 385, 89, 23);
		add(btnBack);
	}

	// ===================================== Function
	// ===================================//
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


