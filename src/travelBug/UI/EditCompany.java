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
			library.dialogMessage("The company not found\nPlease contact our staff");
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCompany(mainFrame)));
		}

		// ==================== Content component =========================
		JLabel lblNewLabel = new JLabel("Company Name:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel.setBounds(180, 64, 141, 30);
		add(lblNewLabel);

		txtAsd = new JTextField();
		txtAsd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtAsd.setText(cArray.getIndexElement(companyFoundindex).getCompanyName());
		txtAsd.setBounds(333, 71, 300, 28);
		add(txtAsd);
		txtAsd.setColumns(10);

		JLabel lblShortForm = new JLabel("Short Form:");
		lblShortForm.setHorizontalAlignment(SwingConstants.RIGHT);
		lblShortForm.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblShortForm.setBounds(180, 137, 141, 30);
		add(lblShortForm);

		shortF = new JTextField();
		shortF.setEditable(false);
		shortF.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		shortF.setText(cArray.getIndexElement(companyFoundindex).getShortForm());
		shortF.setBounds(333, 144, 300, 28);
		add(shortF);
		shortF.setColumns(10);

		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhoneNumber.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblPhoneNumber.setBounds(180, 205, 141, 30);
		add(lblPhoneNumber);

		phoneN = new JTextField();
		phoneN.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		phoneN.setText(cArray.getIndexElement(companyFoundindex).getPhoneNum());
		phoneN.setBounds(333, 212, 300, 28);
		add(phoneN);
		phoneN.setColumns(10);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDescription.setBounds(180, 276, 141, 30);
		add(lblDescription);

		Descriptiontxt = new JTextField();
		Descriptiontxt.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		Descriptiontxt.setHorizontalAlignment(SwingConstants.LEFT);
		Descriptiontxt.setText(cArray.getIndexElement(companyFoundindex).getDescription());
		Descriptiontxt.setBounds(333, 278, 300, 28);
		add(Descriptiontxt);
		Descriptiontxt.setColumns(20);

		// ======================== Error Message =========================
		Label lblErrorCompanyName = new Label("");
		lblErrorCompanyName.setForeground(Color.RED);
		lblErrorCompanyName.setBackground(Color.white);
		lblErrorCompanyName.setBounds(333, 105, 300, 14);
		add(lblErrorCompanyName);

		Label lblErrorDescription = new Label("");
		lblErrorDescription.setForeground(Color.RED);
		lblErrorDescription.setBackground(Color.white);
		lblErrorDescription.setBounds(333, 312, 300, 14);
		add(lblErrorDescription);

		Label lblErrorShortForm = new Label("");
		lblErrorShortForm.setForeground(Color.RED);
		lblErrorShortForm.setBackground(Color.white);
		lblErrorShortForm.setBounds(333, 178, 300, 14);
		add(lblErrorShortForm);

		Label lblErrorPhoneNum = new Label("");
		lblErrorPhoneNum.setForeground(Color.RED);
		lblErrorPhoneNum.setBackground(Color.white);
		lblErrorPhoneNum.setBounds(333, 246, 300, 14);
		add(lblErrorPhoneNum);

		lblErrorCompanyName.setVisible(false);
		lblErrorDescription.setVisible(false);
		lblErrorPhoneNum.setVisible(false);
		lblErrorShortForm.setVisible(false);
		// =========================== Buttons =============================
		JButton btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnModify.addActionListener(event -> {
			int error = 0;
			String companyName = txtAsd.getText();
			String sFormString = shortF.getText();
			String editP = phoneN.getText();
			String descriptionString = Descriptiontxt.getText();

			lblErrorCompanyName.setVisible(false);
			lblErrorDescription.setVisible(false);
			lblErrorPhoneNum.setVisible(false);
			lblErrorShortForm.setVisible(false);
			lblErrorCompanyName.setText("");
			lblErrorPhoneNum.setText("");
			lblErrorShortForm.setText("");
			lblErrorDescription.setText("");

			if (companyName.isEmpty()) {
				lblErrorCompanyName.setText("The company name cannot be empty");
				lblErrorCompanyName.setVisible(true);
				error++;
			}
			if (sFormString.isEmpty()) {
				lblErrorShortForm.setText("The short form cannot be empty");
				lblErrorShortForm.setVisible(true);
				error++;
			} else {
				if (!library.isAlpha(sFormString)) {
					lblErrorShortForm.setText("The short form must be alphabetic");
					lblErrorShortForm.setVisible(true);
					error++;
				} else if (sFormString.length() > 10) {
					lblErrorShortForm.setText("The short form must not excess 10 letter");
					lblErrorShortForm.setVisible(true);
					error++;
				}
			}
			if (phoneN.getText().isEmpty()) {
				lblErrorPhoneNum.setText("The phone number cannot be empty");
				lblErrorPhoneNum.setVisible(true);
				error++;
			} else if (editP == null) {
				lblErrorPhoneNum.setText("The phone number is invalid");
				lblErrorPhoneNum.setVisible(true);
				error++;
			}
			if (descriptionString.isEmpty()) {
				lblErrorDescription.setText("The description is empty");
				lblErrorDescription.setVisible(true);
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
		btnModify.setBounds(276, 368, 150, 50);
		add(btnModify);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(event -> SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCompany(mainFrame))));
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnBack.setBounds(470, 368, 150, 50);
		add(btnBack);

	}
}
