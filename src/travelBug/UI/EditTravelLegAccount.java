package travelBug.UI;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import travelBug.library.LinkArray;
import travelBug.library.ReadWriteFile;
import travelBug.library.library;
import travelBug.obj.TravelLegAccount;

public class EditTravelLegAccount extends JPanel {

	private static final long serialVersionUID = 5629499624569369278L;
	private JTextField txtPassword;
	private LinkArray<TravelLegAccount> tArray = new LinkArray<TravelLegAccount>();
	private ReadWriteFile<TravelLegAccount> tFile = new ReadWriteFile<TravelLegAccount>("TravelLegAccount.txt",
			TravelLegAccount.class);
	private final UIControl mainFrame;
	private int indexT = -1;
	
	/**
	 * Create the frame.
	 */
	public EditTravelLegAccount(UIControl parent, String company, String travelLegAcc) {
		// ========================================Jpanel Setting ==================================================//
				super();
				this.mainFrame = parent;
				setLayout(null);
				setBackground(new Color(0, 0, 0, 0));
				setBounds(new Rectangle(new Dimension(900, 450)));
				// ======================================== Validate ===================================================//
				// Read the file and find the company name
				tArray = tFile.readLinkArray();
				for(int i = 0;i<tArray.size();i++) {
					if(tArray.getIndexElement(i).getUsername().equalsIgnoreCase(travelLegAcc)) {
						indexT = i;
					}
				}
				if(indexT<=-1) {
					//return to the list travelleg acc
				}
				// ======================================== Content component ==============================================//
				JLabel lblUsername = new JLabel("Username:");
				lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 18));
				lblUsername.setBounds(163, 166, 107, 19);
				add(lblUsername);

				JTextField txtUsername = new JTextField();
				txtUsername.setText(tArray.getIndexElement(indexT).getUsername());
				txtUsername.setEditable(false);
				txtUsername.setBounds(313, 163, 256, 22);
				add(txtUsername);
				txtUsername.setColumns(10);

				JLabel lblPassword = new JLabel("Password:");
				lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 18));
				lblPassword.setBounds(163, 213, 107, 16);
				add(lblPassword);

				txtPassword = new JTextField();
				txtPassword.setText(tArray.getIndexElement(indexT).getPassword());
				txtPassword.setBounds(313, 210, 256, 22);
				add(txtPassword);
				txtPassword.setColumns(10);


				JLabel lblAddTravelLeg = new JLabel(company);
				lblAddTravelLeg.setFont(new Font("Segoe UI Emoji", Font.BOLD, 27));
				lblAddTravelLeg.setBounds(163, 86, 538, 67);
				add(lblAddTravelLeg);

				JLabel lblAddTravellegAccount = new JLabel("Edit Travelleg Account");
				lblAddTravellegAccount.setFont(new Font("Segoe UI", Font.BOLD, 27));
				lblAddTravellegAccount.setBounds(156, 38, 502, 51);
				add(lblAddTravellegAccount);

				Label lblPasswordError = new Label("");
				lblPasswordError.setForeground(new Color(255, 0, 0));
				lblPasswordError.setBackground(Color.white);
				lblPasswordError.setBounds(312, 233, 416, 16);
				add(lblPasswordError);

				new JPanel();
				Button btnModify = new Button("Modify");
				btnModify.setActionCommand("Modify");
				btnModify.addActionListener(event -> {
					String username = txtUsername.getText();
					String password = String.valueOf(txtPassword.getText());
					String checkPass = library.validPassword(password);
					int error = 0;

					lblPasswordError.setText("");

					if (password.isEmpty()) {
						lblPasswordError.setText("The password cannot be empty");
						error++;
					} else if (checkPass != null) {
						lblPasswordError.setText(checkPass);
						error++;
					}
					if (error == 0) {
						// run the code for storing
						for(int i = 0; i<tArray.size();i++) {
							if(tArray.getIndexElement(i).getUsername().equalsIgnoreCase(username)) {
								tArray.getIndexElement(i).setPassword(password);
							}
						}
						tFile.writeLinkArray(tArray);
						library.dialogMessage("The travelleg account is updated!!");
						SwingUtilities.invokeLater(
								() -> mainFrame.changePanel(new ListTravelLegAccount(mainFrame, company)));
					}

				});

				btnModify.setForeground(new Color(0, 0, 0));
				btnModify.setBounds(313, 319, 79, 24);
				add(btnModify);

				Button btnBack = new Button("Back");
				btnBack.addActionListener(event->{
					SwingUtilities.invokeLater(
							() -> mainFrame.changePanel(new ListTravelLegAccount(mainFrame, company)));
				});
				btnBack.setForeground(new Color(0, 0, 0));
				btnBack.setBounds(490, 319, 79, 24);
				add(btnBack);
	}

}
