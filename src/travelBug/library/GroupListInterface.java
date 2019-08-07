package travelBug.library;

import java.util.Iterator;
import java.lang.Iterable;

public interface GroupListInterface<T> extends Iterable<T> {
	public Iterator<T> iterator();

	public void clear();
	
	public int getNumberOfEntries();

	public boolean isEmpty();
}
