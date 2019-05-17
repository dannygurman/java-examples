package lambdaAndStream.list.to.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class ListToMap2 {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person(100, "Mohan"));
        list.add(new Person(200, "Sohan"));
        list.add(new Person(300, "Mahesh"));

        Function<Person ,Integer> keyMapper = Person::getId; //Method reference
        Function<Person ,String> valueMapper = Person::getName;


        Map<Integer, String> map = list.stream()
                .collect(Collectors.toMap(keyMapper, valueMapper));

        map.forEach((x, y) -> System.out.println("Key: " + x +", value: "+ y));
    }
}
