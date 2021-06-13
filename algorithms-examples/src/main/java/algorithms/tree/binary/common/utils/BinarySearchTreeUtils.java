package algorithms.tree.binary.common.utils;

import algorithms.tree.binary.common.model.Node;

//Methods for binary SEARCH tree
public class BinarySearchTreeUtils {


    private Node addRecursive(Node current, int valueToAdd) {
        if (current == null) {
            return new Node(valueToAdd);
        }
        if (valueToAdd < current.value) {
            current.left = addRecursive(current.left, valueToAdd);
        } else if (valueToAdd > current.value) {
            current.right = addRecursive(current.right, valueToAdd);
        } else {
            // value already exists
            return current;
        }
        return current;
    }
}
