package travelBug.obj;

import java.io.Serializable;

public class TravelLegAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username, password;

	public TravelLegAccount(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public void print() { // testing purpose
		System.out.println("Username: " + this.username);
		System.out.println("Password: " + this.password);
	}
}
