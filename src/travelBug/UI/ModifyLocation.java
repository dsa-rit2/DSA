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
	private static final long serialVersionUID = 1L;	// Serializable purpose
	private JTextField txtLocationName,txtContinent,txtState;
	private JLabel lblNewLabel, lblNewLabel_1, lblNewLabel_2, lblCountry, lblState, lblType;
	private Label lblErrorLocationName,lblErrorContinent, lblErrorType, lblErrorState, lblErrorCountry;
	private JComboBox<Object> cbCountry, cbType;
	private LinkArray<Location> lArray = new LinkArray<Location>();
	private ReadWriteFile<Location> lFile = new ReadWriteFile<Location>("Location.txt",Location.class); 
	private int companyFound;
	private final UIControl mainFrame;
	
	public ModifyLocation(UIControl parent,String inputName,String inputState) {
		
		//==================== JPanel setting =====================
		super();
		mainFrame = parent;
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));
		
		//==================== Validate the company =====================
		
		companyFound = 0;
		boolean foundLocation = false;
		lArray = lFile.readLinkArray();
		for(int i = 0;i<lArray.size();i++) {
			if(inputName.equalsIgnoreCase(lArray.getIndexElement(i).getName())&& inputState.equalsIgnoreCase(lArray.getIndexElement(i).getState())) {
				companyFound = i;
				foundLocation = true;
			}
		}
		
		// ====================================================Content Component ==========================================================//
		
		txtLocationName = new JTextField();
		txtLocationName.setEditable(false);
		txtLocationName.setBounds(367, 120, 265, 25);
		txtLocationName.setText(lArray.getIndexElement(companyFound).getName());
		txtLocationName.addActionListener(event ->{
				submit();
		});
		add(txtLocationName);
		txtLocationName.setColumns(10);

		txtContinent = new JTextField();
		txtContinent.setBounds(367, 171, 265, 25);
		txtContinent.setText(lArray.getIndexElement(companyFound).getContinent());
		txtContinent.addActionListener(event ->{
				submit();
		});
		add(txtContinent);
		txtContinent.setColumns(10);

		lblNewLabel = new JLabel("Modify Location");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel.setBounds(246, 50, 265, 40);
		add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Location Name");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_1.setBounds(218, 123, 137, 22);
		add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Continent");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_2.setBounds(263, 171, 96, 25);
		add(lblNewLabel_2);

		lblCountry = new JLabel("Country");
		lblCountry.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblCountry.setBounds(279, 223, 80, 26);
		add(lblCountry);

		lblState = new JLabel("State");
		lblState.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblState.setBounds(301, 272, 56, 22);
		add(lblState);

		lblType = new JLabel("Type");
		lblType.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblType.setBounds(303, 323, 56, 28);
		add(lblType);

		txtState = new JTextField();
		txtState.setEditable(false);
		txtState.setBounds(367, 272, 265, 29);
		txtState.setText(lArray.getIndexElement(companyFound).getState());
		txtState.addActionListener(event->{
				submit();
		});
		add(txtState);
		txtState.setColumns(10);

		cbCountry = new JComboBox<Object>();
		
		cbCountry.setModel(new DefaultComboBoxModel<Object>(new String[] { "<Choose country>", "Afghanistan", "Albania",
				"Algeria", "Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Australia", "Austria",
				"Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin",
				"Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina",
				"Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile",
				"China", "Colombia", "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba",
				"Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor",
				"Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji",
				"Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala",
				"Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia",
				"Iran", "Iraq", "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan",
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
				"Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe" }));
		cbCountry.setBounds(367, 220, 163, 26);
		cbCountry.setSelectedItem(lArray.getIndexElement(companyFound).getCountry());
		add(cbCountry);
		
		
		cbType = new JComboBox<Object>();
		cbType.setModel(new DefaultComboBoxModel<Object>(new String[] {"<Choose Type>", "Small City", "Medium City",
				"Large City", "Natural formation", "Designated Park/Reserve", "Man-made landmark" }));
		cbType.setBounds(367, 327, 172, 26);
		cbType.setSelectedItem(lArray.getIndexElement(companyFound).getType());
		add(cbType);
		
		//=============================================== Error Message =====================================================//

		lblErrorLocationName = new Label("");
		lblErrorLocationName.setForeground(Color.RED);
		lblErrorLocationName.setBackground(Color.white);
		lblErrorLocationName.setBounds(367, 142, 265, 26);
		add(lblErrorLocationName);

		lblErrorContinent = new Label("");
		lblErrorContinent.setForeground(Color.RED);
		lblErrorContinent.setBackground(Color.WHITE);
		lblErrorContinent.setBounds(367, 192, 265, 25);
		add(lblErrorContinent);

		lblErrorCountry = new Label("");
		lblErrorCountry.setForeground(Color.RED);
		lblErrorCountry.setBackground(Color.white);
		lblErrorCountry.setBounds(367, 243, 265, 25);
		add(lblErrorCountry);

		lblErrorState = new Label("");
		lblErrorState.setForeground(Color.RED);
		lblErrorState.setBackground(Color.white);
		lblErrorState.setBounds(367, 296, 265, 25);
		add(lblErrorState);

		lblErrorType = new Label("");
		lblErrorType.setForeground(Color.RED);
		lblErrorType.setBackground(Color.WHITE);
		lblErrorType.setBounds(367, 352, 265, 23);
		add(lblErrorType);
		
		// ============================================== Button ============================================= //

		JButton btnAdd = new JButton("Change");
		btnAdd.addActionListener(event->{
				submit();
		});
		btnAdd.setBounds(279, 393, 97, 25);
		add(btnAdd);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(event->{
			library.dialogMessage("The page will redirect to the list location");
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListLocation(mainFrame)));
		});
		btnCancel.setBounds(489, 393, 97, 25);
		add(btnCancel);
		if(foundLocation == false) {
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListLocation(mainFrame)));
		}
	}
	public void submit() {
		lArray = lFile.readLinkArray();
		
		String locationName = txtLocationName.getText();
		String continent = txtContinent.getText();
		String state = txtState.getText();
		String country = cbCountry.getSelectedItem().toString();
		String type = cbType.getSelectedItem().toString();
		
		int error= 0;
		lblErrorLocationName.setText("");
		lblErrorContinent.setText("");
		lblErrorCountry.setText("");
		lblErrorState.setText("");
		lblErrorType.setText("");
		if(locationName.isEmpty()) {
			lblErrorLocationName.setText("The location name cannot be empty");
			error++;
		}
		if(continent.isEmpty()) {
			lblErrorContinent.setText("The continent cannot be empty");
			error++;
		}
		if(state.isEmpty()) {
			lblErrorState.setText("The state cannot be empty");
			error++;
		}
		if(cbCountry.getSelectedIndex()==0) {
			lblErrorCountry.setText("Please select country");
			error++;
		}
		if(cbType.getSelectedIndex()==0) {
			lblErrorType.setText("Please select type");
			error++;
		}
		if(error==0) {
			lArray.getIndexElement(companyFound).setName(locationName);
			lArray.getIndexElement(companyFound).setContinent(continent);
			lArray.getIndexElement(companyFound).setCountry(country);
			lArray.getIndexElement(companyFound).setState(state);
			lArray.getIndexElement(companyFound).setType(type);
			lFile.writeLinkArray(lArray);
			lArray.getIndexElement(companyFound).print();
			library.dialogMessage("The location is updated\nThe page will redirect to list location");
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListLocation(mainFrame)));
			
		}
	}
}
