package lambdaAndStream.mapReduce;

import lambdaAndStream.Person;

import java.util.Arrays;
import java.util.List;

public class SkipLimitExample {

    private static List<Person> persons = Arrays.asList(
            new Person("1", 100),
            new Person("2", 100),
            new Person("3", 100),
            new Person("4", 5),
            new Person("5", 100),
            new Person("6", 100),
            new Person("7", 100),
            new Person("8", 100),
            new Person("9", 100),
            new Person("10", 100));



    public static void main(String[] args) {
        persons.stream()
                .skip(2)
                .limit(3)
                .filter(person -> person.getAge() > 20)
                .forEach(System.out::println);
    }

    //result:
    // Person{name='3', age=100}
    //Person{name='5', age=100}
}
