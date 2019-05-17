package algorithms.tree.binary.traversal.preorder;

import algorithms.tree.binary.Node;
import algorithms.tree.binary.traversal.TraversalExample;
import algorithms.tree.binary.traversal.postorder.PostOrderTraversalRecursion;

import java.util.List;

public class PreOrderTraversalRecursion extends TraversalExample {
    public static void main(String[] args) {
        PreOrderTraversalRecursion travereseExample = new PreOrderTraversalRecursion();
        travereseExample.testTreeTraverese();
       /* Preorder (Root, Left, Right) : 1 2 4 5 3*/

    }



    protected  void traverseTreeInternal(List<Node> list, Node node){

        Node rightNode = node.right;
        Node leftNode = node.left;

        list.add(node);

        if (leftNode != null){
            traverseTreeInternal( list, leftNode) ;
        }


        if (rightNode != null){
            traverseTreeInternal( list, rightNode) ;
        }

    }

}
