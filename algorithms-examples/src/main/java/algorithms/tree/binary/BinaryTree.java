package algorithms.tree.binary;

public class BinaryTree {

    // Root of Binary Tree
    public Node root;

    BinaryTree()    {
        root = null;
    }


    // see algorithms-examples\src\main\java\algorithms\tree\binary\example_binary_tree.gif
    public static BinaryTree buildExampleRegularBinaryTree(){

         //         1
       //     2            3
     //  4          5    6      7

        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(4);
        tree.root.right.right = new Node(5);

        return tree;
    }
}
