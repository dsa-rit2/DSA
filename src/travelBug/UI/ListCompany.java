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

	private JTextField txtAsd;
	private JTextField shortF;
	private JTextField phoneN;
	private JTextField Descriptiontxt;
	private int companyFoundindex;

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
		updateList(null);
		displayList.setFont(new Font("Calibri", Font.BOLD, 15));
		scrollPane.setViewportView(displayList);
		displayList.setBorder(new LineBorder(new Color(0, 0, 0)));
		

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
		JButton btnSelect = new JButton("Select");
		btnSelect.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnSelect.setBounds(403, 368, 106, 55);
		btnSelect.addActionListener(event -> {
			if (displayList.getSelectedValue() == null) {
				library.dialogMessage("You must select company first");
			} else {
				String getCompany = displayList.getSelectedValue().toString();
				boolean gotCompany = false;
				companyFoundindex = 0;
				cArray = rFile.readLinkArray();
				for(int i = 0; i < cArray.size(); i++) {
					if(getCompany.equalsIgnoreCase(cArray.getIndexElement(i).getCompanyName())) {
						gotCompany = true;
						companyFoundindex = i;
					}
				}
				if(gotCompany == false) {
					//Redirect to the previous page
					
					
				}
				txtAsd.setText(cArray.getIndexElement(companyFoundindex).getCompanyName());
				shortF.setText(cArray.getIndexElement(companyFoundindex).getShortForm());
				phoneN.setText(cArray.getIndexElement(companyFoundindex).getPhoneNum());
				Descriptiontxt.setText(cArray.getIndexElement(companyFoundindex).getDescription());
			}
		});
		add(btnSelect);
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(event -> {
			// redirect to mainmenu //

		});
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnBack.setBounds(736, 368, 106, 55);
		add(btnBack);
		
		JLabel label = new JLabel("Company Name:");
		label.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label.setBounds(369, 36, 141, 30);
		add(label);
		
		txtAsd = new JTextField();
		txtAsd.setText((String) null);
		txtAsd.setFont(new Font("Calibri", Font.BOLD, 14));
		txtAsd.setColumns(10);
		txtAsd.setBounds(514, 38, 160, 28);
		add(txtAsd);
		
		Label lblErrorCompanyName = new Label("");
		lblErrorCompanyName.setForeground(Color.RED);
		lblErrorCompanyName.setBackground(Color.WHITE);
		lblErrorCompanyName.setBounds(683, 52, 207, 14);
		add(lblErrorCompanyName);
		
		JLabel label_2 = new JLabel("Short Form:");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label_2.setBounds(369, 89, 141, 30);
		add(label_2);
		
		shortF = new JTextField();
		shortF.setText((String) null);
		shortF.setFont(new Font("Calibri", Font.BOLD, 14));
		shortF.setColumns(10);
		shortF.setBounds(514, 94, 160, 28);
		add(shortF);
		
		Label lblErrorShortForm = new Label("");
		lblErrorShortForm.setForeground(Color.RED);
		lblErrorShortForm.setBackground(Color.WHITE);
		lblErrorShortForm.setBounds(683, 105, 207, 14);
		add(lblErrorShortForm);
		
		JLabel label_4 = new JLabel("Phone Number:");
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label_4.setBounds(369, 152, 141, 30);
		add(label_4);
		
		phoneN = new JTextField();
		phoneN.setText((String) null);
		phoneN.setFont(new Font("Calibri", Font.BOLD, 14));
		phoneN.setColumns(10);
		phoneN.setBounds(514, 157, 158, 28);
		add(phoneN);
		
		Label lblErrorPhoneNum = new Label("");
		lblErrorPhoneNum.setForeground(Color.RED);
		lblErrorPhoneNum.setBackground(Color.WHITE);
		lblErrorPhoneNum.setBounds(683, 168, 207, 14);
		add(lblErrorPhoneNum);
		
		JLabel label_6 = new JLabel("Description:");
		label_6.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label_6.setBounds(369, 216, 141, 30);
		add(label_6);
		
		Descriptiontxt = new JTextField();
		Descriptiontxt.setText((String) null);
		Descriptiontxt.setHorizontalAlignment(SwingConstants.LEFT);
		Descriptiontxt.setFont(new Font("Calibri", Font.BOLD, 15));
		Descriptiontxt.setColumns(20);
		Descriptiontxt.setBounds(514, 221, 160, 28);
		add(Descriptiontxt);
		
		Label lblErrorDescription = new Label("");
		lblErrorDescription.setForeground(Color.RED);
		lblErrorDescription.setBackground(Color.WHITE);
		lblErrorDescription.setBounds(683, 232, 207, 14);
		add(lblErrorDescription);
		
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(event-> {

			String companyName = txtAsd.getText();
			int error = 0;
			String sFormString =  shortF.getText();
			String editP  =  phoneN.getText();
			String descriptionString = Descriptiontxt.getText();
			
			lblErrorCompanyName.setText("");
			lblErrorPhoneNum.setText("");
			lblErrorShortForm.setText("");
			lblErrorDescription.setText("");

			if (companyName.isEmpty()) {
				lblErrorCompanyName.setText("The company name cannot be empty");
				error++;
			} else {
				boolean errorCompanyName = false;
				for (int i = 0; i < cArray.size(); i++) {
					if (cArray.getIndexElement(i).getShortForm().equalsIgnoreCase(sFormString)) {
						errorCompanyName = true;
					}
				}
			}
			if (sFormString.isEmpty()) {
				lblErrorShortForm.setText("The short form cannot be empty");
				error++;
			} else {
				boolean errorShortForm = false;
				for (int i = 0; i < cArray.size(); i++) {
					if (cArray.getIndexElement(i).getShortForm().equalsIgnoreCase(sFormString)) {
						errorShortForm = true;
					}
//					System.out.println(cArray.indexGetElement(i).getCompanyName());
				}
				if (!library.isAlpha(sFormString)) {
					lblErrorShortForm.setText("The short form must be alphabetic");
					error++;
				} else if (sFormString.length() > 10) {
					lblErrorShortForm.setText("The short form must not excess 10 letter");
					error++;
				}
			}
			if (phoneN.getText().isEmpty()) {
				lblErrorPhoneNum.setText("The phone number cannot be empty");
				error++;
			} else if (editP == null) {
				lblErrorPhoneNum.setText("The phone number is invalid");
				error++;
			}
			if (descriptionString.isEmpty()) {
				lblErrorDescription.setText("The description is empty");
				error++;
			}
			if (error == 0) {
				cArray.getIndexElement(companyFoundindex).setCompanyName(companyName);
				cArray.getIndexElement(companyFoundindex).setShortForm(sFormString);
				cArray.getIndexElement(companyFoundindex).setPhoneNum(editP);
				cArray.getIndexElement(companyFoundindex).setDescription(descriptionString);
				
				rFile.writeLinkArray(cArray);
				//Print the window for the details thing 
//				 Show the company info
				for (int i = 0; i < cArray.size(); i++) {
					cArray.getIndexElement(i).print();
				}
				library.dialogMessage("Company Updated successful!!!");
				SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCompany(mainFrame)));
			}
			
		});
		btnModify.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnModify.setBounds(572, 368, 119, 55);
		add(btnModify);
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
