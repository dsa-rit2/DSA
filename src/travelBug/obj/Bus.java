package travelBug.obj;

import java.time.LocalDate;
import java.time.LocalTime;

public class Bus {
	private String companyName, depart, dest;
	private double price;
	private LocalTime departTime, reachTime;
	private LocalDate departDate, reachDate;

	public void setCompanyName(String name) {
		this.companyName = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public void setDepartTime(LocalTime time) {
		this.departTime = time;
	}

	public LocalTime getDepartTime() {
		return departTime;
	}

	public void setReachTime(LocalTime time) {
		this.reachTime = time;
	}

	public LocalTime getReachTime() {
		return reachTime;
	}

	public void setDepartDate(LocalDate date) {
		this.departDate = date;
	}

	public LocalDate getDepartDate() {
		return departDate;
	}

	public void setReachDate(LocalDate date) {
		this.reachDate = date;
	}

	public LocalDate getReachDate() {
		return reachDate;
	}
}
