package travelBug.UI;

//=========================
//	Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.util.Comparator;
import java.util.Date;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.awt.event.ItemEvent;

public class PlanTrip extends JPanel {
	private static final long serialVersionUID = 1L; // Serializable purpose
	private int adultCount = 0, childCount = 0;
	private final UIControl mainFrame; // Store main frame

	ReadWriteFile<Location> readWriteFile = new ReadWriteFile<Location>("Location.txt", Location.class);
	LinkArray<Location> locationArray = readWriteFile.readLinkArray();

	String[] locationType = { "-Select type-", "Small City", "Medium City", "Large City", "Natural formation",
			"Designated Park/Reserve", "Man-made landmark" };
	
	public PlanTrip(UIControl parent) {
		super();
		this.mainFrame = parent;

		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		createGUI();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createGUI() {
		// ****************************** Container ******************************//
		JLabel lblFindTheBest = new JLabel("Find the best trip");
		lblFindTheBest.setBounds(10, 15, 878, 45);
		add(lblFindTheBest);
		lblFindTheBest.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblFindTheBest.setHorizontalAlignment(SwingConstants.CENTER);

		// ========================== Continent ============================
		SinglyLinkedList<Location> locationList = library.Convertion(locationArray);

		GroupList<Location, SinglyLinkedList<Location>> testGroupList = new GroupList<Location, SinglyLinkedList<Location>>(
				locationList, Comparator.comparing(Location::getContinent));

		String[] continents = new String[testGroupList.getNumberOfEntries() + 1];
		continents[0] = "-Select continent-";
		int i = 1;
		for (SinglyLinkedList<Location> element : testGroupList) {
			continents[i++] = element.getFirst().getContinent();
		}

		JComboBox continent1 = new JComboBox(continents);
		continent1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		continent1.setBounds(62, 133, 150, 30);
		add(continent1);

		JComboBox continent2 = new JComboBox(continents);
		continent2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		continent2.setBounds(64, 293, 150, 30);
		add(continent2);

		// ======================= Country ======================
		JComboBox country1 = new JComboBox();
		country1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		country1.setBounds(226, 133, 150, 30);
		country1.setEnabled(false);
		add(country1);

		JComboBox country2 = new JComboBox();
		country2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		country2.setBounds(228, 293, 150, 30);
		country2.setEnabled(false);
		add(country2);

		// ======================= Place =======================
		JComboBox place1 = new JComboBox(locationType);
		place1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		place1.setBounds(388, 133, 149, 30);
		place1.setEnabled(false);
		add(place1);

		JComboBox place2 = new JComboBox(locationType);
		place2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		place2.setBounds(390, 293, 149, 30);
		place2.setEnabled(false);
		add(place2);

		// ========================= Location ========================
		JComboBox locationName1 = new JComboBox();
		locationName1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		locationName1.setBounds(551, 133, 150, 30);
		locationName1.setEnabled(false);
		add(locationName1);

		JComboBox locationName2 = new JComboBox();
		locationName2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		locationName2.setBounds(553, 293, 150, 30);
		locationName2.setEnabled(false);
		add(locationName2);

		// ========================= Date chooser ==========================
		Date getlastestDate = new Date(); // Check today's date

		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBorder(new EmptyBorder(1, 1, 1, 1));
		dateChooser_1.setMinSelectableDate(getlastestDate);
		dateChooser_1.setBounds(713, 133, 130, 30);
		JTextFieldDateEditor editor1 = (JTextFieldDateEditor) dateChooser_1.getDateEditor();
		editor1.setEditable(false);
		add(dateChooser_1);

		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setBorder(new EmptyBorder(1, 1, 1, 1));
		dateChooser_2.setMinSelectableDate(getlastestDate);
		dateChooser_2.setBounds(715, 293, 130, 30);
		JTextFieldDateEditor editor2 = (JTextFieldDateEditor) dateChooser_2.getDateEditor();
		editor2.setEditable(false);
		add(dateChooser_2);

		// ============================ Label =============================
		JLabel lblContinents = new JLabel("Continent:");
		lblContinents.setForeground(Color.BLACK);
		lblContinents.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblContinents.setBounds(62, 105, 150, 30);
		add(lblContinents);

		JLabel lblType = new JLabel("Location Type:");
		lblType.setForeground(Color.BLACK);
		lblType.setBounds(388, 105, 150, 30);
		lblType.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		add(lblType);

		JLabel lblLocationName = new JLabel("Location Name:");
		lblLocationName.setForeground(Color.BLACK);
		lblLocationName.setBounds(551, 105, 150, 30);
		lblLocationName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		add(lblLocationName);

		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setForeground(Color.BLACK);
		lblCountry.setBounds(226, 105, 150, 30);
		lblCountry.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		add(lblCountry);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setForeground(Color.BLACK);
		lblDate.setBounds(713, 105, 130, 30);
		lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		add(lblDate);

		// ================= Number of People ================ //
		JSpinner adultSpinner = new JSpinner();
		adultSpinner.setBounds(131, 354, 50, 30);
		adultSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		add(adultSpinner);

		JSpinner childSpinner = new JSpinner();
		childSpinner.setBounds(252, 354, 50, 30);
		childSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		add(childSpinner);

		JLabel lblAdult = new JLabel("Adult: ");
		lblAdult.setBounds(72, 350, 60, 30);
		lblAdult.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAdult.setLabelFor(adultSpinner);
		add(lblAdult);

		JLabel lblChild = new JLabel("Child: ");
		lblChild.setBounds(193, 350, 60, 30);
		lblChild.setLabelFor(lblChild);
		lblChild.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		add(lblChild);

		// ================ Radio Button =============== //
		Button confirmBtn = new Button("Confirm");
		confirmBtn.setBounds(483, 410, 120, 40);
		confirmBtn.setForeground(Color.WHITE);
		confirmBtn.setBackground(Color.GRAY);
		confirmBtn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		add(confirmBtn);

		Button backBtn = new Button("Back");
		backBtn.setBounds(310, 410, 120, 40);
		backBtn.setForeground(Color.WHITE);
		backBtn.setBackground(Color.GRAY);
		backBtn.setActionCommand("");
		backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		add(backBtn);

		JLabel label = new JLabel("Continent:");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		label.setBounds(62, 264, 150, 30);
		add(label);

		JLabel label_1 = new JLabel("Country:");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		label_1.setBounds(226, 264, 150, 30);
		add(label_1);

		JLabel label_2 = new JLabel("Location Type:");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		label_2.setBounds(388, 264, 150, 30);
		add(label_2);

		JLabel label_3 = new JLabel("Location Name:");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		label_3.setBounds(551, 264, 150, 30);
		add(label_3);

		JLabel label_4 = new JLabel("Date:");
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		label_4.setBounds(713, 264, 130, 30);
		add(label_4);

		JLabel lblFrom = new JLabel("From:");
		lblFrom.setIcon(new ImageIcon(library.currentDirectoryPath + "\\images\\locationIcon.png"));
		lblFrom.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblFrom.setBounds(64, 73, 100, 30);
		add(lblFrom);

		JLabel lblTo = new JLabel("To:");
		lblTo.setIcon(new ImageIcon(library.currentDirectoryPath + "\\images\\locationIcon.png"));
		lblTo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTo.setBounds(62, 235, 100, 30);
		add(lblTo);

		// ===================== Event Handler =======================

		continent1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				GroupList<Location, SinglyLinkedList<Location>> countryGroupList = new GroupList<Location, SinglyLinkedList<Location>>(
						locationList, Comparator.comparing(Location::getCountry));
				
				country1.removeAllItems();
				country1.addItem("-Select country-");
				if (continent1.getSelectedIndex() > 0) {
					for (SinglyLinkedList<Location> item : countryGroupList) {
						if (item.getFirst().getContinent().compareTo(continent1.getSelectedItem().toString()) == 0) {
							country1.addItem(item.getFirst().getCountry());
						}
					}
				}
				
				if (continent1.getSelectedIndex() == 0) {
					country1.setEnabled(false);
					place1.setEnabled(false);
					locationName1.setEnabled(false);
				}
				else {
					country1.setEnabled(true);
					place1.setEnabled(false);
					locationName1.setEnabled(false);
				}
			}
		});
		
		continent2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				GroupList<Location, SinglyLinkedList<Location>> countryGroupList = new GroupList<Location, SinglyLinkedList<Location>>(
						locationList, Comparator.comparing(Location::getCountry));
				
				country2.removeAllItems();
				country2.addItem("-Select country-");
				if (continent2.getSelectedIndex() > 0) {
					for (SinglyLinkedList<Location> item : countryGroupList) {
						if (item.getFirst().getContinent().compareTo(continent2.getSelectedItem().toString()) == 0) {
							country2.addItem(item.getFirst().getCountry());
						}
					}
				}
				
				
				if (continent2.getSelectedIndex() == 0) { 
					country2.setEnabled(false);
					place2.setEnabled(false);
					locationName2.setEnabled(false);
				}
				else {
					country2.setEnabled(true);
					place2.setEnabled(false);
					locationName2.setEnabled(false);
				}
			}
		});

		country1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (country1.getSelectedIndex() == 0) {
					place1.setEnabled(false);
					locationName1.setEnabled(false);
				}
				else {
					place1.setEnabled(true);
					locationName1.setEnabled(false);
				}
			}
		});
		
		country2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (country2.getSelectedIndex() == 0) {
					place2.setEnabled(false);
					locationName2.setEnabled(false);
				}
				else {
					place2.setEnabled(true);
					locationName2.setEnabled(false);
				}
			}
		});
		
		place1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				GroupList<Location, SinglyLinkedList<Location>> placeGroupList = new GroupList<Location, SinglyLinkedList<Location>>(
						locationList, Comparator.comparing(Location::getName));
				
				locationName1.removeAllItems();
				locationName1.addItem("-Select location-");
				if (place1.getSelectedIndex() > 0) {
					for (SinglyLinkedList<Location> item : placeGroupList) {
						if (item.getFirst().getType() == library.getTypeChar(place1.getSelectedItem().toString())) {
							locationName1.addItem(item.getFirst().getName());
						}
					}
				}
				
				if (place1.getSelectedIndex() == 0) {
					locationName1.setEnabled(false);
				}
				else {
					locationName1.setEnabled(true);
				}
			}
		});
		
		place2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				GroupList<Location, SinglyLinkedList<Location>> placeGroupList = new GroupList<Location, SinglyLinkedList<Location>>(
						locationList, Comparator.comparing(Location::getName));
				
				locationName2.removeAllItems();
				locationName2.addItem("-Select location-");
				if (place2.getSelectedIndex() > 0) {
					for (SinglyLinkedList<Location> item : placeGroupList) {
						if (item.getFirst().getType() == library.getTypeChar(place2.getSelectedItem().toString())) {
							locationName2.addItem(item.getFirst().getName());
						}
					}
				}
				
				if (place2.getSelectedIndex() == 0) {
					locationName2.setEnabled(false);
				}
				else {
					locationName2.setEnabled(true);
				}
			}
		});
		
		// ----------------- Submit button trigger -----------------
		confirmBtn.addActionListener(event -> {
			boolean error = false;
			
			if (continent1.getSelectedIndex() <= 0) {
				error = true;
			}
			else if (country1.getSelectedIndex() <= 0) {
				error = true;
			}
			else if (place1.getSelectedIndex() <= 0) {
				error = true;
			}
			else if (locationName1.getSelectedIndex() <= 0) {
				error = true;
			}
			
			if (continent2.getSelectedIndex() <= 0) {
				error = true;
			}			
			else if (country2.getSelectedIndex() <= 0) {
				error = true;
			}
			else if (place2.getSelectedIndex() <= 0) {
				error = true;
			}
			else if (locationName2.getSelectedIndex() <= 0) {
				error = true;
			}
			
			if (editor1.getText().isEmpty()) {
				error = true;
			}
			if (editor2.getText().isEmpty()) {
				error = true;
			}
			
			
			if (!error) {
				adultCount = Integer.parseInt(adultSpinner.getValue().toString());
				childCount = Integer.parseInt(childSpinner.getValue().toString());
			}
			else {
				System.out.println("Error occur !");
			}
		});
		
		// ======================= Algorithms =========================

	}
}
