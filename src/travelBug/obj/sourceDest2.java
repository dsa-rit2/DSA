package travelBug.obj;

import java.time.LocalDate;

import travelBug.library.library;

public class sourceDest2 {
	String l1;
	String l2;
	LocalDate localDate;
	
	public sourceDest2(String source, String dest) {
		this.l1 = source;
		this.l2 = dest;
		this.localDate = LocalDate.now();
	}
	public String getL1() {
		return l1;
	}

	public void setL1(String source) {
		this.l1 = l1;
	}

	public String getL2() {
		return l2;
	}

	public void setL2(String dest) {
		this.l2 = l2;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

}
