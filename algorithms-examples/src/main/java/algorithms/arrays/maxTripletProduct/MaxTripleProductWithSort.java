package algorithms.arrays.maxTripletProduct;
/*
Approach 3: O(nlogn) Time, O(1) Space

 1. Sort the array using some efficient in-place sorting algorithm in ascending order.
 2.   Return the maximum of product of last three elements of the array and product of
   first two elements and last element.
 */

import java.util.Arrays;

public class MaxTripleProductWithSort {
    /* Function to find a maximum product of a triplet
     in array of integers of size n */
    static int maxProduct(int arr[], int n) {
        // if size is less than 3, no triplet exists
        if (n < 3) {
            return -1;
        }

        // Sort the array in ascending order
        Arrays.sort(arr);

        // Return the maximum of product of last three elements and product of first two elements and last element
        int max1 = arr[0] * arr[1] * arr[n - 1]; //last three elements  - biggest numbers in sorted array
        int max2 = arr[n - 1] * arr[n - 2] * arr[n - 3]; //first two elements and last element   - smallest numbers (might be negative) + biggest number
        return Math.max(max1, max2);
    }

    // Driver program to test above functions
    public static void main(String[] args) {
        int arr[] = {-10, -3, 5, 6, -20};
        int n = arr.length;

        int max = maxProduct(arr, n);

        if (max == -1) {
            System.out.println("No Triplet Exists");
        } else {
            System.out.println("Maximum product is " + max);
        }

    }
}
