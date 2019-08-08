package travelBug.library;

import java.util.Iterator;

public interface AllList<T> {
	public Iterator<T> iterator();

	public void clear();
	
	public int getNumberOfEntries();

	public boolean isEmpty();
}
