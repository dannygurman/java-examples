package lambdaAndStream.list.to.map;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ListToMapWithMergeFunction {

   // a merge function, used to resolve collisions between
    //values associated with the same key, as supplied

    public static void main(String[] args) {

        List<Person> list = new ArrayList<>();
        list.add(new Person(100, "Mohan"));
        list.add(new Person(100, "Sohan"));
        list.add(new Person(300, "Mahesh"));

        Function<Person ,Integer> keyMapper = Person::getId; //Method reference
        Function<Person ,String> valueMapper = Person::getName;

       // Represents an operation upon two operands of the same type, producing a result * of the same type as the operands
        BinaryOperator <String> binaryOperator =  (x, y) ->  x+", "+ y;

        Map<Integer, String> map = list.stream().collect(Collectors.toMap(keyMapper, valueMapper, binaryOperator));

        map.forEach((x, y) -> System.out.println("Key: " + x +", value: "+ y));

       // Output:
       // Key: 100, value: Mohan, Sohan
        //Key: 300, value: Mahesh


        //If we want to return LinkedHashMap, we need to pass supplier as LinkedHashMap::new.
        Supplier< LinkedHashMap<Integer, String>> mapSupplier = LinkedHashMap::new;

        LinkedHashMap<Integer, String> map2 = list.stream().collect(Collectors.toMap(keyMapper, valueMapper, binaryOperator , mapSupplier));

        map2.forEach((x, y) -> System.out.println("Key: " + x +", value: "+ y));

        // Output:
        // Key: 100, value: Mohan, Sohan
        //Key: 300, value: Mahesh

    }
}
