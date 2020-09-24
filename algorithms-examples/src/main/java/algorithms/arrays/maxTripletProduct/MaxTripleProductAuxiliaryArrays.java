package algorithms.arrays.maxTripletProduct;

/*Approach 2: O(n) Time, O(n) Space

1. Construct four auxiliary arrays leftMax[], rightMax[], leftMin[] and rightMin[] of same size as input array.

2. Fill leftMax[], rightMax[], leftMin[] and rightMin[] in below manner.
    //leftMax[i] will contain maximum element on left of arr[i] excluding arr[i]. For index 0, left will contain -1.
    //leftMin[i] will contain minimum element on left of arr[i] excluding arr[i]. For index 0, left will contain -1.
    //rightMax[i] will contain maximum element on right of arr[i] excluding arr[i]. For index n-1, right will contain -1.
   //rightMin[i] will contain minimum element on right of arr[i] excluding arr[i]. For index n-1, right will contain -1.


3.For all array indexes i except first and last index, compute maximum of arr[i]*x*y where x can be leftMax[i]
    or leftMin[i] and y can be rightMax[i] or rightMin[i].

4. Return the maximum from step 3.*/

import java.util.Arrays;

public class MaxTripleProductAuxiliaryArrays {
    static int maxProduct(int []arr, int n)    {
        // if size is less than 3, no triplet exists
        if (n < 3)
            return -1;

        // Construct four auxiliary vectors
        // of size n and initiailize them by -1
        int[] leftMin = new int[n];
        int[] rightMin = new int[n];
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        Arrays.fill(leftMin, -1);
        Arrays.fill(leftMax, -1);
        Arrays.fill(rightMax, -1);
        Arrays.fill(rightMin, -1);

        // will contain max product
        int max_product = Integer.MIN_VALUE;

        // to store maximum element on left of array
        int max_sum = arr[0];

        // to store minimum element on left of array
        int min_sum = arr[0];

        // leftMax[i] will contain max element on left of arr[i] excluding arr[i].
        // leftMin[i] will contain min element on left of arr[i] excluding arr[i].
        for (int i = 1; i < n; i++) { //Iterating forward - skipping first index (value remain -1)

            leftMax[i] = max_sum;
            if (arr[i] > max_sum) {
                max_sum = arr[i];
            }

            leftMin[i] = min_sum;
            if (arr[i] < min_sum) {
                min_sum = arr[i];
            }
        }

        // reset max_sum to store maximum element on
        // right of array
        max_sum = arr[n - 1];

        // reset min_sum to store minimum element on
        // right of array
        min_sum = arr[n - 1];

        // rightMax[i] will contain max element on right of arr[i] excluding arr[i].
        // rightMin[i] will contain min element on right of arr[i] excluding arr[i].
        for (int j = n - 2; j >= 0; j--){ //Iterating backward - skipping last index (value remain -1)
            rightMax[j] = max_sum;
            if (arr[j] > max_sum) {
                max_sum = arr[j];
            }

            rightMin[j] = min_sum;
            if (arr[j] < min_sum) {
                min_sum = arr[j];
            }
        }

        // For all array indexes i except first and last, compute maximum of arr[i]*x*y where
        // x can be leftMax[i] or leftMin[i] and
        // y can be rightMax[i] or rightMin[i].
        for (int i = 1; i < n - 1; i++)  {
            int max1_a = arr[i] * leftMax[i] * rightMax[i];
            int max1_b = arr[i] * leftMin[i] * rightMin[i];
            int max1 = Math.max(max1_a, max1_b);

            int max2_a = arr[i] * leftMax[i] * rightMin[i];
            int max2_b = arr[i] * leftMin[i] * rightMax[i];
            int max2 = Math.max(max2_a, max2_b);

            max_product = Math.max(max_product, Math.max(max1, max2));
        }

        return max_product;
    }

    // Driver code
    public static void main (String[] args)  {
        int []arr = { 1, 4, 3, -6, -7, 0 };
        int n = arr.length;

        int max = maxProduct(arr, n);

        if (max == -1)
            System.out.println("No Triplet Exists");
        else
            System.out.println("Maximum product is "+max);
    }
}
