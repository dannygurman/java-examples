package lambdaAndStream.mapReduce;

/**
 * Created by dannyg on 6/29/2017.
 */
import lambdaAndStream.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMapExample1 {

    public static void main(String[] args) {

        List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );

        String name = persons.stream()
                .filter(x -> "jack".equals(x.getName()))
                .map(Person::getName)                        //convert lambdaAndStream to String
                .findAny()
                .orElse("");

        System.out.println("name : " + name);
        System.out.println("-------");
        List<String> collect = persons.stream()
                .map(Person::getName)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);

    }

    /*
    Output:
    name : jack
    -----------
    mkyong
    jack
    lawrence
     */
}