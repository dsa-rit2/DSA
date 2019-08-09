package travelBug.UI;

import java.awt.*;
import javax.swing.*;

import travelBug.library.library;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
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
		
		// ========================= Component ===============================
//		JLabel lblAdmin = new JLabel("Admin ");
//		lblAdmin.setBounds(804, 24, 56, 16);
//		add(lblAdmin);
//		
//		JLabel menu = new JLabel("Menu");
//		menu.setFont(new Font("Tahoma", Font.BOLD, 21));
//		menu.setBounds(331, 24, 69, 32);
//		add(menu);
//		
//		JLabel lblWelcomeBack = new JLabel("Welcome Back:");
//		lblWelcomeBack.setBounds(725, 25, 92, 14);
//		add(lblWelcomeBack);
//		
//		Button button = new Button("Plan Trip");
//		button.addActionListener(event -> {
//			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new PlanTrip(mainFrame)));
//		});
//		button.setBackground(Color.LIGHT_GRAY);
//		button.setBounds(178, 127, 84, 57);
//		add(button);
//		
//		Button button_1 = new Button("Log Out");
//		button_1.addActionListener(event ->{
//			logout();
//		});
//		button_1.setBackground(Color.LIGHT_GRAY);
//		button_1.setBounds(435, 127, 92, 57);
//		add(button_1);
		
//		Button button = new Button("Plan Trip");
//		button.addActionListener(event -> {
//			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new PlanTrip(mainFrame)));
//		});
//		button.setForeground(Color.BLACK);
//		button.setBackground(Color.LIGHT_GRAY);
//		button.setBounds(178, 114, 100, 48);
//		add(button);
//		
//		Button button_1 = new Button("Log Out");
//		button_1.addActionListener(event ->{
//			logout();
//		});
//		button_1.setBackground(Color.LIGHT_GRAY);
//		button_1.setForeground(Color.BLACK);
//		button_1.setBounds(440, 284, 92, 48);
//		add(button_1);
//		
//		Button button_2 = new Button("Travel Leg Maintanance");
//		button_2.addActionListener(event->{
//			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new TravelLegMaintenance(mainFrame)));
//		});
//		button_2.setBackground(Color.LIGHT_GRAY);
//		button_2.setBounds(331, 114, 124, 48);
//		add(button_2);
		

//		Button button = new Button("Customer List");
//		button.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		button.setBackground(Color.lightGray);
//		button.addActionListener(event ->{
//		
//			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCustomer(mainFrame)));
//
//		});
//		button.setBounds(103, 139, 214, 61);
//		add(button);
//		
//		Button button_1 = new Button("Company List");
//		button_1.setBackground(Color.LIGHT_GRAY);
//		button_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		button_1.addActionListener(event ->{
//			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCompany(mainFrame)));
//
//		});
//		button_1.setBounds(403, 139, 189, 61);
//		add(button_1);
//		
//		Button button_2 = new Button("Location List");
//		button_2.setBackground(Color.LIGHT_GRAY);
//		button_2.setForeground(Color.BLACK);
//		button_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		button_2.addActionListener(event-> {
//			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListLocation(mainFrame)));
//
//		});
//		button_2.setBounds(403, 237, 189, 61);
//		add(button_2);
//		
//		Button button_3 = new Button("Report List");
//		button_3.setBackground(Color.LIGHT_GRAY);
//		button_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		button_3.addActionListener(event ->{
//			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new AdminReport(mainFrame)));
//
//		});
//		button_3.setBounds(103, 237, 214, 61);
//		add(button_3);
//		
//		Button button_4 = new Button("Log out");
//		button_4.addActionListener(event ->{
//			logout();
//		});
//		button_4.setBackground(Color.LIGHT_GRAY);
//		button_4.setForeground(Color.RED);
//		button_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
//		button_4.setBounds(690, 383, 70, 22);
//		add(button_4);

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
		if (parent.authUser.getRole().equalsIgnoreCase("Admin")){
			adminGUI();
		}
		else if (parent.authUser.getRole().equalsIgnoreCase("TravelLeg")){
			travelLegGUI();
		}
		else if (parent.authUser.getRole().equalsIgnoreCase("User")) {
		userGUI();
		}
		
	}
	
	private void adminGUI() {

		Button button = new Button("Customer List");
		button.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button.setBackground(Color.lightGray);
		button.addActionListener(event ->{
		
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCustomer(mainFrame)));

		});
		button.setBounds(103, 139, 214, 61);
		add(button);
		
		Button button_1 = new Button("Company List");
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button_1.addActionListener(event ->{
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCompany(mainFrame)));

		});
		button_1.setBounds(403, 139, 189, 61);
		add(button_1);
		
		Button button_2 = new Button("Location List");
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.setForeground(Color.BLACK);
		button_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button_2.addActionListener(event-> {
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListLocation(mainFrame)));

		});
		button_2.setBounds(403, 237, 189, 61);
		add(button_2);
		
		Button button_3 = new Button("Report List");
		button_3.setBackground(Color.LIGHT_GRAY);
		button_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button_3.addActionListener(event ->{
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new AdminReport(mainFrame)));

		});
		button_3.setBounds(103, 237, 214, 61);
		add(button_3);
		
		Button button_4 = new Button("Log out");
		button_4.addActionListener(event ->{
			logout();
		});
		button_4.setBackground(Color.LIGHT_GRAY);
		button_4.setForeground(Color.RED);
		button_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		button_4.setBounds(690, 383, 70, 22);
		add(button_4);

		lblCustomer.setVisible(true);
		lblLocation.setVisible(true);
		lblReport.setVisible(true);
		lblCompany.setVisible(true);

	}
	
	private void travelLegGUI() {
		Button button = new Button("Plan Trip");
		button.addActionListener(event -> {
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new PlanTrip(mainFrame)));
		});
		button.setForeground(Color.BLACK);
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(178, 114, 100, 48);
		add(button);
		
		Button button_1 = new Button("Log Out");
		button_1.addActionListener(event ->{
			logout();
		});
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setForeground(Color.BLACK);
		button_1.setBounds(440, 284, 92, 48);
		add(button_1);
		
		Button button_2 = new Button("Travel Leg Maintanance");
		button_2.addActionListener(event->{
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new TravelLegMaintenance(mainFrame)));
		});
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.setBounds(331, 114, 124, 48);
		add(button_2);
	}
	
	private void userGUI() {
		Button button = new Button("Plan Trip");
		button.addActionListener(event -> {
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new PlanTrip(mainFrame)));
		});
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(178, 127, 84, 57);
		add(button);
		
		Button button_1 = new Button("Log Out");
		button_1.addActionListener(event ->{
			logout();
		});
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(435, 127, 92, 57);
		add(button_1);
	}
	
	private void logout() {
		mainFrame.dispose();
		new Authentication();
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
}
