package com.company;

/******************************
 *  Binary tree class with generics
 *  Methods
 *  * Constructor()
 *  * add(K key, V value) : void
 *  * remove(K key) : boolean
 *  * find(K key) : Value
 *  * toString(boolean isForward, String separator) : String
 *  author: Denis Koptev
 ******************************/

public class BinaryTree<K extends Comparable<? super K>, V> {

    /******************************
     *  Internal node class
     ******************************/
    private static class Node<K,V> {
        private K key;
        private V value;
        private Node<K,V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }

    /* *
    * Tree-root private field
    * */
    private Node<K,V> root;

    /******************************
     *  User-accessible public methods
     ******************************/

    /* *
    * Constructor
    * */
    public BinaryTree() {
        root = null;
    }

    /* *
    * Add method
    * If node with the same key exists, value will be updated
    * */
    public void add(K key, V value) {
        Node<K,V> node = root, parent = null;
        int cmp = -1;

        while (node != null) {
            cmp = key.compareTo(node.key);
            parent = node;
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                node.value = value;
                return;
            }
        }

        Node<K,V> new_node = new Node<>(key, value);
        if (parent == null) {
            root = new_node;
        } else if (cmp < 0) {
            parent.left = new_node;
        } else {
            parent.right = new_node;
        }
    }

    /* *
    * Remove method
    * Return value: success of removing.
    * Node was not found - false
    * */
    public boolean remove(K key) {
        Node<K,V> node = root, parent = null;

        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp == 0) {
                break;
            } else {
                parent = node;
                if (cmp < 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }

        if (node == null) {
            return false;
        }

        if (node.right == null) {
            if (parent == null) {
                root = node.left;
            } else if (node == parent.left) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
        } else {
            Node<K,V> rightMin = node.right;
            parent = null;
            while (rightMin.left != null) {
                parent = rightMin;
                rightMin = rightMin.left;
            }
            if (parent != null) {
                parent.left = rightMin.right;
            } else {
                node.right = rightMin.right;
            }
            node.key = rightMin.key;
        }
        return true;
    }

    /* *
    * Find method
    * Return value: value-part of <key,value> pair
    * */
    public V find(K key) {
        Node<K,V> node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    /* *
    * toString public method
    * Uses recursive internal method
    * isForward - direction of output
    * */
    public String toString(boolean isForward, String separator) {
        StringBuilder str = new StringBuilder();
        toString(root, str, separator, isForward);
        return str.toString();
    }

    /******************************
     *  Internal methods
     ******************************/

    private void toString(Node<K,V> node, StringBuilder str,
                          String separator, boolean isForward) {
        if (node != null) {
            Node<K,V> first = (isForward ? node.left : node.right);
            Node<K,V> second = (isForward ? node.right : node.left);
            toString(first, str, separator, isForward);
            str
                .append("<")
                .append(node.key)
                .append(",")
                .append(node.value)
                .append(">")
                .append(separator);
            toString(second, str, separator, isForward);
        }
    }
}
