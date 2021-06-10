package algorithms.tree.binary;

import algorithms.tree.binary.common.model.BinaryTree;
import algorithms.tree.binary.common.model.TraversalType;
import algorithms.tree.binary.common.utils.BinaryTreeUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static algorithms.tree.binary.common.utils.BinaryTreeUtils.TreeValues.*;

public class RegularBinaryTreeOperationsTest {

    private BinaryTree tree;

    @Before
    //TREE:
    //         1(A)
    //     2 (B)                     3(C)
    //  4 (D)         5(E)    20(F)      G(100)
    public void setUp() {
         tree = BinaryTreeUtils.buildExampleRegularBinaryTree();
    }


    @Test
    public void testRegularTreeMinAndMaxFind() {
        Integer minFound = BinaryTreeUtils.getRegularBinaryTreeMinElement(this.tree.root);
        Integer maxFound = BinaryTreeUtils.getRegularBinaryTreeMaxElement(this.tree.root);
        System.out.println("Min:" + minFound + " Max:" + maxFound);
        assertEquals(BinaryTreeUtils.MIN_VALUE, minFound);
        assertEquals(BinaryTreeUtils.MAX_VALUE, maxFound);
    }

    @Test
    public void testNodesTraverersal_InOrder_ImplementedBy_Recursion() {
        BinaryTreeUtils.TreeValues[] expectedOrderedValues = {D, B, E, A , F, C ,G};
        ////4(D), 2(B),5(E),1(A) ,20(F),3(C),100(G)
        verifyNodesTraverersalInternal(TraversalType.INORDER_RECURSION, expectedOrderedValues);
    }

    @Test
    public void testNodesTraverersal_InOrder_ImplementedBy_Stack() {
        BinaryTreeUtils.TreeValues[] expectedOrderedValues = {D, B, E, A , F, C ,G};
        ////4(D), 2(B),5(E),1(A) ,20(F),3(C),100(G)
        verifyNodesTraverersalInternal(TraversalType.INORDER_STACK, expectedOrderedValues);
    }

    @Test
    public void testNodesTraverersal_PreOrder_ImplementedBy_Recursion() {
        BinaryTreeUtils.TreeValues[] expectedOrderedValues = {A, B, D, E , C, F ,G};
        ////1(A), 2(B), 4(D), 5(E), 3(C), 20(F), 100(G)
        verifyNodesTraverersalInternal(TraversalType.PREORDER_RECURSION, expectedOrderedValues);
    }

    @Test
    public void testNodesTraverersal_PreOrder_ImplementedBy_Stack() {
        BinaryTreeUtils.TreeValues[] expectedOrderedValues = {A, B, D, E , C, F ,G};
        ////1(A), 2(B), 4(D), 5(E), 3(C), 20(F), 100(G)
        verifyNodesTraverersalInternal(TraversalType.PREORDER_STACK, expectedOrderedValues);
    }

    @Test
    public void testNodesTraverersal_PostOrder_ImplementedBy_Recursion() {
        BinaryTreeUtils.TreeValues[] expectedOrderedValues = {D, E, B, F , G, C ,A};
        ////4(D), 5(E), 2(B), 20(F), 100(G), 3(C), 1(A))
        verifyNodesTraverersalInternal(TraversalType.POSTORDER_RECURSION, expectedOrderedValues);
    }

    @Test
    public void testNodesTraverersal_PostOrder_ImplementedBy_Stack() {
        BinaryTreeUtils.TreeValues[] expectedOrderedValues = {D, E, B, F , G, C ,A};
        ////4(D), 5(E), 2(B), 20(F), 100(G), 3(C), 1(A))
        verifyNodesTraverersalInternal(TraversalType.POSTORDER_STACK, expectedOrderedValues);
    }

    private void verifyNodesTraverersalInternal(TraversalType traversalType,
                                                BinaryTreeUtils.TreeValues[] expectedOrderedValues ) {

        List<Integer>  orderedTreeNodeValues = BinaryTreeUtils.traversalTree(this.tree, traversalType);

        orderedTreeNodeValues.forEach(System.out::println);

        List <Integer> expectedOrderedTreeNodeValues = Arrays.asList(expectedOrderedValues)
                .stream().map(BinaryTreeUtils.TreeValues::getValue).collect(Collectors.toList());
        ////4(D), 2(B),5(E),1(A) ,20(F),3(C),100(G)
        assertEquals(expectedOrderedTreeNodeValues, orderedTreeNodeValues);
    }


    @Test
    public void test_deleteNode_ByValue() {
        int nodeToDeleteKey = A.getValue();

        System.out.println("\nBefore deleting:" + nodeToDeleteKey);
        printTreeElementsInOrder();
        List<Integer>  orderedTreeNodeValuesBeforeDeletion = BinaryTreeUtils.traversalTree(this.tree,  TraversalType.INORDER_RECURSION);
        assertThat(orderedTreeNodeValuesBeforeDeletion, hasItems(nodeToDeleteKey));

        BinaryTreeUtils.deleteNodeByValue(this.tree.root, nodeToDeleteKey);

        System.out.println("\nAfter delete:" + nodeToDeleteKey);
        printTreeElementsInOrder();
        List<Integer>  orderedTreeNodeValuesAfterDeletion = BinaryTreeUtils.traversalTree(this.tree,  TraversalType.INORDER_RECURSION);
        //Assert NOT contain item after deletion
        assertThat(orderedTreeNodeValuesAfterDeletion, not(hasItems(nodeToDeleteKey)));
    }

    @Test
    public void test_FindDeepest() {
        BinaryTreeUtils.DeepestResult result = BinaryTreeUtils.findDeepestRightMostNode_2(this.tree.root);
        assertEquals(3, result.deepestLevel);
        assertEquals(G.getValue(), result.deepestValue);
    }

    @Test
    public void test_InsertInDeepestRightMost() {
        int value = 200;
        BinaryTreeUtils.insertInDeepestRightMost(this.tree.root, value);

        BinaryTreeUtils.DeepestResult result = BinaryTreeUtils.findDeepestRightMostNode_2(this.tree.root);
        assertEquals(4, result.deepestLevel);
        assertEquals(value, result.deepestValue);
    }


    private void printTreeElementsInOrder() {
        System.out.println("-----------------------");
        List<Integer>  orderedTreeNodeValues = BinaryTreeUtils.traversalTree(this.tree, TraversalType.INORDER_RECURSION);
        orderedTreeNodeValues.forEach(System.out::println);
    }
}
