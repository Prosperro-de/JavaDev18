package org.example.tdd;

import java.util.Objects;

public class LinkedListTdd<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T element) {
        if (head == null) {
            head = tail = new Node<>(element);
            size++;
            return;
        }
        Node<T> temp = new Node<>(element);
        tail.next = temp;
        tail = temp;
        size++;
    }

    public T get(int index) {
//        if (index < 0 || index >= size ) {
//            throw new IndexOutOfBoundsException();
//        }
        Objects.checkIndex(index, size);

        if (index == 0) {
            return head.value;
        } else if (index == size - 1) {
            return tail.value;
        }
        Node<T> temp = head;
        int count = 0;
        while (count < index) {
            temp = temp.next;
        }
        return temp.value;
    }

    public int size() {
        return size;
    }


    static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}
