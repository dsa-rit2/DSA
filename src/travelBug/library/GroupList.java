package travelBug.library;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class GroupList<T> implements GroupListInterface<T> {

	private Node firstNode;
	private int numberOfEntries;
	private Comparator<? super T> c;

	private GroupList() {
		this.clear();
	}

	@SuppressWarnings("unchecked")
	public GroupList(SinglyLinkedList<T> listNode, Comparator<? super T> c) {
		this();
		this.c = c;
	
//		listNode.forEach(item -> {
//			Node currentNode = this.firstNode;
//			Node previousNode = null;
//
//			while ((currentNode != null) && (c.compare(item, currentNode.data)) > 0) {
//				previousNode = currentNode;
//				currentNode = currentNode.next;
//			}
//
//			if (previousNode != null) {
//				previousNode.next = new Node(item, currentNode);
//			}
//			else {
//				this.firstNode = new Node(item, firstNode);
//			}
//		});
		
		SortedLinkedList<T> sortedLinkedList = new SortedLinkedList<T>(listNode, c);
		SinglyLinkedList<T> tempLinkedList = new SinglyLinkedList<T>();
		
		sortedLinkedList.forEach(list -> {
			if (tempLinkedList.isEmpty() || (c.compare(list, tempLinkedList.getFirst())) == 0) {
				tempLinkedList.add(list);
				System.out.println(list);
			}
			else {
				this.add((T) tempLinkedList);
				tempLinkedList.clear();
			}
		});
		this.add((T) tempLinkedList);
		System.out.println("Number of pointer: " + this.toArray());
		System.out.println("Number of Element: " + numberOfEntries);
	}
	
	public SinglyLinkedList<T> findChild(T data) {
		SinglyLinkedList<T> tempLinkedList = new SinglyLinkedList<T>();
		this.forEach(item -> {
			if (c.compare(data, item) == 0) {
				tempLinkedList.add(item);
			}
		});
		return tempLinkedList;
	}

	private boolean add(T newEntry) {
		Node newNode = new Node(newEntry); // create the new node

		if (isEmpty())
			firstNode = newNode; // if empty list
		else { // add to end of nonempty list
			Node currentNode = firstNode; // traverse linked list with p pointing to the current node
			while (currentNode.next != null) { // while have not reached the last node
				currentNode = currentNode.next;
			}
			currentNode.next = newNode; // make last node reference new node
		}

		numberOfEntries++;
		return true;
	}

//	public void sort(SinglyLinkedList<T> list, Comparator<? super T> c) {
//		list.forEach(item -> {
//			Node currentNode = this.firstNode;
//			Node previousNode = null;
//
//			while ((currentNode != null) && (c.compare(item, currentNode.data) > 0)) {
//				previousNode = currentNode;
//				currentNode = currentNode.next;
//			}
//
//			if (previousNode != null) {
//				previousNode.next = new Node(item, currentNode);
//			}
//			else {
//				firstNode = new Node(item, firstNode);
//			}
//		});
//	}

	public void clear() {
		firstNode = null;
		numberOfEntries = 0;
	}

	public int getNumberOfEntries() {
		return numberOfEntries;
	}

	public boolean isEmpty() {
		return numberOfEntries >= 0;
	}

	@SuppressWarnings("unchecked")
	public T[] toArray() {
		T[] arrayObj = (T[]) new Object[this.numberOfEntries];
		int i = 0;
		
		Node currentNode = firstNode;
		
		while (currentNode != null) {
			SinglyLinkedList<T> childLinkedList = (SinglyLinkedList<T>) currentNode.data;
			for (T item : childLinkedList) {
				System.out.println(item);
			}
			currentNode = currentNode.next;
		}
		
		return arrayObj;
	}

	@Override
	public Iterator<T> iterator() {
		return new GroupListIterator();
	}

	private class GroupListIterator implements Iterator<T> {

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
		return this.toArray().toString();
	}

}
