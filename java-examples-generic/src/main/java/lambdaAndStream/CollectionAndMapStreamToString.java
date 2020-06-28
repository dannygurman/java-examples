package lambdaAndStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectionAndMapStreamToString {

    static class InternalEntity {
        int x = 5;
        int y = 3;
        @Override
        public String toString() {
            return "Some{" +
                "x=" + x +
                ", y=" + y +
                '}';
        }
    }

    private final static Collector<CharSequence, ?, String> JOIN_STRING_COLLECTOR =  Collectors.joining(",");
    private final static Function <InternalEntity , String> INTERNAL_ENTITY_TO_STRING = x -> x.toString();
    private final static String MAP_ENTRY_FORMAT = "%s : %s ";


    private static <T>  Function <T , String> getNullSafeDescriptionMapper (Function <T , String> objectToDescriptionMapper){
        Function <T , String> nullSafeObjToDescriptionMapper = x -> x != null ? objectToDescriptionMapper.apply(x) : "NULL";
        return nullSafeObjToDescriptionMapper;
    }

    private static <T> String getJoinedString (Collection<T> collection,
                                               Function <T , String> objectToDescriptionMapper) {
        if (collection == null){
            return "NULL collection";
        }
        Function <T , String> nullSafeObjToDescriptionMapper = getNullSafeDescriptionMapper (objectToDescriptionMapper);
        return collection.stream().map(nullSafeObjToDescriptionMapper).collect( JOIN_STRING_COLLECTOR);
    }

    public static String getJoinedInternalEntityCollection (Collection<InternalEntity> collection) {
        return getJoinedString (collection, INTERNAL_ENTITY_TO_STRING);
    }

    private static <T> String getJoinedString (Map<String, T> map,
                                               Function <T , String> objectToDescriptionMapper) {
        if (map == null){
            return "NULL map";
        }
        Function <T , String> nullSafeObjToDescriptionMapper = getNullSafeDescriptionMapper (objectToDescriptionMapper);
        Function <Map.Entry<String , T> ,String>  entryDescriptionMapper = entry ->
            String.format(MAP_ENTRY_FORMAT, entry.getKey() ,  nullSafeObjToDescriptionMapper.apply(entry.getValue()));
        return map.entrySet().stream().map(entryDescriptionMapper).collect( JOIN_STRING_COLLECTOR);
    }

    public static String getJoinedInternalEntityMap (Map<String, InternalEntity> map) {
        return getJoinedString (map, INTERNAL_ENTITY_TO_STRING );
    }

    public static void main(String[] args) {
        Map<String , InternalEntity> myMap = new HashMap<>();
        myMap.put("1", null);
        myMap.put("2", new InternalEntity());
        myMap.put("3", new InternalEntity());
        myMap.put(null, new InternalEntity());

        String joinedMap =  getJoinedInternalEntityMap (myMap);
        System.out.println(joinedMap);

        System.out.println("-----------------------");

        Collection <InternalEntity> list = new ArrayList<>();
        list.add(new InternalEntity());
        list.add(null);
        String joinedList = getJoinedInternalEntityCollection (list);
        System.out.println(joinedList);


    }

}
