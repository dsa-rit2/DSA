package travelBug.UI;

//=========================
//	Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddLocation extends JPanel {
	private static final long serialVersionUID = 1L; // Serializable purpose
	private JTextField txtLocationName, txtState;
	private JLabel lblNewLabel, lblNewLabel_1, lblNewLabel_2, lblCountry, lblState, lblType;	
	private JLabel lblLongitude, lblLatitude;
	private Label lblErrorLocationName, lblErrorContinent, lblErrorType, lblErrorState, lblErrorCountry;
	private Label lblErrorLongitude, lblErrorLatitude;
	private JComboBox<String> cbCountry, cbType;
	private LinkArray<Location> lArray = new LinkArray<Location>();
	private ReadWriteFile<Location> lFile = new ReadWriteFile<Location>("Location.txt", Location.class);
	private JComboBox<String> cbContinent;
	private final UIControl mainFrame; // Store main frame
	private JTextField txtLongitude;
	private JTextField txtLatitude;

	public AddLocation(UIControl parent) {
		super();
		this.mainFrame = parent;

		// ===================== JPanel setting ======================
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));
		createGui();
	}

	public void createGui() {
		removeAll();
		// ===================== Content component =====================
		txtLocationName = new JTextField();
		txtLocationName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtLocationName.setBounds(300, 64, 500, 30);
		add(txtLocationName);
		txtLocationName.setColumns(10);

		lblNewLabel = new JLabel("Add Location");
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
		txtState.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				submit();
			}
		});
		add(txtState);
		txtState.setColumns(10);

		cbCountry = new JComboBox<String>();
		loadC(null);
		cbCountry.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbCountry.setBounds(300, 182, 300, 30);
		add(cbCountry);

		String[] state = { "<Choose Type>", "Small City", "Medium City", "Large City", "Natural formation",
				"Designated Park/Reserve", "Man-made landmark", "Station" };
		cbType = new JComboBox<String>(state);
		cbType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbType.setBounds(300, 298, 300, 30);
		add(cbType);

		cbContinent = new JComboBox<String>(new String[] { "<Choose Continent>", "Asia", "Africa",
				"South America", "North America", "Europe", "Australia" });
		cbContinent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbContinent.addActionListener(event -> {
			if (cbContinent.getSelectedIndex() != 0)
				loadC(cbContinent.getSelectedItem().toString());
			else
				loadC(null);
		});
		cbContinent.setBounds(300, 123, 282, 30);
		add(cbContinent);

		txtLongitude = new JTextField();
		txtLongitude.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtLongitude.setBounds(300, 346, 197, 22);
		add(txtLongitude);
		txtLongitude.setColumns(10);

		txtLatitude = new JTextField();
		txtLatitude.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtLatitude.setBounds(640, 346, 197, 22);
		add(txtLatitude);
		txtLatitude.setColumns(10);

		lblLongitude = new JLabel("Longitude:");
		lblLongitude.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblLongitude.setBounds(196, 341, 94, 30);
		add(lblLongitude);

		lblLatitude = new JLabel("Latitude:");
		lblLatitude.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblLatitude.setBounds(560, 348, 81, 16);
		add(lblLatitude);

		// ======================= Button ========================
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnAdd.addActionListener(event -> submit());
		btnAdd.setBounds(300, 402, 120, 35);
		add(btnAdd);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnCancel.addActionListener(event -> SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListLocation(mainFrame))));
		btnCancel.setBounds(569, 402, 120, 35);
		add(btnCancel);

		// ==================== Error message ====================
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
		lblErrorType.setBounds(600, 296, 265, 16);
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
		revalidate();
		repaint();
	}

	private void submit() {
		lArray = lFile.readLinkArray();

		// ===================== Get data =======================
		String locationName = library.makeUpper(txtLocationName.getText());
		String continent = cbContinent.getSelectedItem().toString();
		String state = library.makeUpper(txtState.getText());
		String country = cbCountry.getSelectedItem().toString();
		String type = cbType.getSelectedItem().toString();
		double longitude = 0.00;
		double latitude = 0.00;
		char typeChar = 0;
		boolean error = false;

		// =============== Reset error message =============
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

		// =============== Check validation ==============
		if (locationName.isEmpty()) {
			lblErrorLocationName.setText("The location name cannot be empty");
			lblErrorLocationName.setVisible(true);
			error = true;
		} else {
			int nError = 0;
			for (int i = 0; i < lArray.size(); i++) {
				if (lArray.getIndexElement(i).getName().equalsIgnoreCase(locationName)) {
					nError++;
				}
			}
			if (nError != 0) {
				lblErrorLocationName.setText("The location is duplicated");
				lblErrorLocationName.setVisible(true);
				error = true;
			}
		}

		if (cbContinent.getSelectedIndex() == 0) {
			lblErrorContinent.setText("Please select the continent");
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
			try {
				longitude = Double.parseDouble(txtLongitude.getText());
			} catch (NumberFormatException e) {
				lblErrorLongitude.setText("Longitude must in numeric");
				lblErrorLongitude.setVisible(true);
				error = true;
			}
		}
		
		if (txtLatitude.getText().isEmpty()) {
			lblErrorLatitude.setText("Latitude is empty");
			lblErrorLatitude.setVisible(true);
			error = true;
		} else {
			try {
				latitude = Double.parseDouble(txtLatitude.getText());
			} catch (NumberFormatException e) {
				lblErrorLatitude.setText("Latitude must in numeric");
				lblErrorLatitude.setVisible(true);
				error = true;
			}
		}

		if (!error) {
			Location location = new Location(locationName, continent, country, state, typeChar, longitude, latitude);
			lArray.addItem(location);
			lFile.writeLinkArray(lArray);
			int result = JOptionPane.showConfirmDialog(null,
					"Add location successful!!!\nDo you want to add more location??", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				createGui();
			} else {
				try {
					SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListLocation(mainFrame)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void loadC(String continent) {
		cbCountry.removeAllItems();
		if (continent == null) {
			String[] checkStrings = library.getCountryArray("AllCountry");
			if (checkStrings != null) {
				cbCountry.setEnabled(false);
				for (String string : checkStrings) {
					cbCountry.addItem(string);
				}
			}
		} else {
			String[] checkStrings = library.getCountryArray(continent);
			if (checkStrings != null) {
				cbCountry.setEnabled(true);
				for (String string : checkStrings) {
					cbCountry.addItem(string);
				}
			}
		}
	}
}
