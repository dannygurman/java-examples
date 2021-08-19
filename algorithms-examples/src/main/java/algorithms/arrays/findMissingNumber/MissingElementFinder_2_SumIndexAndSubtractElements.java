package algorithms.arrays.findMissingNumber;
/*
Iterating - sum index and subtracting current value

Approach: The approach remains the same but there can be overflow if n is large.
In order to avoid integer overflow iterating over array , adding element value and subtract index

Create a variable sum = 1 which will store the missing number and a counter variable c = 2.
Traverse the array from start to end.
Update the value of sum as sum = sum – array[i] + c and update c as c++.
Print the missing number as a sum.

* Complexity Analysis:
    * Time Complexity: O(n).
    * Only one traversal of the array is needed.
    * Space Complexity: O(1).*/

public class MissingElementFinder_2_SumIndexAndSubtractElements implements MissingElementFinder {

    @Override
    public int findMissingElement(final int[] nums) {
        int total = 1; //First element of (1... n+1) - sum of indexes from 1 to (num.length + 1)

        for (int i = 2; i <= (nums.length + 1); i++) {
            total += i; // Adding the index i (2 .. (n+1))
            total -= nums[i - 2];
        }
        return total;
    }
}
