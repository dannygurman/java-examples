package lambdaAndStream.collections.map;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class MapMergeExample {

    public static void main(String[] args) {
        BiConsumer<String ,  List<String> > printEntryAction  =    (key , val) -> System.out.println(key + ":" + val);

        Map<String, List<String>>  map1= new HashMap<>();
        map1.put("A", new ArrayList<>(Arrays.asList("A1" , "A2")));
        map1.put("B", new ArrayList<>(Arrays.asList("B1" , "B2")));
        System.out.println(" MAP 1:");
        map1.forEach(printEntryAction);

        Map<String, List<String>>  map2= new HashMap<>();
        map2.put("A", new ArrayList<>(Arrays.asList("A3" , "A4")));
        map2.put("C", new ArrayList<>(Arrays.asList("C3" , "C4")));
        System.out.println(" MAP 2:");
        map2.forEach(printEntryAction);


        BiFunction <List<String> , List<String> ,  List<String> >  mergeListsBiFunction= (existingValues, newValues) -> {
            existingValues.addAll(newValues);
            return existingValues;
        };

        BiConsumer <String  , List<String> >  mergeToMap1Consumer = (key, value) ->   map1.merge(key, value,mergeListsBiFunction);


        map2.forEach(mergeToMap1Consumer);


        System.out.println(" MAP 1 after merge:");
        map1.forEach(printEntryAction);



    }
}
