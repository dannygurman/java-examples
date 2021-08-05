package compareSort.arraySort;

import java.util.Arrays;

/**
 * Created by dannyg on 01/08/2016.
 *
 *  * How about sorting with Fruit’s “fruitName” or “Quantity”? The Comparable interface is only allow to sort a single property.
 * To sort with multiple properties, you need Comparator. See the new updated Fruit class again :
 *
 */
public class SortFruitObjectTest {

    public static void main(String[] args) {

        Fruit[] fruits = new Fruit[4];

        Fruit pineappale = new Fruit("Pineapple", "Pineapple description",70);
        Fruit apple = new Fruit("Apple", "Apple description",100);
        Fruit orange = new Fruit("Orange", "Orange description",80);
        Fruit banana = new Fruit("Banana", "Banana description",90);

        fruits[0]=pineappale;
        fruits[1]=apple;
        fruits[2]=orange;
        fruits[3]=banana;

        System.out.println("Sorting with FruitNameComparator" );
        Arrays.sort(fruits, Fruit.FruitNameComparator);
        printFruits(fruits );

        System.out.println("Sorting with Default Comparator" );
        Arrays.sort(fruits);
        printFruits(fruits );
    }

    private static void printFruits(Fruit[] fruits ) {
        int i=0;
        for(Fruit fruit: fruits){
            System.out.println("fruits " + ++i + " : " + fruit.getFruitName() +
                    ", Quantity : " + fruit.getQuantity());
        }
    }



}
