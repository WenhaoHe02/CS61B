package deque;


import java.util.Iterator;


/**
 * ClassName : LinkedListDeque
 * Package:deque
 * Description:
 *
 * @Author JS123748 何文昊
 * @Create 2023/10/21 16:19
 * @Version Latest
 */
public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private Node sentinel;
    private int size;
    
    private class Node {
        
        private T item;
        private Node prev;
        private Node next;
        
        Node(T value, Node prev, Node next) {
            this.item = value;
            this.next = next;
            this.prev = prev;
        }
        
        public T getRecur(int index) {
            if (size == 0) {
                System.out.println("error!NoNode");
                return null;
            }
            if (index < 0) {
                System.out.println("error!InvalidIndex");
                return null;
            }
            if (index >= size) {
                System.out.println("error!OutOfSize");
                return null;
            }
            if (index > 0) {
                return next.getRecur(index - 1);
            }
            return this.item;
        }
        
    }
    
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }
    
    private class LinkedListDequeIterator implements Iterator<T> {
        private Node position;
        
        LinkedListDequeIterator() {
            position = sentinel.next;
        }
        
        @Override
        public boolean hasNext() {
            if (size == 0) {
                return false;
            }
            if (position != sentinel) {
                return true;
            }
            return false;
        }
        
        @Override
        public T next() {
            T returnValue = position.item;
            position = position.next;
            return returnValue;
        }
    }
    
    public void addFirst(T x) {
        if (sentinel.next == null) {
            Node node = new Node(x, sentinel, sentinel);
            sentinel.next = node;
            sentinel.prev = node;
            size++;
            return;
        }
        
        Node node = new Node(x, sentinel, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        size++;
    }
    
    public void addLast(T x) {
        if (sentinel.prev == null) {
            Node node = new Node(x, sentinel, sentinel);
            sentinel.next = node;
            sentinel.prev = node;
            size++;
            return;
        }
        Node node = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
        size++;
    }
    
    public T removeFirst() {
        if (size == 0) {
            System.out.println("error!NoNode");
            return null;
        } else if (sentinel.next == sentinel.prev) {
            T returnValue = sentinel.next.item;
            sentinel.prev = null;
            sentinel.next = null;
            size--;
            return returnValue;
        }
        T returnValue = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return returnValue;
    }
    
    public T removeLast() {
        if (size == 0) {
            System.out.println("error!NoNode");
            return null;
        } else if (sentinel.prev == sentinel.next) {
            T returnValue = sentinel.next.item;
            sentinel.next = null;
            sentinel.prev = null;
            size--;
            return returnValue;
        }
        T returnValue = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return returnValue;
    }
    
    public T get(int i) {
        if (size == 0) {
            System.out.println("error!NoNode");
            return null;
        }
        if (i >= size) {
            System.out.println("error!OutOfSize");
            return null;
        }
        Iterator it = new LinkedListDequeIterator();
        for (int times = 0; times < i; times++) {
            it.next();
        }
        return (T) it.next();
    }
    
    public int size() {
        return size;
    }
    
    public T getRecursive(int index) {
        return sentinel.next.getRecur(index);
    }
    
    public void printDeque() {
        Iterator it = new LinkedListDequeIterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }
    @Override
    public boolean equals(Object list) {
        if (this == list) {
            return true;
        }
        if (list == null) {
            return false;
        }
        if (!(list instanceof Deque)) {
            return false;
        }
        Deque<T> myList = (Deque<T>) list;
        if (this.size != myList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(myList.get(i))) {
                return false;
            }
        }
        return true;
    }
}

