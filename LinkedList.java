public class LinkedList<E> {
	//  Nested node class for the linked list
	private class Node<E> {
		//  Node variables for the element, next node, and previous node
		E element;
		Node<E> next = null;
		Node<E> prev = null;

		//  Constructor with element parameter
		public Node(E element){
			this.element = element;
		}

		//  toString method for the node, returns the element as a string
		public String toString() {
			return element.toString();
		}

		//  Getters and setters for the node variables, all of which have O(1) time complexity
		public E getElement() {
			return element;
		}
		
		public Node<E> getNext() {
			return next;
		}
		
		public void setNext(Node<E> next) {
			this.next = next;
		}
		
		public Node<E> getPrev() {
			return prev;
		}
		
		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}
	}

	//  Linked list variables
	private Node<E> head = null;  //  Head of the linked list
	private Node<E> tail = null;  //  Tail of the linked list
	private int size = 0;  //  Size of the linked list

	//  Empty constructor
	public LinkedList() {
		
	}

	//  Add an element to the end of the linked list. Time complexity: O(1)
	public void add(E element) {
		Node<E> newNode = new Node<>(element);  //  Create a new node with the element
		if(isEmpty())  //  If the linked list is empty, set the head to the new node
			head = newNode;
		else {	//  Otherwise, set the next node of the tail to the new node
			tail.setNext(newNode);
		}
		size++;  //  Update the size
		newNode.setPrev(tail);  //  Set the previous node of the new node to the tail
		tail = newNode;  //  Set the tail to the new node
	}

	//  Remove an element from the linked list. Worst case time complexity: O(n), if removing from the head or tail, O(1).
	//  Currently in this program, we never go into the worst case scenario as we always remove from the head or tail.
	public E remove(E element) {
		if(isEmpty())  //  Cannot remove from an empty linked list, so null is returned
			return null;
		Node<E> removedNode;  //  Node to be removed

		//  If the element is at the head or tail, remove it
		if(head.getElement() == element) {
			removedNode = head;
			head = head.getNext();
		}
		else if(tail.getElement() == element) {
			removedNode = tail;
			tail = tail.getPrev();
		}

		//  Otherwise, loop through the linked list to find the element
		else {
			Node<E> current = head;  //  Start at the head
			while(current.getNext().getElement() != element) {  //  While the next node is not the element, keep going. Time complexity: O(n)
				current = current.getNext();
			}
			removedNode = current.getNext();  //  Set the removed node to the next node
			current.setNext(current.getNext().getNext());  //  Set the next node of the current node to the next node of the next node
			//  We let the garbage collector take care of the removed node
		}
		//  Decrease the size and return the removed element
		size--;
		return removedNode.getElement();
	}

	//  toString method for the linked list, returns the elements as a string
	public String toString() {
		if(isEmpty()) //  Can't print an empty list, so null is returned
			return null;
		String string = head.toString(); //  Begin with the head
		Node<E> current = head;
		//  Loop through the linked list and add the elements to the string. Time complexity: O(n)
		while(current.getNext() != null) {
			string = string + " " + current.getNext();
			current = current.getNext();
		}
		return string;
	}

	//  Getters for the linked list variables, all of which have O(1) time complexity
	public int size() {
		return size;
	}
	
	public E head() {
		return head.getElement();
	}
	
	public E tail() {
		return tail.getElement();
	}

	//  Check if the linked list is empty. Time complexity: O(1)
	public boolean isEmpty() {
		return size == 0;
	}
	

}