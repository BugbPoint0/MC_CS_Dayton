import java.util.Comparator;
import java.util.ListIterator;

/**
 * Creates a sorted double linked list
 * @author David Dayton
 * @param <T> generic type
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

	Comparator<T> comparator; // the comparator that is going to be used to sort this double linked list
	
	public SortedDoubleLinkedList(Comparator<T> comparator2) {
		head = null;
		tail = null;
		listSize = 0;
		comparator = comparator2;
	}
	
	/**
	 * Adds a node to the SortedDoubleLinkedList and puts it in the correct order based on the given comparator 
	 * @param data The data that should be added to the sorted array
	 * @return Current object
	 */
	public SortedDoubleLinkedList<T> add(T data) {
		Node node = new Node(data); 
		Node nodeCheck = head; 
		
		if (head == null) {
			head = node;
			tail = node;
		} else if (comparator.compare(nodeCheck.data, node.data) > 0) {
			node.next = head;
			head.prev = node;
			head = node;
		} else if (comparator.compare(tail.data, node.data) < 0) {
			tail.next = node;
			node.prev = tail;
			tail = node;
		} else { 
			while (nodeCheck.next != null && comparator.compare(nodeCheck.next.data, node.data) < 0) {
				nodeCheck = nodeCheck.next;
			}
			node.next = nodeCheck.next;
			node.next.prev = node;
			nodeCheck.next = node;
			node.prev = nodeCheck;
		}	
		listSize++;
		return this;
	}
	
	@Override
	public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public ListIterator<T> iterator() {
		return super.iterator();
	}

	@Override
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
		super.remove(data, comparator);
		return this;
	}
	
	
}
