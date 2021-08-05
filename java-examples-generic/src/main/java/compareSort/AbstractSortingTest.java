package compareSort;

import com.google.common.collect.Lists;
import compareSort.arraySort.Fruit;
import org.junit.Assert;

import java.util.Arrays;
import java.util.function.IntFunction;

public class AbstractSortingTest {

    protected final Fruit pineapple = new Fruit("Pineapple",3);
    protected final Fruit apple = new Fruit("Apple",2);
    protected final Fruit orange = new Fruit("Orange",  1);
    protected final Fruit banana = new Fruit("Banana",4);

    protected Fruit[] generateReversedArrayCopy(Fruit[] fruits ) {
       return generateReversedArrayCopyInternal(fruits, Fruit[]::new );
    }

    protected String[] generateReversedArrayCopy(String[] fruits ) {
        return generateReversedArrayCopyInternal(fruits, String[]::new );
    }

    private   <T> T [] generateReversedArrayCopyInternal(T[] a, IntFunction <T[]> generator ) {
        return Lists.reverse(Arrays.asList(a)).toArray(generator);
    }

    protected void verifyExpectedArraySortInternal(Fruit[] actualSortedFruits, Fruit[] fruitsExpected) {
        iterateArrayAndPrint(actualSortedFruits);
        Assert.assertArrayEquals( fruitsExpected, actualSortedFruits);
    }

    protected void verifyExpectedArraySortInternal(String[] actualItems, String[] expectedItems) {
        iterateArrayAndPrint(actualItems);
        Assert.assertArrayEquals( actualItems, expectedItems);
    }

    private void iterateArrayAndPrint(Fruit[] fruits){
        int i=0;
        for(Fruit fruit: fruits){
            System.out.println("fruits " + ++i + " : " + fruit);
        }
    }

    private void iterateArrayAndPrint(String[] items){
        int i=0;
        for(String item: items){
            System.out.println("items " + ++i + " : " + item);
        }
    }

}
