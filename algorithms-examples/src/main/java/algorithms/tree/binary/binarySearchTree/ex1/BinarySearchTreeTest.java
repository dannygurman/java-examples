package algorithms.tree.binary.binarySearchTree.ex1;

import algorithms.tree.binary.Node;
import algorithms.tree.binary.binarySearchTree.BSTUtils;

/**
 * Created by dannyg on 1/8/2018.
 */
public class BinarySearchTreeTest {

    //Building tree complexity:
    //expected time is O(n log n).
    // The worst case is still O(n^2)

    public static void main(String[] args) {
        new BinarySearchTreeTest().run();
    }



    public void run() {
        System.out.println("Binary Tree Example");

        // build the simple tree from chapter 11.
        Node root = new Node(5);

        System.out.println("\nBuilding tree with root value " + root.value);
        BSTUtils.insert(root, 1);
        BSTUtils.insert(root, 3);
        BSTUtils.insert(root, 2);
        BSTUtils.insert(root, 4);
        BSTUtils.insert(root, 7);
        BSTUtils.insert(root, 8);
        BSTUtils.insert(root, 6);
        BSTUtils.insert(root, 3);
        BSTUtils.insert(root, 9);
        //Result:
       // Inserted 1 to left of 5
        //Inserted 3 to right of 1
        //Inserted 2 to left of 3
        //Inserted 4 to right of 3
        //Inserted 7 to right of 5
        //Inserted 8 to right of 7
        //Inserted 6 to left of 7
        //Inserted 9 to right of 8


        System.out.println("\n\nTraversing tree in order");
        printInOrder(root);

        //result:
        //Traversed 1
        //Traversed 2
       // Traversed 3
       // Traversed 4
       // Traversed 5
       // Traversed 6
       // Traversed 7
       // Traversed 8
       // Traversed 9

        System.out.println("\n\nTraversing tree front-to-back from location 7");
        printFrontToBack(root, 5);
        //result
        //Traversed 4
        //Traversed 3
        //Traversed 2
        //Traversed 1

        //Traversed 6
       // Traversed 7
        //Traversed 8
    }


    public void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.println("  Traversed " + node.value);
            printInOrder(node.right);
        }
    }

    /**
     * uses in-order traversal when the origin is less than the node's value
     *
     * uses reverse-order traversal when the origin is greater than the node's
     * order
     */
    public void printFrontToBack(Node node, int camera) {
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
        } else {

            printFrontToBack(node.left, camera);

            printFrontToBack(node.right, camera);
        }
    }

}
