package compareSort.tests;

import compareSort.Comparators;
import compareSort.Fruit;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortFruitObjectsCollectionTest extends AbstractSortingTest {

    private List <Fruit>fruits, expectedSortedFruitsByQuantity, expectedSortedFruitsByName, expectedSortedFruitsByNameInverseOrder;

    @Before
    public void before() {
        fruits =  Stream.of(pineapple, apple , orange, banana).collect(Collectors.toList());
        expectedSortedFruitsByQuantity =  Stream.of(orange, apple, pineapple, banana).collect(Collectors.toList());
        expectedSortedFruitsByName = Stream.of(apple, banana, orange, pineapple).collect(Collectors.toList());
        expectedSortedFruitsByNameInverseOrder =  Stream.of(pineapple, orange, banana, apple).collect(Collectors.toList());
    }


    @Test
    public void testSortComparableObjectCollection() {
        Collections.sort(fruits);
        verifyExpectedArraySortInternal(fruits, expectedSortedFruitsByQuantity);
    }
    @Test
    public void testSortObjectCollectionUsingNameAscendingComparator() {
        Collections.sort(fruits, Comparators.FruitNameAscendingComparator);
        verifyExpectedArraySortInternal(fruits, expectedSortedFruitsByName);
    }

    @Test
    public void testSortObjectCollectionUsingNameDescendingComparator() {
        Collections.sort(fruits, Comparators.FruitNameDescendingComparator);
        verifyExpectedArraySortInternal(fruits, expectedSortedFruitsByNameInverseOrder);
    }
}
