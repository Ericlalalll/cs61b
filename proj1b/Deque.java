public interface Deque<T> {
    void addFirst (T item);
    T removeFirst ();
    void addLast (T item);
    T removeLast();
    int size();
    boolean isEmpty();
    T get(int index);
    void printDeque();

}
