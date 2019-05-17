package algorithms.arrays.twosum;

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
        You may assume that each input would have exactly one solution, and you may not use the same element twice.
        Example:
        Given nums = [2, 7, 11, 15], target = 9,
        Because nums[0] + nums[1] = 2 + 7 = 9,
        return [0, 1].
*/

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    //Approach 2: Two-pass Hash Table
   // We reduce the look up time from O(n)O(n) to O(1)O(1) by trading space for speed.
    // A hash table is built exactly for this purpose, it supports fast look up in near constant time.
    // I say "near" because if a collision occurred, a look up could degenerate to O(n)O(n) time.
   // But look up in hash table should be amortized O(1) time as long as the hash function was chosen carefully.


    public static void main(String[] args) {
        int [] nums = { 2 ,7 , 11 ,15};
        int target = 18;
        int []  result = twoSum( nums,  target);
        System.out.println(java.util.Arrays.toString(result));
    }

//Return indexes of the two num that sum to target
    public static int[] twoSum(int[] nums, int target) {
        //map for num and index pair
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int currentValue = nums[i];
            int complement = target - currentValue;

            if ( map.containsKey(complement) ) {
                int complementIndex = map.get(complement);
                //match found
                return new int[] { i, complementIndex };
            } else {
                //adding index and value to map
                 map.put(currentValue, i);
            }
        }
        throw new IllegalArgumentException("No two twosum solution");
    }
}
