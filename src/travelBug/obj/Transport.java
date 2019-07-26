package travelBug.obj;

import java.time.LocalDate;
import java.time.LocalTime;

public interface Transport {

	public void setCompanyName(String name);

	public String getCompanyName();

	public double getPrice();

	public void setPrice(double price);

	public String getDepart();

	public void setDepart(String depart);

	public String getDest();

	public void setDest(String dest);

	public void setDepartTime(LocalTime time);

	public LocalTime getDepartTime();

	public void setReachTime(LocalTime time);

	public LocalTime getReachTime();

	public void setDepartDate(LocalDate date);

	public LocalDate getDepartDate();

	public void setReachDate(LocalDate date);

	public LocalDate getReachDate();

}
