package travelBug.obj;

import java.time.LocalDate;
import java.time.LocalTime;

public interface TravelLegInterface extends Comparable<TravelLegInterface>{
	public void setrecordNo(String recordNo);
	public String getrecordNo();
	public void setMode(String mode);
	public String getMode();
	public void setSource(String source);
	public String getSource();
	public void setDest(String dest);
	public String getDest();
	public void setPrice(double price);
	public double getPrice();
	public void setDistance(int distance);
	public int getDistance();
	public void setfromDate(LocalDate fromDate);
	public LocalDate getfromDate();
	public void settoDate(LocalDate toDate);
	public LocalDate gettoDate();
	public void setfromTime(LocalTime fromTime);
	public LocalTime getfromTime();
	public void settoTime(LocalTime toTime);
	public LocalTime gettoTime();
}
