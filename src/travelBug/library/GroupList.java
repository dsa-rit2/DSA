package travelBug.library;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class GroupList<T, E> implements GroupListInterface<T, E> {

	private Node headNode;
	private int numberOfEntries;
	private Comparator<? super T> c;

	private GroupList() {
		this.clear();
	}

	@SuppressWarnings("unchecked")
	public GroupList(SinglyLinkedList<T> listNode, Comparator<? super T> c) {
		this();
		this.c = c;

		SortedLinkedList<T> sortedLinkedList = new SortedLinkedList<T>(listNode, c);
		SinglyLinkedList<T> tempLinkedList = new SinglyLinkedList<T>();
		
		for (T list : sortedLinkedList) {
			if (!tempLinkedList.isEmpty() && (c.compare(list, tempLinkedList.getFirst())) != 0) {
				this.addGroup((E) tempLinkedList);
				tempLinkedList = new SinglyLinkedList<T>();
			}
			tempLinkedList.add(list);
		}
		this.addGroup((E) tempLinkedList);
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

	private boolean addGroup(E newEntry) {
		Node newNode = new Node(newEntry); // create the new node

		if (this.isEmpty())
			headNode = newNode; // if empty list
		else { // add to end of nonempty list
			Node currentNode = headNode;
			while (currentNode.next != null) { // while have not reached the last node
				currentNode = currentNode.next;
			}
			currentNode.next = newNode; // make last node reference new node
		}

		numberOfEntries++;
		return true;
	}

	public void clear() {
		headNode = null;
		numberOfEntries = 0;
	}

	public int getNumberOfEntries() {
		return numberOfEntries;
	}

	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new GroupListIterator();
	}

	private class GroupListIterator implements Iterator<E> {
		private Node currentNode = headNode;

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
