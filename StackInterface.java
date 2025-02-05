//  The methods here were given to us in the slides
public interface StackInterface<E> {
    int size();
    boolean isEmpty();
    E top();
    void push(E element);
    E pop();
}
