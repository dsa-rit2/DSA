package travelBug.UI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import travelBug.library.CircularLinkedList;
import travelBug.library.SinglyLinkedList;
import travelBug.library.SortedLinkedList;
import travelBug.library.library;
import travelBug.obj.TravelLegInfo;
import travelBug.obj.TravelPlane;
import java.util.Comparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewTrip extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel callPanel;
	private JButton btnBack;
	private JTextPane[] txtArray;
	private String callFrontArrow = "--[", callBackArrow = "]-->";
	private int adult = 0, child = 0, txtNum = 0;
	private final UIControl mainFrame;
	private SinglyLinkedList<CircularLinkedList<TravelLegInfo>> temp = new SinglyLinkedList<CircularLinkedList<TravelLegInfo>>();
	private SinglyLinkedList<TravelPlane> linkedList = new SinglyLinkedList<TravelPlane>();

	public ViewTrip(UIControl parent, SinglyLinkedList<CircularLinkedList<TravelLegInfo>> t, int a, int c) {
		super();
		this.mainFrame = parent;
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));
		temp = t;
		adult = a;
		child = c;

		createGUI();
	}

	private void createGUI() {
<<<<<<< HEAD
		
		int sortDuration = 0;
		String pStrings = new String();
		double totalPrice = 0;

		// ====================Title=======================//
=======
		// ==================== Title =====================
>>>>>>> branch 'master' of https://github.com/dsa-rit2/DSA.git
		JLabel lblViewTrip = new JLabel("View Trip");
		lblViewTrip.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewTrip.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblViewTrip.setBounds(10, 15, 878, 45);
		add(lblViewTrip);
		// ===============================================//
		
		//=====================Store combine and sort by price===============//
		
		for(int i=0; i<temp.getNumberOfEntries();i++) {
			
			int adult = this.adult;
			int kiddo = this.child;
			String source = new String();
			String dest = new String();
			
			for (int j = 1; j <= temp.getEntry(i+1).getNumberOfEntries(); j++) {
				
				double kids = temp.getEntry(i+1).getEntry(j).getPrice() * 0.5 * kiddo;
				double adults = temp.getEntry(i+1).getEntry(j).getPrice() * adult;

				totalPrice += kids + adults;
				
				if (j == 1) {// Display first source and first destination

					pStrings += temp.getEntry(i+1).getEntry(j).getSource();
					source = temp.getEntry(i+1).getEntry(j).getSource();

					pStrings += callFrontArrow;
					pStrings += temp.getEntry(i+1).getEntry(j).getMode();
					pStrings += callBackArrow;

					pStrings += temp.getEntry(i+1).getEntry(j).getDest();
					pStrings += callFrontArrow;
					pStrings += temp.getEntry(i+1).getEntry(j).getMode();
					pStrings += callBackArrow;

				} else {// Display next destination
					pStrings += temp.getEntry(i+1).getEntry(j).getDest();

					if (temp.getEntry(i+1).getEntry(j + 1) != null) {
						pStrings += callFrontArrow;
						pStrings += temp.getEntry(i+1).getEntry(j).getMode();
						pStrings += callBackArrow;
						
						sortDuration = temp.getEntry(i+1).getEntry(j).getDuration();

					}
					
				}
				dest = temp.getEntry(i+1).getEntry(j).getDest();
			}
			
			linkedList.add(new TravelPlane(pStrings, totalPrice, sortDuration, source, dest));
			pStrings = "";
			totalPrice = 0;
		}
		SortedLinkedList<TravelPlane> tArrayLinkedList = new SortedLinkedList<TravelPlane>(linkedList,
				Comparator.comparing(TravelPlane::getPrice));
		
		//=====================================================================================//

		// ================== Containers ==================
		Font callFont = new Font("Segoe UI", Font.PLAIN, 16);
		LineBorder lineBorder = new LineBorder(Color.GRAY, 2, true);
		callPanel = new JPanel(new GridLayout(5, 5));
		callPanel.setBorder(lineBorder);
		callPanel.setBounds(35, 58, 827, 334);
<<<<<<< HEAD
		
		this.txtNum = 0;
		if(tArrayLinkedList.getLength()>5)
			txtNum = 5;
		else
			txtNum = tArrayLinkedList.getLength();
=======

		this.txtNum = temp.getNumberOfEntries() > 5 ? 5 : temp.getNumberOfEntries();
>>>>>>> branch 'master' of https://github.com/dsa-rit2/DSA.git
		txtArray = new JTextPane[txtNum];
		
<<<<<<< HEAD
		//====================On Click=========================//
=======
		// ==================== On Click =====================
>>>>>>> branch 'master' of https://github.com/dsa-rit2/DSA.git
		for (int i = 0; i < txtNum; ++i) {
			txtArray[i] = new JTextPane();
			txtArray[i].setFont(callFont);
			txtArray[i].setEditable(false);
			txtArray[i].setBackground(Color.white);
<<<<<<< HEAD
=======
			// txtArray[i].setHorizontalAlignment(SwingConstants.CENTER);

			if (i == 0) {
				txtArray[i].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 1) {
							for (int j = 0; j < txtNum; j++) {
								if (j == 0)	txtArray[0].setBackground(Color.lightGray);
								else txtArray[j].setBackground(Color.white);
							}
						}
					}
				});
			} else if (i == 1) {
				txtArray[i].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 1) {
							for (int j = 0; j < txtNum; j++) {
								if (j == 1)	txtArray[1].setBackground(Color.lightGray);
								else txtArray[j].setBackground(Color.white);
							}
						}
					}
				});
			} else if (i == 2) {
				txtArray[i].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 1) {
							for (int j = 0; j < txtNum; j++) {
								if (j == 2)	txtArray[2].setBackground(Color.lightGray);
								else txtArray[j].setBackground(Color.white);
							}
						}
					}
				});
			} else if (i == 3) {
				txtArray[i].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 1) {

							for (int j = 0; j < txtNum; j++) {
								if (j == 3)	txtArray[3].setBackground(Color.lightGray);
								else txtArray[j].setBackground(Color.white);
							}
						}
					}
				});
			} else if (i == 4) {
				txtArray[i].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 1) {
							for (int j = 0; j < txtNum; j++) {
								if (j == 4)	txtArray[4].setBackground(Color.lightGray);
								else txtArray[j].setBackground(Color.white);
							}
						}
					}
				});
			}
>>>>>>> branch 'master' of https://github.com/dsa-rit2/DSA.git
			
			// ======================== Double Click =========================
			if (i == 0) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2) {
							SwingUtilities.invokeLater(() -> mainFrame
									.changePanel(new DisplayTrip(mainFrame, temp.getEntry(1), temp, adult, child)));

						}
					}
				});
			} else if (i == 1) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2) {
							SwingUtilities.invokeLater(() -> mainFrame
									.changePanel(new DisplayTrip(mainFrame, temp.getEntry(2), temp, adult, child)));

						}
					}
				});
			} else if (i == 2) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2) {
							SwingUtilities.invokeLater(() -> mainFrame
									.changePanel(new DisplayTrip(mainFrame, temp.getEntry(3), temp, adult, child)));

						}
					}
				});
			} else if (i == 3) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2) {
							SwingUtilities.invokeLater(() -> mainFrame
									.changePanel(new DisplayTrip(mainFrame, temp.getEntry(4), temp, adult, child)));

						}
					}
				});
			} else if (i == 4) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2) {
							SwingUtilities.invokeLater(() -> mainFrame
									.changePanel(new DisplayTrip(mainFrame, temp.getEntry(5), temp, adult, child)));

						}
					}
				});
			}

<<<<<<< HEAD
			
			txtArray[i].setText(tArrayLinkedList.getEntry(i+1).getPlan() + "\nPrice: RM " + 
								tArrayLinkedList.getEntry(i+1).getPrice());
=======
			/////////////////////////// Pass data and display/////////////////////////////

			String pStrings = new String();
			double totalPrice = 0;

			int adult = this.adult;
			int kiddo = this.child;
			for (int j = 1; j <= temp.getEntry(i + 1).getNumberOfEntries(); j++) {

				double kids = temp.getEntry(i + 1).getEntry(j).getPrice() * 0.5 * kiddo;
				double adults = temp.getEntry(i + 1).getEntry(j).getPrice() * adult;

				totalPrice += kids + adults;

				///////////////////////// Sort///////////////////////////////////////////

				linkedList.add(new TravelPlane(temp.getEntry(i + 1).getEntry(j).getSource(),
						temp.getEntry(i + 1).getEntry(j).getDest(), totalPrice,
						temp.getEntry(i + 1).getEntry(j).getDuration()));

				SortedLinkedList<TravelPlane> tArrayLinkedList = new SortedLinkedList<TravelPlane>(linkedList,
						Comparator.comparing(TravelPlane::getPrice));

				for (int k = 1; k <= tArrayLinkedList.getLength(); k++) {
					System.out.print(tArrayLinkedList.getEntry(k).getPrice());
					System.out.println(tArrayLinkedList.getEntry(k).getSourceString());
				}
				///////////////////////////////////////////////////////////////////////////

				if (j == 1) {// Display first source and first destination

					pStrings += temp.getEntry(i + 1).getEntry(j).getSource();

					pStrings += callFrontArrow;
					pStrings += temp.getEntry(i + 1).getEntry(j).getMode();
					pStrings += callBackArrow;

					pStrings += temp.getEntry(i + 1).getEntry(j).getDest();
					pStrings += callFrontArrow;
					pStrings += temp.getEntry(i + 1).getEntry(j).getMode();
					pStrings += callBackArrow;

				} else {// Display next destination
					pStrings += temp.getEntry(i + 1).getEntry(j).getDest();

					if (temp.getEntry(i + 1).getEntry(j + 1) != null) {
						pStrings += callFrontArrow;
						pStrings += temp.getEntry(i + 1).getEntry(j).getMode();
						pStrings += callBackArrow;

					}
				}

			}
			///////////////////////////////////////////////////////////////////////////
			// put data to panel

			// txtArray[i].setHorizontalAlignment(SwingConstants.LEFT);
			txtArray[i].setText(pStrings + "\nPrice: RM " + totalPrice);

>>>>>>> branch 'master' of https://github.com/dsa-rit2/DSA.git
			callPanel.add(txtArray[i]);

		}
<<<<<<< HEAD
		add(callPanel);// display panel
		
		
=======
		add(callPanel);	// display panel
>>>>>>> branch 'master' of https://github.com/dsa-rit2/DSA.git

		// ===================== Button ======================
		btnBack = new JButton("Back");
		btnBack.addActionListener(event -> SwingUtilities.invokeLater(() -> mainFrame.changePanel(new PlanTrip(mainFrame))));
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnBack.setForeground(Color.BLACK);
		btnBack.setBackground(Color.GRAY);
		btnBack.setActionCommand("");
		btnBack.setBounds(394, 403, 120, 40);
		add(btnBack);
		if (temp.isEmpty()) {
			library.dialogMessage("0 result is found");
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new PlanTrip(mainFrame)));
		}

	}
}
