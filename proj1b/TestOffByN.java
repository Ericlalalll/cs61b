import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offByN;

    @Test
    public void testEqualChars(){
       offByN = new OffByN(3);
       assertTrue(offByN.equalChars('a', 'd'));
       assertTrue(offByN.equalChars('c', 'f'));
       assertFalse(offByN.equalChars('a', 'a'));


    }
}
