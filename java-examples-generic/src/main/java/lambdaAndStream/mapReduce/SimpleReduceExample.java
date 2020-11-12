package lambdaAndStream.mapReduce;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleReduceExample {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
       Integer identity = 0;
        BinaryOperator<Integer> accumulator =(subtotal, element) -> subtotal + element;
        int result = numbers .stream().reduce(identity, accumulator);
        int expected = 21;
        assertThat(result).isEqualTo(expected);
        //To make the code even more concise, we can use a method reference, instead of a lambda expression:

        int result2 = numbers.stream().reduce(identity, Integer::sum);
        assertThat(result2).isEqualTo(expected);
    }
}
