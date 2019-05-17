package lambdaAndStream.java.utils.functional.predicates;

import lambdaAndStream.Person;

import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * Created by dannyg on 11/5/2017.
 *
 * // A predicate take an object and return a boolean
 */
public class PredicateMain {

    public static void main(String[] args) {
        System.out.println( "Predicate:");
        Predicate<Person> ageGT20 = person -> person.getAge() > 20;
        Person danny = new Person("danny" , 40);
        System.out.print( "Danny age greater then 20:"+ ageGT20.test( danny));

        System.out.println( "Bi Predicate:");
        BiPredicate<Integer, Integer> bi = (x, y) -> x > y;
        System.out.println(bi.test(2, 3));

        System.out.println( "IntPredicate:");
        IntPredicate i = (x)-> x < 0;
        System.out.println(i.test(123));

    }
}
