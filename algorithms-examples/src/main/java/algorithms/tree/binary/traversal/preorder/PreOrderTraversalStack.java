package algorithms.tree.binary.traversal.preorder;

import algorithms.tree.binary.Node;
import algorithms.tree.binary.traversal.TraversalExample;

import java.util.List;
import java.util.Stack;

public class PreOrderTraversalStack extends TraversalExample {

   // https://www.geeksforgeeks.org/iterative-preorder-traversal/

    public static void main(String[] args) {
        PreOrderTraversalStack travereseExample = new PreOrderTraversalStack();
        travereseExample.testTreeTraverese();
       /* Preorder (Root, Left, Right) : 1 2 4 5 3*/

    }

    protected  void traverseTreeInternal(List<Node> list, Node node) {

        // Create an empty stack and push root to it
        Stack<Node> stack = new Stack<Node>();

        //Base case
        if (node == null) {
            return;
        }

        stack.push(node);

        while ( !  stack.isEmpty()){
            node = stack.pop();
            list.add(node);

            // Push right and left children of the popped node to stack
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

    }
}
