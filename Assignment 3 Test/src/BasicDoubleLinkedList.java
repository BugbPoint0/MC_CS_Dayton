import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Creates a basic double linked list
 * @author David Dayton
 * @param <T> generic type
 */
public class BasicDoubleLinkedList<T> implements Iterable<T>{
	
	Node head; // the first node in the linked list (prev will point to null)
	Node tail; // the last node in the linked list (next will point to null)
	int listSize; // tracks the size of the double linked list
	
	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
		listSize = 0;
	}
	
	/**
	 * Gets the size of the BasicDoubleLinkedList
	 * @return The size of the BasicDoubleLinkedList 
	 */
	public int getSize() {
	
		return listSize; 
	}
	
	/**
	 * Adds a node to the end of the BasicDoubleLinkedList
	 * @param data: The data stored within the node
	 * @return: Reference to current object 
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		Node node = new Node(data);
		
		if (tail == null) {
			head = node;
			tail = node;
		} else {
			tail.next = node;
			node.prev = tail; 
			tail = node;
		}
		
		listSize++;
		
		return this; 
	}
	
	/**
	 * Adds a node to the front of the BasicDoubleLinkedList
	 * @param data: The data stored within the node
	 * @return: Reference to current object 
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {
		
		Node node = new Node(data);
		
		if (head == null) {
			head = node;
			tail = node;
		} else {
			head.prev = node;
			node.next = head;
			head = node;
		}
		
		listSize++;
		
		return this; 
	}
	
	/**
	 * Gets the data from the first node in the BasicDoubleLinkedList
	 * @return: The data stored in the first node or head
	 */
	public T getFirst() {
		if (head == null) {
			return null;
		} else {
			return head.data;
		}
	}
	
	/**
	 * Gets the data from the last node in the BasicDoubleLinkedList
	 * @return: The data stored in the last node or tail
	 */
	public T getLast() {
		if (tail == null) {
			return null;
		} else {
			return tail.data;
		}
	}
	
	/**
	 * Removes the node from the BasicDoubleLinkedList that contains the data you passed into this function
	 * @param targetData: The data we want to remove
	 * @param comparator: Allows us to search within the linked list to compare nodes
	 * @return Current Object
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		
		Node node = head;
		
		// check to see if the node is the head and if so remove it
		if (comparator.compare(targetData, node.data) == 0) {
			if (getSize() == 1) {
				head = null;
				tail = null;
				listSize--;
			} else {
				head = node.next;
				head.prev = null;
				listSize--;
			}
			
		}
		
		while (node.next != null) {
			node = node.next;
			if (comparator.compare(targetData, node.data) == 0) {
				// check to see if the node is the head and if so remove it
				if (comparator.compare(node.data, tail.data) == 0) {
					if (getSize() == 1) {
						head = null;
						tail = null;
						listSize--;
					} else {
						tail = node.prev;
						tail.next = null;
						listSize--;
					}					
					break;
				}
				// removes node if it is in the middle
				Node nodePrev = node.prev;
				Node nodeNext = node.next;
				node = null;
				nodePrev.next = nodeNext;
				nodeNext.prev = nodePrev;
				listSize--;
				break;
			}
		}
		 
		return this; 
	}
	
	/**
	 * Gets head's data then removes head 
	 * @return if head is null return null, else return the data from head 
	 */
	public T retrieveFirstElement() {
		Node node = head;
		
		if (head == null) {
			return null;
		} else {
			T data = head.data;
			head = node.next;
			head.prev = null;
			listSize--;
			return data;
		} 
	}

	/**
	 * Gets tails's data then removes head 
	 * @return if tail is null return null, else return the data from tail 
	 */
	public T retrieveLastElement() {
		Node node = tail;
		
		if (tail == null) {
			return null;
		} else {
			T data = tail.data;
			tail = node.prev;
			tail.next = null;
			listSize--;
			return data;
		} 
	}
	
	/**
	 * Turns the BasicDoubleLinkedList into an ArrayList
	 * @return the ArrayList that now holds all the data from the BasicDoubleLinkedList
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> a = new ArrayList<T>();
		Node node = head;
		
		while (node != null) {
			a.add(node.data);
			node = node.next;
		}
		
		return a;
	}
	
	/**
	 * Creates a reference to a ListIterator
	 * @Override
	 * @return A listIterator reference
	 * @throws UnsupportedOperationException
	 * @throws NoSuchElementException
	 */
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
		
		return new Iterate(); 
	}
	
	/**
	 * @author David Dayton
	 * This class creates the nodes that store the data for the double linked list
	 */
	class Node {
		T data;
		Node next;
		Node prev;
		Node (T data) {
			this.data = data;
		}
	}
	
	/**
	 * @author David Dayton
	 * Creates the iterator that is used to move through the doubleLinkedList
	 */
	class Iterate implements ListIterator<T> {	

		Node node; // the current node
		Node nodePrev; // the previous node
		
		Iterate() {
			node = head;
		}
		
		
		@Override
		public T next() throws NoSuchElementException {
			if (node != null) {
				T data = node.data;
				nodePrev = node;
				node = node.next;
				return data;
			} else {					
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public T previous() throws NoSuchElementException {
			if (nodePrev != null) {
				T data = nodePrev.data;
				node = nodePrev;
				nodePrev = node.prev;
				return data;
			} else {
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public boolean hasNext() {
			if (node != null) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public boolean hasPrevious() {
			if (nodePrev != null) {
				return true;
			} else {
				return false;
			} 
		}

		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(T arg0) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			 throw new UnsupportedOperationException();
		} 
	
		@Override
		public void set(T arg0) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}	
	}
	
	
	
}

