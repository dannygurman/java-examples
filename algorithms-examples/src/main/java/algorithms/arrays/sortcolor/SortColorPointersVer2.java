package algorithms.arrays.sortcolor;

import algorithms.utils.AlgorithmUtils;

public class SortColorPointersVer2 {

   // All the element in the range [0, left -1 ] are 0 (less than the pivot == 1)

   // All the elements in the range [right, A.length - 1] are 2 (larger than the pivot == 1)

    //All the elements in the range [left, curIndex] are 1 (equal to the pivot == 1)


    public static void sortColors(int [] A) {
        int pivot = 1;
        colorSort(A, pivot);
    }

    public static void colorSort(int[] A, int pivot) {
        int left = 0;

        int right = A.length - 1;
        int i  = 0;
        while(i <= right) {
            if (A[i] < pivot) {//a[i]=0
               // if A[curIndex] < pivot, we exchange it with A[left], now A[left] == 0,
                // A[curIndex] == 1, to keep the Invariant , we let left++ and curIndex++.
                AlgorithmUtils.swap(A, left, i);
                left++;
                i++;
            } else if (A[i] > pivot) { //a[i] =2
                //if A[curIndex] > pivot, we exchange it with A[right], now A[right] == 2,   then right–
                AlgorithmUtils.swap(A, i, right);
                right--;
                continue;//not increasint i
            } else if (A[i] == pivot) {//a[i] =1
               // if A[curIndex] == pivot, we just have curIndex++.
                i++;
            }
        }
        int x = A.length;
    }
}
