package algorithms.tree.binary.common.utils;

import algorithms.tree.binary.common.model.BinaryTree;
import algorithms.tree.binary.common.model.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiFunction;

//Utils for regular (NOT BST) binary tree
public class BinaryTreeUtils {

    public static final Integer MIN_VALUE = 1;
    public static final Integer MAX_VALUE = 100;

     // see algorithms-examples\src\main\java\algorithms\tree\binary\example_binary_tree.gif
    public static BinaryTree buildExampleRegularBinaryTree() {
        //         1
        //     2            3
        //  4          5    6      7

        BinaryTree tree = new BinaryTree();
        tree.root = new Node(MIN_VALUE);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(20);
        tree.root.right.right = new Node(MAX_VALUE);

        return tree;
    }


    //---------------MIN MAX -------------------------


    public static int getRegularBinaryTreeMinElement(Node root) {
        BiFunction<Integer, Integer, Integer> compareFunction = Math::min;
        return getMinOrMaxElement(compareFunction, root);
    }

    public static int getRegularBinaryTreeMaxElement(Node root) {
        BiFunction<Integer, Integer, Integer> compareFunction = Math::max;
        return getMinOrMaxElement(compareFunction, root);
    }

    private static int getMinOrMaxElement(BiFunction<Integer, Integer, Integer> compareFunction, Node root) {
        //Check whether tree is empty
        if (root == null) {
            System.out.println("Tree is empty");
            return 0;
        } else {
            return getMinOrMaxElementInternal(compareFunction, root);
        }
    }

    private static int getMinOrMaxElementInternal(BiFunction<Integer, Integer, Integer> compareFunction, Node node) {

        int leftMaxOrMin, rightMaxOrMin;
        //Max will store temp's data
        int maxOrMin = node.value;

        //It will find largest element in left subtree
        if (node.left != null) {
            leftMaxOrMin = getMinOrMaxElementInternal(compareFunction, node.left);
            //Compare max with leftMax and store greater value into max
            maxOrMin = compareFunction.apply(maxOrMin, leftMaxOrMin);
        }

        //It will find largest element in right subtree
        if (node.right != null) {
            rightMaxOrMin = getMinOrMaxElementInternal(compareFunction, node.right);
            //Compare max with rightMax and store greater value into max
            maxOrMin = compareFunction.apply(maxOrMin, rightMaxOrMin);
        }
        return maxOrMin;
    }

    //------------------------------------------


    //------ DELETE
    // Function to delete deepest element in binary tree
    //Using queue instead of recursion
    // Assumption - the node  to delete is already the DEEPESET (last leaf)

    private static void deleteDeepest(Node root, Node nodeToDelete) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        Node temp;
        // Do level order traversal until last node (adding right the left)
        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp == nodeToDelete) {
                //we found it  -delete by setting null;
                temp = null;
                return;
            }

            //searching on right
            if (temp.right != null) {
                if (temp.right == nodeToDelete) {
                    //we found it  -delete by setting null;
                    temp.right = null;
                    return;
                } else
                    q.add(temp.right);
            }

            if (temp.left != null) {
                if (temp.left == nodeToDelete) {
                    //we found it  -delete by setting null;
                    temp.left = null;
                    return;
                } else
                    q.add(temp.left);
            }
        }

    }


    /*    Function to delete given element in binary tree
          Algorithm :
        1. Starting at root, find the deepest and RIGHT MOSET node in binary tree and node which we want to delete.
        2. Replace the deepest rightmost node’s data with node to be deleted.
        3. Then delete the deepest rightmost node.

        See deletion-in-binary-tree.png

        //Note: We can also replace node’s data that is to be deleted with any node whose left and right child
         points to NULL but we only use deepest node in order to maintain the BALANCE34  of a binary tree.

        */
    public static void deleteNodeByValue(Node root, int nodeToDeleteKey) {
        if (root == null)
            return;

        if (!hasChildren(root)) {
            if (root.value == nodeToDeleteKey) {
                root = null;
                return;
            } else
                return;
        }
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        Node temp = null, nodeMatchingKey = null;

        // Do level order traversal until
        // we find key and last node.
        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp.value == nodeToDeleteKey)
                nodeMatchingKey = temp;

            if (temp.left != null)
                q.add(temp.left);

            if (temp.right != null)
                q.add(temp.right);
        }

        Node deepesetNode = temp;

        if (nodeMatchingKey != null) {
            int x = deepesetNode.value;
            deleteDeepest(root, deepesetNode);
            nodeMatchingKey.value = x;
        }
    }

    public static boolean hasChildren(Node node) {
        return ((node.left != null) || (node.right != null));
    }

}
