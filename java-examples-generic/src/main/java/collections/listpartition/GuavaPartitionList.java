package collections.listpartition;

import org.junit.Test;

import java.util.List;
import com.google.common.collect.Lists;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GuavaPartitionList {
    @Test
    public void givenList_whenParitioningIntoNSublists_thenCorrect() {
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        int partitionSize = 3;
        List<List<Integer>> subSets = Lists.partition(intList, partitionSize);
        assertThat(subSets.size(), equalTo(3));

        List<Integer> lastPartition = subSets.get(2);
        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(7, 8);
        assertThat(lastPartition, equalTo(expectedLastPartition));
    }
}
