package algorithms.tree.binary.common.utils;

import algorithms.tree.binary.common.model.BinaryTree;
import algorithms.tree.binary.common.model.Node;
import algorithms.tree.binary.common.model.TraversalType;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.BiFunction;

import static algorithms.tree.binary.common.utils.BinaryTreeUtils.TreeValues.*;

//Utils for regular (NOT BST) binary tree
public class BinaryTreeUtils {

    public static final Integer MIN_VALUE = 1;
    public static final Integer MAX_VALUE = 100;

    @Getter
    public enum TreeValues {
        A(MIN_VALUE),
        B(2),
        C(3),
        D(4),
        E(5),
        F(20),
        G(MAX_VALUE);

        TreeValues(int value) {
            this.value = value;
        }
        private int value;
    }

     // see algorithms-examples\src\main\java\algorithms\tree\binary\example_binary_tree.gif
    public static BinaryTree buildExampleRegularBinaryTree() {
        //         1(A)
        //     2 (B)                     3(C)
        //  4 (D)         5(E)    20(F)      G(100)

        BinaryTree tree = new BinaryTree();
        tree.root = new Node(A.value);
        tree.root.left = new Node(B.value);
        tree.root.right = new Node(C.value);
        tree.root.left.left = new Node(D.value);
        tree.root.left.right = new Node(E.value);
        tree.root.right.left = new Node(F.value);
        tree.root.right.right = new Node(G.value);

        return tree;
    }

    //---------------Traversal -------------------------
    public static List<Integer> traversalTree(BinaryTree tree, TraversalType traversalType) {
        return traversalType.getTraversalImplementor().travereseTree(tree);
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

    private static void deleteNode(Node root, Node nodeToDelete) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        Node temp;
        // Do level order traversal until last node (adding right the left)
        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp == nodeToDelete) {
                temp = null;
                return;
            }
            if (isNodeMatch(temp.right, q, nodeToDelete)) {
               //Removing reference
                temp.right = null;
                return;
            }
            if (isNodeMatch(temp.left, q, nodeToDelete)) {
                //Removing reference
                temp.left = null;
                return;
            }
        }
    }

    /**
     * @parm queue - adding currentNode to queue if not match (and not null
     * @return - Boolean - If found
     */
    private static boolean isNodeMatch(Node currentNode, Queue<Node> queue, Node nodeToDelete) {
        if (currentNode == null) {
            return false;
        }
        if (currentNode == nodeToDelete) {
            return true;
        } else
            queue.add(currentNode);
        return false;
    }



    /*    Function to delete given element in binary tree
          Algorithm :
        1. Starting at root, find the deepest and RIGHT MOST node in binary tree and node which we want to delete.
        2. Replace the deepest rightmost node’s data with node to be deleted.
        3. Then delete the deepest rightmost node.

        See deletion-in-binary-tree.png

        //Note: We can also replace node’s data that is to be deleted with any node whose left and right child
         points to NULL but we only use deepest node in order to maintain the BALANCE34  of a binary tree.

        */
    public static void deleteNodeByValue(Node root, int nodeToDeleteKey) {
        if (root == null)
            return;

        if ( ! hasChildren(root)) {
            if (root.value == nodeToDeleteKey) {
                root = null;
                return;
            } else
                return;
        }

        //Note: We can find nodeMatchingKey and deepesetNode in one traversal
        // on the tree but for readability we separate to 2 different methods.
        Node nodeMatchingKey = searchNodeByValue(root, nodeToDeleteKey);

        if (nodeMatchingKey != null) {

            Node deepesetNode = findDeepestRightMostNode( root);

            int x = deepesetNode.value;
            //delete deepest node
            //Note !  :setting  deepesetNode = null will NOT work - will only set deepeset Node reference to null  but parent node right/left reference will not be null.
            deleteNode(root, deepesetNode);

            nodeMatchingKey.value = x;
        }
    }

    public static boolean hasChildren(Node node) {
        return ((node.left != null) || (node.right != null));
    }

    //  // Do level order traversal until we find key and last node.
    public static Node findDeepestRightMostNode(Node root) {
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        Node temp = null;
        // Do LEVEL ORDER  traversal until
        // we find key and last node.
        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp.left != null) {
                q.add(temp.left);
            }
            if (temp.right != null) {
                q.add(temp.right);
            }
        }
        return temp;
    }

    public static Node searchNodeByValue(Node root, int searchValue) {
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        Node temp = null;
        // Do level order traversal until we find key
        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp.value == searchValue) {
                return temp;
            } else {
                if (temp.left != null) {
                    q.add(temp.left);
                }

                if (temp.right != null) {
                    q.add(temp.right);
                }
            }
        }
        return null; //Not found
    }

    public static class DeepestResult {
        public DeepestResult(int deepestValue, int deepestLevel) {
            this.deepestValue = deepestValue;
            this.deepestLevel = deepestLevel;
        }

        public int deepestValue;
        public int deepestLevel;
    }

    public static DeepestResult findDeepestRightMostNode_2(Node root) {
        return findDeepestInternal(root, 0, new DeepestResult(0, 0));
    }

    private static DeepestResult findDeepestInternal(Node node, int level, DeepestResult deepesetResult) {
        int deepestValue = deepesetResult.deepestValue;
        int deepestLevel = deepesetResult.deepestLevel;
        if (node != null) {
            level++;
            DeepestResult deepesetResultFound = findDeepestInternal(node.left, level, deepesetResult);
            deepestValue = deepesetResultFound.deepestValue;
            deepestLevel = deepesetResultFound.deepestLevel;

            if (level >= deepestLevel) {
                deepestValue = node.value;
                deepestLevel = level;
            }

            deepesetResultFound = findDeepestInternal(node.right, level, new DeepestResult(deepestValue, deepestLevel));
            deepestValue = deepesetResultFound.deepestValue;
            deepestLevel = deepesetResultFound.deepestLevel;
        }
        return new DeepestResult(deepestValue, deepestLevel);
    }


    /*function to insert element in binary tree */
    static public void insertInDeepestRightMost(Node root, int key) {
        Node temp = root;
        if (root == null) {
            root = new Node(key);
            return;
        }
        Queue<Node> q = new LinkedList<Node>();
        q.add(temp);
        // Do level order traversal until we find
        // an empty place.
        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp.left == null) {
                temp.left = new Node(key);
                break;
            } else {
                q.add(temp.left);
            }

            if (temp.right == null) {
                temp.right = new Node(key);
                break;
            } else {
                q.add(temp.right);
            }
        }
    }

}
