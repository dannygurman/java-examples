package compareSort.tests;

import compareSort.Comparators;
import compareSort.Fruit;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by dannyg on 01/08/2016.
 */
public class SortFruitObjectsArrayTest extends AbstractSortingTest {

    private Fruit[] fruits, expectedSortedFruitsByQuantity, expectedSortedFruitsByName, expectedSortedFruitsByNameInverseOrder;

    @Before
    public void before() {
        fruits = new Fruit[]{pineapple, apple, orange, banana};
        expectedSortedFruitsByQuantity = new Fruit[]{orange, apple, pineapple, banana};
        expectedSortedFruitsByName = new Fruit[]{ apple, banana, orange, pineapple};
        expectedSortedFruitsByNameInverseOrder =  generateReversedArrayCopy(expectedSortedFruitsByName);
    }


    @Test
   public void testSortComparableObjectArray() {
        Arrays.sort(fruits);
        verifyExpectedArraySortInternal(fruits, expectedSortedFruitsByQuantity);
    }
    @Test
    public void testSortObjectArrayUsingNameAscendingComparator() {
        Arrays.sort(fruits, Comparators.FruitNameAscendingComparator);
        verifyExpectedArraySortInternal(fruits, expectedSortedFruitsByName);
    }

    @Test
    public void testSortObjectArrayUsingNameDescendingComparator() {
        Arrays.sort(fruits, Comparators.FruitNameDescendingComparator);
        verifyExpectedArraySortInternal(fruits, expectedSortedFruitsByNameInverseOrder);
    }



}