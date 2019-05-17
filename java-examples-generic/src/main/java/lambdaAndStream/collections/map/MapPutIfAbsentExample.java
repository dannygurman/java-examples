package lambdaAndStream.collections.map;

import lambdaAndStream.City;
import lambdaAndStream.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class MapPutIfAbsentExample {

    public static void main(String[] args) {

        Map<City, List<Person>> cityToPersonMap = CityMapUtils.createInitialCityMap();

        BiConsumer<City , List <Person> > printEntryAction  = CityMapUtils.getPrintEntryBiConsumer();


        City ramatGan = new City("RamatGan");

        //If the specified key is NOT already associated with a value (or is mapped
        //to  null)  associates it with the given value and returns
        //null, else returns the current value.
        System.out.println("Put if absent");
        City yaffo = new City("Yaffo");

        //Will also work fine -  cityToPersonMap.put(yaffo , null);
        cityToPersonMap.putIfAbsent(yaffo , new ArrayList<Person>());//We make sure that null will not be returned (initialization)
        cityToPersonMap.get(yaffo).add(new Person ("test" , 5));//Now we can safely call add -


        //Return the previous value.Will not take effect
        cityToPersonMap.putIfAbsent(ramatGan , new ArrayList<>());

        System.out.println("\nPrint map:");
        cityToPersonMap.forEach(printEntryAction);
    }
}
