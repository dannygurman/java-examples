package algorithms.arrays.find_duplicate_and_missing;

/**
 * Approach:
 * Create a temp array temp[] of size n with all initial values as 0.
 *
 * Traverse the input array arr[], and do following for each arr[i]
 * if(temp[arr[i]] == 0) temp[arr[i]] = 1;
 * if(temp[arr[i]] == 1) output “arr[i]” //repeating
 *
 * Traverse temp[] and output the array element having value as 0 (This is the missing element)
 * Time Complexity: O(n) *
 * Auxiliary Space: O(n)
 */
public class UsingCountArray_2_DuplicateAndMissingFinder extends DuplicateAndMissingFinder {

        @Override
    public FoundDuplicateAndMissingResult findDuplicateAndMissing(final int[] numbers) {
            int [] countArray = new int[numbers.length];
            for (int i=0; i < numbers.length; i++){
                int currentNum = numbers[i];
               int  countArrayIndex = currentNum -1;//Preventing array out of index when currentNum = N
                if (countArray[countArrayIndex] == 0) {
                    countArray[countArrayIndex] = 1;
                } else if (countArray[countArrayIndex] == 1) {
                   result.setDuplicate(currentNum);
                }
            }
            for (int i=0; i < countArray.length; i++){
                if (countArray[i] == 0){
                    int missingNum = i + 1; //remind countArrayIndex = currentNum -1
                    result.setMissing(missingNum);
                    break;
                }
            }
            return  result;
    }
}
