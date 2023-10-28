package deque;

import java.util.Comparator;

/**
 * ClassName : ComparatorOfInteger
 * Package:deque
 * Description:
 *
 * @Author 71122202 何文昊
 * @Create 2023/10/27 13:03
 * @Version Latest
 */
public class ComparatorOfInteger implements Comparator<Integer> {
    @Override
    public int compare(Integer x1, Integer x2) {
        return (x1 - x2);
    }
}
