package travelBug.obj;

public class TravelLocation {
	private String name;
	private String continent;
	private String contry;
	private String state;
	private char type;

	public TravelLocation(String name, String continent, String contry, String state, char type) {
		this.name = name;
		this.continent = continent;
		this.contry = contry;
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

	public String getContry() {
		return contry;
	}

	public void setContry(String contry) {
		this.contry = contry;
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
}
