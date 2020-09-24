package algorithms.arrays.maxTripletProduct;

/* Approach 4: O(n) Time, O(1) Space
     1. Scan the array and compute Maximum, second maximum and third maximum element present in the array.
     2. Scan the array and compute Minimum and second minimum element present in the array.
     3. Return the maximum of product of -
     Maximum, second maximum and third maximum
     and product of -
     Minimum, second minimum and Maximum element.
      Note – Step 1 and Step 2 can be done in single traversal of the array.
   */

public class MaxTripleProductMaxMinElements {

    /* Function to find a maximum product of a triplet
    in array of integers of size n */
    static int maxProduct(int arr[], int n){
        // if size is less than 3, no triplet exists
        if (n < 3) {
            return -1;
        }

        // Initialize Maximum, second maximum and third maximum element
        int maxA = Integer.MIN_VALUE, maxB = Integer.MIN_VALUE, maxC = Integer.MIN_VALUE;

        // Initialize Minimum and second mimimum element
        int minA = Integer.MAX_VALUE, minB = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            // Update Maximum, second maximum and third
            // maximum element
            if (arr[i] > maxA) {
                maxC = maxB;
                maxB = maxA;
                maxA = arr[i];
            }

            // Update second maximum and third maximum element
            else if (arr[i] > maxB)  {
                maxC = maxB;
                maxB = arr[i];
            }

            // Update third maximum element
            else if (arr[i] > maxC) {
                maxC = arr[i];
            }

            // Update Minimum and second mimimum element
            if (arr[i] < minA){
                minB = minA;
                minA = arr[i];
            }

            // Update second mimimum element
            else if(arr[i] < minB) {
                minB = arr[i];
            }
        }

        int max1 = minA * minB * maxA;
        int max2 = maxA * maxB * maxC;

        return Math.max(max1,max2);
    }

    // Driver program to test above function
    public static void main (String [] args) {
        int arr[] = {1, -4, 3, -6, 7, 0};
        int n = arr.length;

        int max = maxProduct(arr, n);
        if (max == -1) {
            System.out.println("No Triplet Exists");
        } else {
            System.out.println("Maximum product is " + max);
        }
    }

}
