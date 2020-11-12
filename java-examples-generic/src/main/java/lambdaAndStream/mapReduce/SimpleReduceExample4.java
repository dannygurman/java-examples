package lambdaAndStream.mapReduce;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleReduceExample4 {


    public static void main(String[] args) {
      List<User> users = Arrays.asList(new User("John", 30), new User("Julie", 35));
      int identity = 0;
        BiFunction<Integer, User, Integer> accumulator = (partialAgeResult, user) -> partialAgeResult + user.getAge();
        BinaryOperator<Integer> combiner = Integer::sum;
        int computedAges = users.stream().reduce(identity, accumulator, combiner);
        assertThat(computedAges).isEqualTo(65);

    }
}
