package algorithms.arrays.twosum;

import java.util.HashMap;
import java.util.Map;

public class TwoSum2 {

    public static void main(String[] args) {
        int [] nums = { 2 ,7 , 11 ,15};
        int target = 18;
        int []  result = twoSum( nums,  target);
        System.out.println(java.util.Arrays.toString(result));
    }

    public  static int [] twoSum (int [] nums, int target) {
        Map <Integer,Integer> valToIndex = new HashMap<>();
        for (int i=0 ; i < nums.length; i++) {
            int num = nums[i];
            int comp = target - num;
            if  (valToIndex.containsKey(comp)){
                int com_ind = valToIndex.get(comp);
                return new int [] {i , com_ind};
            } else {
                valToIndex.put(num, i);
            }

        }
        return new int []{};
    }
}
