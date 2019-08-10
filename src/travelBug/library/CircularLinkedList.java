package travelBug.library;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<T> implements CircularLinkListInterface<T> {

	private Node firstNode;
	private int size;

	public CircularLinkedList() {
		this.clear();
	}

	@Override
	public void add(T newEntry) {
		Node newNode = new Node(newEntry);
		newNode.setNext(firstNode);

		if (isEmpty()) {
			firstNode = newNode;
			firstNode.setNext(newNode);

		} else {
			Node currentNode = getLastNode();
			currentNode.setNext(newNode);
		}
		size++;
	}

	public T getEntry(int index) {
		if (index > 0 && index < size + 1) {
			Node currentNode = firstNode;

			for (int i = 1; i < index; i++) {
				currentNode = currentNode.next;
			}

			return (T) currentNode.data;
		} else {

			return null;

		}
	}

	public boolean removeEntry(int index) {

		if (index > 0 && index < size + 1) {
			Node currentNode = firstNode;

			for (int i = 1; i < index - 1; i++) {
				currentNode = currentNode.next;// pointer loop
			}
			currentNode.next = currentNode.next.next;
			size--;
			return true;

		} else {

			return false;
		}

	}

	@Override
	public boolean contains(T anEntry) {
		Node currentNode = firstNode;
		Node newNode = new Node(anEntry);
		boolean contains = false;
		do {
			if (currentNode.data.equals(anEntry)) {
				contains = true;
				break;

			}
			currentNode = currentNode.next;
		} while (currentNode != firstNode);

		return contains;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	private Node getLastNode() {

		Node currentNode = firstNode;

		do {
			currentNode = currentNode.next;

		} while (!currentNode.next.equals(firstNode));

		return currentNode;

	}

	@Override
	public String toString() {
		String str = "";
		Node currentNode = firstNode;
		if (size != 0) {
			do {
				str += currentNode.data.toString() + "\n";
				currentNode = currentNode.next;
			} while (!currentNode.equals(firstNode));
		}

		return str;
	}

	private class Node {

		private T data;
		private Node next;

		public Node(T data) {
			this.data = data;
			this.next = null;
		}

		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

	}

	@Override
	public void clear() {
		firstNode = null;
		size = 0;
	}

	public int getNumberOfEntries() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new CicularLinkedIterator();
	}

	private class CicularLinkedIterator implements Iterator<T> {

		private Node currentNode = firstNode;
		private boolean firstTime = true;

		@Override
		public boolean hasNext() {
			return firstTime || currentNode != firstNode;
		}

		@Override
		public T next() {
			if (hasNext()) {
				T returnData = currentNode.data;
				currentNode = currentNode.next;
				firstTime = false;
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
