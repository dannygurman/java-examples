package collections.listpartition;

import com.google.common.collect.Iterables;
import org.junit.Test;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class GuavaPartitionCollection {

    @Test
    public void givenCollection_whenParitioningIntoNSublists_thenCorrect() {
        Collection<Integer> intCollection = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        int partitionSize = 3;
        Iterable<List<Integer>> subSets = Iterables.partition(intCollection, partitionSize);

        List<Integer> firstPartition = subSets.iterator().next();
        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(1, 2, 3);
        assertThat(firstPartition, equalTo(expectedLastPartition));
    }

    //Keep in mind that the partitions are sublist views of the original collection –
    //which means that changes in the original collection will be reflected in the partitions:
    @Test
    public void givenListPartitioned_whenOriginalListIsModified_thenPartitionsChangeAsWell() {
        // Given
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        int partitionSize = 3;
        List<List<Integer>> subSets = Lists.partition(intList, partitionSize);

        // When
        intList.add(9);

        // Then
        List<Integer> lastPartition = subSets.get(2);
        // The '9' is also expected
        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(7, 8, 9);
        assertThat(lastPartition, equalTo(expectedLastPartition));
    }

}
