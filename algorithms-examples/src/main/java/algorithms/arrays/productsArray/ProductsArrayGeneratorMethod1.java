package algorithms.arrays.productsArray;

/**
 * Naive Solution:
 * Approach: Create two extra space, i.e. two extra arrays:
 * productsArray1 - store the product of all the array elements from start, up to that index
 * productsArray2 - store the product of all the array elements from the end of the array to that index.
 * <p>
 * To get the product excluding that index, multiply the prefix (productsArray1) product up to
 * index i-1 with the suffix (productsArray2)  product up to index i+1.
 * <p>
 * Algorithm:
 * <p>
 * Create two array prefix and suffix of length n, * i.e length of the original array,
 * initialize prefix[0] = 1 and suffix[n-1] = 1 and also another array to store the product.
 * <p>
 * Traverse the array from second index to end.
 * For every index i update prefix[i] as prefix[i] = prefix[i-1] * array[i-1],
 * i.e store the product upto i-1 index from the start of array.
 * <p>
 * Traverse the array from second last index to start.
 * For every index i update suffix[i] as suffix[i] = suffix[i+1] * array[i+1],
 * i.e store the product upto i+1 index from the end of array
 * <p>
 * Traverse the array from start to end.
 * For every index i the output will be prefix[i] * suffix[i],
 * the product of the array element except that element.
 * <p>
 * Complexity Analysis:  *
 * Time Complexity: O(n).
 * The array needs to be traversed three times, so the time complexity is O(n).
 * <p>
 * Space Complexity: O(n).
 * Two extra arrays and one array to store the output is needed so the space complexity is O(n)
 * Note: The above method can be optimized to work in space complexity O(1)
 */
public class ProductsArrayGeneratorMethod1 implements ProductsArrayGenerator {

    public int[] createProductsArray(int[] originalArray) {
        int originalArraySize = originalArray.length;

        // Base case
        if (originalArraySize == 1) {
            return new int[]{0};
        }

        // Initialize memory to all arrays
        int left[] = new int[originalArraySize];
        int right[] = new int[originalArraySize];
        int prod[] = new int[originalArraySize];
        int i, j;

        /* Left most element of left array is always 1 */
        left[0] = 1;

        /* Rightmost most element of right array is always 1 */
        right[originalArraySize - 1] = 1;

        /* Construct the left (prefix) array */
        for (i = 1; i < originalArraySize; i++) {
            left[i] = originalArray[i - 1] * left[i - 1];
        }

        /* Construct the right (suffix) array */
        for (j = originalArraySize - 2; j >= 0; j--) {
            right[j] = originalArray[j + 1] * right[j + 1];
        }

        /* Construct the product array using left[] and right[] */
        for (i = 0; i < originalArraySize; i++) {
            prod[i] = left[i] * right[i];
        }

        return prod;
    }
}
