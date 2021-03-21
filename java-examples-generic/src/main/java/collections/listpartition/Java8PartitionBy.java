package collections.listpartition;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java8PartitionBy {

    ///Note: The resulting partitions are NOT a view of the main List,
    //so any changes happen to the main List will NOT affect the partitions.

    @Test
    public void givenList_whenParitioningIntoSublistsUsingPartitionBy_thenCorrect() {
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        Predicate<Integer> predicate = s -> s > 6;

        Map<Boolean, List<Integer>> groups =  intList.
            stream().
            collect(Collectors.partitioningBy(predicate));

       // {false=[1, 2, 3, 4, 5, 6],
         //true=[7, 8]}

        List<List<Integer>> subSets = new ArrayList<List<Integer>>(groups.values());
        assertThat(subSets.size(), equalTo(2));

        List<Integer> lastPartition = subSets.get(1);
        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(7, 8);
        assertThat(lastPartition, equalTo(expectedLastPartition));
    }

}
