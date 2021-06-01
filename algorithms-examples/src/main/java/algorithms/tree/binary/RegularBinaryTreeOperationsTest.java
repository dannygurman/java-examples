package algorithms.tree.binary;

import algorithms.tree.binary.common.model.BinaryTree;
import algorithms.tree.binary.common.model.TraversalType;
import algorithms.tree.binary.common.utils.BinaryTreeUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static algorithms.tree.binary.common.utils.BinaryTreeUtils.TreeValues.*;

public class RegularBinaryTreeOperationsTest {

    private BinaryTree tree;

    @Before
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
    public void testNodesTraverersalInorder_ImplementedBy_Recursion() {
        BinaryTreeUtils.TreeValues[] expectedOrderedValues = {D, B, E, A , F, C ,G};
        ////4(D), 2(B),5(E),1(A) ,20(F),3(C),100(G)
        verifyNodesTraverersalInternal(TraversalType.INORDER_RECURSION, expectedOrderedValues);
    }

    @Test
    public void testNodesTraverersalInorder_ImplementedBy_Stack() {
        BinaryTreeUtils.TreeValues[] expectedOrderedValues = {D, B, E, A , F, C ,G};
        ////4(D), 2(B),5(E),1(A) ,20(F),3(C),100(G)
        verifyNodesTraverersalInternal(TraversalType.INORDER_STACK, expectedOrderedValues);
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
}
