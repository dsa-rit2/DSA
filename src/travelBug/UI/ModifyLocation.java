package travelBug.UI;

//=========================
//		Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================

import java.awt.*;
import javax.swing.*;

public class ModifyLocation extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtLocationName, txtState, txtLongitude, txtLatitude;
	private JLabel lblNewLabel, lblNewLabel_1, lblNewLabel_2, lblCountry, lblState, lblType;
	private Label lblErrorLocationName, lblErrorContinent, lblErrorType, lblErrorState, lblErrorCountry, lblErrorLongitude, lblErrorLatitude;
	private JComboBox<String> cbCountry, cbType;
	private LinkArray<Location> lArray = new LinkArray<Location>();
	private ReadWriteFile<Location> lFile = new ReadWriteFile<Location>("Location.txt", Location.class);
	private int locationFound;
	private JComboBox<String> cbContinent;
	private final UIControl mainFrame;

	public ModifyLocation(UIControl parent, String inputName) {
		// ==================== JPanel setting =====================
		super();
		mainFrame = parent;
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		// ==================== Validate the company =====================

		locationFound = 0;
		boolean foundLocation = false;
		lArray = lFile.readLinkArray();
		for (int i = 0; i < lArray.size(); i++) {
			if (inputName.equalsIgnoreCase(lArray.getIndexElement(i).getName())) {
				locationFound = i;
				foundLocation = true;
			}
		}

		// ====================== Content Component =========================
		txtLocationName = new JTextField();
		txtLocationName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtLocationName.setBounds(300, 64, 500, 30);
		txtLocationName.setText(lArray.getIndexElement(locationFound).getName());
		add(txtLocationName);
		txtLocationName.setColumns(10);

		lblNewLabel = new JLabel("Edit Location");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel.setBounds(12, 13, 876, 50);
		add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Location Name: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_1.setBounds(100, 64, 180, 30);
		add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Continent: ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_2.setBounds(100, 123, 180, 30);
		add(lblNewLabel_2);

		lblCountry = new JLabel("Country: ");
		lblCountry.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCountry.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblCountry.setBounds(100, 182, 180, 30);
		add(lblCountry);

		lblState = new JLabel("State: ");
		lblState.setHorizontalAlignment(SwingConstants.RIGHT);
		lblState.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblState.setBounds(100, 238, 180, 30);
		add(lblState);

		lblType = new JLabel("Type: ");
		lblType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblType.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblType.setBounds(100, 298, 180, 30);
		add(lblType);

		txtState = new JTextField();
		txtState.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtState.setBounds(300, 238, 500, 30);
		txtState.addActionListener(e -> submit());
		txtState.setText(lArray.getIndexElement(locationFound).getState());
		txtState.setColumns(10);
		add(txtState);

		txtLongitude = new JTextField();
		txtLongitude.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtLongitude.setBounds(300, 346, 197, 22);
		txtLongitude.setText(Double.toString(lArray.getIndexElement(locationFound).getLongitude()));
		add(txtLongitude);
		txtLongitude.setColumns(10);

		txtLatitude = new JTextField();
		txtLatitude.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtLatitude.setBounds(640, 346, 197, 22);
		txtLatitude.setText(Double.toString(lArray.getIndexElement(locationFound).getLatitude()));
		add(txtLatitude);
		txtLatitude.setColumns(10);

		JLabel lblLongitude = new JLabel("Longitude:");
		lblLongitude.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblLongitude.setBounds(196, 341, 94, 30);
		add(lblLongitude);

		JLabel lblLatitude = new JLabel("Latitude:");
		lblLatitude.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblLatitude.setBounds(560, 348, 81, 16);
		add(lblLatitude);

		cbType = new JComboBox<String>(new String[] { "<Choose Type>", "Small City", "Medium City",
				"Large City", "Natural formation", "Designated Park/Reserve", "Man-made landmark", "Station" });
		cbType.setBounds(300, 304, 282, 26);
		cbType.setSelectedItem(library.getTypeString(lArray.getIndexElement(locationFound).getType()));
		add(cbType);

		cbContinent = new JComboBox<String>(new String[] { "<Choose Continent>", "Asia", "Africa",
				"South America", "North America", "Europe", "Australia" });
		cbContinent.setBounds(300, 123, 282, 30);
		cbContinent.setSelectedItem(lArray.getIndexElement(locationFound).getContinent());
		cbContinent.addActionListener(event -> {
			if (cbContinent.getSelectedIndex() != 0)
				loadC(cbContinent.getSelectedItem().toString(), null);
			else
				loadC(null, null);
		});
		add(cbContinent);

		cbCountry = new JComboBox<String>(new String[] { "<Choose Continent>", "Asia", "Africa",
				"South America", "North America", "Europe", "Australia" });
		cbCountry.setBounds(300, 182, 300, 30);
		add(cbCountry);
		loadC(lArray.getIndexElement(locationFound).getContinent(), lArray.getIndexElement(locationFound).getCountry());
		
		// ====================== Error Message =======================
		lblErrorLocationName = new Label("");
		lblErrorLocationName.setForeground(Color.RED);
		lblErrorLocationName.setBackground(Color.white);
		lblErrorLocationName.setBounds(300, 94, 265, 16);
		add(lblErrorLocationName);

		lblErrorContinent = new Label("");
		lblErrorContinent.setForeground(Color.RED);
		lblErrorContinent.setBackground(Color.white);
		lblErrorContinent.setBounds(300, 153, 265, 16);
		add(lblErrorContinent);

		lblErrorCountry = new Label("");
		lblErrorCountry.setForeground(Color.RED);
		lblErrorCountry.setBackground(Color.white);
		lblErrorCountry.setBounds(600, 182, 265, 16);
		add(lblErrorCountry);

		lblErrorState = new Label("");
		lblErrorState.setForeground(Color.RED);
		lblErrorState.setBackground(Color.white);
		lblErrorState.setBounds(300, 268, 265, 16);
		add(lblErrorState);

		lblErrorType = new Label("");
		lblErrorType.setForeground(Color.RED);
		lblErrorType.setBackground(Color.white);
		lblErrorType.setBounds(600, 312, 265, 16);
		add(lblErrorType);

		lblErrorLongitude = new Label("");
		lblErrorLongitude.setBackground(Color.WHITE);
		lblErrorLongitude.setForeground(Color.RED);
		lblErrorLongitude.setBounds(300, 370, 247, 22);
		add(lblErrorLongitude);

		lblErrorLatitude = new Label("");
		lblErrorLatitude.setBackground(Color.WHITE);
		lblErrorLatitude.setForeground(Color.RED);
		lblErrorLatitude.setBounds(640, 370, 247, 22);
		add(lblErrorLatitude);

		lblErrorContinent.setVisible(false);
		lblErrorCountry.setVisible(false);
		lblErrorLatitude.setVisible(false);
		lblErrorLocationName.setVisible(false);
		lblErrorLongitude.setVisible(false);
		lblErrorState.setVisible(false);
		lblErrorType.setVisible(false);
		
		// ====================== Buttons =========================
		JButton btnAdd = new JButton("Change");
		btnAdd.addActionListener(event -> submit());
		btnAdd.setBounds(279, 393, 97, 25);
		add(btnAdd);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(event -> SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListLocation(mainFrame))));
		btnCancel.setBounds(489, 393, 97, 25);
		add(btnCancel);

		if (foundLocation == false) {
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListLocation(mainFrame)));
		}
	}

	public void submit() {
		lArray = lFile.readLinkArray();

		String locationName = txtLocationName.getText();
		String continent = cbContinent.getSelectedItem().toString();
		String state = txtState.getText();
		String country = cbCountry.getSelectedItem().toString();
		String type = cbType.getSelectedItem().toString();
		double longitude = 0.00;
		double latitude = 0.00;
		char typeChar = 0;
		boolean error = false;

		// ===================== Clear error message ======================
		lblErrorContinent.setVisible(false);
		lblErrorCountry.setVisible(false);
		lblErrorLatitude.setVisible(false);
		lblErrorLocationName.setVisible(false);
		lblErrorLongitude.setVisible(false);
		lblErrorState.setVisible(false);
		lblErrorType.setVisible(false);
		lblErrorLocationName.setText("");
		lblErrorContinent.setText("");
		lblErrorCountry.setText("");
		lblErrorState.setText("");
		lblErrorType.setText("");
		lblErrorLongitude.setText("");
		lblErrorLatitude.setText("");

		if (locationName.isEmpty()) {
			lblErrorLocationName.setText("The location name cannot be empty");
			lblErrorLocationName.setVisible(true);
			error = true;
		}
		
		if (cbContinent.getSelectedIndex() == 0) {
			lblErrorContinent.setText("The continent cannot be empty");
			lblErrorContinent.setVisible(true);
			error = true;
		}
		
		if (state.isEmpty()) {
			lblErrorState.setText("The state cannot be empty");
			lblErrorState.setVisible(true);
			error = true;
		}
		
		if (cbCountry.getSelectedIndex() == 0) {
			lblErrorCountry.setText("Please select country");
			lblErrorCountry.setVisible(true);
			error = true;
		}
		
		if (cbType.getSelectedIndex() == 0) {
			lblErrorType.setText("Please select type");
			lblErrorType.setVisible(true);
			error = true;
		} else {
			typeChar = library.getTypeChar(type);
			if (typeChar == 0) {
				lblErrorType.setText("The type is wrong");
				lblErrorType.setVisible(true);
				error = true;
			}
		}
		
		if (txtLongitude.getText().isEmpty()) {
			lblErrorLongitude.setText("Longitude is empty");
			lblErrorLongitude.setVisible(true);
			error = true;
		} else {
			longitude = Double.parseDouble(txtLongitude.getText());
		}
		
		if (txtLatitude.getText().isEmpty()) {
			lblErrorLatitude.setText("Latitude is empty");
			lblErrorLatitude.setVisible(true);
			error = true;
		} else {
			latitude = Double.parseDouble(txtLatitude.getText());
		}
		
		if (!error) {
			lArray.getIndexElement(locationFound).setName(locationName);
			lArray.getIndexElement(locationFound).setContinent(continent);
			lArray.getIndexElement(locationFound).setCountry(country);
			lArray.getIndexElement(locationFound).setState(state);
			lArray.getIndexElement(locationFound).setType(typeChar);
			lArray.getIndexElement(locationFound).setLongitude(longitude);
			lArray.getIndexElement(locationFound).setLatitude(latitude);
			lFile.writeLinkArray(lArray);
			lArray.getIndexElement(locationFound).print();
			library.dialogMessage("The location is updated\nThe page will redirect to list location");
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListLocation(mainFrame)));

		}
	}

	public void loadC(String continent, String countrySelected) {
		cbCountry.removeAllItems();
		if (continent == null) {
			cbCountry.setEnabled(false);
			String[] checkStrings = library.getCountryArray("AllCountry");
			if (checkStrings != null) {
				for (String string : checkStrings) {
					cbCountry.addItem(string);
				}
			}
		} else {
			cbCountry.setEnabled(true);
			String[] checkStrings = library.getCountryArray(continent);
			if (checkStrings != null)
				for (String string : checkStrings) {
					cbCountry.addItem(string);
				}
		}
		if (countrySelected != null) {
			cbCountry.setSelectedItem(countrySelected);
		}
	}
}
