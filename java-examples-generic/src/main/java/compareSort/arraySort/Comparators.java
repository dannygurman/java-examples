package compareSort.arraySort;

import java.util.Comparator;

public class Comparators {

    public static Comparator<Fruit> FruitNameAscendingComparator =
        (Fruit fruit1, Fruit fruit2) -> compareFruitNames(fruit1, fruit2, true);

    public static Comparator<Fruit> FruitNameDescendingComparator =
        (Fruit fruit1, Fruit fruit2) -> compareFruitNames(fruit1, fruit2, false);


    private static int compareFruitNames(Fruit fruit1, Fruit fruit2, boolean ascendingOrder) {
        String fruitName1 = fruit1.getFruitName();
        String fruitName2 = fruit2.getFruitName();
        return compareStringInsensitive(fruitName1, fruitName2, ascendingOrder);
    }

    public static int compareStringInsensitive(String s1, String s2, boolean ascendingOrder) {
        if (ascendingOrder) {
            return String.CASE_INSENSITIVE_ORDER.compare(s1, s2);
        } else {
            //descending order
            return String.CASE_INSENSITIVE_ORDER.compare(s2, s1);
        }
    }

}
