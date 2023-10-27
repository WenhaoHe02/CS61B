package deque;

import java.util.Comparator;

/**
 * ClassName : ComparatorOfDouble
 * Package:deque
 * Description:
 *
 * @Author 71122202 何文昊
 * @Create 2023/10/27 13:05
 * @Version Latest
 */
public class ComparatorOfDouble implements Comparator<Double> {
    @Override
    public int compare(Double x1, Double x2){
        if(x1- x2 > 0)return 1;
        else if(x1 - x2 == 0)return 0;
        else return -1;
    }
}

