package lambdaAndStream.collections.iterableListCollection;

import lambdaAndStream.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class RemoveIfExample {

    List<Person> people= new ArrayList<>();

    public static void main(String[] args) {
        List<Person> people= new ArrayList<>();
        people.add(new Person("danny"  ,42));
        people.add(new Person("yo"  ,6));
        people.add(new Person("ya"  ,6));

        System.out.println("Before remove if");
        Consumer<Person> printPerson = p -> System.out.println(p);
        people.forEach(printPerson);

        Predicate<Person> youngerThen20 = person-> person.getAge() < 20;
       //removeIf method in Collection interface
        people.removeIf(youngerThen20);

        System.out.println("After remove if");
        people.forEach(printPerson);

    }
}
