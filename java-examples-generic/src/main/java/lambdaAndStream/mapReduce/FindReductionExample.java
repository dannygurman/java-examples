package lambdaAndStream.mapReduce;

//There are two types of find reduction: findAll() and findAny()
//They might have nothing to return:
//If the stream is empty
//Or if there is no value that matches the predicate
//So they both return an Optional, that can be empty


import lambdaAndStream.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class FindReductionExample {

    private static List<Person> people = Arrays.asList(
            new Person("1", 10),
            new Person("2", 20),
            new Person("3", 30),
            new Person("4", 40),
            new Person("5", 50));


    public static void main(String[] args) {

        Predicate<Person> ageAbove20Predicate = p -> p.getAge() > 20;
        //Find first  - Returns the first person, if any, wrapped in an Optional
        //The first person means the stream has an order, if not then any person is returned
        Optional<Person> optionalPerson = people.stream().filter(ageAbove20Predicate).findFirst();
        displayOptionalResult (optionalPerson);


        //Returns any person, if it exists, wrapped in an Optional
        optionalPerson = people.stream().filter(ageAbove20Predicate).findAny();
        displayOptionalResult (optionalPerson);
    }

    private static void displayOptionalResult (Optional<Person> optionalPerson) {
        if (optionalPerson.isPresent()) {
            System.out.println("Found value:" + optionalPerson.get());
        }
    }
}
