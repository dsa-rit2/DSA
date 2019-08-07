package travelBug.library;

import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import travelBug.obj.ComparePrice;

public class SortedLinkedList<T extends Comparable<? super T>> implements SortedLinkListInterface<T>,Serializable {
	  private Node firstNode;
	  private Node lastNode;
	  private int length;

	  public SortedLinkedList() {
	    firstNode = null;
	    lastNode = null;
	    length = 0;
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
		  while(currentNode.next!=null) {
			 currentNode = currentNode.next;
		  }
		  lastNode = currentNode;
		  return lastNode.data;
	  }
	  private Node add(T newEntry, Node currNode)
	  {
	      if((currNode == null)||newEntry.compareTo(currNode.data)<=0)
	      {
	          currNode = new Node(newEntry, currNode);
	      }
	      else
	      {
	          Node nodeAfter = add(newEntry, currNode.next);
	          currNode.next = nodeAfter;
	      }
	      return currNode;
	  }
	  public boolean addAll(SinglyLinkedList<T> anEntry) {
		  for(int i = 1; i <= anEntry.getNumberOfEntries();i++) {
			  add((T)anEntry.getEntry(i));
		  }
		  return true;
	  }

	  public T getEntry(int givenPosition) {
	    T result = null;

	    if ((givenPosition >= 1) && (givenPosition <= length)) {
	      Node currentNode = firstNode;
	      for (int i = 1; i < givenPosition; ++i) {
	        currentNode = currentNode.next;		// advance currentNode to next node
	      }
	      result = currentNode.data;	// currentNode is pointing to the node at givenPosition
	    }

	    return result;
	  }

	  public boolean contains(T anEntry) {
	   	    
	    boolean found = false;
	    Node tempNode = firstNode;
	    int pos = 1;

	    while (!found && (tempNode != null)) {
	      if (anEntry.compareTo(tempNode.data) <= 0) {
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
	      if (givenPosition == 1) {      	// CASE 1: remove first entry
	        result = firstNode.data;     	// save entry to be removed 
	        firstNode = firstNode.next;		// update firstNode to point to the next node
	      } else {                         	// CASE 2: remove interior entry or last entry
	        Node nodeBefore = firstNode;
	        for (int i = 1; i < givenPosition - 1; ++i) {
	          nodeBefore = nodeBefore.next;		// advance nodeBefore to its next node
	        }
	        result = nodeBefore.next.data;  	// save entry to be removed	
	        nodeBefore.next = nodeBefore.next.next;	// make node before point to node after the 
	      } 															// one to be deleted (to disconnect node from chain)

	      length--;
	    }

	    return result;
	  }

	  public final void clear() {
	    firstNode = null;
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
	      outputStr += currentNode.data + "\n";;
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
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean remove(T anEntry) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int getPosition(T anEntry) {
		// TODO Auto-generated method stub
		return 0;
	}
}