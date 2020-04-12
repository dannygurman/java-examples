package optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class OptionalMapOrElseGet {
    public static void main(String[] args) {
        Optional<String> op1 = Optional.empty();
        Optional<String> op2 = Optional.of("x");
        Optional<String> op3 = Optional.empty();
        Optional<String> op4 = Optional.of("y");

        System.out.println(isValueExist(op1));
        System.out.println(isValueExist(op2));

        List<Optional<String>> list = Arrays.asList(op1, op2, op3, op4);

        List<String> results =list.stream().
                filter(op -> isValueExist(op)).
                map(op -> op.get()).
                collect(Collectors.toList());

        results.forEach(System.out::println);

    }


    public static boolean isValueExist(Optional<String> optional) {
        Supplier<Boolean> noResult =   () -> {
            System.err.println("No value!!!");
            return false;
        };
        return optional.map(val  -> true).orElseGet(noResult);
    }
}
