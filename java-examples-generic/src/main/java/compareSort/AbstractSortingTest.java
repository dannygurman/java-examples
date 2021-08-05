package compareSort;

import com.google.common.collect.Lists;
import compareSort.arraySort.Fruit;

import java.util.Arrays;
import java.util.function.IntFunction;

public class AbstractSortingTest {

    protected Fruit[] generateReversedArrayCopy(Fruit[] fruits ) {
       return generateReversedArrayCopyInternal(fruits, Fruit[]::new );
    }

    private   <T> T [] generateReversedArrayCopyInternal(T[] a, IntFunction <T[]> generator ) {
        return Lists.reverse(Arrays.asList(a)).toArray(generator);
    }

}
