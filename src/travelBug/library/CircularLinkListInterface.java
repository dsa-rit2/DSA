package travelBug.library;

import java.util.Iterator;
import java.lang.Iterable;

public interface CircularLinkListInterface <T> extends AllList<T>, Iterable<T>{
	
	public Iterator<T> iterator();
    public void add(T newEntry);
    public T getEntry(int index);
    public boolean removeEntry(int index);
    public boolean contains(T anEntry);
    public boolean isEmpty();
    public String toString();
    public void clear();
	public int getNumberOfEntries();

	
}
