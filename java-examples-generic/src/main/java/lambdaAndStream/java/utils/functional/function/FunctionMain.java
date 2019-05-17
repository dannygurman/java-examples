package lambdaAndStream.java.utils.functional.function;

import lambdaAndStream.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Created by dannyg on 11/2/2017.
 *
 *  * A function take an object an return another object (of another type)
 *
 */
public class FunctionMain {

    public static void main(String[] args) {
        System.out.println("Function");
        //Function<T, R>
        Function<Person, Integer> ageMapper = person -> person.getAge();
        Function <Person, Integer> ageMapper2 = Person::getAge;

        System.out.println("Bi Function");
        // BiFunction<T, U, R>
        BiFunction<Integer, Integer, String> adder = (x, y) -> { return "result:"+ x + y ;};
        System.out.println(adder.apply(3, 4));

      System.out.println("Calculator (bi function)");
        Calculator calculator = new Calculator();
        String result = calculator.calc((a, b) -> ": " + (a * b),3,  5);
        System.out.println(result);

        System.out.println("Unary operator");
        // UnaryOperator<T> extends Function<T, T>
        UnaryOperator<Integer> unaryOpt = i -> i*i;
        List<Integer> list = Arrays.asList(10,20,30,40,50);

        List <Integer> resultsList = unaryOperatorFun(unaryOpt, list);
        resultsList.forEach(x->System.out.println(x));


        System.out.println("Binary operator");
        //BinaryOperator<T> extends BiFunction<T,T,T> {
        BinaryOperator<Integer> adder2 = (n1, n2) -> n1 + n2;
        System.out.println(adder2.apply(3, 4));
    }

    private static List<Integer> unaryOperatorFun(UnaryOperator<Integer> unaryOpt, List<Integer> inList){
        List<Integer> resultList = new ArrayList<>();
        inList.forEach(i-> resultList.add(unaryOpt.apply(i)));
        return resultList;
    }

}
