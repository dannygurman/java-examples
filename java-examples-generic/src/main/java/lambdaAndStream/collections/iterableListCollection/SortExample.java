package lambdaAndStream.collections.iterableListCollection;

import lambdaAndStream.Person;

import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Created by dannyg on 11/16/2017.
 */
public class SortExample {

    public static void main(String[] args) {
        List<Person> people = new ArrayList();
        people.add(new Person("danny1" , 1));
        people.add(new Person("danny2" , 21));
        people.add(new Person("danny2" , 35));
        people.add(new Person("danny2" , 21));
        //Comparing       

        Function <Person , String> getNameFunction = p -> p.getName();
        Function <Person , Integer> getAgeFunction = Person::getAge;

        Comparator <Person> personComparator = Comparator.comparing(getNameFunction).thenComparing(getAgeFunction);

        //Sort in List interface
        people.sort(personComparator);


        //ALTERNATIVES using java.util.collections:

        // 1.  Collections.sort( people);

        //IN THIS CASE Person must implement Comparable


        //2
       // Collections.sort( people , personComparator);

        people.forEach(System.out::println);

    }



}
