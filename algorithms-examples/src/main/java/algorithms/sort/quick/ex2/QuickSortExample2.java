package algorithms.sort.quick.ex2;

import algorithms.utils.AlgorithmUtils;

import java.util.Arrays;

public class QuickSortExample2 {

    public static void main(String[] args) {
        int [] arr  =  {54,26,93,17,77,31,44,55,20};

        quickSort(arr);

        System.out.println(Arrays.toString(arr));
    }


    private static void  quickSort(int[] arr) {
        quickSortHelper(arr, 0 ,arr.length-1);
    }

    private static void quickSortHelper (int[] arr , int first , int last) {
        if ( first < last){

            int splitPoint = partition(arr ,first, last);

            quickSortHelper(arr ,first,splitPoint-1 );

            quickSortHelper(arr ,splitPoint+1 ,last);
        }
    }

    private static int  partition(int[] arr , int first , int last){
        int pivotValue = arr[first];//Taking pivot value from first index//

        System.out.println("In partition start. array:" +   Arrays.toString(Arrays.copyOfRange(arr , first , last + 1)) + " Pivot:" + pivotValue);
        int leftmark = first+1;
        int rightmark = last;

        boolean done = false;
        while ( ! done){
//Moving left mark right while still  lower then pivot
            while ( (leftmark <= rightmark) &&  (arr[leftmark] <= pivotValue) ) {
                leftmark = leftmark + 1;
            }
//Moving left mark left while still  higher then pivot
            while( (arr[rightmark] >= pivotValue) && (rightmark >= leftmark) ) {
                rightmark = rightmark - 1;
            }


            if (rightmark < leftmark) {
                done = true; // stopping
            }   else {
                //Swapping right mark and leftmark
                AlgorithmUtils.swap (arr , rightmark , leftmark);
            }
        }

        //Swap the value in first index (the pivot value) with the value in  split point (rightmark)
        AlgorithmUtils.swap (arr , first , rightmark);

        System.out.println("In partition end.  array:" +  Arrays.toString(Arrays.copyOfRange(arr , first , last + 1)));

        //Rightmark is the split point
        return rightmark;

    }



}