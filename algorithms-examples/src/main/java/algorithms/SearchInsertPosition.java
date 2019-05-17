package algorithms;

/*Given a sorted array and a target value , return the index if the target is found.
If not  return the index where it would be if it were inserted in order.
You may assume no duplicates.class
Examples:
[1 ,3 ,5 ,6 ] ,5 ? 2
[1 ,3 5 ,6 ] , 2 ? 1
[1 ,3 5 ,6 ] , 7 ? 4
 [1 ,3 5 ,6 ] , 0 ? 0

 */
public class SearchInsertPosition {

    public static void main(String[] args) {
        int [] nums = { 1 ,3 , 5 ,6 };
        int target = 5;
        int result = searchInsert (nums , target);
        System.out.println("result:" + result);
    }

    public static  int searchInsert ( int [] nums , int target) {

        int low = 0;
        int high = nums.length - 1;

        while (low <= high){
            int mid = (high + low) / 2;
            int midVal = nums[mid];
            if (midVal == target) {
                return mid;
            }
            if ( midVal > target) {
                high = mid  -1  ;
            } else {
                low = mid  + 1;
            }
        }

        return low;

    }
}
