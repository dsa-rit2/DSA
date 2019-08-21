package travelBug.obj;

public class TravelPlane {
	
	private int duration;
	private double price;
	private String plan;
	private String source;
	private String dest;
	private double adultPrice;
	private double kiddoPrice;
	private double distance;
	private int index;
		

	public TravelPlane(String plan, double adultPrice, double kiddoPrice, double price,int duration, String source, String dest, double distance, int index) {
		this.plan = plan;
		this.duration = duration;
		this.adultPrice = adultPrice;
		this.kiddoPrice = kiddoPrice;
		this.price = price;
		this.source = source;
		this.dest = dest;
		this.distance = distance;
		this.index = index;
		
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getDuration() {
		return duration;
	}
	public void setAdultPrice(double adultPrice)
	{
		this.adultPrice = adultPrice;
	}
	public double getAdultPrice() {
		return adultPrice;
	}
	public double getKiddoPrice() {
		return kiddoPrice;
	}
	public void setKiddoPrice(double kiddoPrice) {
		this.kiddoPrice = kiddoPrice;
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
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
