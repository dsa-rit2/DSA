package travelBug.UI;

//=========================
//	Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddLocation extends JPanel {
	private static final long serialVersionUID = 1L;		// Serializable purpose
	private JTextField txtLocationName, txtContinent, txtState;
	private JLabel lblNewLabel, lblNewLabel_1, lblNewLabel_2, lblCountry, lblState, lblType;
	private Label lblErrorLocationName,lblErrorContinent, lblErrorType, lblErrorState, lblErrorCountry;
	private JComboBox<String> cbCountry, cbType;
	private LinkArray<Location> lArray = new LinkArray<Location>();
	private ReadWriteFile<Location> lFile = new ReadWriteFile<Location>("Location.txt", Location.class);
	private final UIControl mainFrame;		// Store main frame

	public AddLocation(UIControl parent) {
		super();
		this.mainFrame = parent;
		
		//===================== JPanel setting ======================
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		//===================== Content component =====================
		txtLocationName = new JTextField();
		txtLocationName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtLocationName.setBounds(300, 90, 500, 30);
		add(txtLocationName);
		txtLocationName.setColumns(10);

		txtContinent = new JTextField();
		txtContinent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtContinent.setBounds(300, 149, 500, 30);
		add(txtContinent);
		txtContinent.setColumns(10);

		lblNewLabel = new JLabel("Add Location");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel.setBounds(12, 13, 876, 50);
		add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Location Name: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_1.setBounds(100, 90, 180, 30);
		add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Continent: ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_2.setBounds(100, 149, 180, 30);
		add(lblNewLabel_2);

		lblCountry = new JLabel("Country: ");
		lblCountry.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCountry.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblCountry.setBounds(100, 208, 180, 30);
		add(lblCountry);

		lblState = new JLabel("State: ");
		lblState.setHorizontalAlignment(SwingConstants.RIGHT);
		lblState.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblState.setBounds(100, 264, 180, 30);
		add(lblState);

		lblType = new JLabel("Type: ");
		lblType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblType.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblType.setBounds(100, 324, 180, 30);
		add(lblType);

		txtState = new JTextField();
		txtState.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtState.setBounds(300, 264, 500, 30);
		txtState.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				submit();
			}
		});
		add(txtState);
		txtState.setColumns(10);
		String[] country = new String[] { "<Choose country>", "Afghanistan", "Albania", "Algeria",
		"Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan",
		"Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan",
		"Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi",
		"Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China",
		"Colombia", "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba", "Cyprus",
		"Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador",
		"Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland",
		"France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea",
		"Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran",
		"Iraq", "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan",
		"Kazakhstan", "Kenya", "Kiribati", "Korea North", "Korea South", "Kosovo", "Kuwait", "Kyrgyzstan",
		"Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
		"Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
		"Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro",
		"Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand",
		"Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea",
		"Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation",
		"Rwanda", "St Kitts & Nevis", "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino",
		"Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore",
		"Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain",
		"Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan",
		"Tanzania", "Thailand", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan",
		"Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay",
		"Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe" };
		cbCountry = new JComboBox(country);
		cbCountry.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbCountry.setBounds(300, 208, 300, 30);
		add(cbCountry);
		String[] state = { "<Choose Type>", "Small City", "Medium City", "Large City",
				"Natural formation", "Designated Park/Reserve", "Man-made landmark" };
		cbType = new JComboBox(state);
		cbType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbType.setBounds(300, 324, 300, 30);
		add(cbType);
		
		// ==================================== Button ==================================//
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnAdd.addActionListener(event -> submit());
		btnAdd.setBounds(300, 400, 120, 35);
		add(btnAdd);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnCancel.addActionListener(event -> {
//			library.dialogMessage("The page will redirect to list location");
			
			JOptionPane.showMessageDialog(null, "The page will redirect to list location\n");
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListLocation(mainFrame)));
		});
		btnCancel.setBounds(502, 400, 120, 35);
		add(btnCancel);
		
		//===================== Error message ====================
		lblErrorLocationName = new Label("");
		lblErrorLocationName.setForeground(Color.RED);
		lblErrorLocationName.setBackground(Color.white);
		lblErrorLocationName.setBounds(300, 120, 265, 16);
		add(lblErrorLocationName);

		lblErrorContinent = new Label("");
		lblErrorContinent.setForeground(Color.RED);
		lblErrorContinent.setBackground(Color.white);
		lblErrorContinent.setBounds(300, 179, 265, 16);
		add(lblErrorContinent);

		lblErrorCountry = new Label("");
		lblErrorCountry.setForeground(Color.RED);
		lblErrorCountry.setBackground(Color.white);
		lblErrorCountry.setBounds(600, 208, 265, 16);
		add(lblErrorCountry);

		lblErrorState = new Label("");
		lblErrorState.setForeground(Color.RED);
		lblErrorState.setBackground(Color.white);
		lblErrorState.setBounds(300, 294, 265, 16);
		add(lblErrorState);

		lblErrorType = new Label("");
		lblErrorType.setForeground(Color.RED);
		lblErrorType.setBackground(Color.white);
		lblErrorType.setBounds(600, 322, 265, 16);
		add(lblErrorType);
	}

	private void submit() {
		lArray = lFile.readLinkArray();

		//===================== Get data =======================
		String locationName = txtLocationName.getText();
		String continent = txtContinent.getText();
		String state = txtContinent.getText();
		String country = cbCountry.getSelectedItem().toString();
		String type = cbType.getSelectedItem().toString();

		boolean error = false;
		
		//=============== Reset error message =============
		lblErrorLocationName.setText("");
		lblErrorContinent.setText("");
		lblErrorCountry.setText("");
		lblErrorState.setText("");
		lblErrorType.setText("");
		
		//=============== Check validation ==============
		if (locationName.isEmpty()) {
			lblErrorLocationName.setText("The location name cannot be empty");
			error = true;
		}
		
		if (continent.isEmpty()) {
			lblErrorContinent.setText("The continent cannot be empty");
			error = true;
		}
		
		if (state.isEmpty()) {
			lblErrorState.setText("The state cannot be empty");
			error = true;
		}
		
		if (cbCountry.getSelectedIndex() == 0) {
			lblErrorCountry.setText("Please select country");
			error = true;
		}
		
		if (cbType.getSelectedIndex() == 0) {
			lblErrorType.setText("Please select type");
			error = true;
		}
		
		if (!error) {
			Location location = new Location(locationName, continent, country, state, type);
			lArray.addItem(location);
			lFile.writeLinkArray(lArray);
			int result = JOptionPane.showConfirmDialog(null,
					"Add location successful!!!\nDo you want to add more location??",
					"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				try {
					SwingUtilities.invokeLater(() -> mainFrame.changePanel(new AddLocation(mainFrame)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListLocation(mainFrame)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
