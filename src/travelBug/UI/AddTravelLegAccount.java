package travelBug.UI;

//=========================
//Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.management.loading.PrivateClassLoader;
import javax.swing.JButton;

public class AddTravelLegAccount extends JPanel {
	private static final long serialVersionUID = 5629499624569369278L;
	private JPanel contentPane;
	private JTextField tfUsername;
	private JTextField txtPassword;
	private ReadWriteFile<TravelLegAccount> readWriteFile = new ReadWriteFile<TravelLegAccount>("TravelLegAccount.txt",
			TravelLegAccount.class);
	private LinkArray<TravelLegAccount> tArray = new LinkArray<TravelLegAccount>();
	private LinkArray<Company> cArray = new LinkArray<Company>();
	private ReadWriteFile<TravelLegAccount> tFile = new ReadWriteFile<TravelLegAccount>("TravelLegAccount.txt",
			TravelLegAccount.class);
	private ReadWriteFile<Company> cFile = new ReadWriteFile<Company>("Company.txt", Company.class);
	private JTextField txtUserNameNum;
	private JTextField textField;
	private final UIControl mainFrame;

	public AddTravelLegAccount(UIControl parent,String anything) {
		
		// ========================================Jpanel Setting ==================================================//
		super();
		this.mainFrame = parent;
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));
		

		
		// ======================================== Validate ===================================================//
		// Read the file and find the shortform
		String shortFormString = null;
		tArray = tFile.readLinkArray();
		cArray = cFile.readLinkArray();
		for (int i = 0; i < cArray.size(); i++) {
			if (anything.equalsIgnoreCase(cArray.getIndexElement(i).getCompanyName())) {
				shortFormString = cArray.getIndexElement(i).getShortForm();
			}
		}
		tArray = tFile.readLinkArray();
		
		// ======================================== Content component ==============================================//
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(12, 135, 89, 16);
		add(lblUsername);

		JTextField txtUserFront = new JTextField();
		txtUserFront.setText(shortFormString);
		txtUserFront.setEditable(false);
		txtUserFront.setBounds(131, 132, 79, 22);
		add(txtUserFront);
		txtUserFront.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(12, 182, 89, 16);
		add(lblPassword);

		txtPassword = new JTextField();
		txtPassword.setBounds(131, 179, 256, 22);
		add(txtPassword);
		txtPassword.setColumns(10);

		txtUserNameNum = new JTextField();
		txtUserNameNum.setBounds(222, 132, 79, 22);
		add(txtUserNameNum);
		txtUserNameNum.setColumns(10);
		
		JLabel lblAddTravelLeg = new JLabel(anything);
		lblAddTravelLeg.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblAddTravelLeg.setBounds(12, 42, 392, 67);
		add(lblAddTravelLeg);
		
		JLabel lblNewLabel = new JLabel(".");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(214, 134, 24, 16);
		add(lblNewLabel);
		
		// ============================================ Error Message ===============================================//
		
		JLabel lblUsernameError = new JLabel("");
		lblUsernameError.setForeground(new Color(255, 0, 0));
		lblUsernameError.setBounds(128, 163, 416, 16);
		add(lblUsernameError);

		JLabel lblPasswordError = new JLabel("");
		lblPasswordError.setForeground(new Color(255, 0, 0));
		lblPasswordError.setBounds(141, 127, 416, 16);
		add(lblPasswordError);

		// ================================================ Button ====================================================//
		contentPane = new JPanel();
		Button btnAdd = new Button("Add");
		btnAdd.addActionListener(event->{
				String username = txtUserFront.getText() + "." + txtUserNameNum.getText();
				String password = String.valueOf(txtPassword.getText());
				String checkPass = library.validPassword(password);
				int error = 0;

				lblUsernameError.setText("");
				lblPasswordError.setText("");

				if (txtUserNameNum.getText().isEmpty()) {
					lblUsernameError.setText("The username code cannot be empty");
					error++;
				} else {
					boolean errorUsername = false;
					for (int i = 0; i < tArray.size(); i++) {
						if(username.equalsIgnoreCase(tArray.getIndexElement(i).getUsername())) {
							errorUsername = true;
						}
					}
					if(errorUsername) {
						lblUsernameError.setText("The username is duplicated!!!");
						error++;
					}
				}
				// The auto number compare got crush or not
				if (password.isEmpty()) {
					lblPasswordError.setText("The password cannot be empty");
					error++;
				} else if (checkPass != null) {
					lblPasswordError.setText(checkPass);
					error++;
				}
				if (error == 0) {
					// run the code for storing
					TravelLegAccount pAccount = new TravelLegAccount(username, password);
					tArray.addItem(pAccount);
					tFile.writeLinkArray(tArray);
					library.dialogMessage("Account added successful!!!");
					SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCompany(mainFrame)));
				}

			
		});

		btnAdd.setForeground(new Color(0, 0, 0));
		btnAdd.setBounds(185, 317, 79, 24);
		add(btnAdd);

		Button btnReset = new Button("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUserNameNum.setText("");
				txtPassword.setText("");
				lblUsernameError.setText("");
				lblPasswordError.setText("");
			}
		});
		btnReset.setForeground(new Color(0, 0, 0));
		btnReset.setBounds(317, 317, 79, 24);
		add(btnReset);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean checkRepeat = true;
				do {
					txtUserNameNum.setText(library.randomID(0, 99999));
					checkRepeat = false;
				} while (checkRepeat);
			}
		});
		btnGenerate.setBounds(399, 131, 97, 25);
		add(btnGenerate);

		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPassword.setText(library.generatePassword());
			}
		});
		btnNewButton.setBounds(399, 178, 97, 25);
		add(btnNewButton);

	}
}
