package algorithms.tree.binary.ex1;

import algorithms.tree.binary.Node;

/**
 * Created by dannyg on 1/8/2018.
 */
public class BinaryTreeTest {

    //Building tree complexity:
    //expected time is O(n log n).
    // The worst case is still O(n^2)

    public static void main(String[] args) {
        new BinaryTreeTest().run();
    }



    public void run() {
        System.out.println("Binary Tree Example");

        // build the simple tree from chapter 11.
        Node root = new Node(5);


        System.out.println("\nBuilding tree with root value " + root.value);
        insert(root, 1);
        insert(root, 3);
        insert(root, 2);
        insert(root, 4);
        insert(root, 7);
        insert(root, 8);
        insert(root, 6);
        insert(root, 3);
        insert(root, 9);

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


    public void insert(Node node, int value) {
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

    //recursion
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
