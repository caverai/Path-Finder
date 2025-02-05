//  The methods here were given to us in the slides
public interface QueueInterface<E> {
    int size();
    boolean isEmpty();
    E first();
    void enqueue(E e);
    E dequeue();
}