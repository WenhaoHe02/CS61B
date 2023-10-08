package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */

public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> simple = new AListNoResizing<>();
        BuggyAList<Integer> complicated = new BuggyAList<>();
        for (int i = 3; i <= 5; ++i) {
            simple.addLast(i);
            complicated.addLast(i);
        }
        for (int i = 3; i <= 5; ++i) {
            simple.addLast(i);
            complicated.addLast(i);
        }
        for (int i = 3; i <= 5; ++i) {
            simple.removeLast();
            complicated.removeLast();
            
        }
        
        assertEquals(simple.size(), complicated.size());
        
        assertEquals(simple.removeLast(), complicated.removeLast());
        assertEquals(simple.removeLast(), complicated.removeLast());
        assertEquals(simple.removeLast(), complicated.removeLast());
        
    }
    
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();
        
        int N = 8000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
                System.out.println("L.addLast(" + randVal + ")");
                System.out.println("BL.addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("L.size: " + size);
                System.out.println("BL.size: " + size);
            } else if (operationNumber == 2) {
                if (L.size() > 0) {
                    L.getLast();
                    System.out.println("L.getLast(" + L.getLast() + ")");
                }else {System.out.println("collapse");}
                if (BL.size() > 0) {
                    BL.getLast();
                    System.out.println("BL.getLast(" + BL.getLast() + ")");
                } else {System.out.println("collapse");}
            } else if (operationNumber == 3) {
                if (L.size() > 0) {
                    int removeNumber = L.removeLast();
                    System.out.println("L.remove(" + removeNumber + ")");
                    
                }else {System.out.println("collapse");}
                if (BL.size() > 0) {
                    int removeNumber = BL.removeLast();
                    System.out.println("BL.getLast(" + removeNumber + ")");
                } else {System.out.println("collapse");}
            }
        }
    }
}

