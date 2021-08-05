package compareSort.arraySort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SortStringArrayTest {

    String[] fruits;
    String[] expectedSortedFruitsByName;
    String[] expectedSortedFruitsByReverseNameOrder;

    @Before
    public void before() {
        String pine = "Pineapple" , apple = "Apple", orange = "Orange", banana = "Banana";
        fruits = new String[] {pine, apple , orange, banana};
        expectedSortedFruitsByName = new String[] {apple, banana, orange, pine};
    }

    @Test
    public void testSortStringArray() {
        Arrays.sort(fruits);

        iterateArrayAndPrint(fruits);
        Assert.assertArrayEquals( expectedSortedFruitsByName, fruits);
    }


    private void iterateArrayAndPrint(String[] fruits){
        int i=0;
        for(String fruit: fruits){
            System.out.println("fruits " + ++i + " : " + fruit);
        }
    }

}
