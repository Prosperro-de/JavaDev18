package org.example;

public class TreeDemo {
    public static void main(String[] args) {
        CustomBinarySearchTree<Integer> binarySearchTree = new CustomBinarySearchTree<>();
        binarySearchTree.insert(5);
        binarySearchTree.insert(7);
        binarySearchTree.insert(3);
        binarySearchTree.insert(2);
        binarySearchTree.insert(9);

        System.out.println(binarySearchTree.contains(12));

        String s = "";
    }
}
