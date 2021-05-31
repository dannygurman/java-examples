package algorithms.tree.binary.traversal.inorder;

import algorithms.tree.binary.Node;
import algorithms.tree.binary.traversal.TraversalExample;

import java.util.List;

public class InOrderTraversalRecursion extends TraversalExample {

    public static void main(String[] args) {
        InOrderTraversalRecursion travereseExample = new InOrderTraversalRecursion();
        travereseExample.testTreeTraverese();
       /* 4
        2
        5
        1
        3*/
    }



    protected  void traverseTreeInternal(List <Node> list, Node node){

        Node rightNode = node.right;
        Node leftNode = node.left;

        if (leftNode != null){
            traverseTreeInternal( list, leftNode) ;
        }

        list.add(node);

        if (rightNode != null){
            traverseTreeInternal( list, rightNode) ;
        }

    }
}
