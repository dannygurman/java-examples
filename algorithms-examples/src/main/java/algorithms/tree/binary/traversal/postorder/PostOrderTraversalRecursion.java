package algorithms.tree.binary.traversal.postorder;

import algorithms.tree.binary.Node;
import algorithms.tree.binary.traversal.TraversalExample;
import algorithms.tree.binary.traversal.inorder.InOrderTraversalRecursion;

import java.util.List;

public class PostOrderTraversalRecursion extends TraversalExample {

    public static void main(String[] args) {
        PostOrderTraversalRecursion travereseExample = new PostOrderTraversalRecursion();
        travereseExample.testTreeTraverese();
       /*  (Left, Right, Root) : 4 5 2 3 1  */

    }



    protected  void traverseTreeInternal(List<Node> list, Node node){

        Node rightNode = node.right;
        Node leftNode = node.left;

        if (leftNode != null){
            traverseTreeInternal( list, leftNode) ;
        }


        if (rightNode != null){
            traverseTreeInternal( list, rightNode) ;
        }

        list.add(node);
    }
}
