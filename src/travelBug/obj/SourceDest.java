package travelBug.obj;

import java.io.Serializable;
import java.time.LocalDate;


public class SourceDest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String l1;
	String l2;
	LocalDate localDate;
	
	public SourceDest(String source, String dest) {
		this.l1 = source;
		this.l2 = dest;
		this.localDate = LocalDate.now();
	}
	public String getL1() {
		return l1;
	}

	public void setL1(String source) {
		this.l1 = source;
	}

	public String getL2() {
		return l2;
	}

	public void setL2(String dest) {
		this.l2 = dest;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

}
