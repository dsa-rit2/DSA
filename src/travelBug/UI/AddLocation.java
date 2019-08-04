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
	private static final long serialVersionUID = 1L; // Serializable purpose
	private JTextField txtLocationName, txtState;
	private JLabel lblNewLabel, lblNewLabel_1, lblNewLabel_2, lblCountry, lblState, lblType;
	private Label lblErrorLocationName, lblErrorContinent, lblErrorType, lblErrorState, lblErrorCountry;
	private JComboBox<String> cbCountry, cbType;
	private LinkArray<Location> lArray = new LinkArray<Location>();
	private ReadWriteFile<Location> lFile = new ReadWriteFile<Location>("Location.txt", Location.class);
	private JComboBox cbContinent;
	private final UIControl mainFrame; // Store main frame

	public AddLocation(UIControl parent) {
		super();
		this.mainFrame = parent;

		// ===================== JPanel setting ======================
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		// ===================== Content component =====================
		txtLocationName = new JTextField();
		txtLocationName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtLocationName.setBounds(300, 90, 500, 30);
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

		cbCountry = new JComboBox();
		loadC(null);
//		cbCountry.setModel(model);
		cbCountry.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbCountry.setBounds(300, 208, 300, 30);
		add(cbCountry);

		String[] state = { "<Choose Type>", "Small City", "Medium City", "Large City", "Natural formation",
				"Designated Park/Reserve", "Man-made landmark" };
		cbType = new JComboBox(state);
		cbType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbType.setBounds(300, 324, 300, 30);
		add(cbType);

		cbContinent = new JComboBox();
		cbContinent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbContinent.setModel(new DefaultComboBoxModel(new String[] { "<Choose Continent>", "Asia", "Africa",
				"South America", "North America", "Europe", "Australia" }));
		cbContinent.addActionListener(event -> {
			if(cbContinent.getSelectedIndex() !=0)
				loadC(cbContinent.getSelectedItem().toString());
			else
				loadC(null);
		});
		cbContinent.setBounds(300, 149, 282, 30);
		add(cbContinent);

		// ==================================== Button ==================================//
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnAdd.addActionListener(event -> submit());
		btnAdd.setBounds(300, 400, 120, 35);
		add(btnAdd);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnCancel.addActionListener(event -> {
			library.dialogMessage("The page will redirect to list location");
			SwingUtilities.invokeLater(() -> mainFrame.changePanel(new ListLocation(mainFrame)));
		});
		btnCancel.setBounds(502, 400, 120, 35);
		add(btnCancel);

		// ===================== Error message ====================
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

		// ===================== Get data =======================
		String locationName = txtLocationName.getText();
		String continent = cbContinent.getSelectedItem().toString();
		String state = txtState.getText();
		String country = cbCountry.getSelectedItem().toString();
		String type = cbType.getSelectedItem().toString();

		boolean error = false;

		// =============== Reset error message =============
		lblErrorLocationName.setText("");
		lblErrorContinent.setText("");
		lblErrorCountry.setText("");
		lblErrorState.setText("");
		lblErrorType.setText("");

		// =============== Check validation ==============
		if (locationName.isEmpty()) {
			lblErrorLocationName.setText("The location name cannot be empty");
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
				error = true;
			}
		}

		if (cbContinent.getSelectedIndex() == 0) {
			lblErrorContinent.setText("Please select the continent");
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
					"Add location successful!!!\nDo you want to add more location??", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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

	public void loadC(String continent) {
		cbCountry.removeAllItems();
		if (continent == null) {
			String[] checkStrings = library.getCountryArray("AllCountry");
			if (checkStrings != null) {
				for (String string : checkStrings) {
					cbCountry.addItem(string);
				}
			}
		} else {

			String[] checkStrings = library.getCountryArray(continent);
			if (checkStrings != null)
				for (String string : checkStrings) {
					cbCountry.addItem(string);
				}
		}
	}
}
