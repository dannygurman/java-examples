package lambdaAndStream.mapReduce;

import lambdaAndStream.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class IntSumMapReduceExample {

    private static Predicate < Person > withoutDannyFilter = person -> ( ! "DANNY".equalsIgnoreCase(person.getName()) );

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        people.add(new Person ("A" ,1));
        people.add(new Person ("B" ,30));
        people.add(new Person ("C" ,40));
        people.add(new Person ("DANNY" ,41));


        System.out.println("Sum ages above 20 (without 'DANNY') using 'regular' stream:  " + getSumAgesAbove20UsingRegularStream( people) );

        System.out.println("Sum ages above 20 (without 'DANNY') using Intstream:  " + getSumAgesAbove20UsingIntStream( people) );

        System.out.println("Sum all ages (with 'DANNY') using combined map reduce (accumulator + combiner):  " + getSumAgesCombinedMapReduce( people) );
    }


    private static  int getSumAgesAbove20UsingRegularStream(List<Person> people) {

        Predicate < Integer> ageAbove20Predicate = age -> (age > 20);

        Function<Person , Integer > getAgeToFunction = p -> ( p.getAge());

        BinaryOperator<Integer> intSumBinaryOperator =  (integer, integer2) -> (Integer.sum(integer, integer2 ));

        Integer sumIdentity = 0 ;

        return people.stream()
                .parallel()
                .filter(withoutDannyFilter)
                .map(getAgeToFunction)
                .filter(ageAbove20Predicate)
                .reduce(sumIdentity ,intSumBinaryOperator );
    }



    private static  int getSumAgesAbove20UsingIntStream(List<Person> people) {

        ToIntFunction<Person> getAgeToIntFunction = p -> ( p.getAge());

        IntPredicate ageAbove20IntPredicate = age -> (age > 20);

        return  people.
                stream().
                parallel().
                filter(withoutDannyFilter).
                mapToInt(getAgeToIntFunction).
                filter(ageAbove20IntPredicate).
                sum();
    }



    private static  int getSumAgesCombinedMapReduce(List<Person> people) {

        Integer sumIdentity = 0 ;

       // The accumulator BiFunction (2nd parameter) is to map stream element type T to U, and at the same time it does the accumulation.
        BiFunction<Integer , Person , Integer > personAgSumAccumulator = (age , person)  -> Integer.sum ( age , person.getAge());

       // The combiner BinaryOperator (3rd parameter) is specifically needed in parallel streams to combine the various split results together at the end.
        // Please note that, the Java 8 stream designers chose to enforce rules which should be working for both sequential and parallel streams without making any specific changes.
        BinaryOperator<Integer> intSumCombiner=  (integer, integer2) -> Integer.sum(integer, integer2 );


        //TEST  1 :The identity value must be an identity for the combiner function:
        // //combiner(identity, u) == u
        int testAgeValue = 100;
        Person testPerson = new Person("X" , 50);
        boolean identityMatchCombiner = ( intSumCombiner.apply(sumIdentity , testAgeValue).equals( testAgeValue) );
        System.out.println("TEST 1: Identity match combiner :" + identityMatchCombiner );


       // Associativity
        //TEST 2  combiner must be compatible with accumulator such as:
        //combiner.apply(u, accumulator.apply(identity, t)) == accumulator.apply(u, t)

        boolean combinerCompatibleWithAccumalator = intSumCombiner.apply( testAgeValue, personAgSumAccumulator.apply(sumIdentity, testPerson) ).equals( personAgSumAccumulator.apply(testAgeValue, testPerson) );
        System.out.println("TEST 2: Combiner compatible with accumalator :" + combinerCompatibleWithAccumalator );


        //Performing the twosum - using accumulator and combiner
        return people.
                stream().
                parallel().
                reduce(sumIdentity ,personAgSumAccumulator , intSumCombiner );
    }


}
