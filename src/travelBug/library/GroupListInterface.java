package travelBug.library;

import java.util.Iterator;
import java.lang.Iterable;

public interface GroupListInterface<T, E> extends Iterable<E> {
	public Iterator<E> iterator();

	public void clear();
	
	public int getNumberOfEntries();

	public boolean isEmpty();
}
