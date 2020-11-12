package lambdaAndStream.mapReduce;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleReduceExample3 {

    public static void main(String[] args) {
        //we can use reduce() in a parallelized stream
        List<Integer> ages = Arrays.asList(25, 30, 45, 28, 32);
        Integer identity = 0;
        BiFunction <Integer, Integer, Integer>  accumulator= (a, b) -> a + b;
/*        When a stream executes in parallel, the Java runtime splits the stream into multiple substreams.
        In such cases, we need to use a function to combine the results of the substreams into a single one.
        This is the role of the combiner –  here we use  Integer::sum method reference.*/
        BinaryOperator<Integer> combiner = Integer::sum;
        int computedAges = ages.parallelStream().reduce(identity, accumulator , combiner);
        assertThat(computedAges).isEqualTo(160);
    }
}
