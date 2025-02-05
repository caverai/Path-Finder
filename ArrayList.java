public class ArrayList<E> {
    //  Declare necessary variables
    private int capacity = 8; //  Initial capacity of the array
    private int size = 0;  //  Number of elements in the array
    private E[] array = (E[]) new Object[capacity];  //  Primitive array to store elements

    //  Empty constructor
    public ArrayList() {
    }

    //  Constructor with list parameter to copy the list (O(n) time complexity)
    public ArrayList(ArrayList<E> list) {
        for(int i=0; i<list.size(); i++) {
            add(list.get(i));
        }
    }

    //  Put an element at a specific position in the array, resize if necessary, update size variable as needed. Worst case time complexity: O(n) if resize is needed
    public void put(int position, E element) {
        while(position >= capacity) {
            resize();
        }
        array[position] = element;
        if(size < position+1) {
            size = position+1;
        }
    }

    //  Get an element from a specific position in the array. Time complexity: O(1)
    public E get(int position) {
        if(position >= capacity) {
            return null;
        } else {
            return array[position];
        }
    }

    //  Add an element to the end of the array, resize if necessary, update size variable as needed. Worst case time complexity: O(n) if resize is needed
    public void add(E element) {
        if(size == capacity) {
            resize();
        }
        array[size] = element;
        size++;
    }

    //  Find if an element is in the array. Worst case time complexity: O(n)
    public boolean contains(E element) {
        for(int i=0; i<size; i++) {
            if(array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    //  Resize the array by creating a new array with double the capacity, copying the elements, and updating the capacity variable. Time complexity: O(n)
    private void resize() {
        E[] newArray = (E[]) new Object[capacity*2];
        for(int i=0; i<capacity; i++) {
            newArray[i] = array[i];
        }
        capacity *= 2;
        array = newArray;
    }

    //  Override the toString method to print the array. Time complexity: O(n)
    public String toString() {
        String result = "";
        for(int i=0; i<size; i++) {
            result += array[i] + " ";
        }
        return result;
    }

    //  Get the size of the array. Time complexity: O(1)
    public int size() {
        return size;
    }

    //  Check if the array is empty. Time complexity: O(1)
    public boolean isEmpty() {
        return size == 0;
    }
}
