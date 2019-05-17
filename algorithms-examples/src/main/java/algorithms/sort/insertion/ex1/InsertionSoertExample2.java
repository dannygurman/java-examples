package algorithms.sort.insertion.ex1;

import java.util.Arrays;

public class InsertionSoertExample2 {


    public static void main(String[] args) {
        int [] arr  =  {54,26,93,17,77,31,44,55,20};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void  insertionSort(int arr[]) {
        for (int index = 1; index < arr.length ; index++){

            int currentValue = arr[index];//The value is current index
            int  position = index;
            System.out.println("\nStart iteration for index " + index + " currentValue " + currentValue + Arrays.toString(arr));

            while ( (position > 0) &&   ( arr[ position - 1] > currentValue) ) { //while previous greather then current - shifting
                arr[position] = arr [position - 1];
                position--;
            }

            arr[position] = currentValue;

            System.out.println("End of iteration " + Arrays.toString(arr));

        }
    }


}
