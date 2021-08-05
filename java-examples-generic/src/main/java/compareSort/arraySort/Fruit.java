package compareSort.arraySort;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;

/**
 * Created by dannyg on 01/08/2016.
 */

@Data
@AllArgsConstructor
public class Fruit implements Comparable<Fruit>{

    private String fruitName;
    private String fruitDesc;
    private int quantity;

    public int compareTo(Fruit compareFruit) {
        int compareQuantity = ((Fruit) compareFruit).getQuantity();
        //ascending order
        return this.quantity - compareQuantity;
        //descending order
        //return compareQuantity - this.quantity;

    }

    public static Comparator<Fruit> FruitNameComparator  = (Fruit fruit1, Fruit fruit2)  -> {
            String fruitName1 = fruit1.getFruitName().toUpperCase();
            String fruitName2 = fruit2.getFruitName().toUpperCase();
            //ascending order
            return fruitName1.compareTo(fruitName2);
            //descending order
            //return fruitName2.compareTo(fruitName1);
        };

}