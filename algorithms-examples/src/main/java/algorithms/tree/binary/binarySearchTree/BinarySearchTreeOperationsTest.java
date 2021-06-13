package algorithms.tree.binary.binarySearchTree;

import algorithms.tree.binary.common.model.BinaryTree;
import algorithms.tree.binary.common.model.TraversalType;
import algorithms.tree.binary.common.utils.BinarySearchTreeUtils;
import algorithms.tree.binary.common.utils.BinaryTreeUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BinarySearchTreeOperationsTest {

    private BinaryTree tree;

    @Before
    //TREE:
   /*             4

             3            20

        1	          5      100

              2
        */
    public void setUp() {
        tree = BinarySearchTreeUtils.buildExampleSearchBinaryTree();
    }


    @Test
    public void testX() {
        List<Integer> orderedTreeNodeValues = BinaryTreeUtils.traversalTree(this.tree, TraversalType.INORDER_RECURSION);
        orderedTreeNodeValues.forEach(System.out::println);
    }

}
