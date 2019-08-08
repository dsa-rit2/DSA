package travelBug.library;

import java.util.Iterator;

public interface SortedLinkListInterface<T> extends Iterable<T> {
	  public boolean add(T newEntry);

	  public boolean addAll(T anEntry);
	  
	  public boolean remove(T anEntry);

	  public int getPosition(T anEntry);

	  public T getEntry(int givenPosition);

	  public boolean contains(T anEntry);

	  public T remove(int givenPosition);

	  public void clear();

	  public int getLength();

	  public boolean isEmpty();

	  public boolean isFull();
	  
	  public Iterator<T> iterator();
}
