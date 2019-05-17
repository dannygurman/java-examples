package algorithms.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class AlgorithmUtils {

    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void printArray( int [] array , int startIndex , int endIndex) {
        printArray(Arrays.copyOfRange(array ,startIndex,endIndex));
    }
    public static void printArray( int [] array ) {
        System.out.println(Arrays.toString(array));
    }
    public static void print (List<Integer> list ){
        System.out.println(Arrays.toString(list.toArray())) ;
    }

    public static void printListOfLists (List <List <Integer>> subsets ){
        subsets.forEach( subset -> print(subset) );
    }

}
