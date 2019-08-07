package travelBug.library;

import java.util.Iterator;
import java.lang.Iterable;

public interface GroupListInterface<T> extends Iterable<T> {
	public Iterator<T> iterator();

	public Iterator<T> getIterator();

	public boolean add(T newEntry);

	public boolean add(int newPosition, T newEntry);

	public T remove(int givenPosition);

	public void clear();

	public boolean replace(int givenPosition, T newEntry);

	public T getEntry(int givenPosition);

	public boolean contains(T anEntry);

	public int getNumberOfEntries();

	public boolean isEmpty();

	public void addPointer(GroupListInterface<T> obj);
}
