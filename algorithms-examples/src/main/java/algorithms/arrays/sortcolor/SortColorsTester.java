package algorithms.arrays.sortcolor;

import algorithms.utils.AlgorithmUtils;

public class SortColorsTester {

    //Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
    // with the colors in the order red, white and blue.

    //Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
    public static void main(String[] args) {
        //int [] nums = { 2 , 0 , 0 , 1 ,2 ,0 ,1 ,2};
        int [] nums = {  1, 2 , 2 ,1 , 0 , 0 , 1 ,2 ,1, 1 ,2 , 0, 1, 0 ,1 ,0 ,2 ,0 , 2, 2 ,1 ,2 ,0 ,0 ,1};

        //SortColorPointersVer1.sortColors( nums);

       // SortColorPointersVer2.sortColors(nums);

       // SortColorTrivialTwoPass.sortColors(nums);

        SortColorTwoPassVer2.sortColors(nums);

        //SortColorTwoPassVer3Improved.sortColors(nums);

        AlgorithmUtils.printArray(nums);
        //[0, 0, 0, 0, 1, 2, 2, 2]
    }



}
