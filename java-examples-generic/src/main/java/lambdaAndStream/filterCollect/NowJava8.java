package lambdaAndStream.filterCollect;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dannyg on 6/28/2017.
 */
public class NowJava8 {
    public static void main(String[] args) {

        List<String> lines = Arrays.asList("spring", "node", "mkyong");

        List<String> result = lines.stream()                // convert list to lambdaAndStream
                .filter(line -> !"mkyong".equals(line))     // we dont like mkyong
                .collect(Collectors.toList());              // collect the output and convert streams to a List

        result.forEach(System.out::println);                //output : spring, node

    }

    //Output:
    //spring
    //  node
}
