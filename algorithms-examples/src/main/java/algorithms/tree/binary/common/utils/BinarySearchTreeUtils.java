package algorithms.tree.binary.common.utils;

import algorithms.tree.binary.common.model.BinaryTree;
import algorithms.tree.binary.common.model.Node;

import static algorithms.tree.binary.common.utils.BinaryTreeUtils.TreeValues;

//Methods for binary SEARCH tree
public class BinarySearchTreeUtils {


    public static BinaryTree buildExampleSearchBinaryTree() {
     /*             4

             3            20

        1	          5      100

              2
        */
        BinaryTree tree = new BinaryTree();
        for (TreeValues value : TreeValues.getMixedValues()) {
            tree.root = insert(tree.root, value.getValue());
        }
        return tree;
    }


    public static Node insert(Node root, int value) {
        root = insertRecursive(root, value);
        return root;
    }

    private static Node insertRecursive(Node current, int valueToAdd) {
        if (current == null) {
            current = new Node(valueToAdd);
        } else if (valueToAdd < current.value) {
            current.left = insertRecursive(current.left, valueToAdd);
        } else if (valueToAdd > current.value) {
            current.right = insertRecursive(current.right, valueToAdd);
        } else {
            // value already exists - do nothing
        }

        //Need to return - as java object reference are passed by value
        return current;
    }

    //Insert for Binary Search Tree
    public static Node insert_Ver2(Node root, Integer valueToAdd) {
        if (root == null) {
            root = new Node(valueToAdd);
        }
        insert_Ver2Internal(root, valueToAdd);
        return root;
    }

    //Insert for Binary Search Tree
    private static void insert_Ver2Internal(Node node, Integer value) {
        if (value < node.value) {
            if (node.left != null) {
                //recursion call
                insert_Ver2Internal(node.left, value);
            } else {
                System.out.println("  Inserted " + value + " to left of " + node.value);
                //New node
                node.left = new Node(value);
            }
        } else if (value > node.value) {
            if (node.right != null) {
                insert_Ver2Internal(node.right, value);
            } else {
                System.out.println("  Inserted " + value + " to right of " + node.value);
                //New node
                node.right = new Node(value);
            }
        }
    }

    public static void printFrontToBack(Node node, int camera) {
        if (node == null)
            return;
        if (node.value > camera) {
            // print in order
            printFrontToBack(node.left, camera);
            System.out.println("  Traversed " + node.value);
            printFrontToBack(node.right, camera);
        } else if (node.value < camera) {
            // print reverse order
            printFrontToBack(node.right, camera);
            System.out.println("  Traversed " + node.value);
            printFrontToBack(node.left, camera);
        } else {//Equal
            printFrontToBack(node.left, camera);
            System.out.println("  Traversed " + node.value);
            printFrontToBack(node.right, camera);
        }
    }


}
