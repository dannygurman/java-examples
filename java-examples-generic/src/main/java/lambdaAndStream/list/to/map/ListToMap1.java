package lambdaAndStream.list.to.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ListToMap1 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Mohan");
        list.add("Sohan");
        list.add("Mahesh");


        Function<String ,String> keyMapper = Function.identity();
        Function<String ,String> valueMapper = s->s;

        Map<String, Object> map = list.stream().collect(Collectors.toMap(keyMapper, valueMapper));

        map.forEach((x, y) -> System.out.println("Key: " + x +", value: "+ y));
    }
}
