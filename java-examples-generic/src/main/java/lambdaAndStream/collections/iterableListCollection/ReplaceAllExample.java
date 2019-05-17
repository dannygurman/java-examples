package lambdaAndStream.collections.iterableListCollection;

import lambdaAndStream.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class ReplaceAllExample {

    public static void main(String[] args) {


        //Replace all
        System.out.println("1 Replace all");
        List < String> names = Arrays.asList("danny" , "moshe", "danna");

        UnaryOperator< String> toUpperCaseUnOperator = name -> name.toUpperCase();

        //replaceAll - in List interface
        names.replaceAll(toUpperCaseUnOperator);
        names.forEach(System.out::println);
    }
}
