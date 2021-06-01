package algorithms.tree.binary;

import algorithms.tree.binary.common.model.BinaryTree;
import algorithms.tree.binary.common.utils.BinaryTreeUtils;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RegularBinaryTreeOperationsTest {


    @Test
    public void testRegularTreeMinAndMaxFind() {
        BinaryTree tree = BinaryTreeUtils.buildExampleRegularBinaryTree();
        Integer minFound = BinaryTreeUtils.getRegularBinaryTreeMinElement(tree.root);
        Integer maxFound = BinaryTreeUtils.getRegularBinaryTreeMaxElement(tree.root);
        System.out.println("Min:" + minFound + " Max:" + maxFound);
        assertEquals(BinaryTreeUtils.MIN_VALUE, minFound);
        assertEquals(BinaryTreeUtils.MAX_VALUE, maxFound);

    }
}
