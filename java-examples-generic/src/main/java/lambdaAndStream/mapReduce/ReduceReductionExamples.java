package lambdaAndStream.mapReduce;

import lambdaAndStream.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class ReduceReductionExamples {

    //There are three types of reduce reduction
    //If no identity element is provided, then an Optional is returned
    //Associativity is assumed for the reduction function, but not enforced

    private static List<Person> people = Arrays.asList(
            new Person("1", 10),
            new Person("2", 20),
            new Person("3", 30),
            new Person("4", 40),
            new Person("5", 50));

    public static void main(String[] args) {
        int identity = 0;
        Function<Person , Integer> getAgeFunction = person -> person.getAge();

        // ------------------Sum combiner
        BinaryOperator<Integer> intSumCombiner = (age1, age2) -> age1 + age2;

        int  sumOfAges= people.stream().map(getAgeFunction).reduce(identity, intSumCombiner);
        System.out.println("Sum of ages : " + sumOfAges);

        //--------------------Max combiner - note that 0 is fine as identity as this involve positive numbers
        BinaryOperator<Integer> maxSumCombiner = (age1, age2) -> Integer.max(age1 , age2);
        int  maxOfAges= people.stream().map(getAgeFunction).reduce(identity, maxSumCombiner);
        System.out.println("Max of ages : " + maxOfAges);



        //--- Getting all ages  - using accumulator + combiner
        BiFunction < ArrayList <Integer> , Person , ArrayList <Integer> > agesListAccumulator =  (list, p) -> { list.add(p.getAge()) ; return list;};

        BinaryOperator < ArrayList <Integer>>  agesListCombiner  = (list1, list2) -> { list1.addAll(list2) ; return list1 ;} ;

        ArrayList <Integer> identityList = new ArrayList<>();

        List<Integer> ages=  people.stream().reduce( identityList, agesListAccumulator, agesListCombiner );

        ages.forEach(System.out::println);

    }
}


