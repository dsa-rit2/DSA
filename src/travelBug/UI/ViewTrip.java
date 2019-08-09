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
import travelBug.obj.User;

import java.util.Date;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ViewTrip extends JPanel {

	private JTable table;
	private JPanel callPanel;
	private JScrollPane scrollPane;
	private JButton btnBack;
	private JButton btnSelect;
	private JTextField[] txtArray = new JTextField[5];
	private String callArrow = "--->";

	private final UIControl mainFrame;

	// Test//
	private ReadWriteFile<TravelLegAccount> tFile = new ReadWriteFile<TravelLegAccount>("TravelLegAccount.txt",
			TravelLegAccount.class);
	private LinkArray<TravelLegAccount> tArray = tFile.readLinkArray();
//	private LinkArray<User> uArray = new LinkArray<User>();

	private CircularLinkedList<TravelLegAccount> tCircular = new CircularLinkedList<TravelLegAccount>();

	////////

	public ViewTrip(UIControl parent) {

		super();
		this.mainFrame = parent;
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		createGUI();
	}

	private void createGUI() {

		////// Test///////

		tArray = tFile.readLinkArray();
		for (int i = 0; i < tArray.size(); i++) {
//			uArray.addItem(new User(tArray.getIndexElement(i).getUsername(), tArray.getIndexElement(i).getPassword(),
//					"TravelLeg"));
			tCircular.add(new TravelLegAccount(tArray.getIndexElement(i).getUsername(),
					tArray.getIndexElement(i).getPassword()));
		}

		///////////////

		// ====================Title=======================//
		JLabel lblViewTrip = new JLabel("View Trip");
		lblViewTrip.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewTrip.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblViewTrip.setBounds(10, 15, 878, 45);
		add(lblViewTrip);

		// ==========================Containers=============//

//	    for (int i = txtArray.length - 1; i > 0; i--) {
//	    	txtArray[i] = new JTextField();
//	    	txtArray[i].setForeground(Color.BLACK);
//	    	
//	    	for(int j = 1; j <= tCircular.getSize() ; j++) {
//	    		
//	    		txtArray[i].setText(tCircular.getEntry(j).getUsername() + callArrow);
//	    		
//	    	}

//		    txtArray[i].setForeground(Color.RED);
//		    txtArray[i].setText(s.getSeqNo() + callArrow + s.getCounter());

//	    	txtArray[i].setText(txtArray[i - 1].getText());

//	    }
		CircularLinkedList<TravelLegAccount> pCircularLinkedList = new CircularLinkedList<TravelLegAccount>();
		pCircularLinkedList.add(new TravelLegAccount("asd", "as45"));
		pCircularLinkedList.add(new TravelLegAccount("asd", "as45"));
		pCircularLinkedList.add(new TravelLegAccount("asd", "as45"));
		pCircularLinkedList.add(new TravelLegAccount("asd", "as45"));
		pCircularLinkedList.add(new TravelLegAccount("asd", "as45"));
		
		Font callFont = new Font("Segoe UI", Font.PLAIN, 16);
		LineBorder lineBorder = new LineBorder(Color.GRAY, 2, true);
		callPanel = new JPanel(new GridLayout(5, 5));
		callPanel.setBorder(lineBorder);
		callPanel.setBounds(10, 60, 878, 339);

		for (int i = 0; i < txtArray.length; ++i) {//
			txtArray[i] = new JTextField(80);
			txtArray[i].setFont(callFont);
			txtArray[i].setEditable(false);
			txtArray[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			String pStrings = new String();
			
			
			
			// can be used for the below one
//			for (int j = 1; j <= tCircular.getSize(); j++) {
//				pStrings += tCircular.getEntry(j).getUsername();
//				
//				if(tCircular.getEntry(j+1) !=null) {
//					pStrings +=  callArrow;
//				}
//			}
			
			
			for (int j = 1; j <= pCircularLinkedList.getSize(); j++) {
				pStrings += pCircularLinkedList.getEntry(j).getUsername();
				//
				
				if(pCircularLinkedList.getEntry(j+1) !=null) {
					pStrings +=  callArrow;
				}
			}
			
			txtArray[i].setText(pStrings);
//	    	tCircular.getEntry(i).getUsername() = new jte
			callPanel.add(txtArray[i]);
		}
		add(callPanel);

//		JScrollPane scrollPane = new JScrollPane(callPanel);
//		scrollPane.setBounds(650, 353, -611, -288);
//		scrollPane.setEnabled(false);
//		add(scrollPane);

		// =====================Button======================//
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnBack.setBounds(340, 410, 120, 40);
		add(btnBack);

		btnSelect = new JButton("Select");
		btnSelect.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnSelect.setBounds(470, 410, 120, 40);
		add(btnSelect);

	}
}
