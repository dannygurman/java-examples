package algorithms.tree.binary;

import algorithms.tree.binary.BinaryTreeUtils;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RegularBinaryTreeOperationsTest {


    @Test
    public void testRegularTreeMinAndMaxFind() {
        BinaryTreeUtils tree = BinaryTreeUtils.buildExampleRegularBinaryTree();
        Integer minFound = BinaryTreeUtils.getRegularBinaryTreeMinElement(tree.root);
        Integer maxFound = BinaryTreeUtils.getRegularBinaryTreeMaxElement(tree.root);
        System.out.println("Min:" + minFound + " Max:" + maxFound);
        assertEquals(BinaryTreeUtils.MIN_VALUE, minFound);
        assertEquals(BinaryTreeUtils.MAX_VALUE, maxFound);

    }
}
