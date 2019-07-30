package travelBug.UI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class PlanTrip extends JPanel {
	private static final long serialVersionUID = 5629499624569369278L;
	
	private int cityCount = 1, adultCount = 0, childCount = 0;

	// *** Component Declaration *** //
	JComboBox<String> country1 = new JComboBox<String>(), country2 = new JComboBox<String>(),
			country3 = new JComboBox<String>(), country4 = new JComboBox<String>(), country5 = new JComboBox<String>(); // Country
	// Selection

	JComboBox<String> place1 = new JComboBox<String>(), place2 = new JComboBox<String>(),
			place3 = new JComboBox<String>(), place4 = new JComboBox<String>(), place5 = new JComboBox<String>(); // Place
	// Selection

	JComboBox<String> locationName1 = new JComboBox<String>(), locationName2 = new JComboBox<String>(),
			locationName3 = new JComboBox<String>(), locationName4 = new JComboBox<String>(),
			locationName5 = new JComboBox<String>(); // Location Selection

	JDateChooser dateChooser_1 = new JDateChooser(), dateChooser_2 = new JDateChooser(),
			dateChooser_3 = new JDateChooser(), dateChooser_4 = new JDateChooser(), dateChooser_5 = new JDateChooser(); // Date
	// Selection
	
	private final UIControl mainFrame;		// Store main frame

	// *** Create the frame *** //
	public PlanTrip(UIControl parent) {
		super();
		this.mainFrame = parent;
		
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		Date getlastestDate = new Date();

		ButtonGroup bg = new ButtonGroup();

		dateChooser_5.setBounds(614, 337, 180, 30);
		add(dateChooser_5);

		dateChooser_5.setBorder(new EmptyBorder(1, 1, 1, 1));
		dateChooser_5.setMinSelectableDate(getlastestDate);
		locationName5.setBounds(411, 337, 180, 30);
		add(locationName5);

		locationName5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		country4.setBounds(10, 294, 180, 30);
		add(country4);

		country4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		place4.setBounds(213, 294, 180, 30);
		add(place4);

		place4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		locationName4.setBounds(411, 294, 180, 30);
		add(locationName4);

		locationName4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		dateChooser_4.setBounds(614, 294, 180, 30);
		add(dateChooser_4);

		dateChooser_4.setBorder(new EmptyBorder(1, 1, 1, 1));
		dateChooser_4.setMinSelectableDate(getlastestDate);
		country5.setBounds(10, 337, 180, 30);
		add(country5);

		country5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		place5.setBounds(213, 337, 180, 30);
		add(place5);

		place5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		dateChooser_1.setBounds(614, 165, 180, 30);
		add(dateChooser_1);

		dateChooser_1.setBorder(new EmptyBorder(1, 1, 1, 1));
		dateChooser_1.setMinSelectableDate(getlastestDate);
		country2.setBounds(10, 208, 180, 30);
		add(country2);

		country2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		place2.setBounds(213, 208, 180, 30);
		add(place2);

		place2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		locationName2.setBounds(411, 208, 180, 30);
		add(locationName2);

		locationName2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		dateChooser_2.setBounds(614, 208, 180, 30);
		add(dateChooser_2);

		dateChooser_2.setBorder(new EmptyBorder(1, 1, 1, 1));
		dateChooser_2.setMinSelectableDate(getlastestDate);
		country3.setBounds(10, 251, 180, 30);
		add(country3);

		country3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		place3.setBounds(213, 251, 180, 30);
		add(place3);

		place3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		locationName3.setBounds(411, 251, 180, 30);
		add(locationName3);

		locationName3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		dateChooser_3.setBounds(614, 251, 180, 30);
		add(dateChooser_3);

		dateChooser_3.setBorder(new EmptyBorder(1, 1, 1, 1));
		dateChooser_3.setMinSelectableDate(getlastestDate);

		// ================= Label ================ //
		JLabel lblType = new JLabel("Location Type:");
		lblType.setForeground(Color.WHITE);
		lblType.setBounds(213, 122, 180, 30);
		add(lblType);
		lblType.setFont(new Font("Segoe UI", Font.BOLD, 22));

		JLabel lblLocationName = new JLabel("Location Name:");
		lblLocationName.setForeground(Color.WHITE);
		lblLocationName.setBounds(411, 122, 180, 30);
		add(lblLocationName);
		lblLocationName.setFont(new Font("Segoe UI", Font.BOLD, 22));

		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setForeground(Color.WHITE);
		lblCountry.setBounds(10, 122, 180, 30);
		add(lblCountry);
		lblCountry.setFont(new Font("Segoe UI", Font.BOLD, 22));

		JLabel lblDate = new JLabel("Date:");
		lblDate.setForeground(Color.WHITE);
		lblDate.setBounds(614, 122, 180, 30);
		add(lblDate);
		lblDate.setFont(new Font("Segoe UI", Font.BOLD, 22));

		JComboBox<String> multiCityCount = new JComboBox<String>(new String[] { "2", "3", "4", "5" });
		multiCityCount.setBounds(333, 74, 50, 30);
		add(multiCityCount);
		multiCityCount.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cityCount = Integer.parseInt(multiCityCount.getSelectedItem().toString());
				checkLocationComponent();
			}
		});
		multiCityCount.setEnabled(false);

		// ================ Radio Button =============== //
		JRadioButton rdbtnSingleCity = new JRadioButton("Single City");
		rdbtnSingleCity.setBounds(37, 72, 120, 30);
		add(rdbtnSingleCity);
		rdbtnSingleCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnSingleCity.isSelected()) {
					multiCityCount.setEnabled(false);
					cityCount = 1;
					checkLocationComponent();
				}
			}
		});
		rdbtnSingleCity.setSelected(true);
		rdbtnSingleCity.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnSingleCity.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		bg.add(rdbtnSingleCity);

		JRadioButton rdbtnMultiCity = new JRadioButton("Multi City");
		rdbtnMultiCity.setBounds(203, 72, 120, 30);
		add(rdbtnMultiCity);
		rdbtnMultiCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnMultiCity.isSelected()) {
					multiCityCount.setEnabled(true);
					cityCount = 2;
					checkLocationComponent();
				}
			}
		});
		rdbtnMultiCity.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnMultiCity.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		rdbtnMultiCity.setBackground(Color.WHITE);
		bg.add(rdbtnMultiCity);
		country1.setBounds(10, 165, 180, 30);
		add(country1);

		// ================ Container =============== //
		country1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		place1.setBounds(213, 165, 180, 30);
		add(place1);

		place1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		locationName1.setBounds(411, 165, 180, 30);
		add(locationName1);

		locationName1.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		Button confirmBtn = new Button("Confirm");
		confirmBtn.setBounds(438, 393, 120, 40);
		add(confirmBtn);
		confirmBtn.setForeground(Color.WHITE);
		confirmBtn.setBackground(Color.GRAY);
		confirmBtn.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		Button backBtn = new Button("Back");
		backBtn.setBounds(243, 393, 120, 40);
		add(backBtn);
		backBtn.setForeground(Color.WHITE);
		backBtn.setBackground(Color.GRAY);
		backBtn.setActionCommand("");
		backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JLabel lblFindTheBest = new JLabel("Find the best trip");
		lblFindTheBest.setBounds(0, 0, 784, 45);
		add(lblFindTheBest);
		lblFindTheBest.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblFindTheBest.setHorizontalAlignment(SwingConstants.CENTER);

		// ================= Number of People ================ //
		JSpinner adultSpinner = new JSpinner();
		adultSpinner.setBounds(567, 74, 50, 30);
		add(adultSpinner);
		adultSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));

		JSpinner childSpinner = new JSpinner();
		childSpinner.setBounds(722, 74, 50, 30);
		add(childSpinner);
		childSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));

		JLabel lblAdult = new JLabel("Adult: ");
		lblAdult.setBounds(495, 70, 60, 30);
		add(lblAdult);
		lblAdult.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAdult.setLabelFor(adultSpinner);

		JLabel lblChild = new JLabel("Child: ");
		lblChild.setBounds(650, 70, 60, 30);
		add(lblChild);
		lblChild.setLabelFor(lblChild);
		lblChild.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* number of people */
				adultCount = Integer.parseInt(adultSpinner.getValue().toString());
				childCount = Integer.parseInt(childSpinner.getValue().toString());
			}
		});

		// Check Current Row State
		checkLocationComponent();
	}

	private void checkLocationComponent() {
		switch (cityCount) {
		case 1:
			/* Row 1 */
			country1.setVisible(true);
			place1.setVisible(true);
			locationName1.setVisible(true);
			dateChooser_1.setVisible(true);

			/* Row 2 */
			country2.setVisible(false);
			place2.setVisible(false);
			locationName2.setVisible(false);
			dateChooser_2.setVisible(false);

			/* Row 3 */
			country3.setVisible(false);
			place3.setVisible(false);
			locationName3.setVisible(false);
			dateChooser_3.setVisible(false);

			/* Row 4 */
			country4.setVisible(false);
			place4.setVisible(false);
			locationName4.setVisible(false);
			dateChooser_4.setVisible(false);

			/* Row 5 */
			country5.setVisible(false);
			place5.setVisible(false);
			locationName5.setVisible(false);
			dateChooser_5.setVisible(false);
			break;
		case 2:
			/* Row 1 */
			country1.setVisible(true);
			place1.setVisible(true);
			locationName1.setVisible(true);
			dateChooser_1.setVisible(true);

			/* Row 2 */
			country2.setVisible(true);
			place2.setVisible(true);
			locationName2.setVisible(true);
			dateChooser_2.setVisible(true);

			/* Row 3 */
			country3.setVisible(false);
			place3.setVisible(false);
			locationName3.setVisible(false);
			dateChooser_3.setVisible(false);

			/* Row 4 */
			country4.setVisible(false);
			place4.setVisible(false);
			locationName4.setVisible(false);
			dateChooser_4.setVisible(false);

			/* Row 5 */
			country5.setVisible(false);
			place5.setVisible(false);
			locationName5.setVisible(false);
			dateChooser_5.setVisible(false);
			break;
		case 3:
			/* Row 1 */
			country1.setVisible(true);
			place1.setVisible(true);
			locationName1.setVisible(true);
			dateChooser_1.setVisible(true);

			/* Row 2 */
			country2.setVisible(true);
			place2.setVisible(true);
			locationName2.setVisible(true);
			dateChooser_2.setVisible(true);

			/* Row 3 */
			country3.setVisible(true);
			place3.setVisible(true);
			locationName3.setVisible(true);
			dateChooser_3.setVisible(true);

			/* Row 4 */
			country4.setVisible(false);
			place4.setVisible(false);
			locationName4.setVisible(false);
			dateChooser_4.setVisible(false);

			/* Row 5 */
			country5.setVisible(false);
			place5.setVisible(false);
			locationName5.setVisible(false);
			dateChooser_5.setVisible(false);
			break;
		case 4:
			/* Row 1 */
			country1.setVisible(true);
			place1.setVisible(true);
			locationName1.setVisible(true);
			dateChooser_1.setVisible(true);

			/* Row 2 */
			country2.setVisible(true);
			place2.setVisible(true);
			locationName2.setVisible(true);
			dateChooser_2.setVisible(true);

			/* Row 3 */
			country3.setVisible(true);
			place3.setVisible(true);
			locationName3.setVisible(true);
			dateChooser_3.setVisible(true);

			/* Row 4 */
			country4.setVisible(true);
			place4.setVisible(true);
			locationName4.setVisible(true);
			dateChooser_4.setVisible(true);

			/* Row 5 */
			country5.setVisible(false);
			place5.setVisible(false);
			locationName5.setVisible(false);
			dateChooser_5.setVisible(false);
			break;
		case 5:
			/* Row 1 */
			country1.setVisible(true);
			place1.setVisible(true);
			locationName1.setVisible(true);
			dateChooser_1.setVisible(true);

			/* Row 2 */
			country2.setVisible(true);
			place2.setVisible(true);
			locationName2.setVisible(true);
			dateChooser_2.setVisible(true);

			/* Row 3 */
			country3.setVisible(true);
			place3.setVisible(true);
			locationName3.setVisible(true);
			dateChooser_3.setVisible(true);

			/* Row 4 */
			country4.setVisible(true);
			place4.setVisible(true);
			locationName4.setVisible(true);
			dateChooser_4.setVisible(true);

			/* Row 5 */
			country5.setVisible(true);
			place5.setVisible(true);
			locationName5.setVisible(true);
			dateChooser_5.setVisible(true);
			break;
		default:
			System.out.println("Error city count.");
			break;
		}
	}
}
