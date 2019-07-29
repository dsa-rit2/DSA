package travelBug.UI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import travelBug.library.LinkArray;
import travelBug.library.ReadWriteFile;
import travelBug.library.library;
import travelBug.obj.TravelLegAccount;
import travelBug.obj.User;

public class Authentication extends JFrame {
	private static final long serialVersionUID = 7701272199204927084L;
	private JPanel contentPane;

	private LinkArray<TravelLegAccount> tArray = new LinkArray<TravelLegAccount>();
	private ReadWriteFile<TravelLegAccount> tFile = new ReadWriteFile<TravelLegAccount>("TravelLegAccount.txt",
			TravelLegAccount.class);
	private LinkArray<User> uArray = new LinkArray<User>();

	public static void main(String [] args) {
		Authentication frame = new Authentication();
		frame.setVisible(true);
	}
	
	public Authentication() {

		//=============Read TravelLeg Account and add role to link array==================//
		tArray = tFile.readLinkArray();
		for (int i = 0; i < tArray.size(); i++) {
			uArray.addItem(new User(tArray.getIndexElement(i).getUsername(), tArray.getIndexElement(i).getPassword(),
					"travelLegAcc"));
		}

		// Customer haven't done

		
		//=============================Login Content===============================//
		setTitle("TravelBug - Login");
		setResizable(false);
		setBackground(new Color(255, 218, 185));
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		TextField usernameField = new TextField();
		usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		usernameField.setBounds(257, 122, 180, 30);
		panel.add(usernameField);

		Label username_label = new Label("Username :");
		username_label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		username_label.setBounds(140, 122, 106, 30);
		panel.add(username_label);

		Label password_label = new Label("Password :");
		password_label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		password_label.setBounds(140, 179, 103, 30);
		panel.add(password_label);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(257, 179, 180, 30);
		panel.add(passwordField);

		JTextPane txt_Title = new JTextPane();
		txt_Title.setFont(new Font("Monospaced", Font.BOLD, 32));
		txt_Title.setText("TravelBug");
		txt_Title.setBounds(201, 33, 177, 49);
		panel.add(txt_Title);

		Label usernameErrorLabel = new Label("");
		usernameErrorLabel.setForeground(Color.RED);
		usernameErrorLabel.setBackground(Color.WHITE);
		usernameErrorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		usernameErrorLabel.setBounds(257, 105, 180, 16);
		panel.add(usernameErrorLabel);

		Label passwordErrorLabel = new Label("");
		passwordErrorLabel.setForeground(Color.RED);
		passwordErrorLabel.setBackground(Color.WHITE);
		passwordErrorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		passwordErrorLabel.setBounds(257, 162, 180, 16);
		panel.add(passwordErrorLabel);

		
		//================Login Button===========================//
		Button loginBtn = new Button("Login");
		loginBtn.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setBackground(Color.GRAY);
		loginBtn.setBounds(201, 250, 177, 40);
		
		
		// ============ When user click login button ===============//
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				String username = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());

				int error = 0;

				boolean u = true;
				boolean p = true;

				usernameErrorLabel.setText("");
				passwordErrorLabel.setText("");

				if (username.isEmpty()) {
					usernameErrorLabel.setText("Username is empty");
					error++;
				} else {
					boolean checkUsername = false;
					for (int i = 0; i < uArray.size(); i++) {
						if (username.equalsIgnoreCase(uArray.getIndexElement(i).getUsername())) {
							checkUsername = true;
						}
					}
					if (checkUsername == true) {

						u = true;

					} else {
						usernameErrorLabel.setText("Username invalid!!!");
						error++;
					}
				}

				if (password.isEmpty()) {
					passwordErrorLabel.setText("Password is empty");
					error++;
				} else {
					boolean checkPassword = false;
					for (int i = 0; i < uArray.size(); i++) {

						if (password.equalsIgnoreCase(uArray.getIndexElement(i).getPassword())) {
							checkPassword = true;
						}
					}
					if (checkPassword == true) {

						p = true;

					} else {
						passwordErrorLabel.setText("Password invalid!!!");
						error++;
					}

				}

				if (error == 0 && u == true && p == true) {
					
					//=======================Redirect======================//
					
					//"Heven't done for the menu, UI contrl need to save the user info after login."
					
					JFrame frame = new JFrame("JOptionPane showMessageDialog example");
					JOptionPane.showMessageDialog(frame, "TravelLeg Account login successful!!!");

				} 

				
			}
		});
		panel.add(loginBtn);

	}
}
