package lambdaAndStream.stream.iterate;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class StreamIterateExample {

    public static void main(String[] args) {

        Long seed = 1L;
        UnaryOperator <Long>  increaseUnaryOperator = n  ->  n  + 1;

        System.out.println("Print 1 to 10:");
        Stream<Long> naturalNumbers = Stream.iterate(seed, increaseUnaryOperator);
        naturalNumbers.limit(10).forEach(System.out::println);

        System.out.println("Print 5 odd numbers -  starting from 2(actually 3) :");
        seed = 2L;
        Stream.iterate(seed, increaseUnaryOperator)
                .filter(StreamIterateExample::isOdd)
                .limit(5)
                .forEach(System.out::println);
        //3 5 7 9

        System.out.println("Print 5 odd numbers - skipping 100 matches( the first 100 odd number) ");
        Stream.iterate(seed,increaseUnaryOperator)
                .filter(StreamIterateExample::isOdd)
                .skip(100)
                .limit(5)
                .forEach(System.out::println);
    }

    //203,205,207.209,211




    public static boolean isOdd(long number) {
        if (number % 2 == 0) {
            return false;
        }
        return true;
    }
}
