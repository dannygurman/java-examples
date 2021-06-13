package algorithms.tree.binary.binarySearchTree;

import algorithms.tree.binary.common.model.BinaryTree;
import algorithms.tree.binary.common.model.Node;
import algorithms.tree.binary.common.model.TraversalType;
import algorithms.tree.binary.common.utils.BinarySearchTreeUtils;
import algorithms.tree.binary.common.utils.BinaryTreeUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.function.BiFunction;

import static algorithms.tree.binary.common.utils.BinaryTreeUtils.MAX_VALUE;
import static org.junit.Assert.assertEquals;

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
    public void test_PrintTree() {
        List<Integer> orderedTreeNodeValues = BinaryTreeUtils.traversalTree(this.tree, TraversalType.INORDER_RECURSION);
        orderedTreeNodeValues.forEach(System.out::println);
    }

    @Test
    public void test_printFrontToBack() {
        int camera = BinaryTreeUtils.TreeValues.E.getValue();
        BinarySearchTreeUtils.printFrontToBack(tree.root, camera);

    }

    @Test
    public void test_Insert_ver1() {
        BiFunction <Node, Integer, Node> insertNodeFunction = BinarySearchTreeUtils::insert;
        test_Insert_internal(insertNodeFunction);
    }

   @Test
    public void test_Insert_ver2() {
        BiFunction <Node, Integer, Node> insertNodeFunction = BinarySearchTreeUtils::insert_Ver2;
        test_Insert_internal(insertNodeFunction);
    }


    private void test_Insert_internal(BiFunction <Node, Integer, Node> insertNodeFunction) {
        int valueToAdd = MAX_VALUE + 1;
        insertNodeFunction.apply(tree.root, valueToAdd);
        List<Integer> orderedTreeNodeValues = BinaryTreeUtils.traversalTree(this.tree, TraversalType.INORDER_RECURSION);
        int lastMostRightValue = orderedTreeNodeValues.get(orderedTreeNodeValues.size() -1);
        assertEquals(valueToAdd, lastMostRightValue);
    }

}
