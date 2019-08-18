package travelBug.obj;

public class TravelPlane {
	
	private int duration;
	private double price;
	private String plan;
	private String source;
	private String dest;
		
	
	public TravelPlane(String plan, double price,int duration, String source, String dest) {
		this.plan = plan;
		this.duration = duration;
		this.price = price;
		this.source = source;
		this.dest = dest;
		
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
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
}
