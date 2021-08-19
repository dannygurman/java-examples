package algorithms.arrays.findMissingElement;

import algorithms.arrays.findMissingElement.MissingElementFinder;

/**
 * Method 1: This method uses the technique of the summation formula.
 *
 * Approach: The length of the array is n-1. So the sum of all n elements,
 * i.e sum of numbers from 1 to n can be calculated using
 * the formula * n*(n+1)/2.
 *
 * Now find the sum of all the elements in the array and subtract it from the sum of first n natural numbers,
 * it will be the value of the missing element.
 *
 * Algorithm:
 * Calculate the sum of first n natural numbers as sumtotal= n*(n+1)/2
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
        int length = nums.length;

        int total = (length + 1) * (length + 2) / 2;

        for (int i = 0; i < length; i++) {
            total -= nums[i];
        }
        return total;
    }
}
