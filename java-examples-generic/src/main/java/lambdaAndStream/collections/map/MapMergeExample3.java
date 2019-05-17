package lambdaAndStream.collections.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class MapMergeExample3 {

    public static void main(String[] args) {

        BiConsumer<Integer ,  String > printEntryAction  =    (key , val) -> System.out.println(key + ":" + val);

        Map<Integer , String> map = new HashMap<>();
        map.put (1,"a");
        map.put (2,"b");
        map.forEach(printEntryAction);

        map.merge(1, "bla", String::concat);
        System.out.println("After merge:");
        map.forEach(printEntryAction);
    }
}
