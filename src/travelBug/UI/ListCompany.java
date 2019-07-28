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

public class ListCompany extends JFrame {

	private JPanel contentPane;
	private LinkArray<Company> cArray = new LinkArray<Company>();
	private ReadWriteFile<Company> rFile = new ReadWriteFile<Company>("Company.txt", Company.class);
	private JTextField textField;
	private JList displayList;
	DefaultListModel listModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListCompany frame = new ListCompany();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ListCompany() {

		// =================================== Jpanel setting ===========================//
		cArray = rFile.readLinkArray();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 515);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Conpany");

		// =================================== Content component =========================//
		JLabel lblConpanyList = new JLabel("Conpany List");
		lblConpanyList.setFont(new Font("Arial", Font.BOLD, 15));
		lblConpanyList.setBounds(20, 11, 125, 14);
		contentPane.add(lblConpanyList);

		listModel = new DefaultListModel();
		displayList = new JList(listModel);
		displayList.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scrollPane = new JScrollPane(displayList);
		scrollPane.setBounds(20, 36, 300, 300);
		displayList.setBounds(20, 36, 300, 300);
		contentPane.add(scrollPane);
		updateList(null);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearch.setBounds(20, 387, 66, 14);
		contentPane.add(lblSearch);

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
		contentPane.add(textField);
		textField.setColumns(10);



		
		// ============================= Button =====================================//
		JButton btnModify = new JButton("Select");
		btnModify.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnModify.setBounds(462, 385, 89, 23);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (displayList.getSelectedValue() == null) {
					library.dialogMessage("You must select company first");
				} else {
					String getCompany = displayList.getSelectedValue().toString();
					dispose();
					new EditCompany(getCompany).setVisible(true);
				}
			}

		});
		contentPane.add(btnModify);

				
	}

	// ===================================== Function ===================================//
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
