package compareSort.tests;

import com.google.common.collect.Lists;
import compareSort.Fruit;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;

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

    protected void verifyExpectedArraySortInternal(List<Fruit> actualSortedFruits,List<Fruit> fruitsExpected) {
        iterateArrayAndPrint(actualSortedFruits);
        Assert.assertThat(actualSortedFruits, is(fruitsExpected));
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

    private void iterateArrayAndPrint(List <Fruit> fruits){
        List<String> fruitsWithIndexStrings = IntStream.range(0, fruits.size())
            .mapToObj(index -> index + ":" + fruits.get(index))
            .collect(Collectors.toList());
        fruitsWithIndexStrings.forEach(System.out::println);
    }




}
