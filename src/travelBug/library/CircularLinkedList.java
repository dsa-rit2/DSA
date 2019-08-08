package travelBug.library;

import javax.xml.soap.Node;

import org.omg.CORBA.PRIVATE_MEMBER;

public class CircularLinkedList <T> implements CircularLinkListInterface <T> {
	
	private Node firstNode;
    private int size;

    public CircularLinkedList() {

        this.firstNode = null;
        this.size = 0;
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
    

    @Override
    
    
//    public boolean remove(T anEntry) {
//
//        boolean success = false;
//
//        if (firstNode.data.equals(anEntry)) {
//
//            if (size == 1) {
//                firstNode = null;
//                size = 0;
//                success = true;
//            } else {
//                getLastNode().setNext(firstNode.next);
//                firstNode = firstNode.next;
//                success = true;
//                size--;
//            }
//
//        } else {
//            Node currentNode = firstNode;
//
//            do {
//                if (currentNode.next.data.equals(anEntry)) { // when the pointer der next = new Entry
//                    currentNode.setNext(currentNode.next.next);
//                    success = true;
//                    size--;
//                    break;
//
//                }
//                currentNode = currentNode.next;
//            } while (currentNode != firstNode);
//
//        }
//
//        return success;
//    }

    
    //@Override
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
    		
    		for (int i = 1; i < index-1; i++) {
    			currentNode = currentNode.next;//pointer loop
    		}
    		currentNode.next = currentNode.next.next;
    		size--;
    		return true;
    		
    	}else {
			
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
        }while (currentNode != firstNode);
        
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

    public int getSize() {
        return size;
    }
    
    
    public class Node<T> {
    
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


}
