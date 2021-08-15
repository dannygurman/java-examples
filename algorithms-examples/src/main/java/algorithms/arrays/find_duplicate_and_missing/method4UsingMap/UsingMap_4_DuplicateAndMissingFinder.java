package algorithms.arrays.find_duplicate_and_missing.method4UsingMap;

import algorithms.arrays.find_duplicate_and_missing.DuplicateAndMissingFinder;
import algorithms.arrays.find_duplicate_and_missing.FoundDuplicateAndMissingResult;

import java.util.HashMap;
import java.util.Map;

public class UsingMap_4_DuplicateAndMissingFinder extends DuplicateAndMissingFinder {

    /**
     * This method involves creating a Hashtable with the help of Map.
     * In this, the elements are mapped to their natural index.
     * In this process, if an element is mapped twice,
     * then it is the repeating element.
     * And if an element’s mapping is not there, then it is the missing element.
     */

    Map<Integer, Boolean> numberMap = new HashMap<>();

    @Override
    public FoundDuplicateAndMissingResult findDuplicateAndMissing(final int[] numbers) {
        for (Integer currentNum : numbers) {
            if (numberMap.get(currentNum) == null) {
                numberMap.put(currentNum, true);
            } else {
                result.setDuplicate(currentNum);
            }
        }

        for (int i = 1; i <= numbers.length; i++) {
            if ( ! numberMap.containsKey(i))  {
                result.setMissing(i);
                break;
            }
        }

        return result;
    }

}
