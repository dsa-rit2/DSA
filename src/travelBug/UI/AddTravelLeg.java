package travelBug.UI;

//=========================
//	Import Package
//=========================
import travelBug.library.*;

import travelBug.obj.*;
//=========================

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.xml.bind.ParseConversionEvent;
import org.junit.Ignore;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddTravelLeg extends JPanel {
	private static final long serialVersionUID = 1L; // Serializable purpose
	private JTextField tfSourceL;
	private JTextField tfDestinationL;
	private JTextField tfFromTime;
	private JTextField tfToTime;
	private JTextField tfDistance;
	private JTextField tfPrice;
	private JComboBox  cbTransport;
	private LinkArray<TravelLegInfo> rArray = new LinkArray<TravelLegInfo>();
	private ReadWriteFile<TravelLegInfo> rFile = new ReadWriteFile<TravelLegInfo>("TravelLeg.txt", TravelLegInfo.class);
	private LinkArray<Location> cArray = new LinkArray<Location>();
	private ReadWriteFile<Location> cFile = new ReadWriteFile<Location>("Location.txt", Location.class);
	private final UIControl mainframe; // Store main frame
	private Set<String> s = new TreeSet<String>();
	private char mode;
	private String companyString;
	

	public AddTravelLeg(UIControl parent) {
		super();
		this.mainframe = parent;
		// =========================== Jpanel setting ==========================
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));
		// =============================Read from textfile==========================//
		cArray = cFile.readLinkArray();
		s = new TreeSet<String>();
		for(int i = 0; i < cArray.size(); i++) {
			s.add(cArray.getIndexElement(i).getName());
		}
		// ============================ Content component =========================//
		JLabel lblSourceLocation = new JLabel("Source location  :");
		lblSourceLocation.setFont(new Font("Source Code Pro Black", Font.BOLD, 16));
		lblSourceLocation.setBounds(37, 44, 207, 16);
		add(lblSourceLocation);
//================================Auto completer for textfield Source location======================//				
		tfSourceL = new JTextField();
		tfSourceL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfSourceL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent evtEvent) {
				boolean foundSource = false;
				boolean foundDestination = false;
				if(evtEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE || evtEvent.getKeyCode() == KeyEvent.VK_SHIFT)
				{					
				}
				else {					
				String to_checkString = tfSourceL.getText();
				int to_check_len = to_checkString.length();
				for(String data:s) {
					
					String check_from_dataString = "";
					for(int i = 0; i < to_check_len;i++) {
						
						if(to_check_len <= data.length()) {
							check_from_dataString = check_from_dataString + data.charAt(i);
							
						}
						if(check_from_dataString.equalsIgnoreCase(to_checkString))
						{
							tfSourceL.setText(data);
							tfSourceL.setSelectionStart(to_check_len);
							tfSourceL.setSelectionEnd(data.length());
						}
					}
				}
			}
				for(int i = 0; i < cArray.size(); i++) {
					if((tfSourceL.getText().toString().equalsIgnoreCase(cArray.getIndexElement(i).getName()))) {
						foundSource = true;
					}
				}
				for(int i = 0; i < cArray.size(); i++) {
					if((tfDestinationL.getText().toString().equalsIgnoreCase(cArray.getIndexElement(i).getName()))){
						foundDestination = true;
					}
				}
				if(foundSource && foundDestination) {
					cbTransport.setEnabled(true);
					tfDistance.setText("0.0");
				}
				else {
					cbTransport.setEnabled(false);
					tfDistance.setText("0.0");
				}
			}
		});
		tfSourceL.setBounds(253, 40, 143, 32);
		add(tfSourceL);
		tfSourceL.setColumns(10);

		JLabel lblDestinationLocation = new JLabel("Destination Location :");
		lblDestinationLocation.setFont(new Font("Source Code Pro Black", Font.BOLD, 16));
		lblDestinationLocation.setBounds(446, 44, 252, 16);
		add(lblDestinationLocation);
//==============================================================================================================//		
//====================================//Get company name to get the list of the travelleg//=======================//
		companyString = parent.authUser.getUsername().toString();
		companyString = library.getUsernameShortForm(companyString);
		
//========================Auto Completer for textfield Distination location================================//
		tfDestinationL = new JTextField();
		tfDestinationL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfDestinationL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent evtEvent) {
				boolean foundSource = false;
				boolean foundDestination = false;
				if(evtEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE || evtEvent.getKeyCode() == KeyEvent.VK_SHIFT)
				{				
				}
				else {					
				String to_checkString = tfDestinationL.getText();
				int to_check_len = to_checkString.length();
				for(String data:s) {
					
					String check_from_dataString = "";
					for(int i = 0; i < to_check_len;i++) {
						
						if(to_check_len <= data.length()) {
							check_from_dataString = check_from_dataString + data.charAt(i);
							
						}
						if(check_from_dataString.equalsIgnoreCase(to_checkString))
						{
							tfDestinationL.setText(data);
							tfDestinationL.setSelectionStart(to_check_len);
							tfDestinationL.setSelectionEnd(data.length());
						}
					}
				}
			}
				for(int i = 0; i < cArray.size(); i++) {
					if((tfSourceL.getText().toString().equalsIgnoreCase(cArray.getIndexElement(i).getName()))) {
						foundSource = true;
					}
				}
				for(int i = 0; i < cArray.size(); i++) {
					if((tfDestinationL.getText().toString().equalsIgnoreCase(cArray.getIndexElement(i).getName()))){
						foundDestination = true;
					}
				}
				if(foundSource && foundDestination) {
					cbTransport.setEnabled(true);
					tfDistance.setText("0.0");
				}
				else {
					cbTransport.setEnabled(false);
					tfDistance.setText("0.0");
				}
			}
		});
		tfDestinationL.setBounds(701, 40, 143, 32);
		add(tfDestinationL);
		tfDestinationL.setColumns(10);
//========================================================================================================================//
		String[] selectionString = { "Select The transport type", "Airplane", "Rail/Train", "Bus", "Car", "Ferry",
				"Boat" };
		cbTransport = new JComboBox(selectionString);
		cbTransport.setEnabled(false);
		cbTransport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double latitude1 = 0.0;
				double latitude2 = 0.0;
				double longtitude1 = 0.0;
				double longtitude2 = 0.0;
				if(cbTransport.getSelectedItem().toString() != "Select The transport type" && tfSourceL.getText().toString() != null && tfDestinationL.getText().toString() != null) {
					for(int i = 0; i < cArray.size(); i++) {
						if((cArray.getIndexElement(i).getName()).equalsIgnoreCase(tfSourceL.getText().toString())) {
						latitude1 = cArray.getIndexElement(i).getLatitude();
						longtitude1 = cArray.getIndexElement(i).getLongitude();
						}
						if((cArray.getIndexElement(i).getName()).equalsIgnoreCase(tfDestinationL.getText().toString())) {
							latitude2 = cArray.getIndexElement(i).getLatitude();
							longtitude2 = cArray.getIndexElement(i).getLongitude();
						}
					double distance1 = library.CoordinateDistance(longtitude1, latitude1, longtitude2, latitude2);
					String distanceString = String.format ("%.2f", distance1);
					tfDistance.setText(distanceString);
				}
			}
			}
		});
		cbTransport.setFont(new Font("Source Code Pro Black", Font.BOLD, 15));
		cbTransport.setBounds(37, 85, 298, 22);
		add(cbTransport);

		Date fromToDayDate = new Date();
		JDateChooser dcFromDate = new JDateChooser();
		dcFromDate.setMinSelectableDate(fromToDayDate);
		dcFromDate.setBounds(175, 223, 154, 46);
		JTextFieldDateEditor editor1 = (JTextFieldDateEditor) dcFromDate.getDateEditor();
		editor1.setEditable(false);
		add(dcFromDate);

		tfFromTime = new JTextField();
		tfFromTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfFromTime.setBounds(175, 320, 154, 42);
		add(tfFromTime);

		tfFromTime.setColumns(10);

		Button btnConfirm = new Button("Confirm");
		btnConfirm.setFont(new Font("Source Code Pro Black", Font.BOLD, 18));
		btnConfirm.setBounds(174, 390, 161, 50);
		add(btnConfirm);

		JLabel lblFromDate = new JLabel("From Date :");
		lblFromDate.setFont(new Font("Source Code Pro Black", Font.BOLD, 17));
		lblFromDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblFromDate.setBounds(37, 223, 126, 31);
		add(lblFromDate);

		JDateChooser dcToDate = new JDateChooser();
		dcToDate.setMinSelectableDate(fromToDayDate);
		dcToDate.setBounds(672, 223, 154, 46);
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dcToDate.getDateEditor();
		editor.setEditable(false);
		add(dcToDate);

		JLabel lblToDate = new JLabel("To Date :");
		lblToDate.setFont(new Font("Source Code Pro Black", Font.BOLD, 17));
		lblToDate.setBounds(524, 223, 135, 31);
		add(lblToDate);

		JLabel lblFromTime = new JLabel("From Time :");
		lblFromTime.setFont(new Font("Source Code Pro Black", Font.BOLD, 17));
		lblFromTime.setBounds(37, 320, 126, 39);
		add(lblFromTime);

		JLabel lblToTime = new JLabel("To Time :");
		lblToTime.setFont(new Font("Source Code Pro Black", Font.BOLD, 17));
		lblToTime.setBounds(524, 320, 136, 39);
		add(lblToTime);

		tfToTime = new JTextField();
		tfToTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfToTime.setBounds(672, 320, 154, 42);
		add(tfToTime);
		tfToTime.setColumns(10);

		// ======================= Error Message =============================//

		Label lblsrcError = new Label("");
		lblsrcError.setBackground(Color.WHITE);
		lblsrcError.setForeground(Color.RED);
		lblsrcError.setBounds(37, 20, 359, 16);
		add(lblsrcError);

		Label lbldestError = new Label("");
		lbldestError.setBackground(Color.WHITE);
		lbldestError.setForeground(Color.RED);
		lbldestError.setBounds(446, 20, 398, 16);
		add(lbldestError);

		Label lblttError = new Label("");
		lblttError.setBackground(Color.WHITE);
		lblttError.setForeground(Color.RED);
		lblttError.setBounds(352, 85, 353, 16);
		add(lblttError);

		Label lblFromDateError = new Label("");
		lblFromDateError.setBackground(Color.WHITE);
		lblFromDateError.setForeground(Color.RED);
		lblFromDateError.setBounds(40, 275, 289, 16);
		add(lblFromDateError);

		Label lblToTimeError = new Label("");
		lblToTimeError.setBackground(Color.WHITE);
		lblToTimeError.setForeground(Color.RED);
		lblToTimeError.setBounds(532, 368, 294, 16);
		add(lblToTimeError);

		Label lblFromTimeError = new Label("");
		lblFromTimeError.setBackground(Color.WHITE);
		lblFromTimeError.setForeground(Color.RED);
		lblFromTimeError.setBounds(40, 368, 289, 16);
		add(lblFromTimeError);

		Label lblToDateEror = new Label("");
		lblToDateEror.setBackground(Color.WHITE);
		lblToDateEror.setForeground(Color.RED);
		lblToDateEror.setBounds(532, 275, 294, 16);
		add(lblToDateEror);
		
		JLabel lblDistance = new JLabel("Distance (km) :");
		lblDistance.setFont(new Font("Source Code Pro Black", Font.BOLD, 16));
		lblDistance.setBounds(37, 137, 173, 16);
		add(lblDistance);
		
		tfDistance = new JTextField();
		tfDistance.setEditable(false);
		tfDistance.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfDistance.setBounds(216, 131, 143, 32);
		add(tfDistance);
		tfDistance.setColumns(10);
		tfDistance.setText("0.0");
		
		JLabel lblPrice = new JLabel("Price (RM)    :");
		lblPrice.setFont(new Font("Source Code Pro Black", Font.BOLD, 16));
		lblPrice.setBounds(506, 137, 173, 16);
		add(lblPrice);
		
		tfPrice = new JTextField();
		tfPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfPrice.setBounds(691, 131, 143, 32);
		add(tfPrice);
		tfPrice.setColumns(10);
		
		Label lblDistanceError = new Label("");
		lblDistanceError.setForeground(Color.RED);
		lblDistanceError.setBackground(Color.WHITE);
		lblDistanceError.setBounds(37, 184, 314, 16);
		add(lblDistanceError);
		
		Label lblPriceError = new Label("");
		lblPriceError.setForeground(Color.RED);
		lblPriceError.setBackground(Color.WHITE);
		lblPriceError.setBounds(506, 184, 320, 16);
		add(lblPriceError);
		
		Button btnCancel = new Button("Cancel");
		btnCancel.setForeground(Color.RED);
		btnCancel.addActionListener(event -> {
			library.dialogMessage("The page will redirect to list TravelLeg\n");
			SwingUtilities.invokeLater(() -> mainframe.changePanel(new TravelLegMaintenance(mainframe)));
		});
		btnCancel.setFont(new Font("Source Code Pro Black", Font.BOLD, 18));
		btnCancel.setBounds(471, 390, 161, 50);
		add(btnCancel);
		
		// ================================ Button =================================//
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean error = false;
				String dstLocationString = tfDestinationL.getText();
				String srcLocationString = tfSourceL.getText();
				double price = 0;
				double distance = 0;
				String priceString = tfPrice.getText();
				String distanceString = tfDistance.getText();
				String transportTypeString = cbTransport.getSelectedItem().toString();
				String dateString = "";
				Date checkDate = dcFromDate.getDate();
				Date checkDate1 = dcToDate.getDate();
				String fromTimeString = tfFromTime.getText();
				String toTimeString = tfToTime.getText();
				int duration = 0;
				LocalDate fromDate = null;
				LocalDate toDate = null;
				LocalTime fromTime = null;
				LocalTime toTime = null;

				// Validation for destination location
				if (dstLocationString.length() == 0 || dstLocationString.isEmpty()) {
					lbldestError.setText("[The destination location cannot be empty!]");
					error = true;
				} else {
					lbldestError.setText("");
				}
				// validation for source location
				if (srcLocationString.length() == 0 || srcLocationString.isEmpty()) {
					lblsrcError.setText("[The source location cannot be empty!]");
					error = true;
				} else {
					lblsrcError.setText("");
				}
				// validation for transport type
				if (transportTypeString == "Select The transport type") {
					lblttError.setText("[The transport type have to be selected!]");
					error = true;
				} else {
					lblttError.setText("");
					mode = library.getModeChar(transportTypeString);
					
				}
				// validation for from date
				if (((JTextField) dcFromDate.getDateEditor().getUiComponent()).getText().isEmpty()) {
					lblFromDateError.setText("[Date should be filled!]");
					error = true;
				} else {
					lblFromDateError.setText("");
					dateString = new SimpleDateFormat("yyyy-MM-dd").format(dcFromDate.getDate());
					fromDate = LocalDate.parse(dateString);
				}
				// validation for to date
				if (((JTextField) dcToDate.getDateEditor().getUiComponent()).getText().isEmpty()) {
					lblToDateEror.setText("[Date should be filled!]");
					error = true;
				} else {
					lblToDateEror.setText("");
					dateString = new SimpleDateFormat("yyyy-MM-dd").format(dcToDate.getDate());
					toDate = LocalDate.parse(dateString);
				}
				// validation for from time
				if (fromTimeString.length() == 0 || fromTimeString.isEmpty()) {
					lblFromTimeError.setText("[The time cannot be empty!]");
					error = true;
				} else {
					lblFromTimeError.setText("");
					fromTimeString = tfFromTime.getText();
					try {
						fromTime = LocalTime.parse(fromTimeString);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				// validation for to date
				if (toTimeString.length() == 0 || toTimeString.isEmpty()) {
					lblToTimeError.setText("[The time cannot be empty!]");
					error = true;
				} else {
					lblToTimeError.setText("");
					toTimeString = tfToTime.getText();
					try {
						toTime = LocalTime.parse(toTimeString);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				if (fromTimeString.length() > 0) {
					if (!library.validateTime(fromTimeString)) {
						lblFromTimeError.setText("[Invalid time or format ]");
						error = true;
					}
				}
				if (toTimeString.length() > 0) {
					if (!library.validateTime(toTimeString)) {
						lblToTimeError.setText("[Invalid time or format ]");
						error = true;
					}
				}
				if ((fromTimeString.length() > 0 && library.validateTime(fromTimeString))
						&& (toTimeString.length() > 0 && library.validateTime(toTimeString))) {
					if (!fromTime.isBefore(toTime)) {
						lblFromTimeError.setText("[The [From] time must before [To] time");
						error = true;
					}
						else {
					}
						duration = library.convertDuration(fromTime, toTime);
						lblFromTimeError.setText("");
				}
				if (!(((JTextField) dcFromDate.getDateEditor().getUiComponent()).getText().isEmpty())
						&& !(((JTextField) dcToDate.getDateEditor().getUiComponent()).getText().isEmpty())) {
					if (!(fromDate.isBefore(toDate))) {
						lblFromDateError.setText("[The [From] date must before [To] date] or Qeual");
						error = true;
					}
					if(!(fromDate.equals(toDate))){
						lblFromDateError.setText("[The [From] date must before [To] date] or Equal");
						error = true;
					}
					if(fromDate.isAfter(toDate)) {
						lblFromDateError.setText("[The [From] date must before [To] date] or Equal");
						error = true;
					}
					else {
						lblFromDateError.setText("");
					}
				}
				if(srcLocationString.length() > 0) {
					boolean srcError = true;
					for(int i = 0 ; i < cArray.size(); i ++) {
						if(srcLocationString.equals(cArray.getIndexElement(i).getName())) {
							srcError = false;							
						}
					}
					if(srcError) {
						lblsrcError.setText("[The location is not exist]");
						error = true;
					}
					else {
						lblsrcError.setText("");
					}
				}
				if(dstLocationString.length() > 0) {
					boolean dstError = true;
					for(int i = 0 ; i < cArray.size(); i ++) {
						if(dstLocationString.equals(cArray.getIndexElement(i).getName())) {
							dstError = false;
						}
					}
					if(dstError) {
						lbldestError.setText("[The lcoation is not exist]");
						error = true;
					}
					else {
						lbldestError.setText("");
					}
				}
				if((srcLocationString.length() > 0 && dstLocationString.length() > 0)) {
					boolean sameExist = false;
						if(srcLocationString.equals(dstLocationString)) {
							sameExist = true;
						}
						if(sameExist) {
							lblsrcError.setText("[The location cannot be the same]");
							lbldestError.setText("[The location cannot be the same]");
							error = true;
						}
						else {
							lblsrcError.setText("");
							lbldestError.setText("");
						}
					}
				//=========================Validation for price and distance========================//
				if(tfPrice.getText().length() == 0 || tfPrice.getText().isEmpty()) {
					lblPriceError.setText("[The price cannot be empty]");
					error = true;
				}
				else {
					priceString = tfPrice.getText();
					lblPriceError.setText("");
				}
				if(Double.parseDouble(tfDistance.getText()) == 0.0) {
					lblDistanceError.setText("[The distance cannot be empty]");
					error = true;
				}
				else {
					distanceString = tfDistance.getText();
					distance = Double.parseDouble(distanceString);
					lblDistanceError.setText("");
				}
				if(priceString.length() > 0) {
					if(!(library.isValidPrice(priceString))) {
						lblPriceError.setText("[The price format is incorrect (xx.xx)]");
						error = true;
					}
					else {
						lblPriceError.setText("");
						price = Double.parseDouble(priceString);
					}
				}
				//========================= Write travel leg info into text file==================//
				if (!error) {
					rArray = rFile.readLinkArray();
					TravelLegInfo travelLegInfo = new TravelLegInfo(companyString,mode,srcLocationString,
							dstLocationString,price,distance, fromDate, toDate, fromTime, toTime, duration);
					rArray.addItem(travelLegInfo);
					rFile.writeLinkArray(rArray);
					int result = JOptionPane.showConfirmDialog(null,
							"New travel leg info added successful!!!\nDo you want to add more travel leg info?",
							"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {
						try {
							SwingUtilities.invokeLater(() -> mainframe.changePanel(new AddTravelLeg(mainframe)));
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						try {
							SwingUtilities.invokeLater(() -> mainframe.changePanel(new TravelLegMaintenance(mainframe)));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
	}
}
