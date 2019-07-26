package travelBug.obj;

import java.io.Serializable;

public class Location implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String continent;
	private String country;
	private String state;
	private String type;

	public Location(String name, String continent, String country, String state, String type) {
		this.name = name;
		this.continent = continent;
		this.country = country;
		this.state = state;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public void print() {
		System.out.println("Location Name: " + name);
		System.out.println("Continent: " + continent);
		System.out.println("State: " + state);
		System.out.println("Country: " + country);
		System.out.println("Type: " + type);
	}
}
