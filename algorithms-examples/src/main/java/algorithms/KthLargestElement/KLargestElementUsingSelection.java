package algorithms.KthLargestElement;

//The smart approach for this prpblem to use the selection algorithm
// (based on the partition method - the same one as used in quick sort)

//Complexity O(N) best case / O(N^2) worst case running time + O(1) memory.System

//  To improve - make O(N) guaranteed?// - by randomizing input.

import algorithms.utils.AlgorithmUtils;

import java.security.AlgorithmParametersSpi;
import java.util.Random;

public class KLargestElementUsingSelection {


    public static void main(String[] args) {
        int  k = 2;
        int secondLargestElement = findKthLargest ( new int [] {3 , 2, 1 ,5 ,6 ,4} , k);
        System.out.println("2th largest element = " + secondLargestElement);

    }
    public static  int findKthLargest( int [] nums , int k) {

        //Shuffle input to improve efficiency (O(N) guaranteed
        shuffle (nums);

        //k -> n-k  like in array of 6 and k=2  (2th largest elemet) 2 -> 6-2 = 4
        // //requested index num = array length - k
        k = nums.length - k;

        int lo = 0;
        int hi = nums.length - 1;

        while (lo < hi) {
            final int splitIndex = partition( nums , lo , hi);
            //All element with index lower then splitIndex - have lower value the the one in splitIndex
            //All element with index higher then splitIndex - have higher value the the one in splitIndex

            System.out.println("Split index:" + splitIndex);

            if ( splitIndex < k) {
                //search in sub array right to split index
                lo = splitIndex + 1;
            } else if ( splitIndex > k){
                //search in sub array left to split index
                hi = splitIndex -1;
            } else { //k = split index //found
                break;
            }
        }

        return nums [ k];
    }


    private static int partition (int [] a , int lo , int hi) {
      int pivotIndex = lo;
        int i = lo;
        int j = hi + 1;

        int pivotVal =  a[lo];

        while (true) {
            //Moving leftmark right while values lower then pivot
            while ( (i < hi) &&  (a[++i] < pivotVal));

            while ( (j > lo) &&  (a[--j] > pivotVal  ));

            if ( i >= j ) {
                //split point found
                break;
            }
            //See quicksort
            AlgorithmUtils.swap(a , i , j);
        }
        //j is split point
        AlgorithmUtils.swap(a , pivotIndex , j);

        return j;
    }

    private static void shuffle (int a[] ) {
        final Random random = new Random();
        for (int ind =1 ; ind < a.length ; ind++) {
            final int r = random.nextInt(ind + 1 );
            AlgorithmUtils.swap(a , ind , r);
        }

    }





}
