package travelBug.UI;

import java.awt.*;
import javax.swing.*;
import javax.xml.transform.Templates;

import travelBug.library.CircularLinkedList;
import travelBug.library.SinglyLinkedList;
import travelBug.obj.TravelLegInfo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DisplayTrip extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UIControl mainFrame;
	private JButton btnBack;
	private CircularLinkedList<TravelLegInfo> tempCircularLinkedList;
	private SinglyLinkedList<CircularLinkedList<TravelLegInfo>> temp = new SinglyLinkedList<CircularLinkedList<TravelLegInfo>>();
	private int adult = 0, child = 0;
	
	public DisplayTrip(UIControl parent,CircularLinkedList<TravelLegInfo> d, SinglyLinkedList<CircularLinkedList<TravelLegInfo>> t, int a, int c) {

		super();
		this.mainFrame = parent;
		tempCircularLinkedList = d;
		adult = a;
		child = c;
		temp = t;
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		createGUI();
	}
	
	
	private void createGUI() {
		
		String source = null;
		String dest = null;
		double distance = 0.00;
		int duration = 0;
		double travelprice = 0.00;
		double price = 0.00;
		double AdultCost = 0.00;
		double ChildCost = 0.00;
		double total = 0.00;
		
		source   = tempCircularLinkedList.getEntry(1).getSource();
		dest     = tempCircularLinkedList.getEntry(tempCircularLinkedList.getNumberOfEntries()).getDest();
		

		for(int i = 1; i <= tempCircularLinkedList.getNumberOfEntries(); i++) {
			

			distance += tempCircularLinkedList.getEntry(i).getDistance();
			duration += tempCircularLinkedList.getEntry(i).getDuration();
			price    = tempCircularLinkedList.getEntry(i).getPrice();
			travelprice += price;
			AdultCost = travelprice * adult;
			ChildCost = travelprice * child * 0.5;
			total = AdultCost + ChildCost;
		}
		
		// ====================Title=======================//
		JLabel lblViewTrip = new JLabel("Display Trip");
		lblViewTrip.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewTrip.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblViewTrip.setBounds(10, 15, 878, 45);
		add(lblViewTrip);
		
		//////////////////////////////////////////////////////Content/////////////////////////////////////////////////////////////
		
		//============================From================================//
		JLabel lblFrom = new JLabel("From                       : ");
		lblFrom.setHorizontalAlignment(SwingConstants.LEFT);
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFrom.setBounds(109, 55, 212, 45);
		add(lblFrom);
		
		JLabel lblFromData = new JLabel("");
		lblFromData.setHorizontalAlignment(SwingConstants.LEFT);
		lblFromData.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFromData.setBounds(328, 55, 493, 45);
		add(lblFromData);
		
		//===============================================================//
		
		//============================To================================//
		JLabel lblTo = new JLabel("To                            : ");
		lblTo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTo.setBounds(109, 89, 212, 45);
		add(lblTo);
		
		JLabel lblToData = new JLabel("");
		lblToData.setHorizontalAlignment(SwingConstants.LEFT);
		lblToData.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblToData.setBounds(328, 89, 493, 45);
		add(lblToData);
		
		//===============================================================//
		
		//=========================Total Distance============================//
		JLabel lblDistance = new JLabel("Total Distance        : ");
		lblDistance.setHorizontalAlignment(SwingConstants.LEFT);
		lblDistance.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDistance.setBounds(109, 123, 212, 45);
		add(lblDistance);
		
		JLabel lblDistanceData = new JLabel("");
		lblDistanceData.setHorizontalAlignment(SwingConstants.LEFT);
		lblDistanceData.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDistanceData.setBounds(328, 123, 493, 45);
		add(lblDistanceData);
		
		//===============================================================//
		
		//=========================Total Time============================//
		JLabel lblTotalduration = new JLabel("Total Duration        : ");
		lblTotalduration.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalduration.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotalduration.setBounds(109, 156, 334, 45);
		add(lblTotalduration);
		
		JLabel lbldurationData = new JLabel("");
		lbldurationData.setHorizontalAlignment(SwingConstants.LEFT);
		lbldurationData.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbldurationData.setBounds(328, 156, 493, 45);
		add(lbldurationData);
		
		//===============================================================//
		
		//=========================Adult============================//
		JLabel lblAdult = new JLabel("Adlut                       : ");
		lblAdult.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdult.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAdult.setBounds(109, 191, 212, 45);
		add(lblAdult);
		
		JLabel lblAdultData = new JLabel("");
		lblAdultData.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdultData.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAdultData.setBounds(328, 191, 493, 45);
		add(lblAdultData);
		//===============================================================//
		
		//=========================Child============================//
		JLabel lblChild = new JLabel("Child                        : ");
		lblChild.setHorizontalAlignment(SwingConstants.LEFT);
		lblChild.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblChild.setBounds(109, 226, 212, 45);
		add(lblChild);
		
		JLabel lblChildData = new JLabel("");
		lblChildData.setHorizontalAlignment(SwingConstants.LEFT);
		lblChildData.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblChildData.setBounds(328, 226, 493, 45);
		add(lblChildData);
		
		//===============================================================//
		
		//=========================Travel Cost============================//
		JLabel lblTravelCost = new JLabel("Travel Cost              : ");
		lblTravelCost.setHorizontalAlignment(SwingConstants.LEFT);
		lblTravelCost.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTravelCost.setBounds(109, 261, 212, 45);
		add(lblTravelCost);
		
		JLabel lblTravelCostData = new JLabel("");
		lblTravelCostData.setHorizontalAlignment(SwingConstants.LEFT);
		lblTravelCostData.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTravelCostData.setBounds(328, 261, 493, 45);
		add(lblTravelCostData);
		
		//===============================================================//
		
		//=========================Total Adult Cost============================//
		JLabel lblAdultCost = new JLabel("Total Adult Cost      : ");
		lblAdultCost.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdultCost.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAdultCost.setBounds(109, 298, 212, 45);
		add(lblAdultCost);
		
		JLabel lblAdultCostData = new JLabel("");
		lblAdultCostData.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdultCostData.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAdultCostData.setBounds(328, 298, 493, 45);
		add(lblAdultCostData);
		
		//===============================================================//
		
		//=========================Total Child Cost============================//
		JLabel lblChildCost = new JLabel("Total Child Cost       : ");
		lblChildCost.setHorizontalAlignment(SwingConstants.LEFT);
		lblChildCost.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblChildCost.setBounds(109, 336, 212, 45);
		add(lblChildCost);
		
		JLabel lblChildCostData = new JLabel("");
		lblChildCostData.setHorizontalAlignment(SwingConstants.LEFT);
		lblChildCostData.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblChildCostData.setBounds(328, 336, 493, 45);
		add(lblChildCostData);
		
		//===============================================================//
		
		//=========================Total Travel Cost============================//
		JLabel lblTotalTravelCost = new JLabel("Total Travel Cost     : ");
		lblTotalTravelCost.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalTravelCost.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotalTravelCost.setBounds(109, 374, 212, 45);
		add(lblTotalTravelCost);
		
		JLabel lblTotalTravelCostData = new JLabel("");
		lblTotalTravelCostData.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalTravelCostData.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotalTravelCostData.setBounds(328, 374, 493, 45);
		add(lblTotalTravelCostData);
		
		//===============================================================//
		
		//Button
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(() -> mainFrame
						.changePanel(new ViewTrip(mainFrame, temp,adult,child)));
				
			}
		});
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnBack.setForeground(Color.BLACK);
		btnBack.setBackground(Color.GRAY);
		
		btnBack.setBounds(733, 399, 120, 40);
		add(btnBack);
		//
		
		lblFromData.setText(source);
		lblToData.setText(dest);
		lblDistanceData.setText(Double.toString(distance));
		lbldurationData.setText(Integer.toString(duration));
		lblAdultData.setText(Integer.toString(adult));
		lblChildData.setText(Integer.toString(child));
		lblTravelCostData.setText(Double.toString(travelprice));
		lblAdultCostData.setText(Double.toString(AdultCost));
		lblChildCostData.setText(Double.toString(ChildCost));
		lblTotalTravelCostData.setText(Double.toString(total));
		


		
	}
}
