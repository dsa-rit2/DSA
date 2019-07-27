package travelBug.UI;

import java.awt.*;
import javax.swing.*;

public class UIControl extends JFrame {
	private static final long serialVersionUID = 1L;								// Serializable purpose
	protected final String currentDirectoryPath = System.getProperty("user.dir");	// Get root directory path
	protected static String titleName = "";
	
	public UIControl() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { e.printStackTrace(); }
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(currentDirectoryPath + "\\images\\logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(900, 630));
		setBounds(new Rectangle(new Dimension(900, 600)));
		pack();
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		//=================== Programe logo and name ==================
		JLabel logoName = new JLabel("TravelBug");
		logoName.setFont(new Font("Monospaced", Font.BOLD, 50));
		logoName.setIcon(new ImageIcon(currentDirectoryPath + "\\images\\logo.png"));
		logoName.setBounds(12, 0, 870, 130);
		getContentPane().add(logoName);
		logoName.setHorizontalAlignment(SwingConstants.CENTER);
		
		//=================== Content panel control ===================
		AddCompany addCompany = new AddCompany();
		addCompany.setLocation(0, 115);
//		getContentPane().add(addCompany);		// Add Company Panel
		
		AddLocation addLocation = new AddLocation();
		addLocation.setLocation(0, 115);
		getContentPane().add(addLocation);
		
		//===================== Background image ======================
		JLabel backgroundImg = new JLabel();
		backgroundImg.setBounds(0, 0, 894, 620);
		backgroundImg.setIcon(new ImageIcon(currentDirectoryPath + "\\images\\form-background.jpg"));
		getContentPane().add(backgroundImg);
		
		setTitle("TravelBug - " + titleName);	// Set Title
	}
}
