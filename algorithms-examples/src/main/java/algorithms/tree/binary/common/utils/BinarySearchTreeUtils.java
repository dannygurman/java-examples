package algorithms.tree.binary.common.utils;

import algorithms.tree.binary.common.model.BinaryTree;
import algorithms.tree.binary.common.model.Node;

import java.util.function.BiFunction;

import static algorithms.tree.binary.common.utils.BinaryTreeUtils.TreeValues;

//Methods for binary SEARCH tree
public class BinarySearchTreeUtils {


    public static BinaryTree buildExampleSearchBinaryTree() {
        //TREE:
   /*             4(D)

             3(C)           20(F)

        1(A)	          5(E)       100(G)

              2(B)
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

    //DELETE

    public static void delete(Node root, int value) {
        deleteRecursive(root, value);
    }

    private static Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (value == current.value) {
            // Node to delete found
            return deleteNodeInternal(current);
        }

        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        } else {
            current.right = deleteRecursive(current.right, value);
        }
        return current;
    }

    private static Node deleteNodeInternal(Node nodeToDelete) {
        if (nodeToDelete.left == null && nodeToDelete.right == null) {
            //a node has no children-we just need to replace this node with null in its parent node
            return null;
        }
        //a node has exactly one child – in the parent node, we replace this node with its only child.
        //We're returning the non-null child so it can be assigned to the parent node
        if (nodeToDelete.right == null) {
            return nodeToDelete.left;
        }
        if (nodeToDelete.left == null) {
            return nodeToDelete.right;
        }
        //Both childs exists - requires a tree reorganization
        //First, we need to find the node that will replace the deleted node.
        // We'll use the smallest node of the soon to be deleted node's right sub-tree:
        int smallestValue = findBinarySearchTreeMinElement(nodeToDelete.right);
        //Then we assign the smallest value to the node to delete, and after that, we'll delete it from the right sub-tree:
        nodeToDelete.value = smallestValue;
        //And after that, we'll delete smallestValue from the right sub-tree:
        nodeToDelete.right = deleteRecursive(nodeToDelete.right, smallestValue);
        return nodeToDelete;
    }


    //--------  printFrontToBack
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

    //---------------MIN MAX -------------------------
    public static int findBinarySearchTreeMinElement(Node root) {
        if (root.left == null) {
            return root.value;
        } else {
            return findBinarySearchTreeMinElement(root.left);
        }
    }

    public static int findBinarySearchTreeMaxElement(Node root) {
        if (root.right == null) {
            return root.value;
        } else {
            return findBinarySearchTreeMaxElement(root.right);
        }
    }

    ///-----------------Check if  binary is search tree

    boolean isBST_WRONG(Node node) {
        //This approach is WRONG as this will return true for below binary tree (and below tree is not a
        // BST because 4 is in left subtree of 3)
        //         3
        //  2                  5
        //1     4
        if (node == null) {
            return true;
        }
        /* False if left is > than node */
        if (node.left != null && node.left.value > node.value)
            return false;
        /* False if right is < than node */
        if (node.right != null && node.right.value < node.value)
            return false;
        /* False if, recursively, the left or right is not a BST */
        if (!isBST_WRONG(node.left) || !isBST_WRONG(node.right))
            return false;
        /* Passing all that, it's a BST */
        return true;
    }


    public static boolean isBST_V1(Node node) {
        //Correct but NOT  efficient - since it traverses over some parts of the tree many times
        if (node == null) {
            return true;
        }
        /* false if the max of the left is >= than us */
        if (node.left != null && (findBinarySearchTreeMaxElement(node.left) >= node.value)) {
            return false;
        }
        /* false if the min of the right is <= than us */
        if (node.right != null && (findBinarySearchTreeMinElement(node.right) <= node.value)) {
            return false;
        }
        /* false if, recursively, the left or right is NOT  a BST */
        if (!isBST_V1(node.left) || !isBST_V1(node.right)) {
            return false;
        }
        /* passing all that, it's a BST */
        return true;
    }

    public static boolean isBST_V2(Node root) {
        // The initial values for min and max should be INT_MIN and INT_MAX — they narrow from there.
        //Note!!: This method is not applicable if there are duplicate elements with value INT_MIN or INT_MAX.
        //Time Complexity: O(n)
        //Auxiliary Space: O(1) if Function Call Stack size is not considered, otherwise O(n)
        return isBSTV2Util(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /* Returns true if the given tree is a BST and itsvalues are >= min and <= max.*
    * Traverses down the tree keeping track of the narrowing min and max allowed values as it goes,
    * looking at each node only once.
    *
    * */
    private static boolean isBSTV2Util(Node node, int min, int max)  {
        /* an empty tree is BST */
        if (node == null)
            return true;
        /* false if this node violates the min/max constraints */
        if (node.value< min || node.value> max) {
            return false;
        }
        /* otherwise check the subtrees recursively tightening the min/max constraints */
        // Allow only distinct values
        return (isBSTV2Util(node.left, min, node.value-1) && isBSTV2Util(node.right, node.value+1, max));
    }


    public static boolean isBST_V3(Node root) {
       // Note!!: This method is not applicable if there are duplicate elements with value INT_MIN or INT_MAX.
        return isBST_V3Util(root, null, null);
    }

    private static boolean isBST_V3Util(Node node, Node l, Node r) {
        // Base condition{
        if (node == null) {
            return true;
        }
        // if left node exist then check it has correct data or not -i.e. left node's data should be less than root's data
        if (l != null && node.value <= l.value) {
            return false;
        }
        // if right node exist then check it has correct data or not -i.e. right node's data  should be greater than root's data
        if (r != null && node.value >= r.value) {
            return false;
        }
        // check recursively for every node.
        return (isBST_V3Util(node.left, l, node) && isBST_V3Util(node.right, node, r));
    }

}
