package algorithms.tree.binary.findMaxOrMin;

import algorithms.tree.binary.BinaryTree;
import algorithms.tree.binary.Node;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FindRegularBinaryTreeMaxOrMin {


    public static int getMinElement(Node root) {
        BiFunction<Integer, Integer, Integer> compareFunction = Math::min;
        return getMinOrMaxElement(compareFunction, root);
    }

    public static int getMaxElement(Node root) {
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


    public static void main(String[] args) {
        BinaryTree tree = BinaryTree.buildExampleRegularBinaryTree();
        int min = getMinElement(tree.root);
        int max = getMaxElement(tree.root);
        System.out.println("Min:" + min + " Max:" + max);
    }
}
