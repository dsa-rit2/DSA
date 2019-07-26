package travelBug.obj;

import java.time.LocalTime;

public class TravelLeg {
	private String mode,source,dest;
	private LocalTime departTime;
	private float duration;
	
	public TravelLeg(String mode,String source,String dest,LocalTime departTime,float duration) {
		this.mode = mode;
		this.source = source;
		this.dest = dest;
		this.departTime = departTime;
		this.duration = duration;
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
	public void setDepartTime(LocalTime departTime) {
		this.departTime = departTime;
	}
	public LocalTime getDepartTime() {
		return departTime;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
	public float getDuration() {
		return duration;
	}
}
