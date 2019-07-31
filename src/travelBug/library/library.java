package travelBug.library;

import java.util.regex.*;
import javax.swing.JOptionPane;

import travelBug.UI.UIControl;

import java.util.*;
import java.security.SecureRandom;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class library {
	public static final String currentDirectoryPath = System.getProperty("user.dir");	// Get root directory path
	public static UIControl frame = null;
	private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public static boolean validateTime(String time) {
		Pattern pattern;
		Matcher matcher;
		
		final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";		  
		pattern = Pattern.compile(TIME24HOURS_PATTERN);		  
		matcher = pattern.matcher(time);		  
		return matcher.matches();
	}
	
	public static String checkUsername(String in) { // return null means the username is valid
		if (in.isEmpty())
			return "Username is empty";
		else {
			Pattern passPat = Pattern.compile("\\w*");
			Matcher matchpass = passPat.matcher(in);
			if (!matchpass.matches()) {
				System.out.println("[Error] - The username must only contain character and numeric ");
				return "The username must only contain character and numeric";
			} else
				return null;
		}
	}

	public static boolean isAlpha(String in) {
		if (in.matches("^[a-zA-Z]*$")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String generatePassword() {
		StringBuilder returnValue = new StringBuilder(8);
		do {
			for (int i = 0; i < 8; i++) {
				returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
			}
		} while (validPassword(returnValue.toString()) != null);
		return new String(returnValue);
	}

	public static String randomID(int randMin, int randMax) { // random ID generated by system
		Random random = new Random();
		return Integer.toString(random.nextInt(randMax) + randMin);
	}
	
	public static String randomID(char type, int randMin, int randMax) { // random ID generated by system
		Random random = new Random();
		return Character.toUpperCase(type) + Integer.toString(random.nextInt(randMax) + randMin);
	}
	
	public static String convert(String str) {
		// Here validate the name and make the first character is Upper Case
		str = str.trim();
		Pattern p1 = Pattern.compile("[a-z A-Z/. ]*");
		Matcher match = p1.matcher(str);

		if (match.matches()) {
			StringBuffer strBf = new StringBuffer();
			Matcher m = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(str);
			while (m.find())
				m.appendReplacement(strBf, m.group(1).toUpperCase() + m.group(2).toLowerCase());
			return m.appendTail(strBf).toString();
		} else 
			return null;
	}

	public static String checkPhoneNum(String sNum) {
		Pattern p1 = Pattern.compile("([0][1][1])-(\\d{8})"), 		// 011-34567898
				p2 = Pattern.compile("([0][1][02-9])-(\\d{7})"), 	// 012-3456789
				p3 = Pattern.compile("([0][1][1])(\\d{8})"), 		// 01134567898
				p4 = Pattern.compile("([0][1][02-9])(\\d{7})"), 	// 0123456789
				p5 = Pattern.compile("([0][1][1]) (\\d{8})"), 		// 011 12121212
				p6 = Pattern.compile("([0][1][02-9]) (\\d{7})"); 	// 012 1212121

		StringBuffer strBf = new StringBuffer();
		
		// Match the phone number with the format of phone number in Malaysia
		Matcher match1 = p1.matcher(sNum), match2 = p2.matcher(sNum), match3 = p3.matcher(sNum), match4 = p4.matcher(sNum), match5 = p5.matcher(sNum), match6 = p6.matcher(sNum);
		Boolean b1 = match1.matches(), b2 = match2.matches(), b3 = match3.matches(), b4 = match4.matches(), b5 = match5.matches(), b6 = match6.matches();

		// adding the '-'
		if (b1 == true || b2 == true && (b3 == false || b4 == false || b5 == false || b6 == false)) {
			return sNum;
		} else if ((b1 == false || b2 == false || b4 == false || b5 == false || b6 == false) && b3 == true) {
			match3.appendReplacement(strBf, match3.group(1) + "-" + match3.group(2));
			return match3.appendTail(strBf).toString();
		} else if ((b1 == false || b2 == false || b3 == false || b5 == false || b6 == false) && b4 == true) {
			match4.appendReplacement(strBf, match4.group(1) + "-" + match4.group(2));
			return match4.appendTail(strBf).toString();
		} else if ((b1 == false || b2 == false || b3 == false || b4 == false || b6 == false) && b5 == true) {
			match5.appendReplacement(strBf, match5.group(1) + "-" + match5.group(2));
			return match5.appendTail(strBf).toString();
		} else if ((b1 == false || b2 == false || b3 == false || b4 == false || b5 == false) && b6 == true) {
			match6.appendReplacement(strBf, match6.group(1) + "-" + match6.group(2));
			return match6.appendTail(strBf).toString();
		} else
			return null;
	}
	
	public static String checkHomePhoneNum(String in) {
		Pattern p1 = Pattern.compile("([0][3])-(\\d{8})");
		Pattern p2 = Pattern.compile("([0][3]) (\\d{8})");
		Pattern p3 = Pattern.compile("([0][3])(\\d{8})");
		StringBuffer strBf = new StringBuffer();
		Matcher match1 = p1.matcher(in), match2 = p2.matcher(in), match3 = p3.matcher(in);
		if (match1.matches() && (!match2.matches() || !match3.matches()))
			return in;
		else if ((!match2.matches() || !match3.matches()) && match2.matches()) {
			match2.appendReplacement(strBf, match2.group(1) + "-" + match2.group(2));
			return match2.appendTail(strBf).toString();
		} else if ((!match3.matches() || !match2.matches()) && match3.matches()) {
			match3.appendReplacement(strBf, match3.group(1) + "-" + match3.group(2));
			return match3.appendTail(strBf).toString();
		} else
			return null;
	}
//
//	public static boolean validPassword(String password) {
//		if(password.length() < 6) {
//			System.out.println("[Error] - The password length must more or equal to 6");
//			return false;
//		}
//		else {
//			Pattern passPat = Pattern.compile("\\w*");
//			Matcher matchpass = passPat.matcher(password);
//			if (!matchpass.matches()) {
//				System.out.println("[Error] - The password is not character and numeric");
//				return false;
//			} else
//				return true;
//		}
//	}
	
	public static String validPassword(String password) {
		if (password.length() < 6) {
			System.out.println("[Error] - The password length must more or equal to 6");
			return "The password length must more or equal to 6";
		} else {
			Pattern passPat = Pattern.compile("\\w*");
			Matcher matchpass = passPat.matcher(password);
			if (!matchpass.matches()) {
				System.out.println("[Error] - The password must only contain character and numeric");
				return "The password must only contain character and numeric";
			} else
				return null;
		}
	}
	
	
	public static LocalDate returnDate(String inDate) {
		Pattern p1 = Pattern.compile("(\\d{4})(\\d{2})(\\d{2})");		//20180505
		Pattern p2 = Pattern.compile("(\\d{4}).(\\d{2}).(\\d{1,2})");	//2018.02.01    2018/02/05    2018-02-05
		Matcher match1 = p1.matcher(inDate), match2 = p2.matcher(inDate);
		
		try {
			if (match1.matches())
				return LocalDate.of(Integer.parseInt(match1.group(1)), Integer.parseInt(match1.group(2)), Integer.parseInt(match1.group(3)));
			else if (match2.matches())
				return LocalDate.of(Integer.parseInt(match2.group(1)), Integer.parseInt(match2.group(2)), Integer.parseInt(match2.group(3)));
			else if (inDate.equalsIgnoreCase("Today"))
				return LocalDate.now();
			else
				return null;
		} catch(Exception e) {
			return null;
		}
	}

	public static LocalDate checkDate_available(LocalDate date) {
		int increaseDay = 3;
		LocalDate nowDate = LocalDate.now();
		int now_day = nowDate.getDayOfYear(), date_day = date.getDayOfYear();
		if(date.getYear() < nowDate.getYear()) {
			return null;
		} else {
			if(date.isBefore(nowDate)){
				return null;
	        } else if(date_day <= now_day + increaseDay){
	        	return null;
	        } else {
	        	return date;
	        }
		}
	}
	
	public static LocalTime returnTime(String inTime) {		// parse time into right format
		Pattern p1 = Pattern.compile("(\\d{2}).(\\d{2})");
		Pattern p2 = Pattern.compile("(\\d{2})(\\d{2})");
		Matcher match1 = p1.matcher(inTime), match2 = p2.matcher(inTime);
		
		try {
			if(match1.matches())
				return LocalTime.of(Integer.parseInt(match1.group(1)), Integer.parseInt(match1.group(2)));
			else if(match2.matches())
				return LocalTime.of(Integer.parseInt(match2.group(1)), Integer.parseInt(match2.group(2)));
			else
				return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getTodayDate() {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return today.format(formatter);
	}
	
	public static String getTime() {
		LocalTime now = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
		return now.format(formatter);
	}
	public static void dialogMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
}
