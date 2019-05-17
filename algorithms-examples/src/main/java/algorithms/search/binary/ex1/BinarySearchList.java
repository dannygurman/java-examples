package algorithms.search.binary.ex1;

import algorithms.utils.FileReader;

import java.util.Collections;
import java.util.List;

/**
Binary search on sorted list:

 Binary search runs in at worst logarithmic time, making O(log n) comparisons - base 2
 where n is the number of elements in the array,

 SPACE - O(1)) space, meaning that the space taken by the algorithm is the same for any number of elements in the array.

 Although specialized data structures designed for fast searching—such as hash tables—can be searched more
 efficiently, binary search applies to a wider range of problems.


 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class BinarySearchList {

    /**
     * This class should not be instantiated.
     */
    private BinarySearchList() { }


    /**
     * Returns the index of the specified key in the specified array.
     *
     * @param  listInts the list of integers, must be sorted in ascending order
     * @param  key the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public static int indexOf(List <Integer> listInts, int key) {
        int lo = 0;
        int hi = listInts.size() - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            int midValue = listInts.get(mid);
            if ( key < midValue )  {
                hi = mid - 1;
            }
            else if (key > midValue ) {
                lo =  mid + 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * Reads in a sequence of integers from the whitelist file, specified as
     * a command-line argument; reads in integers from standard input;
     * prints to standard output those integers that do <em>not</em> appear in the file.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        // read the integers from a file
        int key = 3;
        FileReader fileUtils = new FileReader("in_ints.txt");
        List <Integer> whitelist = fileUtils.readInts(true);

        // sort the array (!)
        Collections.sort(whitelist);

        System.out.println("\nsorted:");
        System.out.println(whitelist.toString());

        System.out.println("key:" + key);
        System.out.println("key index:" + BinarySearchList.indexOf(whitelist, key));
    }

}