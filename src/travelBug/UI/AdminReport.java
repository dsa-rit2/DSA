package travelBug.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;

public class AdminReport extends JFrame {

	private final UIControl mainFrame;
	private JTable tablexd;
	public AdminReport(UIControl parent) {
		super();
		this.mainFrame = parent;
				setBackground(new Color(0, 0, 0, 0));
				setBounds(new Rectangle(new Dimension(900, 450)));
				getContentPane().setLayout(null);
				
				tablexd = new JTable();
				tablexd.setBounds(725, 87, -666, 220);
				getContentPane().add(tablexd);
				
				JLabel lblNB = new JLabel("Most popular Place");
				lblNB.setBounds(54, 26, 167, 38);
				getContentPane().add(lblNB);
				
	
	}
}
