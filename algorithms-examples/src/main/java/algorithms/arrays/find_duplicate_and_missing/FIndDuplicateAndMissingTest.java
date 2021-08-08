package algorithms.arrays.find_duplicate_and_missing;

import org.junit.Assert;
import org.junit.Test;

public class FIndDuplicateAndMissingTest {

    private int [] numbers = {4, 3, 6, 2, 1, 1};
    private Integer expected_duplicate = 1;
    private Integer expected_Missing = 5;

    @Test
    public void test_duplicate_and_missing_method_a_sort() {
        DuplicateAndMissingFinder finder = new UsingSort_1_DuplicateAndMissingFinder();
        testInternal(finder);
    }

    private void testInternal(DuplicateAndMissingFinder finder) {
        FoundDuplicateAndMissingResult result = finder.findDuplicateAndMissing(numbers);
        Assert.assertEquals("checking expected duplicate", expected_duplicate, result.getDuplicate());
        Assert.assertEquals("checking expected missing", expected_Missing, result.getMissing());
    }
}
