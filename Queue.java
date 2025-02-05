public class Queue<E> implements QueueInterface<E> {
    //  The queue is implemented using a linked list
	LinkedList<E> queue = new LinkedList<>();

    //  Empty constructor
    public Queue() {
    }

    //  Time complexity: O(1), returns the size
    public int size() {
        return queue.size();
    }

    //  Time complexity: O(1), returns true if queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    //  Time complexity: O(1), adds an element to the end of the queue
    public void enqueue(E element) {
        queue.add(element);
    }

    //  Time complexity: O(1), removes an element from the front of the queue, returns the removed element
    public E dequeue() {
        return queue.remove(queue.head());
    }

    //  Time complexity: O(1), returns the first element of the queue
    public E first() {
        return queue.head();
    }

    //  Time complexity: O(n), loops through the queue (from 0 to tail) and prints each element
    public String toString() {
        return queue.toString();
    }
}
