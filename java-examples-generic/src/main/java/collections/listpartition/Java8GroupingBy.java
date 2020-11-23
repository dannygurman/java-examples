package collections.listpartition;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
public class Java8GroupingBy {

    //Note: Just as Collectors.partitioningBy() –
    //the resulting partitions won't be affected by changes in main List.
    @Test
    public final void givenList_whenParitioningIntoNSublistsUsingGroupingBy_thenCorrect() {

        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);

        Function<Integer, Integer> classifier = s -> (s - 1) / 3;

        Map<Integer, List<Integer>> groups = intList.
            stream().
            collect(Collectors.groupingBy(classifier));

        //{0=[1, 2, 3], 1=[4, 5, 6], 2=[7, 8]}
        List<List<Integer>> subSets = new ArrayList<List<Integer>>(groups.values());

        List<Integer> lastPartition = subSets.get(2);
        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(7, 8);

        assertThat(subSets.size(), equalTo(3));
        assertThat(lastPartition, equalTo(expectedLastPartition));
    }

}
