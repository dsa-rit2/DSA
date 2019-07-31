package travelBug.UI;

//=========================
//	Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================
import javax.swing.*;
import java.awt.*;

public class Authentication extends JPanel {
	private static final long serialVersionUID = 7701272199204927084L; // Serializable purpose

	private LinkArray<TravelLegAccount> tArray = new LinkArray<TravelLegAccount>();
	private ReadWriteFile<TravelLegAccount> tFile = new ReadWriteFile<TravelLegAccount>("TravelLegAccount.txt",
			TravelLegAccount.class);

	private LinkArray<Customer> cArray = new LinkArray<Customer>();
	private ReadWriteFile<Customer> cFile = new ReadWriteFile<Customer>("Customer.txt", Customer.class);

	private LinkArray<Admin> aArray = new LinkArray<Admin>();
	private LinkArray<User> uArray = new LinkArray<User>();

	private final UIControl mainFrame; // Store main frame

	public Authentication(UIControl parent) {
		super();
		this.mainFrame = parent;

		// ===================== JPanel setting ======================
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		// ** Read TravelLeg Account and add role to link array **//
		tArray = tFile.readLinkArray();
		for (int i = 0; i < tArray.size(); i++) {
			uArray.addItem(new User(tArray.getIndexElement(i).getUsername(), tArray.getIndexElement(i).getPassword(),
					"travelLegAcc"));
		}

		// ** Read Customer Account and add role to link array **//
		cArray = cFile.readLinkArray();
		for (int i = 0; i < cArray.size(); i++) {
			uArray.addItem(new User(cArray.getIndexElement(i).getUsername(), cArray.getIndexElement(i).getPassword(),
					"CustomerAcc"));
		}

		JLabel username_label = new JLabel("Username :");
		username_label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		username_label.setBounds(220, 172, 106, 30);
		add(username_label);

		JLabel password_label = new JLabel("Password :");
		password_label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		password_label.setBounds(223, 226, 103, 30);
		add(password_label);

		// ======================= Login Content =======================//
		TextField usernameField = new TextField();
		usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		usernameField.setBounds(332, 172, 180, 30);
		add(usernameField);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(332, 226, 180, 30);
		add(passwordField);

		Label usernameErrorLabel = new Label("");
		usernameErrorLabel.setForeground(Color.RED);
		usernameErrorLabel.setBackground(Color.WHITE);
		usernameErrorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		usernameErrorLabel.setBounds(332, 204, 180, 16);
		add(usernameErrorLabel);

		Label passwordErrorLabel = new Label("");
		passwordErrorLabel.setForeground(Color.RED);
		passwordErrorLabel.setBackground(Color.WHITE);
		passwordErrorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		passwordErrorLabel.setBounds(332, 262, 180, 16);
		add(passwordErrorLabel);

		// ===================== Login Button ========================//
		Button loginBtn = new Button("Login");
		loginBtn.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setBackground(Color.GRAY);
		loginBtn.setBounds(380, 368, 132, 40);
		add(loginBtn);
		
		JLabel title = new JLabel("Login");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		title.setBounds(12, 13, 876, 50);
		add(title);

		// ============== When user click login button ===============//
		loginBtn.addActionListener(event -> {
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
				// =======================Redirect======================//
				/*
				 * "Heven't done for the menu, UI contrl need to save the user info after login."
				 */
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				JOptionPane.showMessageDialog(frame, "TravelLeg Account login successful!!!");
			}
		});
	}
}
