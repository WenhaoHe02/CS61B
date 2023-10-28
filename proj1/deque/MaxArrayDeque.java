package deque;

import java.util.Comparator;

/**
 * ClassName : MaxArrayDeque
 * Package:deque
 * Description:
 *
 * @Author JS123748 何文昊
 * @Create 2023/10/27 12:27
 * @Version Latest
 */
public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> dequeComparator;
    
    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.dequeComparator = c;
    }
    
    public T max() {
        if (size() == 0) {
            return null;
        }
        T max = this.get(0);
        for (int i = 0; i < size(); i++) {
            if (dequeComparator.compare(this.get(i), max) > 0) {
                max = this.get(i);
            }
        }
        return max;
    }
    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        T max = this.get(0);
        for (int i = 0; i < size(); i++) {
            if (c.compare(this.get(i), max) > 0) {
                max = this.get(i);
            }
        }
        return max;
    }
    
}

