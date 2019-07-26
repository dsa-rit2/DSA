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

public class AddTravelLegAccount extends JFrame {
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

	public AddTravelLegAccount(String anything) {
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
		getContentPane().setBackground(new Color(204, 255, 0));
		getContentPane().setForeground(Color.WHITE);
		setResizable(false);
		setTitle("TravelBug");
		setResizable(false);
		setBackground(new Color(255, 218, 185));
		setBounds(100, 100, 611, 440);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(12, 66, 89, 16);
		getContentPane().add(lblUsername);

		JTextField txtUserFront = new JTextField();
		txtUserFront.setText(shortFormString);
		txtUserFront.setEditable(false);
		txtUserFront.setBounds(131, 63, 79, 22);
		getContentPane().add(txtUserFront);
		txtUserFront.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(12, 107, 89, 16);
		getContentPane().add(lblPassword);

//		JPasswordField txtPassword = new JPasswordField();
//		txtPassword.setBounds();
//		getContentPane().add(txtPassword);

		txtPassword = new JTextField();
		txtPassword.setBounds(131, 104, 256, 22);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);

		JLabel lblUsernameError = new JLabel("");
		lblUsernameError.setForeground(new Color(255, 0, 0));
		lblUsernameError.setBounds(141, 85, 416, 16);
		getContentPane().add(lblUsernameError);

		JLabel lblPasswordError = new JLabel("");
		lblPasswordError.setForeground(new Color(255, 0, 0));
		lblPasswordError.setBounds(141, 127, 416, 16);
		getContentPane().add(lblPasswordError);

		txtUserNameNum = new JTextField();
		txtUserNameNum.setBounds(230, 63, 79, 22);
		getContentPane().add(txtUserNameNum);
		txtUserNameNum.setColumns(10);

		JLabel lblAddTravelLeg = new JLabel(anything);
		lblAddTravelLeg.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblAddTravelLeg.setBounds(12, 13, 392, 46);
		getContentPane().add(lblAddTravelLeg);
		contentPane = new JPanel();
		Button btnAdd = new Button("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					JFrame frame = new JFrame("JOptionPane showMessageDialog example");
					JOptionPane.showMessageDialog(frame,"Account added successful!!!");
					//Redirect the frame to the details of the company
				}

			}
		});

		btnAdd.setForeground(new Color(0, 0, 0));
		btnAdd.setBounds(187, 371, 79, 24);
		getContentPane().add(btnAdd);

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
		btnReset.setBounds(319, 371, 79, 24);
		getContentPane().add(btnReset);

		JLabel lblNewLabel = new JLabel(".");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(215, 69, 24, 16);
		getContentPane().add(lblNewLabel);

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
		btnGenerate.setBounds(399, 62, 97, 25);
		getContentPane().add(btnGenerate);

		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPassword.setText(library.generatePassword());
			}
		});
		btnNewButton.setBounds(399, 103, 97, 25);
		getContentPane().add(btnNewButton);

	}
}
