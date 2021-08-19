package algorithms.arrays.FindDisappearedNumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisappearedNumsFinderMethod_1_MarkingIndex implements DisappearedNumsFinder{

    @Override
    public List<Integer> findMissingNubers(final int[] nums) {
        System.out.println("Nums array before marking:\n" + Arrays.toString(nums));
        for (int i = 0; i < nums.length; i++) {
            int indexToMark = Math.abs(nums[i]) - 1;
            if (nums[indexToMark] > 0) {
                nums[indexToMark] *= -1;
            }
        }
        System.out.println("Nums array after marking:\n" + Arrays.toString(nums));
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
