package travelBug.UI;

//==========================
import travelBug.library.*;
import travelBug.obj.*;
//==========================

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class EditCompany extends JFrame {

	private JPanel contentPane;
	private JTextField txtAsd;
	private JTextField shortF;
	private JTextField phoneN;
	private JTextField Descriptiontxt;
	private LinkArray<Company> cArray = new LinkArray<Company>();
	private ReadWriteFile<Company> rFile = new ReadWriteFile<Company>("Company.txt", Company.class);
	private int companyFoundindex;

	public EditCompany(String input) {
		boolean gotCompany = false;
		companyFoundindex = 0;
		cArray = rFile.readLinkArray();
		for(int i = 0; i < cArray.size(); i++) {
			if(input.equalsIgnoreCase(cArray.getIndexElement(i).getCompanyName())) {
				gotCompany = true;
				companyFoundindex = i;
			}
		}
		if(gotCompany == false) {
			//Redirect to the previous page
			
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Company Name:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 31, 118, 14);
		contentPane.add(lblNewLabel);
		
		txtAsd = new JTextField();
		txtAsd.setText(cArray.getIndexElement(companyFoundindex).getCompanyName());
		txtAsd.setBounds(176, 29, 207, 20);
		contentPane.add(txtAsd);
		txtAsd.setColumns(10);
		JLabel lblErrorCompanyName = new JLabel("");
		lblErrorCompanyName.setForeground(Color.RED);
		lblErrorCompanyName.setBounds(217, 31, 207, 14);
		contentPane.add(lblErrorCompanyName);
		
		JLabel lblShortForm = new JLabel("Short Form:");
		lblShortForm.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblShortForm.setBounds(10, 68, 118, 14);
		contentPane.add(lblShortForm);
		
		shortF = new JTextField();
		shortF.setText(cArray.getIndexElement(companyFoundindex).getShortForm());
		shortF.setBounds(176, 60, 207, 20);
		contentPane.add(shortF);
		shortF.setColumns(10);
		JLabel lblErrorShortForm = new JLabel("");
		lblErrorShortForm.setForeground(Color.RED);
		lblErrorShortForm.setBounds(217, 68, 207, 14);
		contentPane.add(lblErrorShortForm);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPhoneNumber.setBounds(10, 110, 118, 14);
		contentPane.add(lblPhoneNumber);
		
		phoneN = new JTextField();
		phoneN.setText(cArray.getIndexElement(companyFoundindex).getPhoneNum());

		phoneN.setBounds(176, 107, 207, 20);
		contentPane.add(phoneN);
		phoneN.setColumns(10);
		JLabel lblErrorPhoneNum = new JLabel("");
		lblErrorPhoneNum.setForeground(Color.RED);
		lblErrorPhoneNum.setBounds(217, 110, 207, 14);
		contentPane.add(lblErrorPhoneNum);
		
		
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDescription.setBounds(10, 153, 118, 14);
		contentPane.add(lblDescription);
		
		Descriptiontxt = new JTextField();
		Descriptiontxt.setText(cArray.getIndexElement(companyFoundindex).getDescription());
		Descriptiontxt.setBounds(10, 192, 373, 240);
		contentPane.add(Descriptiontxt);
		Descriptiontxt.setColumns(10);
		JLabel lblErrorDescription = new JLabel("");
		lblErrorDescription.setForeground(Color.RED);
		lblErrorDescription.setBounds(90, 238, 154, 14);
		contentPane.add(lblErrorDescription);
		
		JButton btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
//						System.out.println(cArray.indexGetElement(i).getCompanyName());
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
					//Print the window for the details thing 
//					 Show the company info
					for (int i = 0; i < cArray.size(); i++) {
						cArray.getIndexElement(i).print();
					}
					JFrame frame = new JFrame("JOptionPane showMessageDialog example");

					JOptionPane.showMessageDialog(frame,"Company Updated successful!!!");
//					System.exit(0);
					//Redirect the page to another
				}
				
				
			}
		});
		btnModify.setBounds(441, 405, 89, 23);
		contentPane.add(btnModify);
		
		
		
	
		
		
		
		
	}
}
