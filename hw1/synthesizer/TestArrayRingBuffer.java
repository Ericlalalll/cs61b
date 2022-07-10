package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(4);
        assertTrue(arb.isEmpty());
        arb.enqueue(33);
        arb.enqueue(44);
        arb.enqueue(62);
        arb.enqueue(-3);
        assertTrue(arb.isFull());
        assertEquals(33, (int) arb.peek());
        assertEquals(33, (int) arb.dequeue());
        assertEquals(44, (int) arb.peek());
        arb.enqueue(2);
        assertEquals(44, (int) arb.peek());


    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
