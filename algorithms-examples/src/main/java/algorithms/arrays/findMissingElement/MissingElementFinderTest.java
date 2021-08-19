package algorithms.arrays.findMissingElement;

import org.junit.Assert;
import org.junit.Test;

public class MissingElementFinderTest {

    private int [] nums =  { 1, 2, 4, 5, 6 };
    int expectedMissing = 3;

    @Test
    public void test_MissingElement_Method1_UsingX() {
        MissingElementFinder finder = new MissingElementFinder_1_UsingSumFormula();
        testMissingElementInternal(finder );
    }

    @Test
    public void test_MissingElement_Method2_SumIndexAndSubtractElement() {
        MissingElementFinder finder = new MissingElementFinder_2_SumIndexAndSubtractElements();
        testMissingElementInternal(finder );
    }

    public void testMissingElementInternal( final MissingElementFinder finder )  {
        int foundMissing = finder.findMissingElement(nums);
        Assert.assertEquals("Check foun missing", expectedMissing, foundMissing);
    }

}
