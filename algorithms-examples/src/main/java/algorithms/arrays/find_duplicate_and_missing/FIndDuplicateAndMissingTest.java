package algorithms.arrays.find_duplicate_and_missing;

import algorithms.arrays.find_duplicate_and_missing.method1UsingSort.UsingSort_1_DuplicateAndMissingFinder;
import algorithms.arrays.find_duplicate_and_missing.method2UsingCountArray.UsingCountArray_2_DuplicateAndMissingFinder;
import algorithms.arrays.find_duplicate_and_missing.method3UsingElementAsIndex.UsingElementAsIndex_3_DuplicateAndMissingFinder;
import algorithms.arrays.find_duplicate_and_missing.method4UsingMap.UsingMap_4_DuplicateAndMissingFinder;
import org.junit.Assert;
import org.junit.Test;

public class FIndDuplicateAndMissingTest {

    private int [] numbers = {4, 3, 6, 2, 1, 1};
    private Integer expected_duplicate = 1;
    private Integer expected_Missing = 5;

    @Test
    public void test_duplicate_and_missing_method_1_sort() {
        DuplicateAndMissingFinder finder = new UsingSort_1_DuplicateAndMissingFinder();
        testInternal(finder);
    }

    @Test
    public void test_duplicate_and_missing_method_2_countArray() {
        DuplicateAndMissingFinder finder = new UsingCountArray_2_DuplicateAndMissingFinder();
        testInternal(finder);
    }

    @Test
    public void test_duplicate_and_missing_method_3_usingElementFinder() {
        DuplicateAndMissingFinder finder = new UsingElementAsIndex_3_DuplicateAndMissingFinder();
        testInternal(finder);
    }

    @Test
    public void test_duplicate_and_missing_method_4_usingMap() {
        DuplicateAndMissingFinder finder = new UsingMap_4_DuplicateAndMissingFinder();
        testInternal(finder);
    }


    private void testInternal(DuplicateAndMissingFinder finder) {
        FoundDuplicateAndMissingResult result = finder.findDuplicateAndMissing(numbers);
        Assert.assertEquals("checking expected duplicate", expected_duplicate, result.getDuplicate());
        Assert.assertEquals("checking expected missing", expected_Missing, result.getMissing());
    }
}
