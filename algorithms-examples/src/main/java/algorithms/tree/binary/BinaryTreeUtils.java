package algorithms.tree.binary;

import java.util.function.BiFunction;

//Utils for regular (NOT BST) binary tree
public class BinaryTreeUtils {

    public static final Integer MIN_VALUE = 1;
    public static final Integer MAX_VALUE = 100;

    // Root of Binary Tree
    public Node root;

    BinaryTreeUtils()    {
        root = null;
    }


    // see algorithms-examples\src\main\java\algorithms\tree\binary\example_binary_tree.gif
    public static BinaryTreeUtils buildExampleRegularBinaryTree(){

         //         1
       //     2            3
     //  4          5    6      7

        BinaryTreeUtils tree = new BinaryTreeUtils();
        tree.root = new Node(MIN_VALUE);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(20);
        tree.root.right.right = new Node(MAX_VALUE);

        return tree;
    }


    //----------------------------------------


    public static int getRegularBinaryTreeMinElement(Node root) {
        BiFunction<Integer, Integer, Integer> compareFunction = Math::min;
        return getMinOrMaxElement(compareFunction, root);
    }

    public static int getRegularBinaryTreeMaxElement(Node root) {
        BiFunction<Integer, Integer, Integer> compareFunction = Math::max;
        return getMinOrMaxElement(compareFunction, root);
    }

    private static int getMinOrMaxElement(BiFunction<Integer, Integer, Integer> compareFunction, Node root){
        //Check whether tree is empty
        if(root == null) {
            System.out.println("Tree is empty");
            return 0;
        }  else {
            return getMinOrMaxElementInternal(compareFunction, root);
        }
    }

    private static int getMinOrMaxElementInternal(BiFunction<Integer, Integer, Integer> compareFunction ,Node node){

        int leftMaxOrMin, rightMaxOrMin;
        //Max will store temp's data
        int maxOrMin = node.value;

        //It will find largest element in left subtree
        if(node.left != null){
            leftMaxOrMin = getMinOrMaxElementInternal(compareFunction, node.left);
            //Compare max with leftMax and store greater value into max
            maxOrMin = compareFunction.apply(maxOrMin, leftMaxOrMin);
        }

        //It will find largest element in right subtree
        if(node.right != null){
            rightMaxOrMin = getMinOrMaxElementInternal(compareFunction, node.right);
            //Compare max with rightMax and store greater value into max
            maxOrMin = compareFunction.apply(maxOrMin, rightMaxOrMin);
        }
        return maxOrMin;
    }

   //------------------------------------------


}
