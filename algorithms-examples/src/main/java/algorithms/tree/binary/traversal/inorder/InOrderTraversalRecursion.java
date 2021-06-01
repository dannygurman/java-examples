package algorithms.tree.binary.traversal.inorder;

import algorithms.tree.binary.common.model.Node;
import algorithms.tree.binary.traversal.TreeTraversal;

import java.util.List;

public class InOrderTraversalRecursion extends TreeTraversal {

       /* 4
        2
        5
        1
        3*/

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
