package algorithms.arrays.nextPremutation;

import algorithms.utils.AlgorithmUtils;

public class NextPremutationExample1 {

   /*
    Analysis
    The steps to solve this problem:
  1) scan from RIGHT TO LEFT, find the first element that is less than its previous one.,example
  4 5 6 3 2 1
    |
    p (5 first element less then the right-6)

2) scan from right to left, find the first element that is greater than p.
4 5 6 3 2 1
    |
    q (6 greater then 5)

  3) swap p and q
  4 5 6 3 2 1
swap
4 6 5 3 2 1

 reverse elements [p+1, nums.length]
  1 2 3 5 - > 5 3 2 1

 4 6 1 2 3 5

     */

    public static void main(String[] args) {

        testNextPermutation(new int []{4 , 5 ,6, 3 ,2, 1});
        testNextPermutation( new int []{5 ,4 ,3 , 2 , 1});

    }

    private static void testNextPermutation(int[] nums){
        nextPermutation(nums);
        AlgorithmUtils.printArray(nums);
    }




    private static  void nextPermutation(int[] nums) {
        if(nums == null || nums.length<2)
            return;

        int p=0;//pointer to first place where num[p] is lower then the value in right position
        for(int i=nums.length-2; i>=0; i--){
            if(nums[i]<nums[i+1]){
                p=i;
                break;
            }
        }


        int q = 0; //pointer to first place where value is higher then  nums[p]
        for(int i=nums.length-1; i>p; i--){
            if(nums[i]> nums[p]){
                q=i;
                break;
            }
        }

        //neer to revert all indexes  like - 5 4 3 2 1 -> 12345
        if(p==0 && q==0){
            reverse(nums, 0, nums.length-1);
            return;
        }

        //swap p , q
        swap ( nums , p , q);

        //reverse all element on the right side
        if(p<nums.length-1){
            reverse(nums, p+1, nums.length-1);
        }
    }

    public static void swap (int []  nums , int i , int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }


    public static void reverse(int[] nums, int left, int right){
        while(left<right){
            int temp = nums[left];
            nums[left]=nums[right];
            nums[right]=temp;
            left++;
            right--;
        }
    }

}
