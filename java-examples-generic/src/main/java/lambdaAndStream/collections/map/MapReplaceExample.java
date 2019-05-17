package lambdaAndStream.collections.map;

import lambdaAndStream.City;
import lambdaAndStream.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class MapReplaceExample {

    public static void main(String[] args) {
        Map<String, Integer> stringToIntMap = new HashMap<>();
        BiConsumer<String , Integer > printEntryAction  =    (key , val) -> System.out.println(key + ":" + val);

       stringToIntMap.put("a", 1);
       stringToIntMap.put("b", 2);

        System.out.println("\nPrint initial map:");
        stringToIntMap.forEach(printEntryAction);

        //Replace - exist value
        stringToIntMap.replace("a" ,1 , 10);
        //Replace - non exist value
        stringToIntMap.replace("b" ,1234 , 10);

        System.out.println("\nAfter replacement:");
        stringToIntMap.forEach(printEntryAction);

        BiFunction <String , Integer , Integer>  multipleBiFunction = ( key , oldval ) -> oldval * 2;

        stringToIntMap.replaceAll(multipleBiFunction);

        System.out.println("\nAfter replace all:");
        stringToIntMap.forEach(printEntryAction);

    }
}
