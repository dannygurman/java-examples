package lambdaAndStream;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by DannyG on 8/27/2017.
 */
public class MethodReference {

    //Example 1
    Function<Person , Integer> f = person -> person.getAge();

    Function<Person , Integer> f2 = Person::getAge ;

    //Example 2
    BinaryOperator <Integer> sum1 = (i1 , i2) -> i1 + i2;
    BinaryOperator <Integer> sum2 = (i1 , i2) -> Integer.sum(i1 , i2);

  //Method reference
    BinaryOperator <Integer> sum2b = Integer::sum;


    Consumer<String > printer = s -> System.out.print(s);
    Consumer<String > printer2 = System.out::print;
}
