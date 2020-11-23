package collections.listpartition;

import org.junit.Test;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class SplitListAsSeparator {
//We can also use Java8 to split our List by separator:
//Note: We used “0” as separator –
//we first obtained the indices of all “0” elements in the List,
//then we split the List on these indices.

    @Test
    public void givenList_whenSplittingBySeparator_thenCorrect() {
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 0, 4, 5, 6, 0, 7, 8);

        IntStream intStream1 = IntStream.of(-1);

        IntPredicate filter = i -> intList.get(i) == 0;
        IntStream intStream2 = IntStream.
            range(0, intList.size()).filter(filter);//3, 7
        IntStream intStream3 =  IntStream.of(intList.size());//10

        int[] indexes =
            Stream.of(intStream1, intStream2, intStream3)
                .flatMapToInt(s -> s).
                toArray();
       // indexes { -1, 3, 7, 10}

        IntFunction<List<Integer>> mapper = i -> intList.subList(indexes[i] + 1, indexes[i + 1]);

        List<List<Integer>> subSets = IntStream.
               range(0, indexes.length - 1)
                .mapToObj(mapper)
                .collect(Collectors.toList());

        List<Integer> lastPartition = subSets.get(2);
        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(7, 8);

        assertThat(subSets.size(), equalTo(3));
        assertThat(lastPartition, equalTo(expectedLastPartition));
    }
}
