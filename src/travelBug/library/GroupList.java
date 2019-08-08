package travelBug.library;

import java.awt.event.ItemEvent;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class GroupList<T, E> implements GroupListInterface<T, E> {

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
			}
			else {
				this.add((E) tempLinkedList);
				tempLinkedList.clear();
				tempLinkedList.add(list);
				System.out.println("Element: " + firstNode.data);
			}
			
		});
		this.add((E) tempLinkedList);
		
		System.out.println("Number of Pointer: " + numberOfEntries);
		
	}
	
	public SinglyLinkedList<T> findChild(T data) {
		SinglyLinkedList<T> tempLinkedList = new SinglyLinkedList<T>();
//		this.forEach(item -> {
//			if (c.compare(data, item) == 0) {
//				tempLinkedList.add(item);
//			}
//		});
		return tempLinkedList;
	}

	private boolean add(E newEntry) {
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
		return numberOfEntries <= 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new GroupListIterator();
	}

	private class GroupListIterator implements Iterator<E> {

		private Node currentNode = firstNode;

		@Override
		public boolean hasNext() {
			return currentNode != null;
		}

		@Override
		public E next() {
			if (hasNext()) {
				E returnData = currentNode.data;
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

		private E data;
		private Node next;

		private Node(E data) {
			this.data = data;
			this.next = null;
		}

		private Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
}
