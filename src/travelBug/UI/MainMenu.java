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
	private JLabel lblCustomerList;
	private JLabel lblLocation;
	private JLabel lblCustomer;
	private JLabel lblReport;
	private JLabel lblCompany;

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
		lblCustomer.setBounds(93, 89, 204, 142);
		lblCustomer.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCustomer(mainFrame)));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCustomer(mainFrame)));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCustomer(mainFrame)));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCustomer(mainFrame)));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCustomer(mainFrame)));
			}
		});
		lblCustomer.setIcon(new ImageIcon(library.currentDirectoryPath + "\\images\\output-onlinepngtools.png"));
		
		add(lblCustomer);
		
		lblCustomerList = new JLabel("");
		lblCustomerList.setBounds(157, 275, 92, 14);
		add(lblCustomerList);
		
		lblLocation = new JLabel("");
		lblLocation.setIcon(new ImageIcon(library.currentDirectoryPath + "\\images\\location.png"));
		lblLocation.setBounds(483, 89, 204, 142);
		add(lblLocation);
		
		lblReport = new JLabel("");
		lblReport.setBounds(93, 275, 204, 142);
		add(lblReport);
		
		lblCompany = new JLabel("");
		lblCompany.setBounds(483, 295, 204, 142);
		add(lblCompany);
		
		
		// ----------------------- Check Role ------------------------
		if (parent.authUser.getRole().equalsIgnoreCase("admin")) adminGUI();
		else if (parent.authUser.getRole().equalsIgnoreCase("travelLegAcc")) travelLegGUI();
		else if (parent.authUser.getRole().equalsIgnoreCase("CustomerAcc")) userGUI();
	}
	
	private void adminGUI() {
		lblCustomer.setVisible(true);
		lblLocation.setVisible(true);
		lblReport.setVisible(true);
		lblCompany.setVisible(true);
	}
	
	private void travelLegGUI() {
		
	}
	
	private void userGUI() {
		
	}
}
