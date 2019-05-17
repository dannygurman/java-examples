package compareSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dannyg on 01/08/2016.
 */
public class CollectionSortExample {

    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();

        fruits.add("Pineapple");
        fruits.add("Apple");
        fruits.add("Orange");
        fruits.add("Banana");

        Collections.sort(fruits);

        int i=0;
        for(String temp: fruits){
            System.out.println("fruits " + ++i + " : " + temp);
        }
    }

}
