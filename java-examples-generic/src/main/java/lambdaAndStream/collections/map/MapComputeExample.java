package lambdaAndStream.collections.map;

import lambdaAndStream.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Compute: Computes a new value from: *
 * the key passed as a parameter, that maynot be in the map * -
 * the value that may be associated with that key, OR null * -
 * the lambda that will compute the remapping (Bifunction)
 *
 * computeIfAbsent  a new value from:
 *  the key passed as a parameter, that should not be in the map *
 * the lambda to compute the mapping from the key (Function)
 *
 * computeIfPresent  a new value from:
 * The key passed as a parameter, that should be in the map
 * the existing value -  can not be null
 * the lambda to compute the remapping from the key and the existing value (Bifunction)
 *
 */
public class MapComputeExample {


    public static void main(String[] args) {

        computeExample();

        computeIfAbsentExample();

    }


    private static  void computeExample() {
        System.out.println("\ncomputeExample:\n");
        Map<String, Integer> stringToIntMap = new HashMap<>();
        BiConsumer<String , Integer > printEntryAction  =    (key , val) -> System.out.println(key + ":" + val);
        stringToIntMap.put("a", 1);
        stringToIntMap.put("b", 1);

        System.out.println("\nPrint initial map:");
        stringToIntMap.forEach(printEntryAction);


        BiFunction<String , Integer , Integer> multipleBiFunctionNullSafe = (key , oldval ) ->  oldval == null ? 0 : (oldval * 2);

        //Exist key
        stringToIntMap.compute("a" ,multipleBiFunctionNullSafe);

        //Not exist key
        stringToIntMap.compute("x" ,multipleBiFunctionNullSafe);

        System.out.println("\nPrint multiplied map:");
        stringToIntMap.forEach(printEntryAction);
    }


    private static  void computeIfAbsentExample() {
        System.out.println("\ncomputeIfAbsentExample:\n");
        //Useful to build map sof maps(for instance)
        Map<String, List<Person>> idToPersonListMap= new HashMap<>();
        BiConsumer<String , List<Person> > printEntryAction  =    (key , val) -> System.out.println(key + ":" + val);

        Function <String , List<Person>> initListFunction =   key -> new ArrayList<>();

        // key, newValue - if absent computing function on key and adding to map
        List <Person> computedListForKey = idToPersonListMap.computeIfAbsent("one",initListFunction);
        computedListForKey.add(new Person("danny" , 41));

        System.out.println("\nPrint computed map:");
        idToPersonListMap.forEach(printEntryAction);
    }

}
