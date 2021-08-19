package algorithms.arrays.findMissingNumber;

/**
 * Method 1: This method uses the technique of the summation formula.

 * the formula x elements  x*(x+1)/2.
 * sum of n+1 elements (n is the size of input array with missing element)  - (n + 1) * (n + 2) / 2
 *
 * Now find the sum of all the elements in the array and subtract it from the sum of first n+1 natural numbers,
 * it will be the value of the missing element.
 *
 * Algorithm:
 * Calculate the sum of first n+1 natural numbers as sumtotal= (n + 1) * (n + 2) / 2
 *
 * Create a variable sum to store the sum of array elements.
 * Traverse the array from start to end.
 * Update the value of sum as sum = sum + array[i]
 *
 * Print the missing number as sumtotal – sum
 *
 * Complexity Analysis:
 * Time Complexity: O(n).
 * Only one traversal of the array is needed.
 * Space Complexity: O(1).
 * No extra space is needed
 */

public class MissingElementFinder_1_UsingSumFormula implements MissingElementFinder {

    @Override
    public int findMissingElement(final int[] nums) {
        int n = nums.length;
      // 1+ 2+ ... + n = n(n+1) / 2,
        //// total of  1 +... n + (n+1)  =  (n + 1) * (n + 2) / 2  - remind that nums (with length n)  has missing element
        int total = (n + 1) * (n + 2) / 2; // total of  (1... n+1) natural numbers

        for (int i = 0; i < n; i++) {
            total -= nums[i];
        }
        return total;
    }
}
