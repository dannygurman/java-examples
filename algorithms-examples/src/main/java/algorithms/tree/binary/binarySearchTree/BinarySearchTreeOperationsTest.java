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
import java.util.function.Function;

import static algorithms.tree.binary.common.utils.BinaryTreeUtils.MAX_VALUE;
import static algorithms.tree.binary.common.utils.BinaryTreeUtils.MIN_VALUE;
import static org.junit.Assert.*;

public class BinarySearchTreeOperationsTest {

    private BinaryTree tree;

    @Before
    //TREE:
   /*             4(D)

             3(C)           20(F)

        1(A)	          5(E)       100(G)

              2(B)
        */
    public void setUp() {
        tree = BinarySearchTreeUtils.buildExampleSearchBinaryTree();
    }


    @Test
    public void test_PrintTree() {
       printInorder();
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

    @Test
    public void test_FindMinAndMix() {
        int minElement = BinarySearchTreeUtils.findBinarySearchTreeMinElement(tree.root);
        int maxElement = BinarySearchTreeUtils.findBinarySearchTreeMaxElement(tree.root);
        assertEquals((int)MIN_VALUE, minElement);
        assertEquals((int)MAX_VALUE, maxElement);
    }

    @Test
    public void test_DeleteElements() {
        System.out.println("Before delete");
        printInorder();
        deleteAndVerify(BinaryTreeUtils.TreeValues.F);
        deleteAndVerify(BinaryTreeUtils.TreeValues.E);
        deleteAndVerify(BinaryTreeUtils.TreeValues.D);

    }

    private void deleteAndVerify(BinaryTreeUtils.TreeValues treeValue) {
        int valueToDelete =  treeValue.getValue();//20
        List<Integer> treeNodeValues = BinaryTreeUtils.traversalTree(this.tree, TraversalType.INORDER_RECURSION);
        assertTrue(treeNodeValues.contains(valueToDelete));

        BinarySearchTreeUtils.delete(tree.root, valueToDelete);

        //After delete
        System.out.println("\nAfter deleting:"+ valueToDelete);
        printInorder();
        treeNodeValues = BinaryTreeUtils.traversalTree(this.tree, TraversalType.INORDER_RECURSION);
        assertFalse(treeNodeValues.contains(valueToDelete));
    }

    private void printInorder() {
        List<Integer> orderedTreeNodeValues = BinaryTreeUtils.traversalTree(this.tree, TraversalType.INORDER_RECURSION);
        orderedTreeNodeValues.forEach(System.out::println);
    }

    @Test
    public void test_IsBST_V1() {
        //Correct - but not efficient
        checkIfTreeIsBSTInternal(BinarySearchTreeUtils::isBST_V1);
    }

    @Test
    public void test_IsBST_V2() {
        checkIfTreeIsBSTInternal(BinarySearchTreeUtils::isBST_V2);
    }

    private void checkIfTreeIsBSTInternal(Function<Node, Boolean> isBstFunction) {
        BinaryTree binarySearchTree = this.tree;
        boolean isBinaryTree = isBstFunction.apply(binarySearchTree.root);
        assertTrue(isBinaryTree);

        //Not BST:
        BinaryTree regularTree = BinaryTreeUtils.buildExampleRegularBinaryTree();
        isBinaryTree = isBstFunction.apply(regularTree.root);
        assertFalse(isBinaryTree);
    }

}
