package algorithms.sort.quick.ex1;


import algorithms.utils.AlgorithmUtils;

import java.util.Arrays;

class QuickSortExample {

    // Implementation of quicksort algorithm in Java
    public static void main(String[] args)
    {
        int a[] = { 9, -3, 5, 2, 6, 8, -6, 1, 3 };

        quickSort(a, 0, a.length - 1);

        // print the sorted array
        System.out.println(Arrays.toString(a));
    }


    // Quicksort routine
    public static void quickSort(int[] a ,int start, int end)  {

        // base condition
        if (start >= end) {
            return;
        }

        // rearrange the elements across pivot
        int pivot = partition(a, start, end);

        // recurse on sub-array containing elements less than pivot
        quickSort(a, start, pivot - 1);

        // recurse on sub-array containing elements more than pivot
        quickSort(a, pivot + 1, end);
    }


    // Partition using Lomuto partition scheme
    public static int partition(int[] a, int start, int end)    {

        // Pick rightmost element as pivot from the array
        int pivotVal = a[end];

        System.out.println("Entering partition - start index:" + start + " end index: " + end );

        // elements less than pivot will be pushed to the left of pIndex
        // elements more than pivot will be pushed to the right of pIndex
        // equal elements can go either way
        int pIndex = start; //Initial pivot index in start

        // each time we finds an element less than or equal to pivot,
        // pIndex is incremented and that element would be placed
        // before the pivot.
        for (int i = start; i < end; i++) {
           // System.out.println("Current index:" + i);
            if (a[i] <= pivotVal) {
                AlgorithmUtils.swap(a, i, pIndex);
                //Moving pIndex right
                pIndex++;
                System.out.println("Pivot index:" + pIndex);
            }
        }

        // swap pIndex with Pivot
        AlgorithmUtils.swap (a, end, pIndex);

        System.out.println("Exiting partition.Pivot value " + pivotVal +" array:" +Arrays.toString(a));

        // return pIndex (index of pivot element)
        return pIndex;


    }




}
