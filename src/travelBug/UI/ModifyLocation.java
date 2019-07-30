package travelBug.UI;

//=========================
//		Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class ModifyLocation extends JPanel {
	private JTextField txtLocationName,txtContinent,txtState;
	private JLabel lblNewLabel, lblNewLabel_1, lblNewLabel_2, lblCountry, lblState, lblType;
	private Label lblErrorLocationName,lblErrorContinent, lblErrorType, lblErrorState, lblErrorCountry;
	private JComboBox cbCountry, cbType;
	private LinkArray<Location> lArray = new LinkArray<Location>();
	private ReadWriteFile<Location> lFile = new ReadWriteFile<Location>("Location.txt",Location.class); 
	private JPanel contentPane;
	private int companyFound;
	private final UIControl mainFrame;
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public ModifyLocation(UIControl parent,String inputName,String inputState) {
		
		//==================== JPanel setting =====================
		super();
		mainFrame = parent;
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));
		
		//==================== Validate the company =====================
		
		companyFound = 0;
		boolean foundCompany = false;
		lArray = lFile.readLinkArray();
		for(int i = 0;i<lArray.size();i++) {
			if(inputName.equalsIgnoreCase(lArray.getIndexElement(i).getName())&& inputState.equalsIgnoreCase(lArray.getIndexElement(i).getState())) {
				companyFound = i;
				foundCompany = true;
			}
		}
		
		// ====================================================Content Component ==========================================================//
		
		txtLocationName = new JTextField();
		txtLocationName.setEditable(false);
		txtLocationName.setBounds(133, 50, 265, 22);
		txtLocationName.setText(lArray.getIndexElement(companyFound).getName());
		txtLocationName.addActionListener(event ->{
				submit();
		});
		contentPane.add(txtLocationName);
		txtLocationName.setColumns(10);

		txtContinent = new JTextField();
		txtContinent.setBounds(133, 88, 265, 22);
		txtContinent.setText(lArray.getIndexElement(companyFound).getContinent());
		txtContinent.addActionListener(event ->{
				submit();
		});
		contentPane.add(txtContinent);
		txtContinent.setColumns(10);

		lblNewLabel = new JLabel("Modify Location");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 0, 186, 40);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Location Name");
		lblNewLabel_1.setBounds(12, 53, 96, 16);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Continent");
		lblNewLabel_2.setBounds(12, 91, 56, 16);
		contentPane.add(lblNewLabel_2);

		lblCountry = new JLabel("Country");
		lblCountry.setBounds(12, 129, 56, 16);
		contentPane.add(lblCountry);

		lblState = new JLabel("State");
		lblState.setBounds(12, 168, 56, 16);
		contentPane.add(lblState);

		lblType = new JLabel("Type");
		lblType.setBounds(12, 200, 56, 16);
		contentPane.add(lblType);

		txtState = new JTextField();
		txtState.setEditable(false);
		txtState.setBounds(133, 165, 265, 22);
		txtState.setText(lArray.getIndexElement(companyFound).getState());
		txtState.addActionListener(event->{
				submit();
		});
		contentPane.add(txtState);
		txtState.setColumns(10);

		cbCountry = new JComboBox();
		
		cbCountry.setModel(new DefaultComboBoxModel(new String[] { "<Choose country>", "Afghanistan", "Albania",
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
		cbCountry.setBounds(133, 126, 163, 22);
		cbCountry.setSelectedItem(lArray.getIndexElement(companyFound).getCountry());
		contentPane.add(cbCountry);
		
		
		cbType = new JComboBox();
		cbType.setModel(new DefaultComboBoxModel(new String[] {"<Choose Type>", "Small City", "Medium City",
				"Large City", "Natural formation", "Designated Park/Reserve", "Man-made landmark" }));
		cbType.setBounds(133, 206, 172, 22);
		cbType.setSelectedItem(lArray.getIndexElement(companyFound).getType());
		contentPane.add(cbType);
		
		//=============================================== Error Message =====================================================//

		lblErrorLocationName = new Label("");
		lblErrorLocationName.setForeground(Color.RED);
		lblErrorLocationName.setBounds(133, 71, 265, 16);
		contentPane.add(lblErrorLocationName);

		lblErrorContinent = new Label("");
		lblErrorContinent.setForeground(Color.RED);
		lblErrorContinent.setBounds(133, 110, 265, 16);
		contentPane.add(lblErrorContinent);

		lblErrorCountry = new Label("");
		lblErrorCountry.setForeground(Color.RED);
		lblErrorCountry.setBounds(133, 149, 265, 16);
		contentPane.add(lblErrorCountry);

		lblErrorState = new Label("");
		lblErrorState.setForeground(Color.RED);
		lblErrorState.setBounds(133, 188, 265, 16);
		contentPane.add(lblErrorState);

		lblErrorType = new Label("");
		lblErrorType.setForeground(Color.RED);
		lblErrorType.setBounds(133, 230, 265, 16);
		contentPane.add(lblErrorType);
		
		// ============================================== Button ============================================= //

		JButton btnAdd = new JButton("Change");
		btnAdd.addActionListener(event->{
				submit();
				SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCompany(mainFrame)));
		});
		btnAdd.setBounds(133, 259, 97, 25);
		contentPane.add(btnAdd);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(event->{
			library.dialogMessage("The page will redirect to the list company");
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCompany(mainFrame)));
		});
		btnCancel.setBounds(265, 259, 97, 25);
		contentPane.add(btnCancel);
		if(foundCompany == false) {
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListCompany(mainFrame)));
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
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListLocation(mainFrame)));
			
		}
	}
}
