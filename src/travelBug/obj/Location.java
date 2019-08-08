package travelBug.obj;

import java.io.Serializable;
import java.util.Comparator;

public class Location implements Serializable, Comparator<Location> {
	private static final long serialVersionUID = 1L;
	private String name;
	private String continent;
	private String country;
	private String state;
	private char type;
	private double longitude;
	private double latitude;

	public Location(String name, String continent, String country, String state, char type, double longitude,
			double latitude) {
		this.name = name;
		this.continent = continent;
		this.country = country;
		this.state = state;
		this.type = type;
		this.longitude = longitude;
		this.latitude = latitude;
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

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void print() {
		System.out.println("Location Name: " + name);
		System.out.println("Continent: " + continent);
		System.out.println("State: " + state);
		System.out.println("Country: " + country);
		System.out.println("Type: " + type);
		System.out.println(longitude + "," + latitude);
	}
	
	@Override
	public String toString() {
		String output = new String();
		
//		output = 	"Location Name: " + name + 
//					"\nContinent: " + continent + 
//					"\nState: " + state + 
//					"\nCountry: " + country + 
//					"\nType: " + type + 
//					"\n" + longitude + "," + latitude;

		output = "State: " + state;
		
		return output;
	}

	@Override
	public int compare(Location o1, Location o2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
