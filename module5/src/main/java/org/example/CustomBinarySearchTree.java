package org.example;

public class CustomBinarySearchTree<T extends Comparable<? super T>> {
    private Node<T> root;

    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(Node<T> root, T value) {
        if (root == null) {
            return false;
        }
        if (root.element.compareTo(value) > 0) { //go to left
            return contains(root.left, value);
        } else if (root.element.compareTo(value) < 0) { //go to right
            return contains(root.right, value);
        } else {
            return true;
        }
    }


    public void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
            return;
        }
        insert(root, value);
    }

    private void insert(Node<T> root, T value) {
        if (root.element.compareTo(value) > 0) {  // go to left
            if (root.left == null) {
                root.left = new Node<>(value);
            } else {
                insert(root.left, value);
            }
        } else if (root.element.compareTo(value) < 0) { // go to right
            if (root.right == null) {
                root.right = new Node<>(value);
            } else {
                insert(root.right, value);
            }
        }
    }


    static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;

        public Node(T element) {
            this.element = element;
        }
    }


}
