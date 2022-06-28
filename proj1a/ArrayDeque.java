public class ArrayDeque<T> {
    /*
    * this arraylist is based on circular array.
    * invariants:
    * 1. use nextfirst to trace the index of front position
    * 2. use nextlast to trace the index of back position*/
    private T[] arr;
    private int size;
    private int nextFirst; // record the index where the addfirst() will go
    private int nextLast; // record the index where the addlast() will go
    private final int capacity = 8;


    public ArrayDeque(){
        arr = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // a helper method to keep track of the index of nextfirst
    private int minusOne(int index){
        return (index-1 + arr.length) % arr.length;
    }

    private int plusOne(int index){
        return (index+1) % arr.length;
    }

    private void checksize() {
        if (size == arr.length) {
            resize(arr.length * 2);
        }
        if (size < (arr.length / 4) && arr.length > 16) {
            resize(arr.length / 2);
        }
    }

    /* Invariants:
     * 1. the first element is always by the right of nextfirst
     * 2.the last element is always by the left of nextlast
     * 3.copying array always follows the order from the first element to the last element
     * */
    // if the list is full nextlast = nextfirst + 1, traverse elements all from plusone(nextfirst) to minusone(nextlast)
    private void resize(int capacity){
        T[] newarr = (T[]) new Object[capacity];
        int start = plusOne(nextFirst); // satrt is the index of the first real element
        //int end = minusOne(nextLast);
        for (int i = 0; i < this.size ; i++) {
            newarr[i] = arr[start];
            start = plusOne(start);
        }
        nextFirst = newarr.length - 1;
        nextLast = this.size;
        //newarr[nextLast] = arr[end];
        //nextLast = plusOne(nextLast);
        arr = newarr;
    }

    public void addFirst(T item){
        checksize();
        arr[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }


    public void addLast(T item){
        checksize();
        arr[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    private T getFirst(){
        return arr[plusOne(nextFirst)];
    }

    private T getLast(){
        return arr[minusOne(nextLast)];
    }

    public T removeFirst(){
        T temp = getFirst();
        nextFirst = plusOne(nextFirst);
        arr[nextFirst]= null;
        size--;
        checksize();
        return temp;
    }

    public T removeLast(){
        T temp = getLast();
        nextLast = minusOne(nextLast);
        arr[nextLast]= null;
        size--;
        checksize();
        return temp;
    }
  // Assuming that the input index is in the contex of normal array
    public T get(int index){
        if (index >= size){
            return null;
        }
        int start = plusOne(nextFirst);
        return arr[(start + index) % arr.length];

    }

    public void printDeque(){
        int temp = plusOne(nextFirst);
        for (int i = 0; i < size ; i++) {
            System.out.print(arr[temp] +"  ");
            temp = plusOne(temp);
        }
        System.out.println();
    }


}




