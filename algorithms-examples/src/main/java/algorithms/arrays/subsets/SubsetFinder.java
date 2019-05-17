package algorithms.arrays.subsets;

import algorithms.utils.AlgorithmUtils;

import java.util.*;

//Given a collection (duplicate possible)  of  integers ,nums, return all possible subsets
//The solution set must not contains duplicate subsets ( like {1,2,3} and {3 ,2 ,1}.
/*   For example sulotion for {1 ,2 ,3}:
  []
[1]
[1, 2]
[1, 2, 3]
[1, 3]
[2]
[2, 3]
[3]
*/
public class SubsetFinder {

    public static void main(String[] args) throws Exception{
        int [] nums = {1 ,2 ,2 , 3 };//,4};
        List <List <Integer>> list  = subsets (nums);
        AlgorithmUtils.printListOfLists(list);

    }

    public static List <List <Integer>> subsets (int [] nums) {
        List <List<Integer>> subsets  =new ArrayList<>();

        //Sorting input
        Arrays.sort(nums);

        backtrack (subsets , new ArrayList<>()  ,nums , 0);

        return subsets;
    }


   /* Using recursion
    All subsets =
     subsets (start with nums[0]) + (all combination NOT included nums[0]) +
     subsets (start with nums[1]) + (all combination NOT included nums[0] and num[1]) +
    subsets (start with nums[2]) + (all combination NOT included nums[0] and num[1] and num [1]) +
    .
    .
    .
     nums[n-1]
*/

    private static void backtrack (List <List <Integer>> resultList , List <Integer> tempList , int [] nums , int start) {

        //tempList contains subset prefix to add

        //Add input list - to subset
        resultList.add(new ArrayList<> (tempList));

        //UNCOMMENT FOR DEBUG - System.out.println("start " + start);
        //UNCOMMENT FOR DEBUG -printSubsets (subsets);

        System.out.println("-");
        for (int i = start ; i <nums.length ; i++) {
            System.out.println(i);
            //Adding current value to the input temp list (with prefix members )  before passing it to recursion

            int curValue = nums[i];

            if ( i > start && curValue == nums[ i - 1] ) continue; //skip duplicates

            tempList.add(curValue);


            //Recursion call - after adding current index value to temp list
            // start index  next member  (we want to avoid adding subsets which were already added
            backtrack (resultList , tempList ,  nums , i + 1);

            //Remove the current value that we add before moving to next iteration
            tempList.remove(tempList.size() -1);
        }
    }


    // backtrack
}
