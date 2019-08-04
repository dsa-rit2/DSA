package travelBug.UI;

import java.awt.*;
import javax.swing.*;

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
		lblAdmin.setBounds(425, 13, 56, 16);
		add(lblAdmin);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(318, 204, 97, 25);
		add(btnNewButton);
		
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
