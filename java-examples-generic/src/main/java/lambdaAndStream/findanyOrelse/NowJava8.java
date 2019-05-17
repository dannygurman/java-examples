package lambdaAndStream.findanyOrelse;

/**
 * Created by dannyg on 6/29/2017.
 */
import lambdaAndStream.Person;

import java.util.Arrays;
import java.util.List;

public class NowJava8 {

    public static void main(String[] args) {

        List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );

        Person result1 = persons.stream()                        // Convert to steam
                .filter(x -> "jack".equals(x.getName()))        // we want "jack" only
                .findAny()                                     // If 'findAny' then return found
                .orElse(null);                                  // If not found, return null

        System.out.println(result1);

        Person result2 = persons.stream()
                .filter(x -> "ahmook".equals(x.getName()))
                .findAny()
                .orElse(null);

        System.out.println(result2);

    }

    //outout:
   // Person{name='jack', age=20}
    //null

    //remark:Find any:In a non-parallel operation, it will most likely
    // return the first element in the Stream but there is no guarantee for this

   // The findFirst() method finds the first element in a Stream.
    // Obviously, this method is used when you specifically want the first element from a sequence.
}