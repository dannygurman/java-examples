package algorithms.arrays.FindDisappearedNumbers;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DisappearedNumsFinderTest {

   private int [] nums = {4,3,2,7,8,2,3,1};//2 and 3 appear twice - 5 and 6 are missing
    private List<Integer> expectedMissingNumbers = Arrays.asList(new Integer[]{5, 6});

    @Test
    public void test_disappearedNumFinder_method_1_MarkingIndex() {
        DisappearedNumsFinder finder = new DisappearedNumsFinderMethod_1_MarkingIndex();
        testDisappearedNumFinderInternal(finder);
    }

    private void testDisappearedNumFinderInternal(final DisappearedNumsFinder finder){
        List<Integer> foundMissingNumbers = finder.findMissingNumbers(nums);
        //Assert 2 list matching ignore order - alternative using assertj hasSameElementsAs
        Assert.assertTrue(listAreEqualIgnoreOrder(expectedMissingNumbers, foundMissingNumbers));
    }

    private boolean listAreEqualIgnoreOrder(List<Integer> first, List<Integer> second){
        return ( first.size() == second.size() && first.containsAll(second) && second.containsAll(first) );
    }
}
