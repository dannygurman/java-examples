package algorithms.tree.binary.common.model;

import algorithms.tree.binary.traversal.TreeTraversal;
import algorithms.tree.binary.traversal.inorder.InOrderTraversalRecursion;
import algorithms.tree.binary.traversal.inorder.InOrderTraversalStack;
import algorithms.tree.binary.traversal.postorder.PostOrderTraversalRecursion;
import algorithms.tree.binary.traversal.postorder.PostOrderTraversalStack;
import algorithms.tree.binary.traversal.preorder.PreOrderTraversalRecursion;
import algorithms.tree.binary.traversal.preorder.PreOrderTraversalStack;
import lombok.Getter;

@Getter
public enum TraversalType {
    INORDER_RECURSION(new InOrderTraversalRecursion()),
    INORDER_STACK(new InOrderTraversalStack()),
    POSTORDER_RECURSION(new PostOrderTraversalRecursion()),
    POSTORDER_STACK(new PostOrderTraversalStack()),
    PREORDER_RECURSION(new PreOrderTraversalRecursion()),
    PREORDER_STACK(new PreOrderTraversalStack());

    TraversalType(TreeTraversal implementor) {
        this.traversalImplementor = implementor;
    }

    private TreeTraversal traversalImplementor;
}