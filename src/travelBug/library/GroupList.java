package travelBug.library;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GroupList<T> implements GroupListInterface<T> {

	private Node firstNode;
	private int numberOfEntries;

	public GroupList() {
		clear();
	}


	public boolean add(T newEntry) {
		Node newNode = new Node(newEntry);

		if (isEmpty()) {
			firstNode = newNode;
		} else { // add to end of nonempty list
			Node currentNode = firstNode; // traverse linked list with p pointing to the current node
			while (currentNode.next != null) { // while have not reached the last node
				currentNode = currentNode.next;
			}
			currentNode.next = newNode; // make last node reference new node
		}

		numberOfEntries++;
		return true;
	}

	public boolean add(int newPosition, T newEntry) {

	}

	public T remove(int givenPosition) {
		T result = null;

		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			if (givenPosition == 1) { // case 1: remove first entry
				result = firstNode.data;
				firstNode = firstNode.next;
			} else { // case 2: givenPosition > 1
				Node nodeBefore = firstNode;
				for (int i = 1; i < givenPosition - 1; ++i) {
					nodeBefore = nodeBefore.next; // advance nodeBefore to its next node
				}
				result = nodeBefore.next.data;
				nodeBefore.next = nodeBefore.next.next; // make node before point to node after the
			} // one to be deleted (to disconnect node from chain)

			numberOfEntries--;
		}

		return result;
	}

	public void clear() {
		firstNode = null;
		numberOfEntries = 0;
	}

	public boolean replace(int givenPosition, T newEntry) {

	}

	@SuppressWarnings("unchecked")
	public T getEntry(int givenPosition) {
		T data = null;
		if (givenPosition > 0 && givenPosition < numberOfEntries) {
			Node currentNode = firstNode;
			for (int i = 0; i < givenPosition; i++) {
				currentNode = currentNode.next;
			}
			data = currentNode.data;
		}
		return data;
	}

	public boolean contains(T anEntry) {

	}

	public int getNumberOfEntries() {
		return numberOfEntries;
	}

	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	@Override
	public Iterator<T> iterator() {
		return getIterator();
	}

	@Override
	public Iterator<T> getIterator() {
		return new GroupListIterator();
	}

	private class GroupListIterator implements Iterator<T> {

		private Node currentNode;

		public GroupListIterator() {
			currentNode = firstNode;
		}

		@Override
		public boolean hasNext() {
			return currentNode != null;
		}

		@Override
		public T next() {
			if (hasNext()) {
				T returnData = currentNode.data;
				currentNode = currentNode.next;
				return returnData;
			} else {
				throw new NoSuchElementException("Illegal call to next(); iterator is after end of list.");
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods,
																			// choose Tools | Templates.
		}
	}

	private class Node {

		private T data;
		private Node next;

		private Node(T data) {
			this.data = data;
			this.next = null;
		}

		private Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
