package travelBug.UI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import travelBug.library.CircularLinkedList;
import travelBug.library.LinkArray;
import travelBug.library.ReadWriteFile;
import travelBug.obj.TravelLegAccount;
import travelBug.obj.TravelLegInfo;
import travelBug.obj.User;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.awt.event.ItemEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DisplayTrip extends JPanel {
	
	private JPanel panel;
	private JLabel label;
	private JButton btnBack;
	private CircularLinkedList<TravelLegInfo> tempCircularLinkedList;
	
	
	
	private final UIControl mainFrame;
	
	public DisplayTrip(UIControl parent,CircularLinkedList<TravelLegInfo> d) {

		super();
		this.mainFrame = parent;
		tempCircularLinkedList = d;
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		createGUI();
	}
	
	
	private void createGUI() {
		
		String source = null;
		String dest = null;
		double distance = 0.00;
		double duration = 0.00;
		double price = 0.00;

		
		
<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/dsa-rit2/DSA.git
		for(int i = 1; i <= tempCircularLinkedList.getNumberOfEntries(); i++) {
			
			source   = tempCircularLinkedList.getEntry(i).getSource();
			dest     = tempCircularLinkedList.getEntry(i).getDest();
			distance = tempCircularLinkedList.getEntry(i).getDistance();
			duration = tempCircularLinkedList.getEntry(i).getDuration();
			price    = tempCircularLinkedList.getEntry(i).getPrice();

		}

//		for(int i = 1; i <= tempCircularLinkedList.getSize(); i++) {
//			
//			source   = tempCircularLinkedList.getEntry(i).getSource();
//			dest     = tempCircularLinkedList.getEntry(i).getDest();
//			distance = tempCircularLinkedList.getEntry(i).getDistance();
//			duration = tempCircularLinkedList.getEntry(i).getDuration();
//			price    = tempCircularLinkedList.getEntry(i).getPrice();
//
//		}

		
		
		
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
		JLabel lblTotalTime = new JLabel("Total Time               : ");
		lblTotalTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalTime.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotalTime.setBounds(109, 156, 212, 45);
		add(lblTotalTime);
		
		JLabel lblTotalTimeData = new JLabel("");
		lblTotalTimeData.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalTimeData.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotalTimeData.setBounds(328, 156, 493, 45);
		add(lblTotalTimeData);
		
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
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnBack.setForeground(Color.BLACK);
		btnBack.setBackground(Color.GRAY);
		btnBack.setActionCommand("");
		btnBack.setBounds(733, 399, 120, 40);
		add(btnBack);
		//
		
		lblFromData.setText(source);
		lblToData.setText(dest);
		lblDistanceData.setText(Double.toString(distance));
		lblTotalTimeData.setText(Double.toString(duration));
		lblAdultData.setText("");
		lblChildData.setText("");
		lblTravelCostData.setText(Double.toString(price));
		lblAdultCostData.setText("");
		lblChildCostData.setText("");
		lblTotalTravelCostData.setText("");
		


		
	}
}
