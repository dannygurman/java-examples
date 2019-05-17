package lambdaAndStream.collections.map;

import lambdaAndStream.City;
import lambdaAndStream.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by dannyg on 11/16/2017.
 */
public class MaoGetOrDefaultExample {

    public static void main(String[] args) {

        Map<City, List<Person>> cityToPersonMap = CityMapUtils.createInitialCityMap();


        System.out.println("1 Initial map - without RamatGan\n");
        BiConsumer<City , List <Person> > printEntryAction  = CityMapUtils.getPrintEntryBiConsumer();
        cityToPersonMap.forEach(printEntryAction);


        System.out.println("2. Get Or Default\n");
        // Returns the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key.
        City ramatGan = new City("RamatGan");
        List foundRamatGanPeople = cityToPersonMap.getOrDefault(ramatGan , new ArrayList<Person>());

        System.out.println("Getting default (empty list)  for key ramatGan (as no matching entry exist) :\n");
        foundRamatGanPeople.forEach(System.out::println);//Will not return null

        System.out.println("Adding ramatGan People \n");
        List ramatGanPeople = new ArrayList();
        ramatGanPeople.add(new Person("cdf", 3));
        //This time entry is found
        cityToPersonMap.put(ramatGan , ramatGanPeople);

        foundRamatGanPeople = cityToPersonMap.getOrDefault(ramatGan , new ArrayList<Person>());
        foundRamatGanPeople.forEach(System.out::println);





    }
}
