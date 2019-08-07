package travelBug.obj;

import static org.junit.Assert.isArray;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

import travelBug.library.LinkArray;
import travelBug.library.library;
import travelBug.library.singlyLinkedList;

public class TravelLeg implements Comparable<TravelLeg>,Serializable {
	private static final long serialVersionUID = 1L;
	private String mode,source,dest;
	private LocalTime fromTime;
	private LocalTime toTime;
	private LocalDate fromDate;
	private LocalDate toDate;	
	private String recordNo;
	private double price;
	private int distance;
	
	public TravelLeg(String mode,String source,String dest,double price,int distance,LocalDate fromDate,LocalDate toDate, LocalTime fromTime,LocalTime toTime) {
		this.mode = mode;
		this.source = source;
		this.dest = dest;
		this.price = price;
		this.distance = distance;
		this.fromDate = fromDate;
		this.fromTime= fromTime;
		this.toDate = toDate;
		this.toTime = toTime;
		recordNo = library.randomID(1000, 2000);
	}
	public void setrecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
	public String getrecordNo() {
		return recordNo;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getMode() {
		return mode;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSource() {
		return source;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public String getDest() {
		return dest;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice() {
		return price;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getDistance() {
		return distance;
	}
	public void setfromDate(LocalDate fromDate) {
		this.fromDate= fromDate;
	}
	public LocalDate getfromDate() {
		return fromDate;
	}
	public void settoDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public LocalDate gettoDate() {
		return toDate;
	}
	public void setfromTime(LocalTime fromTime) {
		this.fromTime = fromTime;
	}
	public LocalTime getfromTime() {
		return fromTime;		
	}
	public void settoTime(LocalTime toTime) {
		this.toTime = toTime;
	}
	public LocalTime gettoTime() {
		return toTime;
	}
	@Override
	public int compareTo(TravelLeg o) {
		// TODO Auto-generated method stub
		System.out.println(Double.compare(this.getPrice(), o.getPrice()));
		return Double.compare(this.getPrice(), o.getPrice());
	}

}
