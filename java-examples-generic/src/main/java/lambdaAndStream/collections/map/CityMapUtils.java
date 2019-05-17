package lambdaAndStream.collections.map;

import lambdaAndStream.City;
import lambdaAndStream.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by dannyg on 11/19/2017.
 */
public class CityMapUtils {

    public static  Map<City, List<Person>> createInitialCityMap() {
        Map<City, List<Person>> cityToPersonMap = new HashMap<>() ;

        City paris = new City("Paris");
        List parisPeople = new ArrayList();
        parisPeople.add(new Person("d", 3));
        parisPeople.add(new Person("d2", 4));

        City telAviv = new City("Tel-Aviv");
        List telAvivPeople = new ArrayList();
        telAvivPeople.add(new Person("e", 3));
        telAvivPeople.add(new Person("e2", 4));

        cityToPersonMap.put(paris , parisPeople);
        cityToPersonMap.put(telAviv , telAvivPeople);

        return cityToPersonMap;
    }


    public static Map<Integer,Integer> getNumbersMap () {
        Map<Integer, Integer> numbers = new HashMap<>();
        for (int i = 1; i <= 10; ++i) {
            numbers.put(i, i);
        }
        return numbers;
    }


    public static BiConsumer<City , List <Person> > getPrintEntryBiConsumer() {
        return  (city , list) -> System.out.println(city + ":" + list.size() + " people");
    }

}
