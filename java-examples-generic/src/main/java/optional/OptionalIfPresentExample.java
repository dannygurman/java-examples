package optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalIfPresentExample {
    public static void main(String[] args) {
        List <String> l1 = new ArrayList<>();
        Optional<String> strOpt =  Optional.of("X");
        Optional<String> emptyOpt =  Optional.empty();

        strOpt.ifPresent(l1::add);
        emptyOpt.ifPresent(l1::add);

        l1.forEach(System.out::println);
    }


}
