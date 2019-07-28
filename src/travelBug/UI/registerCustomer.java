package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.LinkArray;
import logic.ReadWriteFile;
import logic.library;
import objClass.Company;
import objClass.Customer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class registerCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField usertxt;
	private JTextField passwordtxt;
	private JTextField comfirmPtxt;
	private LinkArray<Customer> cArray = new LinkArray<Customer>();
	private ReadWriteFile<Customer> rFile = new ReadWriteFile<Customer>("Customer.txt", Customer.class);
	private JTextField txtF;
	private JTextField txtL;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerCustomer frame = new registerCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public registerCustomer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Username:");
		lblName.setBounds(10, 26, 78, 14);
		contentPane.add(lblName);
		
		usertxt = new JTextField();
		usertxt.setBounds(97, 23, 86, 20);
		contentPane.add(usertxt);
		usertxt.setColumns(10);
		
		JLabel lblFirstname_1 = new JLabel("FirstName:");
		lblFirstname_1.setBounds(10, 78, 107, 14);
		contentPane.add(lblFirstname_1);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setBounds(10, 205, 78, 34);
		contentPane.add(lblPassword);
		
		passwordtxt = new JTextField();
		passwordtxt.setBounds(112, 212, 86, 20);
		contentPane.add(passwordtxt);
		passwordtxt.setColumns(10);
		
		JLabel lblComfirmPassword = new JLabel("Comfirm Password:");
		lblComfirmPassword.setBounds(10, 282, 107, 14);
		contentPane.add(lblComfirmPassword);
		
		JLabel lblfirstName = new JLabel("");
		lblfirstName.setBounds(215, 78, 134, 14);
		contentPane.add(lblfirstName);
		
		JLabel lblLastName = new JLabel("");
		lblLastName.setBounds(215, 145, 107, 14);
		contentPane.add(lblLastName);
		comfirmPtxt = new JTextField();
		comfirmPtxt.setBounds(137, 279, 86, 20);
		contentPane.add(comfirmPtxt);
		comfirmPtxt.setColumns(10);
		
		JLabel lblU = new JLabel("");
		lblU.setBounds(215, 26, 107, 14);
		contentPane.add(lblU);
		
		JLabel lblP = new JLabel("haha");
		lblP.setBounds(256, 215, 93, 14);
		contentPane.add(lblP);
		
		JLabel lblCP = new JLabel("haha");
		lblCP.setBounds(272, 282, 121, 14);
		contentPane.add(lblCP);
		
		JLabel lblNewLabel = new JLabel("LastName:");
		lblNewLabel.setBounds(10, 145, 78, 14);
		contentPane.add(lblNewLabel);
		
		txtF = new JTextField();
		txtF.setBounds(97, 75, 86, 20);
		contentPane.add(txtF);
		txtF.setColumns(10);
		
		txtL = new JTextField();
		txtL.setBounds(97, 142, 86, 20);
		contentPane.add(txtL);
		txtL.setColumns(10);
		
		JButton btnRegister = new JButton("register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cArray = rFile.readLinkArray();
				int error = 0;
				String name = usertxt.getText();
				String password = passwordtxt.getText();
				String comfirmPString = comfirmPtxt.getText();
				String fname = txtF.getText();
				String lname = txtL.getText();
				lblComfirmPassword.setText("");
				lblName.setText("");
				lblPassword.setText("");
				lblfirstName.setText("");
				lblLastName.setText("");
				if (library.isEmpty(name)) {
					lblName.setText("The company name cannot be empty");
					error++;
				} else {
					boolean errorName = false;
					for (int i = 0; i < cArray.size(); i++) {
						if (cArray.indexGetElement(i).getUsername().equalsIgnoreCase(name)) {
							errorName = true;
						}
//						System.out.println(cArray.indexGetElement(i).getCompanyName());
					}
					if (errorName) {
						lblName.setText("The Username is duplicated");
						error++;
					}
				}if (library.isEmpty(txtF.getText())) {
					lblfirstName.setText("The First Name cannot be empty");
					error++;
				}
				if (library.isEmpty(txtL.getText())) {
					lblLastName.setText("The Last Name cannot be empty");
					error++;
				}
				if (error == 0) {
					Customer  oCustomer = new Customer(fname,lname,name,password);
					cArray.addItem(oCustomer);
					rFile.writeLinkArray(cArray);
					
//					System.exit(0);
					//Redirect the page to another
				}
			}
		});
		btnRegister.setBounds(451, 308, 89, 23);
		contentPane.add(btnRegister);
		
	
		
	
	}
}
