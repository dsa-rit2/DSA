package travelBug.UI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import travelBug.library.CircularLinkedList;
import travelBug.library.SinglyLinkedList;
import travelBug.library.SortedLinkedList;
import travelBug.obj.TravelLegInfo;
import travelBug.obj.TravelPlane;

import java.util.Comparator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewTrip extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel callPanel;
	private JButton btnBack;
	private JButton btnSelect;
	private JTextField[] txtArray ;
	private String callFrontArrow = "---";
	private String callBackArrow = "-->";

	private final UIControl mainFrame;

	// Test//
//	private ReadWriteFile<TravelLegAccount> tFile = new ReadWriteFile<TravelLegAccount>("TravelLegAccount.txt",
//			TravelLegAccount.class);
//	private LinkArray<TravelLegAccount> tArray = tFile.readLinkArray();
//	private LinkArray<User> uArray = new LinkArray<User>();

//	private CircularLinkedList<TravelLegAccount> tCircular = new CircularLinkedList<TravelLegAccount>();

	////////
	
	
private SinglyLinkedList<CircularLinkedList<TravelLegInfo>> temp = new SinglyLinkedList<CircularLinkedList<TravelLegInfo>>();
private SinglyLinkedList<TravelPlane> linkedList = new SinglyLinkedList<TravelPlane>();
	
	

	public ViewTrip(UIControl parent, SinglyLinkedList<CircularLinkedList<TravelLegInfo>> t) {

		super();
		this.mainFrame = parent;
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));
		temp = t;
		createGUI();
	}

	private void createGUI() {

		
//////////////////////add read write file data to circular link list///////////////////////
//		
//		tArray = tFile.readLinkArray();
//		for (int i = 0; i < tArray.size(); i++) {
//			
//			tCircular.add(new TravelLegInfo(tArray.getIndexElement(i).getMode(), 
//					                        tArray.getIndexElement(i).getSource(),
//					                        tArray.getIndexElement(i).getDest(), 
//					                        tArray.getIndexElement(i).getPrice(),
//					                        tArray.getIndexElement(i).getDistance(),
//					                        tArray.getIndexElement(i).getfromDate(),
//					                        tArray.getIndexElement(i).gettoDate(),
//					                        tArray.getIndexElement(i).getfromTime(),
//					                        tArray.getIndexElement(i).gettoTime(),
//					                        tArray.getIndexElement(i).getDuration()
//					                        ));
//			}
//		for(int i = 1; i <= temp.getNumberOfEntries();i++)
//		for (int j = 1; j <= temp.getEntry(i).getNumberOfEntries(); j++) {
//	        linkedList.add(new TravelLegInfo(temp.getEntry(i).getEntry(j).getCompany(),temp.getEntry(i).getEntry(j).getMode(),temp.getEntry(i).getEntry(j).getSource(),temp.getEntry(i).getEntry(j).getDest(),temp.getEntry(i).getEntry(j).getPrice(),temp.getEntry(i).getEntry(j).getDistance(),temp.getEntry(i).getEntry(j).getfromDate(),temp.getEntry(i).getEntry(j).gettoDate(),temp.getEntry(i).getEntry(j).getfromTime(),temp.getEntry(i).getEntry(j).gettoTime(),temp.getEntry(i).getEntry(j).getDuration()));
//}
//		SortedLinkedList<TravelLegInfo> tArrayLinkedList = new SortedLinkedList<TravelLegInfo>(linkedList,
//				Comparator.comparing(TravelLegInfo::getPrice).thenComparing(TravelLegInfo::getDuration));
//		
//		for(int i = 1;i<=5;i++) {
//			System.out.print(tArrayLinkedList.getEntry(i).getSource());
//			System.out.println(tArrayLinkedList.getEntry(i).getPrice());
//		}

		// ====================Title=======================//
		JLabel lblViewTrip = new JLabel("View Trip");
		lblViewTrip.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewTrip.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblViewTrip.setBounds(10, 15, 878, 45);
		add(lblViewTrip);

		// ==========================Containers=============//

		Font callFont = new Font("Segoe UI", Font.PLAIN, 16);
		LineBorder lineBorder = new LineBorder(Color.GRAY, 2, true);
		callPanel = new JPanel(new GridLayout(5, 5));
		callPanel.setBorder(lineBorder);
		callPanel.setBounds(40, 58, 811, 334);
		
		int txtNum = 0;
		if(temp.getNumberOfEntries()>5)
			txtNum = 5;
		else
			txtNum = temp.getNumberOfEntries();
		txtArray = new JTextField[txtNum];
		//====================On Click=========================//
		for (int i = 0; i < txtNum; ++i) {
			txtArray[i] = new JTextField(80);
			txtArray[i].setFont(callFont);
			txtArray[i].setEditable(false);
			txtArray[i].setBackground(Color.white);
			txtArray[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			if(i == 0) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 1) {
							
							txtArray[0].setBackground(Color.lightGray);
							txtArray[1].setBackground(Color.white);
							txtArray[2].setBackground(Color.white);
							txtArray[3].setBackground(Color.white);
							txtArray[4].setBackground(Color.white);
						}
					}
				});
			}
			else if(i == 1) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 1) {
							
							txtArray[0].setBackground(Color.white);
							txtArray[1].setBackground(Color.lightGray);
							txtArray[2].setBackground(Color.white);
							txtArray[3].setBackground(Color.white);
							txtArray[4].setBackground(Color.white);
						}
					}
				});
			}
			else if(i == 2) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 1) {
							
							txtArray[0].setBackground(Color.white);
							txtArray[1].setBackground(Color.white);
							txtArray[2].setBackground(Color.lightGray);
							txtArray[3].setBackground(Color.white);
							txtArray[4].setBackground(Color.white);
						}
					}
				});
			}
			else if(i == 3) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 1) {
							
							txtArray[0].setBackground(Color.white);
							txtArray[1].setBackground(Color.white);
							txtArray[2].setBackground(Color.white);
							txtArray[3].setBackground(Color.lightGray);
							txtArray[4].setBackground(Color.white);
						}
					}
				});
			}
			else if(i == 4) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 1) {
							
							txtArray[0].setBackground(Color.white);
							txtArray[1].setBackground(Color.white);
							txtArray[2].setBackground(Color.white);
							txtArray[3].setBackground(Color.white);
							txtArray[4].setBackground(Color.lightGray);
						}
					}
				});
			}
			//=============================================================//
			
			//========================Double Click=========================//
			if(i == 0) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2) {
							

						}
					}
				});
			}
			else if(i == 1) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2) {
							SwingUtilities.invokeLater(() -> mainFrame
									.changePanel(new DisplayTrip(mainFrame, temp.getEntry(1))));
							
						}
					}
				});
			}
			else if(i == 2) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2) {
							

						}
					}
				});
			}
			else if(i == 3) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2) {
							

						}
					}
				});
			}
			else if(i == 4) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2) {
							

						}
					}
				});
			}

			/////////////////////////// For hard code display/////////////////////////////

			String pStrings = new String();
			double totalPrice = 0;

			int adult = 1;
			int kiddo = 1;
			for (int j = 1; j <= temp.getEntry(i+1).getNumberOfEntries(); j++) {


				double kids = temp.getEntry(i+1).getEntry(j).getPrice() * 0.5 * kiddo;
				double adults = temp.getEntry(i+1).getEntry(j).getPrice() * adult;

				totalPrice += kids + adults;
				linkedList.add(new TravelPlane(temp.getEntry(i+1).getEntry(j).getSource(), temp.getEntry(i+1).getEntry(j).getDest(), totalPrice, temp.getEntry(i+1).getEntry(j).getDuration()));
				

				if (j == 1) {// Display first source and first destination

					pStrings += temp.getEntry(i+1).getEntry(j).getSource();

					pStrings += callFrontArrow;
//					pStrings += tString;
					pStrings += callBackArrow;

					pStrings += temp.getEntry(i+1).getEntry(j).getDest();
					pStrings += callFrontArrow;
//					pStrings += tString;
					pStrings += callBackArrow;
				} else {// Display next destination
					pStrings += temp.getEntry(i+1).getEntry(j).getDest();

					if (temp.getEntry(i+1).getEntry(j + 1) != null) {
						pStrings += callFrontArrow;
//						pStrings += tString;
						pStrings += callBackArrow;
					}
				}

			}
			///////////////////////////////////////////////////////////////////////////
			// put data to panel
			
			txtArray[i].setText(pStrings + "\n Price: RM " + totalPrice);

			callPanel.add(txtArray[i]);
		}
		add(callPanel);// display panel
		
		SortedLinkedList<TravelPlane> tArrayLinkedList = new SortedLinkedList<TravelPlane>(linkedList,
				Comparator.comparing(TravelPlane::getPrice));
		
		for(int i = 1;i <= tArrayLinkedList.getLength();i++) {
			System.out.print(tArrayLinkedList.getEntry(i).getPrice());
			System.out.println(tArrayLinkedList.getEntry(i).getSourceString());
		}

		// =====================Button======================//
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnBack.setForeground(Color.BLACK);
		btnBack.setBackground(Color.GRAY);
		btnBack.setActionCommand("");
		btnBack.setBounds(340, 410, 120, 40);
		add(btnBack);

		btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				SwingUtilities.invokeLater(() -> mainFrame
//						.changePanel(new DisplayTrip(mainFrame, pCircularLinkedList)));
			}
		});
		btnSelect.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnSelect.setForeground(Color.BLACK);
		btnSelect.setBackground(Color.GRAY);
		btnSelect.setActionCommand("");
		btnSelect.setBounds(470, 410, 120, 40);
		add(btnSelect);

	}

}
