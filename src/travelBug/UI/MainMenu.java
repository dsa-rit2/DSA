package travelBug.UI;

import java.awt.*;
import javax.swing.*;


public class MainMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	private final UIControl mainFrame; // Store main frame

	private JLabel lblUsernameJLabel;

	public MainMenu(UIControl parent) {
		super();
		this.mainFrame = parent;
		// ======================== JPanel setting ===========================
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

//--------------------------Component-----------------------------------

	
		JLabel lblWelcomeBack = new JLabel("Welcome Back:");
		lblWelcomeBack.setBounds(683, 35, 92, 14);
		add(lblWelcomeBack);

		lblUsernameJLabel = new JLabel("");
		lblUsernameJLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsernameJLabel.setBounds(759, 25, 112, 29);
		add(lblUsernameJLabel);

		lblUsernameJLabel.setText(mainFrame.authUser.getUsername());

		JLabel lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setBounds(345, 35, 112, 29);
		add(lblMainMenu);
		lblMainMenu.setFont(new Font("Times New Roman", Font.BOLD, 20));

		
	
		// ----------------------- Check Role ------------------------
		if (parent.authUser.getRole().equalsIgnoreCase("Admin")) {
			adminGUI();
		} else if (parent.authUser.getRole().equalsIgnoreCase("TravelLeg")) {
			travelLegGUI();
		} else if (parent.authUser.getRole().equalsIgnoreCase("User")) {
			userGUI();
		}
	}

	// --------------------------Admin Content----------------------
	private void adminGUI() {

		
		Button button = new Button("Customer List");
		button.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(139, 69, 19));
		button.addActionListener(event ->{
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCustomer(mainFrame)));

		});
		button.setBounds(103, 139, 214, 61);
		add(button);
		
		Button button_1 = new Button("Company List");
		button_1.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(new Color(139, 69, 19));
		button_1.addActionListener(event ->{
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCompany(mainFrame)));

		});
		button_1.setBounds(403, 139, 189, 61);
		add(button_1);
		
		Button button_2 = new Button("Location List");
		button_2.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(new Color(139, 69, 19));
		button_2.addActionListener(event-> {
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListLocation(mainFrame)));

		});
		button_2.setBounds(403, 237, 189, 61);
		add(button_2);
		
		Button button_3 = new Button("Report List");
		button_3.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		button_3.setForeground(Color.WHITE);
		button_3.setBackground(new Color(139, 69, 19));
		button_3.addActionListener(event ->{
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new AdminReport(mainFrame)));

		});
		button_3.setBounds(103, 237, 214, 61);
		add(button_3);
		
		Button button_4 = new Button("Log out");
		button_4.addActionListener(event ->{
			logout();
		});
		button_4.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		button_4.setForeground(Color.RED);
		button_4.setBackground(new Color(139, 69, 19));
		button_4.setBounds(690, 383, 189, 61);
		add(button_4);
		
		Button button_5 = new Button("Travel Lag Maintanance");
		button_5.addActionListener(event->{
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new TravelLegMaintenance(mainFrame)));

		});
		button_5.setForeground(Color.WHITE);
		button_5.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		button_5.setBackground(new Color(139, 69, 19));
		button_5.setBounds(237, 332, 244, 61);
		add(button_5);

	}

	// ------------Travel Leg Content----------------------------
	private void travelLegGUI() {
//		

		Button button_1 = new Button("Log Out");
		button_1.addActionListener(event -> {
			logout();
		});
		button_1.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(new Color(139, 69, 19));
		button_1.setBounds(648, 379, 189, 61);

		add(button_1);

		Button button_2 = new Button("Travel Leg Maintanance");
		button_2.addActionListener(event -> {
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new TravelLegMaintenance(mainFrame)));
		});
		button_2.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(new Color(139, 69, 19));
		button_2.setBounds(300, 124, 298, 61);

		add(button_2);
	}

	// -------------------User Content----------------------------------
	private void userGUI() {
		Button button = new Button("Plan Trip");
		button.addActionListener(event -> {
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new PlanTrip(mainFrame)));
		});
		button.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(139, 69, 19));
		button.setBounds(178, 127, 189, 61);
		add(button);

		Button button_1 = new Button("Log Out");
		button_1.addActionListener(event -> {
			logout();
		});
		button_1.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(new Color(139, 69, 19));
		button_1.setBounds(435, 127, 189, 61);
		add(button_1);
	}

	private void logout() {
		mainFrame.dispose();
		new Authentication();
	}
}
