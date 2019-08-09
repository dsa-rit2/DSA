package travelBug.UI;

import java.awt.*;
import javax.swing.*;

import travelBug.library.library;

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
		lblAdmin.setBounds(765, 24, 56, 16);
		add(lblAdmin);
		
		JLabel menu = new JLabel("Menu");
		menu.setFont(new Font("Tahoma", Font.BOLD, 21));
		menu.setBounds(381, 59, 69, 32);
		add(menu);
		
		JLabel lblWelcomeBack = new JLabel("Welcome Back:");
		lblWelcomeBack.setBounds(693, 25, 92, 14);
		add(lblWelcomeBack);
		
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
