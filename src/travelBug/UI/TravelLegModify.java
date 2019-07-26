package travelBug.UI;

//=========================
//		Import Package
//=========================
import travelBug.library.*;
import travelBug.obj.*;
//=========================

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class TravelLegModify extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfSourceL;
	private JTextField tfDestinationL;
	private JTextField tfFromTime;
	private JTextField tfToTime;
	private LinkArray<TravelLegInfo> rArray = new LinkArray<TravelLegInfo>();
	private ReadWriteFile<TravelLegInfo> rFile = new ReadWriteFile<TravelLegInfo>("TravelLeg.txt", TravelLegInfo.class);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTravelLeg frame = new AddTravelLeg();
					frame.setVisible(true);
					frame.setTitle("Schedule maintenance");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TravelLegModify(Vector vector, String ID) {
		rArray = rFile.readLinkArray();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setBackground(Color.YELLOW);

		JLabel lblSourceLocation = new JLabel("Source location       :");
		lblSourceLocation.setBounds(21, 13, 126, 16);
		contentPane.add(lblSourceLocation);

		tfSourceL = new JTextField();
		tfSourceL.setBounds(159, 10, 116, 22);
		contentPane.add(tfSourceL);
		tfSourceL.setColumns(10);

		JLabel lblDestinationLocation = new JLabel("Destination Location :");
		lblDestinationLocation.setBounds(21, 42, 135, 16);
		contentPane.add(lblDestinationLocation);

		tfDestinationL = new JTextField();
		tfDestinationL.setBounds(159, 39, 116, 22);
		contentPane.add(tfDestinationL);
		tfDestinationL.setColumns(10);

		String[] selectionString = { "Select The transport type", "Airplane", "Rail/Train", "Bus", "Car", "Ferry",
				"Boat" };
		JComboBox cbTransport = new JComboBox(selectionString);
		cbTransport.setBounds(21, 71, 172, 22);
		contentPane.add(cbTransport);

		Date fromToDayDate = new Date();
		JDateChooser dcFromDate = new JDateChooser();
		dcFromDate.setMinSelectableDate(fromToDayDate);
		dcFromDate.setBounds(104, 138, 116, 22);
		JTextFieldDateEditor editor1 = (JTextFieldDateEditor) dcFromDate.getDateEditor();
		editor1.setEditable(false);
		contentPane.add(dcFromDate);

		JLabel lblDuration = new JLabel("Duration :");
		lblDuration.setBounds(21, 109, 83, 16);
		contentPane.add(lblDuration);

		tfFromTime = new JTextField();
		tfFromTime.setBounds(104, 202, 116, 22);
		contentPane.add(tfFromTime);
		tfFromTime.setColumns(10);

		Button button = new Button("Confirm");
		button.setBounds(259, 268, 109, 32);
		contentPane.add(button);

		JLabel lblsrcError = new JLabel("");
		lblsrcError.setForeground(Color.RED);
		lblsrcError.setBackground(Color.WHITE);
		lblsrcError.setBounds(293, 13, 279, 16);
		contentPane.add(lblsrcError);

		JLabel lbldestError = new JLabel("");
		lbldestError.setForeground(Color.RED);
		lbldestError.setBackground(Color.WHITE);
		lbldestError.setBounds(293, 42, 314, 16);
		contentPane.add(lbldestError);

		JLabel lblttError = new JLabel("");
		lblttError.setForeground(Color.RED);
		lblttError.setBackground(Color.WHITE);
		lblttError.setBounds(293, 71, 353, 16);
		contentPane.add(lblttError);

		JLabel lblFromDateError = new JLabel("");
		lblFromDateError.setForeground(Color.RED);
		lblFromDateError.setBackground(Color.WHITE);
		lblFromDateError.setBounds(21, 173, 219, 16);
		contentPane.add(lblFromDateError);

		JLabel lblToTimeError = new JLabel("");
		lblToTimeError.setForeground(Color.RED);
		lblToTimeError.setBackground(Color.WHITE);
		lblToTimeError.setBounds(330, 235, 248, 16);
		contentPane.add(lblToTimeError);

		JLabel lblFromTimeError = new JLabel("");
		lblFromTimeError.setForeground(Color.RED);
		lblFromTimeError.setBackground(Color.WHITE);
		lblFromTimeError.setBounds(21, 235, 264, 16);
		contentPane.add(lblFromTimeError);

		JLabel lblFromDate = new JLabel("From Date :");
		lblFromDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblFromDate.setBounds(21, 141, 71, 16);
		contentPane.add(lblFromDate);

		JDateChooser dcToDate = new JDateChooser();
		dcToDate.setMinSelectableDate(fromToDayDate);
		dcToDate.setBounds(397, 138, 116, 22);
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dcToDate.getDateEditor();
		editor.setEditable(false);
		contentPane.add(dcToDate);

		JLabel lblToDate = new JLabel("To Date :");
		lblToDate.setBounds(330, 138, 56, 16);
		contentPane.add(lblToDate);

		JLabel lblFromTime = new JLabel("From Time :");
		lblFromTime.setBounds(21, 205, 77, 16);
		contentPane.add(lblFromTime);

		JLabel lblToTime = new JLabel("To Time :");
		lblToTime.setBounds(330, 205, 64, 16);
		contentPane.add(lblToTime);

		tfToTime = new JTextField();
		tfToTime.setBounds(397, 202, 116, 22);
		contentPane.add(tfToTime);
		tfToTime.setColumns(10);

		JLabel lblToDateEror = new JLabel("");
		lblToDateEror.setForeground(Color.RED);
		lblToDateEror.setBackground(Color.WHITE);
		lblToDateEror.setBounds(330, 173, 219, 16);
		contentPane.add(lblToDateEror);

		tfSourceL.setText(vector.elementAt(1).toString());
		tfDestinationL.setText(vector.elementAt(2).toString());
		cbTransport.setSelectedItem(vector.elementAt(7));

		LocalDate date2 = (LocalDate) vector.elementAt(3);
		Date date = Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant());
		dcFromDate.setDate(date);

		LocalDate date3 = (LocalDate) vector.elementAt(4);
		Date date1 = Date.from(date3.atStartOfDay(ZoneId.systemDefault()).toInstant());
		dcToDate.setDate(date1);

		tfFromTime.setText(vector.elementAt(5).toString());
		tfToTime.setText(vector.elementAt(6).toString());

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

				// Validation
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
				} else {
					lblFromDateError.setText("");
					dateString = new SimpleDateFormat("yyyy-MM-dd").format(dcFromDate.getDate());
					fromDate = LocalDate.parse(dateString);
				}
				// validation for to date
				if (((JTextField) dcToDate.getDateEditor().getUiComponent()).getText().isEmpty()) {
					lblToDateEror.setText("[Date should be filled!]");
				} else {
					lblFromDateError.setText("");
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
						count++;
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
						count++;
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
					for (int i = 0; i < rArray.size(); i++) {
						if (rArray.getIndexElement(i).getrecordNo().toString().matches(ID)) {
							rArray.getIndexElement(i).setSource(srcLocationString);
							rArray.getIndexElement(i).setDest(dstLocationString);
							rArray.getIndexElement(i).setfromDate(fromDate);
							rArray.getIndexElement(i).settoDate(toDate);
							rArray.getIndexElement(i).setfromTime(fromTime);
							rArray.getIndexElement(i).settoTime(toTime);
							rArray.getIndexElement(i).setMode(transportTypeString);
						}
					}
					rFile.writeLinkArray(rArray);
					JFrame frame = new JFrame("JOptionPane showMessageDialog example");
					JOptionPane.showMessageDialog(frame, "Schedule added successful!!!");
					frame.dispose();
					dispose();
				}
			}
		});

	}
}