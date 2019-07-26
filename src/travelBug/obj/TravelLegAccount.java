package travelBug.obj;

import java.io.Serializable;

public class TravelLegAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username, password, description, companyName;
	private boolean car = false, ship = false, airplane = false, bus = false;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public boolean isCar() {
		return car;
	}

	public void setCar(boolean car) {
		this.car = car;
	}

	public boolean isShip() {
		return ship;
	}

	public void setShip(boolean ship) {
		this.ship = ship;
	}

	public boolean isAirplane() {
		return airplane;
	}

	public void setAirplane(boolean airplane) {
		this.airplane = airplane;
	}

	public boolean isBus() {
		return bus;
	}

	public void setBus(boolean bus) {
		this.bus = bus;
	}

	public void print() { // testing purpose
		System.out.println("Username: " + this.username);
		System.out.println("Password: " + this.password);
		System.out.println("Company Name: " + this.companyName);
		System.out.println("Description: " + this.description);
//		int countTransport = 0;
		if (ship) {
			System.out.println("Ship");
		}
		if (car) {
			System.out.println("Car");
		}

		if (bus) {
			System.out.println("Bus");
		}
		if (airplane) {
			System.out.println("Airplane");
		}
	}
}
