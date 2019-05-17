package algorithms.arrays.sortcolor;

//In solution 1, two arrays are created.
// One is for counting, and the other is for storing the sorted array (space is O(n)).
// We can improve the solution so that it only uses constant space.
// Since we already get the count of each element, we can directly project them to the original array,
// instead of creating a new one.//


public class SortColorTwoPassVer3Improved {

    public static void sortColors(int[] nums) {
        if(nums==null||nums.length < 2){
            return;
        }

        //Creating the count array
        int[] countArray = new int[3];
        for(int i=0; i<nums.length; i++){
            countArray[nums[i]]++;
        }

        int colorIndex = 0;//color index is also coloe value
        int k = 0;
        //running on colors from 0 to 2
        while( colorIndex <= 2 ){

            //runnin g on color until count is 0
            int countForColor = countArray[colorIndex];
            if( countForColor != 0 ){
                nums[k]= colorIndex;
                k++;
                //reducing 1 from count of current value (we use 1)
                countArray[colorIndex] = countForColor - 1;
            }else{
                //moving to next color
                colorIndex++;
            }
        }
    }
}
