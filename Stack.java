public class Stack<E> implements StackInterface<E> {
	//  The stack is implemented using a linked list
	LinkedList<E> stack = new LinkedList<>();

    //  Empty constructor
    public Stack() {
    }

    //  Time complexity: O(1), returns the size
    public int size() {
        return stack.size();
    }

    //  Time complexity: O(1), returns true if queue is empty
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    //  Time complexity: O(1), adds an element to the top of the stack
    public void push(E element) {
        stack.add(element);
    }

    // Time complexity: O(1), removes an element from the top of the stack, returns the removed element
    public E pop() {
        return stack.remove(stack.tail());
    }

    //  Time complexity: O(1), returns the top element of the stack
    public E top() {
        return stack.tail();
    }

    //  Time complexity: O(n), loops through the queue (from 0 to tail) and prints each element
    public String toString() {
        return stack.toString();
    }
}
