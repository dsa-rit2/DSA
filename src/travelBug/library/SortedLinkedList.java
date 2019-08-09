package travelBug.library;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedLinkedList<T> implements SortedLinkListInterface<T>, Serializable {
	private static final long serialVersionUID = 1L;
	private Node firstNode;
	private Node lastNode;
	private int length;
	private Comparator<? super T> comparator;

	public SortedLinkedList(SinglyLinkedList<T> list, Comparator<? super T> c) {
		this.clear();
		comparator = c;
		list.forEach(item -> {
			add(item);
		});
	}
	
	public SortedLinkedList () {
		this.clear();
	}

	public T getfirstNode() {
		return firstNode.data;
	}

	public boolean add(T newEntry) {

		firstNode = add(newEntry, firstNode);
		length++;
		return true;
	}

	public T getLastNode() {
		Node currentNode = firstNode;
		while (currentNode.next != null) {
			currentNode = currentNode.next;
		}
		lastNode = currentNode;
		return lastNode.data;
	}
	Node add(T newEntry, Node currNode) {
		if ((currNode == null) || comparator.compare(newEntry, currNode.data) <= 0) {
			currNode = new Node(newEntry, currNode);
		} else {
			Node nodeAfter = add(newEntry, currNode.next);
			currNode.next = nodeAfter;
		}
		return currNode;
	}

	public boolean addAll(SinglyLinkedList<T> anEntry) {
		for (int i = 1; i <= anEntry.getNumberOfEntries(); i++) {
			add((T) anEntry.getEntry(i));
		}
		return true;
	}

	public boolean remove(T anEntry) {
		throw new UnsupportedOperationException(); // Left as Practical exercise
	}

	public T getEntry(int givenPosition) {
		T result = null;

		if ((givenPosition >= 1) && (givenPosition <= length)) {
			Node currentNode = firstNode;
			for (int i = 0; i < givenPosition - 1; ++i) {
				currentNode = currentNode.next; // advance currentNode to next node
			}
			result = currentNode.data; // currentNode is pointing to the node at givenPosition
		}

		return result;
	}

	public boolean contains(T anEntry) {
		boolean found = false;
		Node tempNode = firstNode;
		int pos = 1;

		while (!found && (tempNode != null)) {
			if (comparator.compare(anEntry, tempNode.data) <= 0) {
				found = true;
			} else {
				tempNode = tempNode.next;
				pos++;
			}
		}
		System.out.println("\n***TRACE: tempNode.data is " + tempNode.data + " " + pos);
		if (tempNode != null && tempNode.data.equals(anEntry))
			return true;
		else
			return false;
	}

	public T remove(int givenPosition) {
		T result = null;

		if ((givenPosition >= 1) && (givenPosition <= length)) {
			if (givenPosition == 1) { // CASE 1: remove first entry
				result = firstNode.data; // save entry to be removed
				firstNode = firstNode.next; // update firstNode to point to the next node
			} else { // CASE 2: remove interior entry or last entry
				Node nodeBefore = firstNode;
				for (int i = 1; i < givenPosition - 1; ++i) {
					nodeBefore = nodeBefore.next; // advance nodeBefore to its next node
				}
				result = nodeBefore.next.data; // save entry to be removed
				nodeBefore.next = nodeBefore.next.next; // make node before point to node after the
			} // one to be deleted (to disconnect node from chain)

			length--;
		}

		return result;
	}

	public final void clear() {
		firstNode = null;
		lastNode = null;
		length = 0;
	}

	public int getLength() {
		return length;
	}

	public boolean isEmpty() {
		return (length == 0);
	}

	public boolean isFull() {
		return false;
	}

	public String toString() {
		String outputStr = "";
		Node currentNode = firstNode;
		while (currentNode != null) {
			outputStr += currentNode.data + "\n";
			;
			currentNode = currentNode.next;
		}
		return outputStr;
	}

	private class Node {

		private T data;
		private Node next;

		private Node(T dataPortion) {
			data = dataPortion;
			next = null;
		}

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}
	}

	@Override
	public boolean addAll(T anEntry) {
		return false;
	}

	@Override
	public int getPosition(T anEntry) {
		return 0;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new SinglyListIterator();
	}

	private class SinglyListIterator implements Iterator<T> {

		private Node currentNode = firstNode;

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
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}

