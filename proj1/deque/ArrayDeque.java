package deque;


import java.util.Iterator;

/**
 * ClassName : ArrayDeque
 * Package:deque
 * Description:
 *
 * @Author JS123748 何文昊
 * @Create 2023/10/22 14:10
 * @Version Latest
 */
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int capacity;
    private final int DESIZE_CAPACITY = 16;
    
    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        capacity = items.length;
    }
    
    public int size() {
        return size;
    }
    
    private void resize(int cap) {
        T[] copyArray = (T[]) new Object[cap];
        int position = 0;
        Iterator<T> it = new ArrayDequeIterator();
        while (it.hasNext()) {
            copyArray[position + 1] = it.next();
            position++;
        }
        nextFirst = 0;
        nextLast = size + 1;
        items = copyArray;
        capacity = cap;
    }
    
    public void printDeque() {
        for (T i : items) {
            System.out.println(i);
        }
    }
    
    public void addFirst(T value) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        items[nextFirst] = value;
        
        nextFirst = (nextFirst - 1 + capacity) % capacity;
        size++;
    }
    
    public void addLast(T value) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        items[nextLast] = value;
        nextLast = (nextLast + 1) % capacity;
        size++;
    }
    
    public T removeFirst() {
        if (size == 0) {
            System.out.println("error!NoItems");
            return null;
        }
        if (capacity >= DESIZE_CAPACITY && capacity / size >= 4) {
            resize(capacity / 2);
        }
        int position = (nextFirst + 1 + capacity) % capacity;
        T returnValue = items[position];
        items[position] = null;
        nextFirst = position;
        size--;
        return returnValue;
    }
    
    public T removeLast() {
        if (size == 0) {
            System.out.println("error!NoItems");
            return null;
        }
        if (capacity >= DESIZE_CAPACITY && capacity / size >= 4) {
            resize(capacity / 2);
        }
        int position = (nextLast - 1 + capacity) % capacity;
        T returnValue = items[position];
        items[position] = null;
        nextLast = position;
        size--;
        return returnValue;
    }
    
    public T get(int i) {
        if (i < 0) {
            System.out.println("invalid input!");
            return null;
        } else if (i > size) {
            System.out.println("OutOfRange");
            return null;
        }
        Iterator<T> it = new ArrayDequeIterator();
        for (int j = i; j > 0; j--) {
            it.next();
        }
        return it.next();
    }
    
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    
    private class ArrayDequeIterator implements Iterator<T> {
        private int itPosition;
        private int times;
        
        ArrayDequeIterator() {
            itPosition = (nextFirst + 1) % capacity;
            times = 0;
        }
        
        @Override
        public boolean hasNext() {
            if (size < capacity) {
                return itPosition != nextLast;
            } else {
                if (times == size && itPosition == nextLast) {
                    return false;
                }
                return true;
            }
            
            
        }
        
        @Override
        public T next() {
            times++;
            T returnValue = items[itPosition];
            itPosition = (itPosition + 1) % capacity;
            return returnValue;
        }
    }
    
    @Override
    public boolean equals(Object aDeque) {
        if (this == aDeque) {
            return true;
        }
        if (aDeque == null) {
            return false;
        }
        if (!(aDeque instanceof Deque)) {
            return false;
        }
        Deque<T> myDeque = (Deque<T>) aDeque;
        if (this.size != myDeque.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            
            if (!myDeque.get(i).equals(this.get(i))) {
                return false;
            }
        }
        return true;
    }
}
