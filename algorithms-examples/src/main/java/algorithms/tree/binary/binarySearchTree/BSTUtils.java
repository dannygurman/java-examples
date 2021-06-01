package algorithms.tree.binary.binarySearchTree;

import algorithms.tree.binary.common.model.Node;

public class BSTUtils {



    //Insert for Binary Search Tree
    public static void insert(Node node, int value) {
        if (value < node.value) {
            if (node.left != null) {
                //recursion call
                insert(node.left, value);
            } else {
                System.out.println("  Inserted " + value + " to left of "  + node.value);
                //New node
                node.left = new Node(value);
            }
        } else if (value > node.value) {
            if (node.right != null) {
                insert(node.right, value);
            } else {
                System.out.println("  Inserted " + value + " to right of "  + node.value);
                //New node
                node.right = new Node(value);
            }
        }
    }
}
