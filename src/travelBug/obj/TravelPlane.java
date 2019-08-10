package travelBug.obj;

public class TravelPlane {
	
	private int duration;
	private double price;
	private String sourceString;
	private String destString;
	
	public TravelPlane(String sourString,String destString,double price,int duration) {
		this.duration = duration;
		this.price = price;
		this.sourceString = sourString;
		this.destString = destString;
		
	
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getDuration() {
		return duration;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	public double getPrice() {
		return price;
	}
	public void setSource(String sourceString) {
		this.sourceString = sourceString;
	}
	public String getSourceString() {
		return sourceString;
	}
	public void setDest(String destString) {
		this.destString = destString;
	}
	public String getDest() {
		return destString;
	}	
}
