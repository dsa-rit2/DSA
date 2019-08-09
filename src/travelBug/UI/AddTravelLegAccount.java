package travelBug.UI;

//=========================
//	Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================

import java.awt.*;
import javax.swing.*;

import org.junit.runners.ParentRunner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddTravelLegAccount extends JPanel {
	private static final long serialVersionUID = 5629499624569369278L;
	private JTextField txtPassword;
	private LinkArray<TravelLegAccount> tArray = new LinkArray<TravelLegAccount>();
	private LinkArray<Company> cArray = new LinkArray<Company>();
	private ReadWriteFile<TravelLegAccount> tFile = new ReadWriteFile<TravelLegAccount>("TravelLegAccount.txt",
			TravelLegAccount.class);
	private ReadWriteFile<Company> cFile = new ReadWriteFile<Company>("Company.txt", Company.class);
	private JTextField txtUserNameNum;
	private final UIControl mainFrame;
	private String anything = null;
	private String shortFormString = null;

	public AddTravelLegAccount(UIControl parent, String anything) {
		// ====================== Jpanel Setting ========================
		super();
		this.anything = anything;
		this.mainFrame = parent;
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		// ======================== Validate ============================
		// Read the file and find the company name
		tArray = tFile.readLinkArray();
		cArray = cFile.readLinkArray();
		for (int i = 0; i < cArray.size(); i++) {
			if (anything.equalsIgnoreCase(cArray.getIndexElement(i).getCompanyName())) {
				shortFormString = cArray.getIndexElement(i).getShortForm();
			}
		}
		tArray = tFile.readLinkArray();
		createGui();
	}
	
	public void createGui() {
		removeAll();
		// ======================================== Content component ==============================================//

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblUsername.setBounds(163, 166, 107, 19);
		add(lblUsername);

		JTextField txtUserFront = new JTextField();
		txtUserFront.setText(shortFormString);
//				txtUserFront.setText(anything);
		txtUserFront.setEditable(false);
		txtUserFront.setBounds(313, 163, 79, 22);
		add(txtUserFront);
		txtUserFront.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblPassword.setBounds(163, 213, 107, 16);
		add(lblPassword);

		txtPassword = new JTextField();
		txtPassword.setBounds(313, 210, 256, 22);
		add(txtPassword);
		txtPassword.setColumns(10);

		txtUserNameNum = new JTextField();
		txtUserNameNum.setBounds(404, 163, 79, 22);
		add(txtUserNameNum);
		txtUserNameNum.setColumns(10);

		JLabel lblAddTravelLeg = new JLabel(anything);
		lblAddTravelLeg.setFont(new Font("Segoe UI Emoji", Font.BOLD, 27));
		lblAddTravelLeg.setBounds(163, 86, 538, 67);
		add(lblAddTravelLeg);

		JLabel lblNewLabel = new JLabel(".");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(393, 169, 24, 16);
		add(lblNewLabel);

		JLabel lblAddTravellegAccount = new JLabel("Add Travelleg Account");
		lblAddTravellegAccount.setFont(new Font("Segoe UI", Font.BOLD, 27));
		lblAddTravellegAccount.setBounds(156, 38, 502, 51);
		add(lblAddTravellegAccount);

		// ============================================ Error Message
		// ===============================================//

		Label lblUsernameError = new Label("");
		lblUsernameError.setForeground(new Color(255, 0, 0));
		lblUsernameError.setBackground(Color.white);
		lblUsernameError.setBounds(313, 185, 416, 16);
		add(lblUsernameError);

		Label lblPasswordError = new Label("");
		lblPasswordError.setForeground(new Color(255, 0, 0));
		lblPasswordError.setBackground(Color.white);
		lblPasswordError.setBounds(312, 233, 416, 16);
		add(lblPasswordError);
		
		lblPasswordError.setVisible(false);
		lblUsernameError.setVisible(false);

		
		// ================================================ Button ====================================================//
		Button btnAdd = new Button("Add");
		btnAdd.addActionListener(event -> {
			String username = txtUserFront.getText() + "." + txtUserNameNum.getText();
			String password = String.valueOf(txtPassword.getText());
			String checkPass = library.validPassword(password);
			int error = 0;
		
			lblPasswordError.setVisible(false);
			lblUsernameError.setVisible(false);
			lblUsernameError.setText("");
			lblPasswordError.setText("");

			if (txtUserNameNum.getText().isEmpty()) {
				lblUsernameError.setText("The username code cannot be empty");
				lblUsernameError.setVisible(true);
				error++;
			} else {
				boolean errorUsername = false;
				for (int i = 0; i < tArray.size(); i++) {
					if (username.equalsIgnoreCase(tArray.getIndexElement(i).getUsername())) {
						errorUsername = true;
					}
				}
				if (errorUsername) {
					lblUsernameError.setText("The username is duplicated!!!");
					lblUsernameError.setVisible(true);
					error++;
				}
			}
			if (password.isEmpty()) {
				lblPasswordError.setText("The password cannot be empty");
				lblPasswordError.setVisible(true);
				error++;
			} else if (checkPass != null) {
				lblPasswordError.setText(checkPass);
				lblPasswordError.setVisible(true);
				error++;
			}
			if (error == 0) {
				// run the code for storing
				TravelLegAccount pAccount = new TravelLegAccount(username, password);
				tArray.addItem(pAccount);
				tFile.writeLinkArray(tArray);
				int result = JOptionPane.showConfirmDialog(null,
						"Add travelleg account successful\n Do you want to add more", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					createGui();
				} else {
					SwingUtilities
							.invokeLater(() -> mainFrame.changePanel(new ListTravelLegAccount(mainFrame, anything)));

				}
//							SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCompany(mainFrame)));
			}

		});
		btnAdd.setForeground(new Color(0, 0, 0));
		btnAdd.setBounds(313, 319, 79, 24);
		add(btnAdd);

		Button btnBack = new Button("Back");
		btnBack.addActionListener(event -> {
			library.dialogMessage("The page will redirect to the company");
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListTravelLegAccount(mainFrame, anything)));
		});
		btnBack.setForeground(new Color(0, 0, 0));
		btnBack.setBounds(490, 319, 79, 24);
		add(btnBack);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(event -> {
			txtUserNameNum.setText(library.randomID(0, 99999));

		});
		btnGenerate.setBounds(618, 162, 97, 25);
		add(btnGenerate);

		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean repeat = true;
				String tempPass = null;
				do {
					tempPass = library.generatePassword();
					String t = library.validPassword(tempPass);
					if (t == null) {
						repeat = false;
					}
				} while (repeat);
				txtPassword.setText(tempPass);
			}
		});
		btnNewButton.setBounds(618, 209, 97, 25);
		add(btnNewButton);
		revalidate();
		repaint();
	}
}
