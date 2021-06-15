package algorithms.tree.binary.common.utils;

import algorithms.tree.binary.common.model.Node;

public class BinarySearchTreeUtils_2 {

    //Non static
    private Node previous; //global variable represent previous visited node  vp

    /*  1) Do In-Order Traversal of the given tree and store the result in a temp array.
        2) This method assumes that there are no duplicate values in the tree
        3) Check if the temp array is sorted in ascending order, if it is, then the tree is BST.
        Time Complexity: O(n)
        We can avoid the use of a Auxiliary Array.
        While doing In-Order traversal, we can keep track of previously visited node.
        If the value of the currently visited node is less than the previous value, then tree is not BST.
       */
    public boolean isBST_V4(Node root) {
        return isBST_V4Util(root);
    }

    /* Returns true if given search tree is binary
       search tree (efficient version) */
    private boolean isBST_V4Util(Node node) {
        // traverse the tree in inorder fashion and
        // keep a track of previous node
        if (node != null) {
            if (!isBST_V4Util(node.left)) {
                return false;
            }
            // allows only distinct values node
            if (previous != null && node.value <= previous.value) {
                return false;
            }
            previous = node;
            return isBST_V4Util(node.right);
        }
        return true;
    }
}
