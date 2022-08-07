import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue< Queue<Item>> ret = new Queue<>();
        for (Item item : items) {
            Queue<Item> tmp = new Queue<>();
            tmp.enqueue(item);
            ret.enqueue(tmp);
        }
        return ret;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> ret = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            ret.enqueue(getMin(q1, q2));
        }
        return ret;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        if (items.size() <= 1) {
            return items;
        }
        int mid = items.size() / 2;
        Queue<Item> left = new Queue<>();
        Queue<Item> right = new Queue<>();
        for (Item tmp : items) {
            if (mid > 0) {
                left.enqueue(tmp);
            } else {
                right.enqueue(tmp);
            }
            mid--;
        }


        Queue<Item> leftSorted = mergeSort(left);
        Queue<Item> rightSorted = mergeSort(right);
        Queue<Item> ret = mergeSortedQueues(leftSorted, rightSorted);
        return ret;
    }


    public static void main(String[] args) {
        Queue<String> uni = new Queue<>();
        uni.enqueue("MIT");
        uni.enqueue("UCB");
        uni.enqueue("Standford");
        uni.enqueue("CMU");
        uni.enqueue("Princetion");
        System.out.println("Original queue : " + uni);
        Queue<String> uniSorted = mergeSort(uni);
        System.out.println("Sorted queue : " + uniSorted);
        System.out.println(uni.size() == uniSorted.size());



        Queue<Integer> num = new Queue<>();
        num.enqueue(5);
        num.enqueue(9);
        num.enqueue(3);
        num.enqueue(7);
        num.enqueue(12);
        System.out.println("Original queue : " + num);
        Queue<Integer> numSorted = mergeSort(num);
        System.out.println("Sorted queue : " + numSorted);
        System.out.println(num.size() == numSorted.size());
    }
}
