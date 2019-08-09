package travelBug.UI;

import java.awt.*;
import javax.swing.*;

import travelBug.library.library;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
		menu.setBounds(322, 34, 69, 32);
		add(menu);
		
		JLabel lblWelcomeBack = new JLabel("Welcome Back:");
		lblWelcomeBack.setBounds(693, 25, 92, 14);
		add(lblWelcomeBack);
		
		JLabel lblCustomer = new JLabel("");
		lblCustomer.setBounds(93, 89, 229, 179);
		lblCustomer.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCustomer(mainFrame)));
			}
		});
		lblCustomer.setIcon(new ImageIcon(library.currentDirectoryPath + "\\images\\output-onlinepngtools.png"));
		
		add(lblCustomer);
		
		JLabel lblCustomerList = new JLabel("Customer List");
		lblCustomerList.setBounds(157, 275, 92, 14);
		add(lblCustomerList);
		
		JLabel lblLocation = new JLabel("");
		lblLocation.setIcon(new ImageIcon(library.currentDirectoryPath + "\\images\\location.png"));
		lblLocation.setBounds(462, 44, 269, 220);
		add(lblLocation);
		
		JLabel label = new JLabel("Customer List");
		label.setBounds(548, 275, 92, 14);
		add(label);
		
		
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
