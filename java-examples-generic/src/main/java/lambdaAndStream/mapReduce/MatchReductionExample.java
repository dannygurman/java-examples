package lambdaAndStream.mapReduce;

import lambdaAndStream.Person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class MatchReductionExample {

    //These three matchers may not evaluate the predicatef or all the elements
// They are calledshort-circuiting terminal operations

    private static List<Person> persons = Arrays.asList(
            new Person("1", 10),
            new Person("2", 20),
            new Person("3", 30),
            new Person("4", 40),
            new Person("5", 50));


    public static void main(String[] args) {

        Predicate<Person> ageAbove20Predicate = p -> p.getAge() > 20;

        boolean anyMatch =   persons.stream().anyMatch(ageAbove20Predicate);
        System.out.println("Any match:" + anyMatch);
        //Any match-Returns true if at least one element matches the predicate

        boolean allMatch =   persons.stream().allMatch(ageAbove20Predicate);
        System.out.println("All match:" + allMatch);
        //All match - Returns true if all the elements match the predicate

        boolean noneMatch =   persons.stream().noneMatch(ageAbove20Predicate);
        System.out.println("None match:" + noneMatch);
        //None match - Returns true if no element matches the predicate
    }


}
