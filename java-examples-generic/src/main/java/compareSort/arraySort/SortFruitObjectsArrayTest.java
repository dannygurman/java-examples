package compareSort.arraySort;

import com.google.common.collect.Lists;
import compareSort.AbstractSortingTest;
import org.junit.Assert;
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
        Fruit pineapple = new Fruit("Pineapple",3);
        Fruit apple = new Fruit("Apple",2);
        Fruit orange = new Fruit("Orange",  1);
        Fruit banana = new Fruit("Banana",4);

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


    private void verifyExpectedArraySortInternal(Fruit[] actualSortedFruits, Fruit[] fruitsExpected) {
        iterateArrayAndPrint(actualSortedFruits);
        Assert.assertArrayEquals( fruitsExpected, actualSortedFruits);
    }

    private void iterateArrayAndPrint(Fruit[] fruits){
        int i=0;
        for(Fruit fruit: fruits){
            System.out.println("fruits " + ++i + " : " + fruit);
        }
    }


}