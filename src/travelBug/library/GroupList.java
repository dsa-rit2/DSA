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
<<<<<<< HEAD
		sortedLinkedList.forEach(list -> {
			if (tempLinkedList.isEmpty() || (c.compare(list, tempLinkedList.getFirst())) == 0) {
				tempLinkedList.add(list);
			}
			else {
				this.add((E) tempLinkedList);
//				System.out.println(firstNode.data);
				tempLinkedList.clear();
				tempLinkedList.add(list);
//				System.out.println(headNode.data);
//				System.out.println(firstNode.next.data);
			}
			
		});
		System.out.print(headNode.data);
//		System.out.println("Number of Pointer: " + numberOfEntries);
		
=======
		
		for (T list : sortedLinkedList) {
			if (!tempLinkedList.isEmpty() && (c.compare(list, tempLinkedList.getFirst())) != 0) {
				this.addGroup((E) tempLinkedList);
				tempLinkedList = new SinglyLinkedList<T>();
			}
			tempLinkedList.add(list);
		}
		this.addGroup((E) tempLinkedList);
>>>>>>> branch 'master' of https://github.com/dsa-rit2/DSA.git
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

<<<<<<< HEAD
		if (isEmpty()) {
			headNode = newNode; // if empty list
		}
=======
		if (this.isEmpty())
			firstNode = newNode; // if empty list
>>>>>>> branch 'master' of https://github.com/dsa-rit2/DSA.git
		else { // add to end of nonempty list
<<<<<<< HEAD
			Node currentNode = headNode; // traverse linked list with p pointing to the current node
=======
			Node currentNode = firstNode;
>>>>>>> branch 'master' of https://github.com/dsa-rit2/DSA.git
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
<<<<<<< HEAD

		private Node currentNode = headNode;
=======
		private Node currentNode = firstNode;
>>>>>>> branch 'master' of https://github.com/dsa-rit2/DSA.git

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
