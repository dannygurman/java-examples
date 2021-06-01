package algorithms.tree.binary.traversal.inorder;

import algorithms.tree.binary.common.model.Node;
import algorithms.tree.binary.traversal.TreeTraversal;

import java.util.List;
import java.util.Stack;

public class InOrderTraversalStack extends TreeTraversal {
        /* left root right       4        2        5        1        3*/

    protected  void traverseTreeInternal(List<Node> list, Node node) {

        //Stack for the nodes
        Stack<Node> stack = new Stack<Node>();

        while (node != null || !stack.isEmpty()) {

            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                //add root
                node = stack.pop();
                list.add(node);

                node = node.right;
            }

        }
    }

}
