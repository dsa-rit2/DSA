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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewTrip extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel callPanel;
	private JButton btnBack, btnPrice, btnDuration;
	private JTextPane[] txtArray;
	private String callFrontArrow = "--[";
	private String callBackArrow = "]-->";
	private int adult = 0, child = 0;
	private final UIControl mainFrame;
	private int txtNum = 0;
	private SortedLinkedList<TravelPlane> tArrayLinkedList;

	private SinglyLinkedList<CircularLinkedList<TravelLegInfo>> temp = new SinglyLinkedList<CircularLinkedList<TravelLegInfo>>();
	private SinglyLinkedList<TravelPlane> linkedList = new SinglyLinkedList<TravelPlane>();
	private CircularLinkedList<TravelPlane> tCircular = new CircularLinkedList<TravelPlane>();

	public ViewTrip(UIControl parent, SinglyLinkedList<CircularLinkedList<TravelLegInfo>> t, int a, int c) {

		super();
		this.mainFrame = parent;
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));
		temp = t;
		adult = a;
		child = c;
		createGUI(0);
	}

	private void createGUI(int num) {
		removeAll();
		int sortDuration = 0;
		String pStrings = new String();
		double totalPrice = 0;
		double adultPrice = 0;
		double kiddoPrice = 0;

		// ====================Title=======================//
		JLabel lblViewTrip = new JLabel("View Trip");
		lblViewTrip.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewTrip.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblViewTrip.setBounds(10, 15, 878, 45);
		add(lblViewTrip);
		// ===============================================//

		// =====================Store combine and sort by price===============//

		for (int i = 1; i <= temp.getNumberOfEntries(); i++) {

			int adult = this.adult;
			int kiddo = this.child;
			String source = new String();
			String dest = new String();
			double distance = 0;

			for (int j = 1; j <= temp.getEntry(i).getNumberOfEntries(); j++) {

				double kids = temp.getEntry(i).getEntry(j).getPrice() * kiddo * 0.5;
				double adults = temp.getEntry(i).getEntry(j).getPrice() * adult;

				adultPrice += adults;
				kiddoPrice += kids;

				totalPrice += kids + adults;

				if (j == 1) {// Display first source and first destination

					pStrings += temp.getEntry(i).getEntry(j).getSource();
					source = temp.getEntry(i).getEntry(j).getSource();

					pStrings += callFrontArrow;
					pStrings += temp.getEntry(i).getEntry(j).getMode();
					pStrings += callBackArrow;

					pStrings += temp.getEntry(i).getEntry(j).getDest();
					pStrings += callFrontArrow;
					pStrings += temp.getEntry(i).getEntry(j).getMode();
					pStrings += callBackArrow;

					sortDuration += temp.getEntry(i).getEntry(j).getDuration();
					distance += temp.getEntry(i).getEntry(j).getDistance();

				} else {// Display next destination
					pStrings += temp.getEntry(i).getEntry(j).getDest();

					if (temp.getEntry(i).getEntry(j + 1) != null) {
						pStrings += callFrontArrow;
						pStrings += temp.getEntry(i).getEntry(j).getMode();
						pStrings += callBackArrow;

						sortDuration += temp.getEntry(i).getEntry(j).getDuration();
						distance += temp.getEntry(i).getEntry(j).getDistance();

					}

				}
				dest = temp.getEntry(i).getEntry(j).getDest();
			}

			linkedList.add(new TravelPlane(pStrings, adultPrice, kiddoPrice, totalPrice, sortDuration, source, dest,
					distance, i));
			pStrings = "";
			totalPrice = 0;
			adultPrice = 0;
			kiddoPrice = 0;
		}

		makeThing(num);

		for (int k = 1; k <= tArrayLinkedList.getLength(); k++) {
			tCircular.add(tArrayLinkedList.getEntry(k));
		}

		// =====================================================================================//

		// ==========================Containers=============//

		Font callFont = new Font("Segoe UI", Font.PLAIN, 16);
		LineBorder lineBorder = new LineBorder(Color.GRAY, 2, true);
		callPanel = new JPanel(new GridLayout(5, 5));
		callPanel.setBorder(lineBorder);
		callPanel.setBounds(35, 58, 827, 334);

		this.txtNum = 0;
		if (tArrayLinkedList.getLength() > 5)
			txtNum = 5;
		else
			txtNum = tArrayLinkedList.getLength();
		txtArray = new JTextPane[txtNum];

		// ====================On Click=========================//
		for (int i = 0; i < txtNum; ++i) {
			txtArray[i] = new JTextPane();
			txtArray[i].setFont(callFont);
			txtArray[i].setEditable(false);
			txtArray[i].setBackground(Color.white);

			if (i == 0) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 1) {
							for (int j = 0; j < txtNum; j++) {
								if (j == 0)
									txtArray[0].setBackground(Color.lightGray);
								else {
									txtArray[j].setBackground(Color.white);
								}
							}

						}
					}
				});
			} else if (i == 1) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 1) {
							for (int j = 0; j < txtNum; j++) {
								if (j == 1)
									txtArray[1].setBackground(Color.lightGray);
								else {
									txtArray[j].setBackground(Color.white);
								}
							}
						}
					}
				});
			} else if (i == 2) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 1) {

							for (int j = 0; j < txtNum; j++) {
								if (j == 2)
									txtArray[2].setBackground(Color.lightGray);
								else {
									txtArray[j].setBackground(Color.white);
								}
							}
						}
					}
				});
			} else if (i == 3) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 1) {

							for (int j = 0; j < txtNum; j++) {
								if (j == 3)
									txtArray[3].setBackground(Color.lightGray);
								else {
									txtArray[j].setBackground(Color.white);
								}
							}
						}
					}
				});
			} else if (i == 4) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 1) {
							for (int j = 0; j < txtNum; j++) {
								if (j == 4)
									txtArray[4].setBackground(Color.lightGray);
								else {
									txtArray[j].setBackground(Color.white);
								}
							}
						}
					}
				});
			}
			// =============================================================//

			// ========================Double Click=========================//
			if (i == 0) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2) {
							SwingUtilities.invokeLater(() -> mainFrame.changePanel(
									new DisplayTrip(mainFrame, tCircular.getEntry(1), temp, adult, child)));

						}
					}
				});
			} else if (i == 1) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2) {
							SwingUtilities.invokeLater(() -> mainFrame.changePanel(
									new DisplayTrip(mainFrame, tCircular.getEntry(2), temp, adult, child)));

						}
					}
				});
			} else if (i == 2) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2) {
							SwingUtilities.invokeLater(() -> mainFrame.changePanel(
									new DisplayTrip(mainFrame, tCircular.getEntry(3), temp, adult, child)));

						}
					}
				});
			} else if (i == 3) {
				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2) {
							SwingUtilities.invokeLater(() -> mainFrame.changePanel(
									new DisplayTrip(mainFrame, tCircular.getEntry(4), temp, adult, child)));
						}
					}
				});
			} else if (i == 4) {

				txtArray[i].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2) {
							SwingUtilities.invokeLater(() -> mainFrame.changePanel(
									new DisplayTrip(mainFrame, tCircular.getEntry(5), temp, adult, child)));
						}
					}
				});
			}

			txtArray[i].setText(tArrayLinkedList.getEntry(i + 1).getPlan() + "\nPrice: RM "
					+ tArrayLinkedList.getEntry(i + 1).getPrice());

			callPanel.add(txtArray[i]);

		}
		add(callPanel);// display panel

		// =====================Button======================//
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(() -> mainFrame.changePanel(new PlanTrip(mainFrame)));

			}
		});
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnBack.setForeground(Color.BLACK);
		btnBack.setBackground(Color.GRAY);
		btnBack.setActionCommand("");
		btnBack.setBounds(742, 403, 120, 40);
		add(btnBack);

		btnPrice = new JButton("Price");
		btnPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/////
				createGUI(0);
			}
		});
		btnPrice.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnPrice.setForeground(Color.BLACK);
		btnPrice.setBackground(Color.GRAY);
		btnPrice.setActionCommand("");
		btnPrice.setBounds(35, 403, 120, 40);
		add(btnPrice);

		btnDuration = new JButton("Duration");
		btnDuration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////
				createGUI(1);
			}
		});
		btnDuration.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnDuration.setForeground(Color.BLACK);
		btnDuration.setBackground(Color.GRAY);
		btnDuration.setActionCommand("");
		btnDuration.setBounds(165, 403, 120, 40);
		add(btnDuration);

		if (temp.isEmpty()) {
			library.dialogMessage("0 result is found");
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new PlanTrip(mainFrame)));
		}
		revalidate();
		repaint();
	}

	public void makeThing(int input) {
		if (input == 0)
			tArrayLinkedList = new SortedLinkedList<TravelPlane>(linkedList,
					Comparator.comparing(TravelPlane::getPrice));
		else if (input == 1)
			tArrayLinkedList = new SortedLinkedList<TravelPlane>(linkedList,
					Comparator.comparing(TravelPlane::getDuration));
//		else if(input==2)
//			tArrayLinkedList = new SortedLinkedList<TravelPlane>(linkedList,
//					Comparator.comparing(TravelPlane::getDuration));
	}
	
}
