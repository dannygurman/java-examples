package lambdaAndStream.predicateComparators;

import java.util.function.Function;

/**
 * Created by dannyg on 9/12/2017.
 */
public class MainComparator {

    public static void main(String ...  args) {

        Person person1 = new Person("danny" , "g" , 40);
        Person person2 = new Person("danny2" , "g" , 40);

        //Functional interface - could be initiate using lambda expression
        Comparator < Person> cmpAge = (p1 , p2 ) ->  p2.getAge() - p1.getAge();
        Comparator < Person> cmpFirstName = (p1 , p2 ) ->  p2.getFirstName().compareTo(p1.getFirstName());
        Comparator < Person> cmpLastName = (p1 , p2 ) ->  p2.getLastName().compareTo(p1.getLastName());

        //Is there common point between those lambda expression ?

        Function <Person , Integer> getPersonAgeFunction = p -> p.getAge();
        Function <Person , String> getPersonFirstNameFunction = p -> p.getFirstName();
        Function <Person , String> getPersonLastNameFunction = p -> p.getLastName();

        //We want a comparator that will receive function
        Comparator <Person> cmpPerson = Comparator.comparingPersonInt(getPersonAgeFunction);
        Comparator <Person> cmpPerson2 = Comparator.comparingPersonInt(p -> p.getAge());
        //Or as a method reference
        Comparator <Person> cmpPerson3 = Comparator.comparingPersonInt(Person::getAge);

        Comparator <Person> cmpPerson4 = Comparator.comparingPersonString(getPersonFirstNameFunction);

        //Using the generic comparing  method
        Comparator <Person> cmpPerson6 = Comparator.comparingPerson(Person::getFirstName);

        //even more generic
        Comparator <Person> cmpPersonFirstName= Comparator.comparing(Person::getFirstName);
        Comparator <Person> cmpPersonAge= Comparator.comparing(Person::getAge);

        //First comparing Age - if equal - comparing by last name
        Comparator <Person> cmp = cmpPersonAge.thenComparing(cmpPersonFirstName);
        printResults (cmp, person1 , person2 );

        Comparator <Person> cmp2 = Comparator.comparing(Person::getAge).
                thenComparing(Person::getFirstName).
                thenComparing(Person::getLastName);
        printResults (cmp, person1 , person2 );


    }

    public static void printResults (Comparator <Person> comparator, Person p_1 , Person p_2 ) {
        System.out.println("Result:" + comparator.compare(p_1 , p_2));
    }
}
