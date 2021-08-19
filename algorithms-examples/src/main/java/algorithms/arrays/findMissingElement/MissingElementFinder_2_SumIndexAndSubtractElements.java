package algorithms.arrays.findMissingElement;
/*
Iterating - sum index and subtracting current value

* Complexity Analysis:
    * Time Complexity: O(n).
    * Only one traversal of the array is needed.
    * Space Complexity: O(1).*/

public class MissingElementFinder_2_SumIndexAndSubtractElements implements MissingElementFinder {

    @Override
    public int findMissingElement(final int[] nums) {
        int total = 0;
        int counter = 3;
        for (int j = 1; j < counter; j++) {
            total += j;
        }

        for (int i = counter; i <= (nums.length -1 + counter); i++) {
            total += i;
            total -= nums[i - counter];
        }
        return total;
    }
}
