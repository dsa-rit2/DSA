package travelBug.UI;

//==========================
import travelBug.library.*;
import travelBug.obj.*;
//==========================

import javax.swing.*;
import java.awt.*;

public class EditCompany extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtAsd;
	private JTextField shortF;
	private JTextField phoneN;
	private JTextField Descriptiontxt;
	private LinkArray<Company> cArray = new LinkArray<Company>();
	private ReadWriteFile<Company> rFile = new ReadWriteFile<Company>("Company.txt", Company.class);
	private int companyFoundindex;
	private final UIControl mainFrame;

	public EditCompany(String input, UIControl parent) {
		super();
		this.mainFrame = parent;

		// ======================= Jpanel Setting ========================
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		// ===================== Validate the company ====================
		boolean gotCompany = false;
		companyFoundindex = 0;
		cArray = rFile.readLinkArray();
		for (int i = 0; i < cArray.size(); i++) {
			if (input.equalsIgnoreCase(cArray.getIndexElement(i).getCompanyName())) {
				gotCompany = true;
				companyFoundindex = i;
			}
		}
		if (gotCompany == false) {
			// Redirect to the previous page

		}

		// ==================== Content component =========================
		JLabel lblNewLabel = new JLabel("Company Name:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(228, 71, 141, 30);
		add(lblNewLabel);

		txtAsd = new JTextField();
		txtAsd.setFont(new Font("Calibri", Font.BOLD, 14));
		txtAsd.setText(cArray.getIndexElement(companyFoundindex).getCompanyName());
		txtAsd.setBounds(417, 76, 207, 28);
		add(txtAsd);
		txtAsd.setColumns(10);

		JLabel lblShortForm = new JLabel("Short Form:");
		lblShortForm.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblShortForm.setBounds(228, 144, 141, 30);
		add(lblShortForm);

		shortF = new JTextField();
		shortF.setFont(new Font("Calibri", Font.BOLD, 14));
		shortF.setText(cArray.getIndexElement(companyFoundindex).getShortForm());
		shortF.setBounds(417, 149, 207, 28);
		add(shortF);
		shortF.setColumns(10);

		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPhoneNumber.setBounds(228, 212, 141, 30);
		add(lblPhoneNumber);

		phoneN = new JTextField();
		phoneN.setFont(new Font("Calibri", Font.BOLD, 14));
		phoneN.setText(cArray.getIndexElement(companyFoundindex).getPhoneNum());
		phoneN.setBounds(417, 217, 207, 28);
		add(phoneN);
		phoneN.setColumns(10);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDescription.setBounds(228, 275, 141, 30);
		add(lblDescription);

		Descriptiontxt = new JTextField();
		Descriptiontxt.setFont(new Font("Calibri", Font.BOLD, 15));
		Descriptiontxt.setHorizontalAlignment(SwingConstants.LEFT);
		Descriptiontxt.setText(cArray.getIndexElement(companyFoundindex).getDescription());
		Descriptiontxt.setBounds(417, 275, 219, 28);
		add(Descriptiontxt);
		Descriptiontxt.setColumns(20);

		// ======================== Error Message =========================
		Label lblErrorCompanyName = new Label("");
		lblErrorCompanyName.setForeground(Color.RED);
		lblErrorCompanyName.setBackground(Color.white);
		lblErrorCompanyName.setBounds(667, 73, 207, 14);
		add(lblErrorCompanyName);

		Label lblErrorDescription = new Label("");
		lblErrorDescription.setForeground(Color.RED);
		lblErrorDescription.setBackground(Color.white);
		lblErrorDescription.setBounds(667, 275, 207, 14);
		add(lblErrorDescription);

		Label lblErrorShortForm = new Label("");
		lblErrorShortForm.setForeground(Color.RED);
		lblErrorShortForm.setBackground(Color.white);
		lblErrorShortForm.setBounds(667, 144, 207, 14);
		add(lblErrorShortForm);

		Label lblErrorPhoneNum = new Label("");
		lblErrorPhoneNum.setForeground(Color.RED);
		lblErrorPhoneNum.setBackground(Color.white);
		lblErrorPhoneNum.setBounds(667, 212, 207, 14);
		add(lblErrorPhoneNum);

		// =========================== Buttons =============================
		JButton btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnModify.addActionListener(event -> {
			int error = 0;
			String companyName = txtAsd.getText();
			String sFormString = shortF.getText();
			String editP = phoneN.getText();
			String descriptionString = Descriptiontxt.getText();

			lblErrorCompanyName.setText("");
			lblErrorPhoneNum.setText("");
			lblErrorShortForm.setText("");
			lblErrorDescription.setText("");

			if (companyName.isEmpty()) {
				lblErrorCompanyName.setText("The company name cannot be empty");
				error++;
			} else {
				for (int i = 0; i < cArray.size(); i++) {
					if (cArray.getIndexElement(i).getShortForm().equalsIgnoreCase(sFormString)) {
					}
				}
			}
			if (sFormString.isEmpty()) {
				lblErrorShortForm.setText("The short form cannot be empty");
				error++;
			} else {
				for (int i = 0; i < cArray.size(); i++) {
					if (cArray.getIndexElement(i).getShortForm().equalsIgnoreCase(sFormString)) {
					}
//						System.out.println(cArray.indexGetElement(i).getCompanyName());
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
				// Print the window for the details thing then show the company info
				for (int i = 0; i < cArray.size(); i++) {
					cArray.getIndexElement(i).print();
				}
				library.dialogMessage("Company Updated successful!!!");
				SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCompany(mainFrame)));
			}
		});
		btnModify.setBounds(276, 354, 141, 62);
		add(btnModify);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(event -> {
			library.dialogMessage("The page will redirect to the list company");
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCompany(mainFrame)));
		});
		btnBack.setFont(new Font("Calibri", Font.BOLD, 15));
		btnBack.setBounds(457, 354, 141, 63);
		add(btnBack);

	}
}
