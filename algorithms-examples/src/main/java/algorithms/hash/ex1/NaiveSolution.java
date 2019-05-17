package algorithms.hash.ex1;

/**
 * Created by dannyg on 1/10/2018.
 *
 * O(N^3) time, O(1) space
 *
 *
 */
public class NaiveSolution {
//using 3 indexes
    private static  void find(int[] arr , boolean stopOnFirstFind) {
        int tripleIndex = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int sumPair = arr[i] + arr[j];
                for (int k = j + 1; k < arr.length; k++) {
                    if ( sumPair + arr[k] == 0) {
                        NumArrayUtils.printTriple(tripleIndex, arr, i, j, k);
                        tripleIndex++;
                        if (stopOnFirstFind) {
                            return;
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        int arr [] = NumArrayUtils.generateNumbers();
        find (arr , true);
    }
}
