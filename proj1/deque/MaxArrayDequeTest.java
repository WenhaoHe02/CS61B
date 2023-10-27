package deque;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest{
   
    @Test
    public void emptySizeTest(){
        ComparatorOfInteger c1 = new ComparatorOfInteger();
        MaxArrayDeque<Integer> m1 = new MaxArrayDeque<>(c1);
        assertEquals("should have the same value", null, m1.max());
        assertEquals("should have the same value", null, m1.max(c1));
        
        
        ComparatorOfDouble c2 = new ComparatorOfDouble();
        MaxArrayDeque<Double> m2 = new MaxArrayDeque<>(c2);
        assertEquals("should have the same value", null, m2.max());
        assertEquals("should have the same value", null, m2.max(c2));
     
    }
    @Test
    public void MaxTest(){
        ComparatorOfInteger c1 = new ComparatorOfInteger();
        MaxArrayDeque<Integer> m1 = new MaxArrayDeque<>(c1);
        for(int i = 0; i < 10; i++){
            m1.addLast(i);
        }
        assertEquals("should have the same value", 9, (int)m1.max());
        assertEquals("should have the same value", 9, (int)m1.max(c1));
        
        
        ComparatorOfDouble c2 = new ComparatorOfDouble();
        MaxArrayDeque<Double> m2 = new MaxArrayDeque<>(c2);
        for(int i = 0; i < 10; i++){
            m2.addLast((double)i );
        }
        assertEquals("should have the same value", 9.0, (double)m2.max(), 0.0);
        assertEquals("should have the same value", 9.0, (double)m2.max(c2), 0.0);
    }
}
