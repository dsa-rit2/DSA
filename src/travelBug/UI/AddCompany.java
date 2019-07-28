package travelBug.UI;

//=========================
//		Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCompany extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtFCompanyName, txtFShortForm;
	private JTextArea tADescription;
	private JLabel lblPhoneNumber, lblDescription,lblCompanyName, lblShortForm, lblTitle ;
	private Label  lblErrorDescription, lblErrorPhoneNum, lblErrorShortForm,lblErrorCompanyName;
	private JTextField txtFPhoneNum;
	private LinkArray<Company> cArray = new LinkArray<Company>();
	private ReadWriteFile<Company> rFile = new ReadWriteFile<Company>("Company.txt", Company.class);

	public AddCompany() {
		//==================== JPanel setting =====================
//		UIControl.titleName = "Add TravelLeg Company";
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		//==================== Content component ====================
		lblTitle = new JLabel("Add Company");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 29));
		lblTitle.setBounds(12, 13, 876, 40);
		add(lblTitle);

		lblCompanyName = new JLabel("Company Name:");
		lblCompanyName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCompanyName.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblCompanyName.setBounds(115, 60, 150, 50);
		add(lblCompanyName);

		lblShortForm = new JLabel("Short Form:");
		lblShortForm.setHorizontalAlignment(SwingConstants.RIGHT);
		lblShortForm.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblShortForm.setBounds(115, 119, 150, 50);
		add(lblShortForm);

		txtFCompanyName = new JTextField();
		txtFCompanyName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		txtFCompanyName.setBounds(277, 66, 500, 40);
		add(txtFCompanyName);
		txtFCompanyName.setColumns(10);

		txtFShortForm = new JTextField();
		txtFShortForm.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		txtFShortForm.setBounds(277, 125, 500, 40);
		add(txtFShortForm);
		txtFShortForm.setColumns(10);
		JScrollPane sp = new JScrollPane();
		sp.setBounds(277, 251, 500, 120);
		add(sp);

		tADescription = new JTextArea();
		tADescription.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		sp.setViewportView(tADescription);

		lblDescription = new JLabel("Description:");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblDescription.setBounds(115, 251, 150, 50);
		add(lblDescription);

		lblPhoneNumber = new JLabel("Phone number:");
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhoneNumber.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblPhoneNumber.setBounds(115, 176, 150, 50);
		add(lblPhoneNumber);

		txtFPhoneNum = new JTextField();
		txtFPhoneNum.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		txtFPhoneNum.setBounds(277, 182, 500, 40);
		add(txtFPhoneNum);
		txtFPhoneNum.setColumns(10);

		//====================== Error Message ==========================
		lblErrorCompanyName = new Label("");
		lblErrorCompanyName.setForeground(Color.RED);
		lblErrorCompanyName.setBackground(new Color(255, 255, 255, 0));
		lblErrorCompanyName.setBounds(277, 106, 322, 16);
		add(lblErrorCompanyName);

		lblErrorShortForm = new Label("");
		lblErrorShortForm.setForeground(Color.RED);
		lblErrorShortForm.setBackground(new Color(255, 255, 255, 0));
		lblErrorShortForm.setBounds(277, 165, 358, 16);
		add(lblErrorShortForm);

		lblErrorPhoneNum = new Label("");
		lblErrorPhoneNum.setForeground(Color.RED);
		lblErrorPhoneNum.setBackground(new Color(255, 255, 255, 0));
		lblErrorPhoneNum.setBounds(277, 222, 358, 16);
		add(lblErrorPhoneNum);

		lblErrorDescription = new Label("");
		lblErrorDescription.setBackground(new Color(255, 255, 255, 0));
		lblErrorDescription.setForeground(Color.RED);
		lblErrorDescription.setBounds(277, 369, 426, 16);
		add(lblErrorDescription);

		//========================= Button ==========================
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnBack.setBounds(259, 402, 120, 35);
		add(btnBack);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				submit();
			}
		});
		btnSubmit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnSubmit.setBounds(449, 402, 120, 35);
		add(btnSubmit);
	}

	private void submit() {
		cArray = rFile.readLinkArray(); // Read file when click the button
		boolean error = false;
		String companyName = txtFCompanyName.getText();
		String shortForm = txtFShortForm.getText();
		String phoneNum = library.checkPhoneNum(txtFPhoneNum.getText()); // Return phone number with valid format
																			// return null
		String description = this.tADescription.getText();

		lblErrorCompanyName.setText("");
		lblErrorPhoneNum.setText("");
		lblErrorShortForm.setText("");
		lblErrorDescription.setText("");

		if (companyName.isEmpty()) {
			lblErrorCompanyName.setText("The company name cannot be empty");
			error = true;
		} else {
			boolean errorCompanyName = false;
			for (int i = 0; i < cArray.size(); i++) {
				if (cArray.getIndexElement(i).getCompanyName().equalsIgnoreCase(companyName)) {
					errorCompanyName = true;
				}
			}
			if (errorCompanyName) { // Check the companyName is duplicated
				lblErrorCompanyName.setText("The company name is duplicated");
				error = true;
			}
		}

		if (shortForm.isEmpty()) { // Check short form is empty
			lblErrorShortForm.setText("The short form cannot be empty");
			error = true;
		} else {
			boolean errorShortForm = false;
			for (int i = 0; i < cArray.size(); i++) {
				if (cArray.getIndexElement(i).getShortForm().equalsIgnoreCase(shortForm)) {
					errorShortForm = true;
				}
			}
			if (!library.isAlpha(shortForm)) { // The short form must in alphabet
				lblErrorShortForm.setText("The short form must be alphabetic");
				error = true;
			} else if (shortForm.length() > 10) { // Cannot too long
				lblErrorShortForm.setText("The short form must not excess 10 letter");
				error = true;
			} else if (errorShortForm) { // Check the shortForm is duplicated
				lblErrorShortForm.setText("The short form is duplicated");
				error = true;
			}
		}

		if (txtFPhoneNum.getText().isEmpty()) {
			lblErrorPhoneNum.setText("The phone number cannot be empty");
			error = true;
		} else if (phoneNum == null) { // If phone number which is invalid in terms of format, size
			lblErrorPhoneNum.setText("The phone number is invalid");
			error = true;
		}

		if (description.isEmpty()) { // If the description is empty
			lblErrorDescription.setText("The description is empty");
			error = true;
		}

		if (!error) { 			// If no error
			Company oCompany = new Company(companyName, shortForm, phoneNum, description);
			cArray.addItem(oCompany);
			rFile.writeLinkArray(cArray);
//			JFrame frame = new JFrame("Show Message to redirect");
			int result = JOptionPane.showConfirmDialog(null,
					"New company,\n" + companyName + " \nadded successful!!!\nDo you want to add new travelleg account",
					"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				try {
					new AddTravelLegAccount(companyName).setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				// redirect to the listcompany
				try {
//					frame.setVisible(false);
//					dispose();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
