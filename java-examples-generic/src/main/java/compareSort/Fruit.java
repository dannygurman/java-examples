package compareSort;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by dannyg on 01/08/2016.
 */

@Data
@AllArgsConstructor
public class Fruit implements Comparable<Fruit> {
    private String fruitName;
    private int quantity;

    public int compareTo(Fruit compareFruit) {
        int compareQuantity = ((Fruit) compareFruit).getQuantity();
        //ascending order
        return this.quantity - compareQuantity;
        //descending order
        //return compareQuantity - this.quantity;

    }


}