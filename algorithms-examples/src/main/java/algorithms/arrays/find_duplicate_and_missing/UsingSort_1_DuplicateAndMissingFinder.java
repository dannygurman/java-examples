package algorithms.arrays.find_duplicate_and_missing;

import java.util.Arrays;

/**
 * Method 1 (Use Sorting)
 * Approach:  Sort the input array.
 * Traverse the array and check for missing and repeating.
 * <p>
 * Time Complexity: O(nLogn) - SORT
 */
public class UsingSort_1_DuplicateAndMissingFinder extends DuplicateAndMissingFinder {


    public FoundDuplicateAndMissingResult findDuplicateAndMissing(int[] numbers) {
       //First sorting - o(nlogn) time
        Arrays.sort(numbers);

        int previous = 0;
        for (int current : numbers) {
            if (current == previous) {
                result.setDuplicate(current);
            } else if (current - previous > 1) {
                result.setMissing(current - 1);
            }
            if (result.bothFound()) {
                return result;//No need to continue
            }
            //Updating previous
            previous = current;


        }
        return result;
    }


}
