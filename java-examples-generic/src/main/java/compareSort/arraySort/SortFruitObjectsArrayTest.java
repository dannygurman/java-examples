package compareSort.arraySort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by dannyg on 01/08/2016.
 */
public class SortFruitObjectsArrayTest {

    private Fruit[] fruits;
    private Fruit[] expectedSortedFruitsByQuantity;
    private Fruit[] expectedSortedFruitsByName;

    @Before
    public void before() {
        Fruit pineapple = new Fruit("Pineapple",3);
        Fruit apple = new Fruit("Apple",2);
        Fruit orange = new Fruit("Orange",  1);
        Fruit banana = new Fruit("Banana",4);

        fruits = new Fruit[]{pineapple, apple, orange, banana};
        expectedSortedFruitsByQuantity = new Fruit[]{orange, apple, pineapple, banana};
        expectedSortedFruitsByName = new Fruit[]{ apple, banana, orange, pineapple};
    }

    @Test
   public void testSortComparableObjectArray() {
        Arrays.sort(fruits);

        iterateArrayAndPrint(fruits);
        Assert.assertArrayEquals( expectedSortedFruitsByQuantity, fruits);
    }
    @Test
    public void testSortObjectArrayUsingComparator() {
        Arrays.sort(fruits, Fruit.FruitNameComparator);

        iterateArrayAndPrint(fruits);
        Assert.assertArrayEquals( expectedSortedFruitsByName, fruits);
    }

    private void iterateArrayAndPrint(Fruit[] fruits){
        int i=0;
        for(Fruit fruit: fruits){
            System.out.println("fruits " + ++i + " : " + fruit);
        }
    }


}