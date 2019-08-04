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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class AddTravelLeg extends JPanel {
	private static final long serialVersionUID = 1L; // Serializable purpose
	private JTextField tfSourceL;
	private JTextField tfDestinationL;
	private JTextField tfFromTime;
	private JTextField tfToTime;
	private LinkArray<TravelLegInfo> rArray = new LinkArray<TravelLegInfo>();
	private ReadWriteFile<TravelLegInfo> rFile = new ReadWriteFile<TravelLegInfo>("TravelLeg.txt", TravelLegInfo.class);
	private final UIControl mainframe; // Store main frame

	public AddTravelLeg(UIControl parent) {
		super();
		this.mainframe = parent;
		// =========================== Jpanel setting ==========================
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(new Rectangle(new Dimension(900, 450)));

		// ========================= Content component =========================
		JLabel lblSourceLocation = new JLabel("Source location       :");
		lblSourceLocation.setBounds(21, 13, 126, 16);
		add(lblSourceLocation);

		tfSourceL = new JTextField();
		tfSourceL.setBounds(159, 10, 116, 22);
		add(tfSourceL);
		tfSourceL.setColumns(10);

		JLabel lblDestinationLocation = new JLabel("Destination Location :");
		lblDestinationLocation.setBounds(21, 42, 135, 16);
		add(lblDestinationLocation);

		tfDestinationL = new JTextField();
		tfDestinationL.setBounds(159, 39, 116, 22);
		add(tfDestinationL);
		tfDestinationL.setColumns(10);

		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox cbTransport = new JComboBox(
				new String[] { "Select The transport type", "Airplane", "Rail/Train", "Bus", "Car", "Ferry", "Boat" });
		cbTransport.setBounds(21, 71, 172, 22);
		add(cbTransport);

		Date fromToDayDate = new Date();
		JDateChooser dcFromDate = new JDateChooser();
		dcFromDate.setMinSelectableDate(fromToDayDate);
		dcFromDate.setBounds(104, 138, 116, 22);
		JTextFieldDateEditor editor1 = (JTextFieldDateEditor) dcFromDate.getDateEditor();
		editor1.setEditable(false);
		add(dcFromDate);

		JLabel lblDuration = new JLabel("Duration :");
		lblDuration.setBounds(21, 109, 83, 16);
		add(lblDuration);

		tfFromTime = new JTextField();
		tfFromTime.setBounds(104, 202, 116, 22);
		add(tfFromTime);

		tfFromTime.setColumns(10);

		Button button = new Button("Confirm");
		button.setBounds(404, 272, 109, 32);
		add(button);

		JLabel lblFromDate = new JLabel("From Date :");
		lblFromDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblFromDate.setBounds(21, 141, 71, 16);
		add(lblFromDate);

		JDateChooser dcToDate = new JDateChooser();
		dcToDate.setMinSelectableDate(fromToDayDate);
		dcToDate.setBounds(397, 138, 116, 22);
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dcToDate.getDateEditor();
		editor.setEditable(false);
		add(dcToDate);

		JLabel lblToDate = new JLabel("To Date :");
		lblToDate.setBounds(330, 138, 56, 16);
		add(lblToDate);

		JLabel lblFromTime = new JLabel("From Time :");
		lblFromTime.setBounds(21, 205, 77, 16);
		add(lblFromTime);

		JLabel lblToTime = new JLabel("To Time :");
		lblToTime.setBounds(330, 205, 64, 16);
		add(lblToTime);

		tfToTime = new JTextField();
		tfToTime.setBounds(397, 202, 116, 22);
		add(tfToTime);
		tfToTime.setColumns(10);

		// ======================= Error Message =============================//

		Label lblsrcError = new Label("");
		lblsrcError.setBackground(Color.WHITE);
		lblsrcError.setForeground(Color.RED);
		lblsrcError.setBounds(293, 13, 279, 16);
		add(lblsrcError);

		Label lbldestError = new Label("");
		lbldestError.setBackground(Color.WHITE);
		lbldestError.setForeground(Color.RED);
		lbldestError.setBounds(293, 42, 314, 16);
		add(lbldestError);

		Label lblttError = new Label("");
		lblttError.setBackground(Color.WHITE);
		lblttError.setForeground(Color.RED);
		lblttError.setBounds(293, 71, 353, 16);
		add(lblttError);

		Label lblFromDateError = new Label("");
		lblFromDateError.setBackground(Color.WHITE);
		lblFromDateError.setForeground(Color.RED);
		lblFromDateError.setBounds(31, 167, 279, 16);
		add(lblFromDateError);

		Label lblToTimeError = new Label("");
		lblToTimeError.setBackground(Color.WHITE);
		lblToTimeError.setForeground(Color.RED);
		lblToTimeError.setBounds(330, 235, 290, 16);
		add(lblToTimeError);

		Label lblFromTimeError = new Label("");
		lblFromTimeError.setBackground(Color.WHITE);
		lblFromTimeError.setForeground(Color.RED);
		lblFromTimeError.setBounds(21, 235, 289, 16);
		add(lblFromTimeError);

		Label lblToDateEror = new Label("");
		lblToDateEror.setBackground(Color.WHITE);
		lblToDateEror.setForeground(Color.RED);
		lblToDateEror.setBounds(330, 167, 290, 16);
		add(lblToDateEror);

		// ================================ Button =================================//
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int count = 0;
				String dstLocationString = tfDestinationL.getText();
				String srcLocationString = tfSourceL.getText();
				String transportTypeString = cbTransport.getSelectedItem().toString();
				String dateString = "";
				Date checkDate = dcFromDate.getDate();
				Date checkDate1 = dcToDate.getDate();
				String fromTimeString = tfFromTime.getText();
				String toTimeString = tfToTime.getText();
				float duration = 0;
				LocalDate fromDate = null;
				LocalDate toDate = null;
				LocalTime fromTime = null;
				LocalTime toTime = null;

				// Validation for destination location
				if (dstLocationString.length() == 0 || dstLocationString.isEmpty()) {
					lbldestError.setText("[The destination location cannot be empty!]");
					count++;
				} else {
					lbldestError.setText("");
				}
				// validation for source location
				if (srcLocationString.length() == 0 || srcLocationString.isEmpty()) {
					lblsrcError.setText("[The source location cannot be empty!]");
					count++;
				} else {
					lblsrcError.setText("");
				}
				// validation for transport type
				if (transportTypeString == "Select The transport type") {
					lblttError.setText("[The transport type have to be selected!]");
					count++;
				} else {
					lblttError.setText("");
				}
				// validation for from date
				if (((JTextField) dcFromDate.getDateEditor().getUiComponent()).getText().isEmpty()) {
					lblFromDateError.setText("[Date should be filled!]");
					count++;
				} else {
					lblFromDateError.setText("");
					dateString = new SimpleDateFormat("yyyy-MM-dd").format(dcFromDate.getDate());
					fromDate = LocalDate.parse(dateString);
				}
				// validation for to date
				if (((JTextField) dcToDate.getDateEditor().getUiComponent()).getText().isEmpty()) {
					lblToDateEror.setText("[Date should be filled!]");
					count++;
				} else {
					lblToDateEror.setText("");
					dateString = new SimpleDateFormat("yyyy-MM-dd").format(dcToDate.getDate());
					toDate = LocalDate.parse(dateString);
				}
				// validation for from time
				if (fromTimeString.length() == 0 || fromTimeString.isEmpty()) {
					lblFromTimeError.setText("[The time cannot be empty!]");
					count++;
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
					count++;
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
						count++;
					}
				}
				if (toTimeString.length() > 0) {
					if (!library.validateTime(toTimeString)) {
						lblToTimeError.setText("[Invalid time or format ]");
						count++;
					}
				}
				if ((fromTimeString.length() > 0 && library.validateTime(fromTimeString))
						&& (toTimeString.length() > 0 && library.validateTime(toTimeString))) {
					if (!fromTime.isBefore(toTime)) {
						lblFromTimeError.setText("[The [From] time must before [To] time");
						count++;
					} else {
						lblFromTimeError.setText("");
					}
				}
				if (!(((JTextField) dcFromDate.getDateEditor().getUiComponent()).getText().isEmpty())
						&& !(((JTextField) dcToDate.getDateEditor().getUiComponent()).getText().isEmpty())) {
					if (!fromDate.isBefore(toDate)) {
						lblFromDateError.setText("[The [From] date must before [To] date]");
						count++;
					} else {
						lblFromDateError.setText("");
						;
					}
				}
				// Write travel leg info into text file
				if (count == 0) {
					TravelLegInfo travelLegInfo = new TravelLegInfo(transportTypeString, dstLocationString,
							srcLocationString, fromDate, toDate, fromTime, toTime);
					rArray.addItem(travelLegInfo);
					rFile.writeLinkArray(rArray);
					library.dialogMessage("Schedule added successful!!!");
				}
			}
		});

	}
}
