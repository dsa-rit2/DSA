package travelBug.UI;

//=========================
//	Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================
import javax.swing.*;
import java.awt.*;

public class Authentication extends JFrame {
	private static final long serialVersionUID = 7701272199204927084L; // Serializable purpose

	TextField usernameField;
	JPasswordField passwordField;
	Label usernameErrorLabel, passwordErrorLabel;

	private ReadWriteFile<TravelLegAccount> tFile = new ReadWriteFile<TravelLegAccount>("TravelLegAccount.txt",
			TravelLegAccount.class);
	private LinkArray<TravelLegAccount> tArray = tFile.readLinkArray();

	private ReadWriteFile<Customer> cFile = new ReadWriteFile<Customer>("Customer.txt", Customer.class);
	private LinkArray<Customer> cArray = cFile.readLinkArray();

	private ReadWriteFile<Admin> aFile = new ReadWriteFile<Admin>("Admin.txt", Admin.class);
	private LinkArray<Admin> aArray = aFile.readLinkArray();

	private LinkArray<User> uArray = new LinkArray<User>();

	public Authentication() {
		// ===================== JFrame setting ======================
		setVisible(true);
		getContentPane().setLayout(null);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(library.currentDirectoryPath + "\\images\\logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TravelBug - Login");
		setPreferredSize(new Dimension(900, 600));
		setBounds(new Rectangle(getPreferredSize()));
		setLocationRelativeTo(null);

		// ===================== JPanel setting ======================
		getContentPane().setBounds(new Rectangle(getPreferredSize()));

		// ** Read TravelLeg Account and add role to link array **//
		tArray = tFile.readLinkArray();
		for (int i = 0; i < tArray.size(); i++) {
			uArray.addItem(new User(tArray.getIndexElement(i).getUsername(), tArray.getIndexElement(i).getPassword(),
					"TravelLeg"));
		}

		// ** Read Customer Account and add role to link array **//
		cArray = cFile.readLinkArray();
		for (int i = 0; i < cArray.size(); i++) {
			uArray.addItem(
					new User(cArray.getIndexElement(i).getUsername(), cArray.getIndexElement(i).getPassword(), "User"));
		}

		aArray = aFile.readLinkArray();
		if (aArray.size() == 0)
			aArray.addItem(new Admin("Admin", "admin")); // Initial default admin
		for (int i = 0; i < aArray.size(); i++) {
			uArray.addItem(new User(aArray.getIndexElement(i).getUsername(), aArray.getIndexElement(i).getPassword(),
					"Admin"));
		}

		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(270, 31, 350, 500));
		panel.setBackground(new Color(255, 255, 255, 100));
		getContentPane().add(panel);
		panel.setLayout(null);

		// ======================= Login Content =======================//
		JLabel logo = new JLabel();
		logo.setBounds(0, 30, 350, 130);
		panel.add(logo);
		logo.setFont(new Font("Monospaced", Font.BOLD, 50));
		logo.setIcon(new ImageIcon(library.currentDirectoryPath + "\\images\\logo.png"));
		logo.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel name = new JLabel("TravelBug");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("Monospaced", Font.BOLD, 30));
		name.setBounds(0, 132, 350, 77);
		panel.add(name);

		JLabel username_label = new JLabel("Username :");
		username_label.setBounds(75, 210, 106, 30);
		panel.add(username_label);
		username_label.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		JLabel password_label = new JLabel("Password :");
		password_label.setBounds(75, 310, 103, 30);
		panel.add(password_label);
		password_label.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		usernameField = new TextField();
		usernameField.setBounds(75, 249, 210, 30);
		panel.add(usernameField);
		usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		passwordField.setBounds(75, 345, 210, 30);
		panel.add(passwordField);

		usernameErrorLabel = new Label("");
		usernameErrorLabel.setBounds(75, 285, 210, 20);
		panel.add(usernameErrorLabel);
		usernameErrorLabel.setForeground(Color.RED);
		usernameErrorLabel.setBackground(new Color(255, 255, 255, 0));
		usernameErrorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		usernameErrorLabel.setVisible(false);

		passwordErrorLabel = new Label("");
		passwordErrorLabel.setBounds(75, 381, 210, 20);
		panel.add(passwordErrorLabel);
		passwordErrorLabel.setForeground(Color.RED);
		passwordErrorLabel.setBackground(new Color(255, 255, 255, 0));
		passwordErrorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		passwordErrorLabel.setVisible(false);

		// ===================== Login Button ========================//
		Button loginBtn = new Button("Login");
		loginBtn.setBounds(75, 420, 210, 40);
		loginBtn.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setBackground(new Color(139, 69, 19));
		panel.add(loginBtn);

		JLabel auth_wallpaper = new JLabel();
		auth_wallpaper.setVerticalAlignment(SwingConstants.TOP);
		auth_wallpaper.setBounds(0, 0, 896, 600);
		auth_wallpaper.setIcon(new ImageIcon(library.currentDirectoryPath + "\\images\\auth_wallpaper.jpg"));
		auth_wallpaper.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(auth_wallpaper);

		// ============== When user click login button ===============//
		loginBtn.addActionListener(e -> {
			checkUser();
		});
	}

	private void checkUser() {
		String username = usernameField.getText();
		String password = String.valueOf(passwordField.getPassword());

		usernameErrorLabel.setVisible(false);
		passwordErrorLabel.setVisible(false);

		boolean error = false;

		if (username.isEmpty()) {
			usernameErrorLabel.setText("Username is empty");
			usernameErrorLabel.setVisible(true);
			error = true;
		}

		if (password.isEmpty()) {
			passwordErrorLabel.setText("Password is empty");
			passwordErrorLabel.setVisible(true);
			error = true;
		} else {
			error = true;
			for (int i = 0; i < uArray.size(); i++) {
				if (username.equalsIgnoreCase(uArray.getIndexElement(i).getUsername())
						&& password.equals(uArray.getIndexElement(i).getPassword())) {
					error = false;
					dispose();
					new UIControl(new User(uArray.getIndexElement(i).getUsername(),
							uArray.getIndexElement(i).getPassword(), uArray.getIndexElement(i).getRole()));
				}
			}
			if (error) {
				passwordErrorLabel.setText("Username or password not correct");
				passwordErrorLabel.setVisible(true);
			}
		}
	}
}
