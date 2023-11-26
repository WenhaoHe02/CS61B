package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>, Iterable<K> {
    private BSTNode root;
    private int size;
    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;
        public BSTNode(K key, V value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    
    public BSTMap() {
        root = null;
        size = 0;
    }
    
    @Override
    public void clear() {
        root = null;
        size = 0;
    }
    private boolean findInContain(BSTNode node, K key) {
        if (node == null) {
            return false;
        } else {
            if (key.compareTo(node.key) > 0) {
                return findInContain(node.right, key);
            } else if (key.compareTo(node.key) < 0) {
                return findInContain(node.left, key);
            } else {
                return true;
            }
        }
    }
    @Override
    public boolean containsKey(K key) {
        boolean returnValue = findInContain(root, key);
        return  returnValue;
    }
    private BSTNode findInGet(BSTNode node, K key) {
        if (node == null) {
            return null;
        } else {
            if (key.compareTo(node.key) > 0) {
                return findInGet(node.right, key);
            } else if (key.compareTo(node.key) < 0) {
                return findInGet(node.left, key);
            } else {
                return node;
            }
        }
    }
    @Override
    public V get(K key) {
        BSTNode bstNode = findInGet(root, key);
        if (bstNode != null) {
            return bstNode.value;
        }
        return null;
    
    }
    @Override
    public int size() {
        return size;
    }
    private BSTNode findInPut(BSTNode node, K key, V value) {
        if (node == null) {
            return new BSTNode(key, value, null, null);
        } else {
            if (key.compareTo(node.key) > 0) {
                node.right = findInPut(node.right, key, value);
            } else if (key.compareTo(node.key) < 0) {
                node.left = findInPut(node.left, key, value);
            } else {
                node.value = value;
            }
        }
        return node;
    }
    @Override
    public  void put(K key, V value) {
        if (containsKey(key)) {
            size += 1;
        }
        if (root == null) {
            root = new BSTNode(key, value, null, null);
            return;
        }
        findInPut(root, key, value);
    }
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        Iterator<K> it = iterator();
        while (it.hasNext()) {
            keySet.add(it.next());
        }
        return keySet;
    }
    private BSTNode findInRemove(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) > 0) {
            return findInRemove(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            return findInRemove(node.right, key);
        }
        return node;
    }
    private BSTNode findParentInRemove(BSTNode node, K key) {
       BSTNode parentNode = null;
        while (node != null) {
            if(node.key.compareTo(key) > 0) {
                parentNode = node;
                node = node.left;
            } else if (node.key.compareTo(key) < 0) {
                parentNode = node;
                node = node.right;
            } else {
                return parentNode;
            }
        }
        return node;
    }
    private boolean findIfLeftInRemove(BSTNode node, BSTNode parentNode, K key) {
        if (parentNode.left == null) {
            return false;
        }
        if (parentNode.left.key == key) {
            return true;
        }
        return false;
    }
    private BSTNode findRightNodeInLeftTree(BSTNode node) {
        node = node.left;
        while (node.right != null) {
            node = node.right;
        }
        return  node;
    }
    @Override
    public V remove(K key) {
       if (!containsKey(key)) {
           return null;
       }
       BSTNode node = findInRemove(root, key);
        V returnValue = get(key);
       if (node.left != null && node.right != null) {
           BSTNode replaceNode = findRightNodeInLeftTree(node);
           remove(replaceNode.key);
           node.key = replaceNode.key;
           node.value = replaceNode.value;
       } else if (node.left == null && node.right == null) {
           if (node == root) {
               root = null;
               size--;
               return returnValue;
           }
           BSTNode parentNode = findParentInRemove(root, key);
           Boolean isLeft = findIfLeftInRemove(node, parentNode, key);
           if (isLeft == true) {
               parentNode.left = null;
           } else {
               parentNode.right = null;
           }

       } else if (node.left == null && node.right != null){
           if (root == node) {
               root = node.right;
               size--;
               return returnValue;
           }
           BSTNode parentNode = findParentInRemove(root, key);
           Boolean isLeft = findIfLeftInRemove(node, parentNode, key);
           if (isLeft == true) {
               parentNode.left = node.right;
           } else {
               parentNode.right = node.right;
           }
       } else if (node.left != null && node.right == null) {
           if (root == node) {
               root = node.left;
               size--;
               return returnValue;
           }
           BSTNode parentNode = findParentInRemove(root, key);
           Boolean isLeft = findIfLeftInRemove(node, parentNode, key);
           if (isLeft == true) {
               parentNode.left = node.left;
           } else {
               parentNode.right = node.left;
           }
       }
       size--;
       return returnValue;
    }
    
    @Override
    public V remove(K key, V value) {
       BSTNode node = findInRemove(root, key);
       V nodeValue = node.value;
       if (nodeValue == value) {
           remove(key);
       }
       return value;
    }
    
    @Override
    public Iterator  iterator() {
        return new BSTMapIterator();
    }
    private class BSTMapIterator implements Iterator<K>{
        private Stack<BSTNode> stack;

        public BSTMapIterator() {
            stack = new Stack<>();
           pushLeft(root);
        }
        private void pushLeft(BSTNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        @Override
        public boolean hasNext() {
            if (!stack.empty()) {
                return true;
            }
            return false;
        }

        @Override
        public K next() {
           BSTNode node = stack.pop();
           pushLeft(node.right);
           return node.key;
        }
        
    }
    
    
}
