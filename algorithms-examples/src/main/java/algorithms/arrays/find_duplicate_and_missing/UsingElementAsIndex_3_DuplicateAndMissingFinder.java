package algorithms.arrays.find_duplicate_and_missing;

/*
Method 3 (Use elements as Index and mark the visited places)
    Approach:
    Traverse the array. While traversing,
    use the absolute value of every element as an index and make the value at this index as
    negative to mark it visited.
     If something is already marked negative then this is the repeating element.
      To find missing, traverse the array again and look for a positive value.
*/

public class UsingElementAsIndex_3_DuplicateAndMissingFinder extends DuplicateAndMissingFinder {

    @Override
    public FoundDuplicateAndMissingResult findDuplicateAndMissing(final int[] numbers) {
        for (int currentNumber : numbers) {
            int absVal = Math.abs(currentNumber);
            int arrayIndexToMark = absVal - 1; //Preventing array out of index when absVal= N
            if (numbers[arrayIndexToMark] > 0) {
                //marking visited by switching sign to negative
                numbers[arrayIndexToMark] = -numbers[arrayIndexToMark];
            } else {
                result.setDuplicate(absVal);
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > 0) {
                result.setMissing(i + 1); //remind that arrayIndexToMark = absVal -1
                break;
            }
        }
        return result;
    }

}
