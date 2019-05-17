package lambdaAndStream.mapReduce;

import lambdaAndStream.Person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PeekExample {

    //The forEach() doesnot return anything
    //The peek() call can be used for logging purposes

    //peek() is an intermediate operation

    //forEach() isa terminal operation

    private static List<Person> persons = Arrays.asList(
            new Person("mkyong", 30),
            new Person("jack", 20),
            new Person("lawrence", 40)
    );

    public static void main(String[] args) {

        Consumer <Integer> beforeFilterAgeConsumer = age -> System.out.println("Age before:" + age);

        Consumer <Integer> resultsAgeConsumer = age -> System.out.println("Age result:" + age);

        Predicate <Integer> ageAbove20Filter = age -> age > 20;
        persons.stream()
                .map(p -> p.getAge())
              //  .forEach(System.out::println) // !!! DOES NOT COMPILE !!!
                 .peek(beforeFilterAgeConsumer)
                .filter(ageAbove20Filter)
                .forEach(resultsAgeConsumer);
    }
}
