package compareSort.arraySort;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by dannyg on 01/08/2016.
 */
public class SortFruitObjectsArrayTest {

    @Before
    public void before() {

    }

    @Test
   public void testSortComparableObjectArray() {
        Fruit[] fruits = new Fruit[4];
        Fruit pineappale = new Fruit("Pineapple", "Pineapple description",70);
        Fruit apple = new Fruit("Apple", "Apple description",100);
        Fruit orange = new Fruit("Orange", "Orange description",80);
        Fruit banana = new Fruit("Banana", "Banana description",90);

        fruits[0]=pineappale;
        fruits[1]=apple;
        fruits[2]=orange;
        fruits[3]=banana;

        Arrays.sort(fruits);

        int i=0;
        for(Fruit temp: fruits){
            System.out.println("fruits " + ++i + " : " + temp.getFruitName() +
                    ", Quantity : " + temp.getQuantity());
        }

    }


}