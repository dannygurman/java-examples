package compareSort.tests;

import compareSort.Comparators;
import compareSort.tests.AbstractSortingTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SortStringArrayTest extends AbstractSortingTest {

   private String[] fruits, expectedSortedFruitsByNameRegularOrder, expectedSortedFruitsByNameReverseOrder;

    @Before
    public void before() {
        String pine = "Pineapple" , apple = "Apple", orange = "Orange", banana = "Banana";
        fruits = new String[] {pine, apple , orange, banana};
        expectedSortedFruitsByNameRegularOrder = new String[] {apple, banana, orange, pine};
        expectedSortedFruitsByNameReverseOrder = generateReversedArrayCopy(expectedSortedFruitsByNameRegularOrder);
    }

    @Test
    public void testSortStringArray() {
        Arrays.sort(fruits);
        verifyExpectedArraySortInternal(fruits, expectedSortedFruitsByNameRegularOrder);
    }


    @Test
    public void testSortStringArrayWithAscendingComparator() {
        Arrays.sort(fruits, Comparators.stringInsensitiveAscendingComparator);
        verifyExpectedArraySortInternal(fruits, expectedSortedFruitsByNameRegularOrder);
    }

    @Test
    public void testSortStringArrayWithDesendingComparator() {
        Arrays.sort(fruits, Comparators.stringInsensitiveDescendingComparator);
        verifyExpectedArraySortInternal(fruits, expectedSortedFruitsByNameReverseOrder);
    }

}
