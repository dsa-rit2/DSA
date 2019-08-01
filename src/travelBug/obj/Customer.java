package travelBug.obj;

public class Customer {
	protected String name;
	protected String username;
	protected String password;
	protected String phoneNumber;

	public Customer(String name, String username, String password, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void print() {
		System.out.println("Username:" + username);
		System.out.println("Password" + password);
		System.out.println("Name" + name);
		System.out.println("Phone Number" + phoneNumber);
	}
}
