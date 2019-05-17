package algorithms.arrays.sortcolor;

public class SortColorTwoPassVer2 {

    public static void sortColors(int [] nums) {
        if(nums==null||nums.length<2){
            return;
        }

        //Creating the count array
        int[] countArray = new int[3];
        for(int i=0; i<nums.length; i++){
            countArray[nums[i]]++;
        }

        //In this phase example array:
        //countArray[0] = 8 //num of 0's
        //countArray [1] = 9// num of 1's
       // countArray[2] = 8 // num of 2's
        for(int i=1; i<=2; i++){
            countArray[i]=countArray[i-1]+countArray[i];
        }
//in this phase array contain the end index -1 for each "color"
       // or -the num of elements equal or lower
// example array - countArray[0] = 8 , countArray[1] = 17 , countArray[2] = 25

        int[] sorted = new int[nums.length];

        for(int i=0;i<nums.length; i++){
            int currentNumValue = nums[i];
            int index = countArray[currentNumValue]-1;

            //reducing 1 from count of current value
            countArray[currentNumValue] = countArray[currentNumValue]-1;
            //adding current num in calculated index
            sorted[index]=currentNumValue;
        }

        //copy sorted result  to num
        System.arraycopy(sorted, 0, nums, 0, nums.length);  
    }
}
