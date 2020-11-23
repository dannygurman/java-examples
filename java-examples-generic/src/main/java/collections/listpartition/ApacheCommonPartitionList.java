package collections.listpartition;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import java.util.List;
import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

public class ApacheCommonPartitionList {
    @Test
    public void givenList_whenParitioningIntoNSublists_thenCorrect() {
        int partitionSize = 3;
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        List<List<Integer>> subSets = ListUtils.partition(intList, partitionSize);

        List<Integer> lastPartition = subSets.get(2);
        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(7, 8);
        assertThat(subSets.size(), equalTo(3));
        assertThat(lastPartition, equalTo(expectedLastPartition));
    }
}
