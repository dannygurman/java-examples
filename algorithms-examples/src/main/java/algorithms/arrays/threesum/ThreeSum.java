package algorithms.arrays.threesum;

import algorithms.utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {

  //  Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
    // Note: The solution set must not contain duplicate triplets.

  /* For example, given array S = [-1, 0, 1, 2, -1, -4],
    A solution set is:
            [
            [-1, 0, 1],
            [-1, -1, 2]
            ]

            https://www.programcreek.com/2012/12/leetcode-3sum/

     The idea is to sort an input array and then run through all indices of possible first element of a triplet.
    For each possible first element we make a standard bi directional 2 sum sweep of the remaining part of the array.
    Also we want to skip equal elements to avoid duplicates in answer

    TIME COMPLEXITY O(N^2) (all element in list are processes for every index
            */

    public static void main(String[] args) {
        int [] nums = { -1 , 0 ,1 ,2 ,-1 ,-4};
        List <List<Integer>> foundTriple = threeSum(nums);
        foundTriple.forEach(list -> AlgorithmUtils.print(list));
    }

    public static List <List<Integer>> threeSum ( int [] nums){

        List <List<Integer>> result = new LinkedList<>();

        //Initial check that contain at least 3 numbers
        if(nums == null || nums.length<3)  return result;


        //First - sorting array !!!!
        Arrays.sort(nums);

        //iterate over numbers
        for(int i=0; i<nums.length-2; i++){
            if(i==0 || nums[i] != nums[i-1]){
                int lowInd = i+1;//low index
                int highInd = nums.length-1;

                while( lowInd < highInd){
                    int sumOfTriple = nums[i]+nums[lowInd]+nums[highInd];

                    if( sumOfTriple == 0){
                      //Founf matching triple !
                        result.add(Arrays.asList(nums[i] , nums[lowInd] , nums[highInd] ));

                        //Increasing low
                       lowInd++;

                        //Reducing high
                       highInd--;

                        //handle duplicate here
                        while(lowInd<highInd && nums[lowInd]==nums[lowInd-1])
                            lowInd++;
                        while(lowInd<highInd && nums[highInd]==nums[highInd+1])
                            highInd--;

                    } else if(sumOfTriple < 0){
                        lowInd++;

                    } else {//sumOfTriple > 0
                        highInd--;
                    }
                }
            }

        }
        return result;
    }
}
