package deque;

/**
 * ClassName : Deque
 * Package:deque
 * Description:
 *
 * @Author JS123748 何文昊
 * @Create 2023/10/27 14:47
 * @Version Latest
 */
public interface Deque<T> {
    void addFirst(T item);
    
    void addLast(T item);
    
    default boolean isEmpty() {
        return size() == 0;
    }
    
    int size();
    
    void printDeque();
    
    T removeFirst();
    
    T removeLast();
    
    T get(int index);
    
    boolean equals(Object o);
}
