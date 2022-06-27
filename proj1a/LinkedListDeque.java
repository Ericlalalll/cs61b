public class LinkedListDeque<T> {
    // inner class node
    public class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        // constructor for sentinel node
        public Node(Node prev, Node next) {
            this.prev = prev;
            this.next = next;
        }
    } // end of the inner class

    private Node sentinel; // sentinel node stored by deque.
    private int size;

    /* Invarits:
    1. the first real item is always being sentinel.next.item
    2. the last real item is always being sentinel.prev.item
    3. the last real item.next is always being sentinel in circular list
    */
    public LinkedListDeque() {
        sentinel = new Node(null, null); // Empty list
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public boolean isEmptey() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        Node node = new Node(item, sentinel, sentinel.next); // sentinel.next is the previous first item in the list
        sentinel.next.prev = node;
        sentinel.next = node;
        size++;
    }

    public void addlast(T item) {
        Node node = new Node(item, sentinel.prev, sentinel); // based on circular list
        sentinel.prev.next = node;
        sentinel.prev = node;
        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T res = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T res = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return res;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node temp = sentinel;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return RecursiveHelper(sentinel.next, index);
    }

    private T RecursiveHelper(Node start, int index) {
        if (index == 0) {
            return start.item;
        }
        return RecursiveHelper(start.next, index - 1);
    }

    public void printDeque() {
        Node temp = sentinel.next;
        while (temp != sentinel) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        list.addFirst(14);
        list.addlast(18);
        list.addFirst(10);
        list.addlast(20);
        list.removeFirst();
        list.removeLast();
        list.addlast(100);
        list.getRecursive(2);
        list.printDeque();

    }
}
