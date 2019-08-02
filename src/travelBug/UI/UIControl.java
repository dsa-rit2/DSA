package travelBug.UI;

import java.awt.*;
import java.nio.channels.NonReadableChannelException;
import java.util.Vector;

import javax.swing.*;

import travelBug.library.library;

public class UIControl extends JFrame {
	private static final long serialVersionUID = 1L; // Serializable purpose
//	protected static String titleName = "";

	public UIControl(String titleName) {
		super(titleName);
		createGUI();
	}

	private void createGUI() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(library.currentDirectoryPath + "\\images\\logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(900, 630));
		setBounds(new Rectangle(new Dimension(900, 600)));
		pack();
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		// ============= Change Panel Section =============
//		changePanel(new AddLocation(this));
//		changePanel(new PlanTrip(this));
//		changePanel(new TravelLegMaintenance(this));
//		changePanel(new AddCompany(this));
		changePanel(new ListCompany(this));


//		changePanel(new AddCompany(this));
//		changePanel(new Authentication(this));
	}

	public void changePanel(JPanel panelName) {
		getContentPane().removeAll(); // Remove all component

		// =================== Programe logo and name ==================
		JLabel logoName = new JLabel("TravelBug");
		logoName.setFont(new Font("Monospaced", Font.BOLD, 50));
		logoName.setIcon(new ImageIcon(library.currentDirectoryPath + "\\images\\logo.png"));
		logoName.setBounds(12, 0, 870, 130);
		getContentPane().add(logoName);
		logoName.setHorizontalAlignment(SwingConstants.CENTER);

		// =================== Content panel control ===================

		panelName.setLocation(0, 115);
		getContentPane().add(panelName);

		// ===================== Background image ======================
		JLabel backgroundImg = new JLabel();
		backgroundImg.setBounds(0, 0, 894, 620);
		backgroundImg.setIcon(new ImageIcon(library.currentDirectoryPath + "\\images\\form-background.jpg"));
		getContentPane().add(backgroundImg);

		// ===================== Update graphics =======================
		update(getGraphics());
		revalidate();
		repaint();
	}
}
