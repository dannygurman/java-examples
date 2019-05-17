package algorithms.arrays.removeelement;

//Given an array and a value , remove all instances of that value in place and return new length;

//DO NOT allocate extra space by another array, you must do this un place with constant memory;

//The order of elements can be changed.
//It does not matter what you leave beyond the new length.

//Example:
//Given input array nums = [3 ,2 ,2 ,3 ] , val = 3
//Should return length = 2; with thw first two elements of num being 2.

//Solution time complexity - is O(n) + space complexity O(1)

import algorithms.utils.AlgorithmUtils;

public class RemoveElement {

    public static void main(String[] args) {
        int nums [] =  { 3 ,2 , 2, 3};
        int targetToRemove = 3;
        int maxIndex = removeElement(nums , targetToRemove);
        AlgorithmUtils.printArray(nums , 0 , maxIndex);
    }

    //Handle empty list
    public static int removeElement ( int [] A , int num) {
        if (A.length == 0){
            return 0;
        }

        int slow =0;

        for (int fast = 0; fast < A.length ; fast ++) {
            if (A[fast] != num){
                A[slow] = A[fast];
                slow ++;
            }
        }

        return  slow;
    }
}
