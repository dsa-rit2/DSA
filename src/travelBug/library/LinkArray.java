package travelBug.library;

import java.io.Serializable;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class LinkArray<T> implements ListInterface<T>, Serializable {
	private static final long serialVersionUID = 1L;		// Serializable purpose
	private static final int MAXSIZE = 1000;				// Initial default array size
	private int array_size;									// Store actual size of array
	private T[] objArray;									// The object of the array

	public LinkArray() {
		makeEmpty();
	}

	/*********** Double the array length ***********/
	private void doubleSize() {
		objArray = Arrays.copyOf(objArray, objArray.length * 2);
	}

	/*********** Add element ***********/
	public void addItem(T items) {
		if (array_size >= objArray.length)
			doubleSize();
		objArray[array_size++] = items;
	}

	/*********** Remove specific element using index ***********/
	public void deleteIndexItem(int index) {
		objArray[index] = null;
		for (int i = index; i < array_size; i++) {
			objArray[i] = objArray[i + 1];
		}
		objArray[array_size--] = null;
	}

	/*********** Edit element in current index ***********/
	public void editElement(int index, T items) {
		objArray[index] = items;
	}

	/*********** Get specific element using index ***********/
	public T getIndexElement(int index) {
		return objArray[index];
	}

	/*********** Check if list is empty ***********/
	public boolean isEmpty() {
		return array_size <= 0;
	}

	/*********** Get current length of the array ***********/
	public int size() {
		return array_size;
	}

	/*********** Get the first element of the array ***********/
	public T getFirst() {
		if (this.isEmpty())
			throw new NoSuchElementException("can't get from an empty list");
		else
			return objArray[0];
	}

	/*********** Check an element is contain in the array ***********/
	public boolean contains(T item) {
		for (int i = 0; i < array_size; i++)
			if (objArray[i].equals(item))
				return true;
		return false;
	}

	/*********** Add an element to the first of array ***********/
	public void addFirst(T item) {
		if (array_size >= objArray.length)
			doubleSize();					// Check if array is full
		for (int i = array_size - 1; i >= 0; i--)
			objArray[i + 1] = objArray[i]; 			// to shift elements to the back
		objArray[0] = item;
		array_size++; 						// update num_nodes
	}

	/*********** Remove the first element in the array ***********/
	public T removeFirst() {
		if (this.isEmpty())
			throw new NoSuchElementException("can't remove from an empty list");
		else {
			T tmp = objArray[0];
			for (int i = 0; i <= array_size; i++)
				objArray[i] = objArray[i + 1]; 		// to shift elements to the front
			objArray[array_size--] = null;
			return tmp;
		}
	}

	/*********** Empty the whole array ***********/
	@SuppressWarnings("unchecked")
	public void makeEmpty() {
		objArray = (T[]) new Object[MAXSIZE];	// Redefine object to make empty
		array_size = 0;						// Update node count back to 0
	}

	public T[] toArray() {
		return Arrays.copyOf(objArray, this.array_size);
	}
	
	@Override
	public String toString() {
		return this.toArray().toString();
	}
}
