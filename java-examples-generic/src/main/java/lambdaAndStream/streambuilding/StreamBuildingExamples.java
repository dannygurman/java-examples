package lambdaAndStream.streambuilding;

import lambdaAndStream.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamBuildingExamples {

    public static void main(String[] args) {
        listStreamExample();

        streamOfExample();

        streamGenerateExample();

        streamIterateExample();

        streamRandomExample();

        streamCharsExample();

        //In addition:
        // a Stream on a regular expression
        //  Stream<String> words =  Pattern.compile("[^\\p{javaLetter}]").splitAsStream(book);

        // a Stream on the lines of a text file
        //Stream<String> lines = Files.lines(path);

    }

    private static void listStreamExample() {
        System.out.println("\n listStreamExample");
        List<Person> people = new ArrayList<>();
        people.add(new Person("x", 1));
        Stream<Person> peopleStream = people.stream();
        peopleStream.forEach(System.out::println);
    }

    private static  void streamOfExample() {
        System.out.println("\n streamOfExample");
        Stream <String> numStringStream = Stream.of("one","two","three");
        numStringStream.forEach(System.out::println);
    }

    private static  void streamGenerateExample() {
        System.out.println("\n streamGenerateExample (constant stream)");
        //Infinite stream - constant
        Supplier <String> elementsSupplier = () -> "one";
        Stream <String> numStringStream = Stream.generate(elementsSupplier);
        numStringStream.limit(3).forEach(System.out::println);
    }

    private static void streamIterateExample() {
        System.out.println("\n streamIterateExample");
        String seed = "+";
        UnaryOperator<String>  plusAdditionUnaryOperator = s -> s + "+";
        Stream <String> iterateStream = Stream.iterate("+", plusAdditionUnaryOperator);
        iterateStream.limit(3).forEach(System.out::println);

    }

    private static void streamRandomExample() {
        System.out.println("\n streamRandomExample");
        IntStream  randomStream = ThreadLocalRandom.current().ints();
        randomStream.limit(3).forEach(System.out::println);

    }


    private static void streamCharsExample() {
        System.out.println("\n streamCharsExample");
        IntStream  charsStream = "Hello".chars();
        charsStream.forEach( i -> System.out.println((char) i));

    }




}
