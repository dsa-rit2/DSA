package travelBug.UI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	private final UIControl mainFrame; // Store main frame

	public MainMenu(UIControl parent) {
		super();
		this.mainFrame = parent;
		// ======================== JPanel setting ===========================
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));
		
		JLabel lblAdmin = new JLabel("Admin ");
		lblAdmin.setBounds(574, 24, 56, 16);
		add(lblAdmin);
		
		JLabel lblNewLabel = new JLabel("Menu");
		lblNewLabel.setBounds(278, 62, 56, 14);
		add(lblNewLabel);
		
		JLabel lblWelcomeBack = new JLabel("Welcome Back:");
		lblWelcomeBack.setBounds(488, 25, 92, 14);
		add(lblWelcomeBack);
		
		JButton btnPlanYouTrip = new JButton("Company List");
		btnPlanYouTrip.addActionListener(event -> {
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCompany(mainFrame)));

		});
		btnPlanYouTrip.setBounds(65, 120, 123, 25);
		add(btnPlanYouTrip);
		
		JButton btnAddCompany = new JButton("Customer List");
		btnAddCompany.addActionListener(event-> {
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCustomer(mainFrame)));

		});
		btnAddCompany.setBounds(65, 170, 123, 25);
		add(btnAddCompany);
		
		JButton btnNewButton_1 = new JButton("Location List");
		btnNewButton_1.addActionListener(event -> {
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListLocation(mainFrame)));

		});
		btnNewButton_1.setBounds(65, 232, 123, 23);
		add(btnNewButton_1);
		
		JButton btnReport = new JButton("Report");
		btnReport.addActionListener(event ->{
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new AdminReport(mainFrame)));

		});
		btnReport.setBounds(80, 297, 89, 23);
		add(btnReport);
		
		// ----------------------- Check Role ------------------------
		if (parent.authUser.getRole().equalsIgnoreCase("admin")) adminGUI();
		else if (parent.authUser.getRole().equalsIgnoreCase("travelLegAcc")) travelLegGUI();
		else if (parent.authUser.getRole().equalsIgnoreCase("CustomerAcc")) userGUI();
	}
	
	private void adminGUI() {
		
	}
	
	private void travelLegGUI() {
		
	}
	
	private void userGUI() {
		
	}
}
