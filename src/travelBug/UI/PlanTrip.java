package travelBug.UI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class PlanTrip extends JPanel {
	private static final long serialVersionUID = 5629499624569369278L;

	private int cityCount = 1, adultCount = 0, childCount = 0;
	Date getlastestDate = new Date();

	// *** Component Declaration *** //
	JComboBox<String> country1 = new JComboBox<String>(), country2 = new JComboBox<String>(),
			country3 = new JComboBox<String>(), country4 = new JComboBox<String>(), country5 = new JComboBox<String>();

	JComboBox<String> place1 = new JComboBox<String>(), place2 = new JComboBox<String>(),
			place3 = new JComboBox<String>(), place4 = new JComboBox<String>(), place5 = new JComboBox<String>();

	JComboBox<String> locationName1 = new JComboBox<String>(), locationName2 = new JComboBox<String>(),
			locationName3 = new JComboBox<String>(), locationName4 = new JComboBox<String>(),
			locationName5 = new JComboBox<String>();

	JDateChooser dateChooser_1 = new JDateChooser(), dateChooser_2 = new JDateChooser(),
			dateChooser_3 = new JDateChooser(), dateChooser_4 = new JDateChooser(), dateChooser_5 = new JDateChooser();

	private final UIControl mainFrame; // Store main frame

	public PlanTrip(UIControl parent) {
		super();
		this.mainFrame = parent;

		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		createGUI();
	}

	private void createGUI() {
		// ****************************** Container ******************************//
		JLabel lblFindTheBest = new JLabel("Find the best trip");
		lblFindTheBest.setBounds(10, 13, 878, 45);
		add(lblFindTheBest);
		lblFindTheBest.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblFindTheBest.setHorizontalAlignment(SwingConstants.CENTER);
		
		// ======================= Country ======================
		country1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		country1.setBounds(10, 165, 180, 30);
		add(country1);

		country2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		country2.setBounds(10, 208, 180, 30);
		add(country2);

		country3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		country3.setBounds(10, 251, 180, 30);
		add(country3);

		country4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		country4.setBounds(10, 294, 180, 30);
		add(country4);

		country5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		country5.setBounds(10, 337, 180, 30);
		add(country5);

		// ======================= Place =======================
		place1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		place1.setBounds(243, 165, 180, 30);
		add(place1);

		place2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		place2.setBounds(243, 208, 180, 30);
		add(place2);

		place3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		place3.setBounds(243, 251, 180, 30);
		add(place3);

		place4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		place4.setBounds(243, 294, 180, 30);
		add(place4);

		place5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		place5.setBounds(243, 337, 180, 30);
		add(place5);

		// ========================= Location ========================
		locationName1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		locationName1.setBounds(480, 165, 180, 30);
		add(locationName1);

		locationName2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		locationName2.setBounds(480, 208, 180, 30);
		add(locationName2);

		locationName3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		locationName3.setBounds(480, 251, 180, 30);
		add(locationName3);

		locationName4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		locationName4.setBounds(480, 294, 180, 30);
		add(locationName4);

		locationName5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		locationName5.setBounds(480, 337, 180, 30);
		add(locationName5);

		// ========================= Date chooser ==========================
		dateChooser_1.setBorder(new EmptyBorder(1, 1, 1, 1));
		dateChooser_1.setMinSelectableDate(getlastestDate);
		dateChooser_1.setBounds(708, 165, 180, 30);
		add(dateChooser_1);

		dateChooser_2.setBorder(new EmptyBorder(1, 1, 1, 1));
		dateChooser_2.setMinSelectableDate(getlastestDate);
		dateChooser_2.setBounds(708, 208, 180, 30);
		add(dateChooser_2);

		dateChooser_3.setBorder(new EmptyBorder(1, 1, 1, 1));
		dateChooser_3.setMinSelectableDate(getlastestDate);
		dateChooser_3.setBounds(708, 251, 180, 30);
		add(dateChooser_3);

		dateChooser_4.setBorder(new EmptyBorder(1, 1, 1, 1));
		dateChooser_4.setMinSelectableDate(getlastestDate);
		dateChooser_4.setBounds(708, 294, 180, 30);
		add(dateChooser_4);

		dateChooser_5.setBorder(new EmptyBorder(1, 1, 1, 1));
		dateChooser_5.setMinSelectableDate(getlastestDate);
		dateChooser_5.setBounds(708, 337, 180, 30);
		add(dateChooser_5);

		// ================= Label ================ //
		JLabel lblType = new JLabel("Location Type:");
		lblType.setForeground(Color.BLACK);
		lblType.setBounds(243, 122, 180, 30);
		add(lblType);
		lblType.setFont(new Font("Segoe UI", Font.BOLD, 22));

		JLabel lblLocationName = new JLabel("Location Name:");
		lblLocationName.setForeground(Color.BLACK);
		lblLocationName.setBounds(480, 122, 180, 30);
		add(lblLocationName);
		lblLocationName.setFont(new Font("Segoe UI", Font.BOLD, 22));

		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setForeground(Color.BLACK);
		lblCountry.setBounds(10, 122, 180, 30);
		add(lblCountry);
		lblCountry.setFont(new Font("Segoe UI", Font.BOLD, 22));

		JLabel lblDate = new JLabel("Date:");
		lblDate.setForeground(Color.BLACK);
		lblDate.setBounds(708, 122, 180, 30);
		add(lblDate);
		lblDate.setFont(new Font("Segoe UI", Font.BOLD, 22));

		JLabel citieslbl = new JLabel("Cities: ");
		citieslbl.setHorizontalAlignment(SwingConstants.CENTER);
		citieslbl.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		citieslbl.setBounds(20, 73, 80, 30);
		add(citieslbl);

		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox<String> citiesCount = new JComboBox(new String[] {"1", "2", "3", "4", "5" });
		citiesCount.setBounds(100, 77, 50, 30);
		add(citiesCount);
		citiesCount.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cityCount = Integer.parseInt(citiesCount.getSelectedItem().toString());
				checkLocationComponent();
			}
		});
		
		// ================= Number of People ================ //
		JSpinner adultSpinner = new JSpinner();
		adultSpinner.setBounds(610, 78, 50, 30);
		add(adultSpinner);
		adultSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));

		JSpinner childSpinner = new JSpinner();
		childSpinner.setBounds(780, 78, 50, 30);
		add(childSpinner);
		childSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));

		JLabel lblAdult = new JLabel("Adult: ");
		lblAdult.setBounds(538, 74, 60, 30);
		add(lblAdult);
		lblAdult.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAdult.setLabelFor(adultSpinner);

		JLabel lblChild = new JLabel("Child: ");
		lblChild.setBounds(708, 74, 60, 30);
		add(lblChild);
		lblChild.setLabelFor(lblChild);
		lblChild.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		// ================ Radio Button =============== //
		Button confirmBtn = new Button("Confirm");
		confirmBtn.setBounds(480, 393, 120, 40);
		add(confirmBtn);
		confirmBtn.setForeground(Color.WHITE);
		confirmBtn.setBackground(Color.GRAY);
		confirmBtn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		confirmBtn.addActionListener(event -> {
			adultCount = Integer.parseInt(adultSpinner.getValue().toString());
			childCount = Integer.parseInt(childSpinner.getValue().toString());
		});

		Button backBtn = new Button("Back");
		backBtn.setBounds(307, 393, 120, 40);
		add(backBtn);
		backBtn.setForeground(Color.WHITE);
		backBtn.setBackground(Color.GRAY);
		backBtn.setActionCommand("");
		backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
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
