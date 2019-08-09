package travelBug.library;

public interface CircularLinkListInterface <T> {
	
    void add(T newEntry);
    //boolean remove(T anEntry);
    T getEntry(int index);
    boolean removeEntry(int index);
    boolean contains(T anEntry);
    int getSize();
    boolean isEmpty();
    String toString();
	
}
