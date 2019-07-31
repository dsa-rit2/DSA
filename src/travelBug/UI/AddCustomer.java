package travelBug.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import travelBug.library.LinkArray;
import travelBug.library.ReadWriteFile;
import travelBug.library.library;
import travelBug.obj.Customer;
import travelBug.obj.TravelLegAccount;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Label;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AddCustomer extends JPanel {

	private final UIControl mainFrame;
	private JTextField txtUsername;
	private JTextField txtName;
	private JPasswordField txtPassword;
	private JTextField txtPhoneNumber;
	private LinkArray<Customer> cArray = new LinkArray<Customer>();
	private ReadWriteFile<Customer> cFile = new ReadWriteFile<Customer>("Customer.txt", Customer.class);
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public AddCustomer(UIControl parent) {
		// =================================================Panel Setting====================================//
		super();
		setForeground(Color.RED);
		this.mainFrame = parent;
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));
		setLayout(null);
		
		// =================================================Content Component====================================//
		JLabel lblNewLabel = new JLabel("Add Customer");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
		lblNewLabel.setBounds(342, 13, 219, 39);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_1.setBounds(174, 66, 105, 29);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Real Name:");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_2.setBounds(174, 131, 105, 29);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_3.setBounds(188, 196, 91, 29);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Phone Number:");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_4.setBounds(141, 259, 138, 32);
		add(lblNewLabel_4);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		txtUsername.setBounds(290, 65, 338, 32);
		add(txtUsername);
		txtUsername.setColumns(10);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		txtName.setBounds(290, 130, 338, 32);
		add(txtName);
		txtName.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		txtPassword.setBounds(290, 195, 338, 32);
		add(txtPassword);
		txtPassword.setColumns(10);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		txtPhoneNumber.setBounds(290, 260, 338, 32);
		add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);
		// =================================================Error Message====================================//
		Label lblErrorUsername = new Label("");
		lblErrorUsername.setForeground(Color.RED);
		lblErrorUsername.setBackground(Color.white);
		lblErrorUsername.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblErrorUsername.setBounds(290, 97, 453, 29);
		add(lblErrorUsername);
		
		Label lblErrorName = new Label("");
		lblErrorName.setForeground(Color.RED);
		lblErrorName.setBackground(Color.white);
		lblErrorName.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblErrorName.setBounds(290, 164, 453, 29);
		add(lblErrorName);
		
		Label lblErrorPassword = new Label("");
		lblErrorPassword.setForeground(Color.RED);
		lblErrorPassword.setBackground(Color.white);
		lblErrorPassword.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblErrorPassword.setBounds(290, 225, 453, 29);
		add(lblErrorPassword);
		
		Label lblErrorPhoneNumber = new Label("");
		lblErrorPhoneNumber.setForeground(Color.RED);
		lblErrorPhoneNumber.setBackground(Color.white);
		lblErrorPhoneNumber.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblErrorPhoneNumber.setBounds(290, 294, 453, 29);
		add(lblErrorPhoneNumber);
		
		//==================================Button============================================//
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(event->{
				cArray = cFile.readLinkArray();
				String username = txtUsername.getText();
				String password = String.valueOf(txtPassword.getPassword());
				String name = txtName.getText();
				String phoneNumber = library.checkPhoneNum(txtPhoneNumber.getText());
				String checkPass = library.validPassword(password);
				int error = 0;

				lblErrorName.setText("");
				lblErrorPassword.setText("");
				lblErrorPhoneNumber.setText("");
				lblErrorUsername.setText("");

				if (txtUsername.getText().isEmpty()) {
					lblErrorUsername.setText("The username code cannot be empty");
					error++;
				} else {
					boolean errorUsername = false;
					for (int i = 0; i < cArray.size(); i++) {
						if(username.equalsIgnoreCase(cArray.getIndexElement(i).getUsername())) {
							errorUsername = true;
						}
					}
					if(errorUsername) {
						lblErrorUsername.setText("The username is duplicated!!!");
						error++;
					}
				}
				// The auto number compare got crush or not
				if (password.isEmpty()) {
					lblErrorPassword.setText("The password cannot be empty");
					error++;
				} else if (checkPass != null) {
					lblErrorPassword.setText(checkPass);
					error++;
				}
				if(name.isEmpty()) {
					lblErrorName.setText("The name code cannot be empty");
					error++;
				}
				if(txtPhoneNumber.getText().isEmpty()) {
					lblErrorPhoneNumber.setText("The phone number cannot be empty");
					error++;
				}else if(phoneNumber==null) {
					lblErrorPhoneNumber.setText("The phone number is invalid");
					error++;
				}
				if (error == 0) {
					// run the code for storing
					Customer customer = new Customer(name, username, password, phoneNumber);
					cArray.addItem(customer);
					cFile.writeLinkArray(cArray);
					int result = JOptionPane.showConfirmDialog(null,
							"Add customer successful\n Do you want to add more",
							"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {
						try {
							SwingUtilities.invokeLater(() -> mainFrame.changePanel(new AddCustomer(mainFrame)));
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						try {
							//list customer
//							SwingUtilities.invokeLater(() -> mainFrame.changePanel(new listTravelLegAccount(mainFrame)));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			
		});
		btnNewButton.setBounds(290, 348, 105, 39);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(event->{
		});
		btnNewButton_1.setBounds(486, 348, 110, 39);
		add(btnNewButton_1);

	}
}
