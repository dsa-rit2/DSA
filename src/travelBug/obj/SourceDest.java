package travelBug.obj;

public class SourceDest {
	String l1;
	String l2;
	int count = 0;
	
	public SourceDest(String source, String dest) {
		this.l1 = source;
		this.l2 = dest;
		this.count = 1;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	public void addCount() {
		this.count++;
	}
}
