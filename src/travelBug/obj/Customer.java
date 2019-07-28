package travelBug.obj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

public class Customer extends Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private int nextCustID = 1001;
	private String password;
	private int custID;

	public Customer(String firstName, String lastName, String username, String password) {
		super(firstName, lastName, username, password);
	}

	public Customer(String firstName, String lastName, String username, String password, int custID) {
		super(firstName, lastName, username, password);
		this.custID = custID;
	}

	public int getNextCustID() {
		return nextCustID++;
	}

	public boolean checkCustExist(String custID) {
		String fileName = "User.txt";
		Scanner sc = new Scanner(System.in);
		String line = null;
		boolean exist = false;
		int choice;
		try {
			// read file
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			// read all from the file
			while ((line = bufferedReader.readLine()) != null) {
				String[] result = line.split(":");

				if (custID.equals(result[0])) {
					exist = true;
					return exist;
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println("ERROR!");
		}
		return exist;
	}

	public String toString() {
		return custID + ":" + super.toString();
	}

	public void registerCust() {
		String fileName = "User.txt";
		Scanner sc = new Scanner(System.in);
		String line = null;
		int choice;
		try {
			// check file exists, yes append else create new text file
			FileWriter fw = new FileWriter(fileName, true);
			PrintWriter pw = new PrintWriter(fw);
			try {
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				StringBuffer stringBuffer = new StringBuffer();
				// read all from the file
				while ((line = bufferedReader.readLine()) != null) {
					nextCustID++;
				}
			} catch (IOException e) {
				System.out.println("ERROR!");
			}
			custID = getNextCustID();
			pw.println(toString());
			pw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("ERROR!");
		}
	}

	public boolean checkLogin(String username, String password) {
		String fileName = "User.txt";
		Scanner sc = new Scanner(System.in);
		String line = null;
		boolean exist = false;
		int choice;
		try {
			// read file
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			// read all from the file
			while ((line = bufferedReader.readLine()) != null) {
				String[] result = line.split(":");

				if (username.equals(result[1]) && password.equals(result[2])) {
					exist = true;
					return exist;
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println("ERROR!");
		}
		return exist;
	}

}
