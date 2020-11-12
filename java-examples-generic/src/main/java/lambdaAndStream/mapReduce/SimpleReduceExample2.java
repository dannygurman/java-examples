package lambdaAndStream.mapReduce;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleReduceExample2 {

    public static void main(String[] args) {
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        String identity = "";

        BinaryOperator<String> accumulator = (partialString, element) -> partialString + element;
        String result = letters.stream().reduce(identity, accumulator);
        String expected =  "abcde";
        assertThat(result).isEqualTo(expected);


        String result2 = letters.stream().reduce(identity, String::concat);
        assertThat(result2).isEqualTo(expected);


        String expected2 =  "ABCDE";
        BinaryOperator<String> accumulator2 = (partialString, element) -> partialString.toUpperCase() + element.toUpperCase();
        String result3 = letters.stream().reduce(identity, accumulator2);
        assertThat(result3).isEqualTo(expected2);


    }
}
