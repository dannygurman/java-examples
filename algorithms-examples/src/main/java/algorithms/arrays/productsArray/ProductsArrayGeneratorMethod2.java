package algorithms.arrays.productsArray;

/**
 * Efficient solution:
 * Approach: In the previous solution, two extra arrays were created to store the prefix and suffix,
 * in this solution store the prefix and suffix product in the output array (or product array) itself.
 * Thus reducing the space required.
 * <p>
 * Algorithm:
 * <p>
 * Create an array product and initialize its value to 1 and a variable temp = 1.
 * Traverse the array from start to end.
 * <p>
 * For every index i update product[i] as product[i] = temp and temp = temp * array[i],
 * i.e store the product upto i-1 index from the start of array.
 * <p>
 * initialize temp = 1 and traverse the array from last index to start.
 * For every index i update product[i] as product[i] = product[i] * temp
 * and temp = temp * array[i], i.e multiply with the product upto i+1 index from the end of array.
 * <p>
 * Time Complexity: O(n).
 * The original array needs to be traversed only once, so the time complexity is constant.
 * <p>
 * Space Complexity: O(n).
 * Even though the extra arrays are removed,
 * the space complexity remains O(n), as the product array is still needed.
 */
public class ProductsArrayGeneratorMethod2 implements ProductsArrayGenerator {

    public int[] createProductsArray(int[] originalArray) {
        int originalArraySize = originalArray.length;

        // Base case
        if (originalArraySize == 1) {
            return new int[]{0};
        }

        int i, temp = 1;

        /* Allocate memory for the product array */
        int products[] = new int[originalArraySize];

        /* Initialize the product array as 1 */
        for (int j = 0; j < originalArraySize; j++) {
            products[j] = 1;
        }

        /* In this loop, temp variable contains product of elements on left side excluding arr[i] */
        for (i = 0; i < originalArraySize; i++) {
            products[i] = temp;
            temp *= originalArray[i];
        }

        /* Initialize temp to 1 for product on right side */
        temp = 1;

        /* In this loop, temp variable contains product of elements on right side excluding arr[i] */
        for (i = originalArraySize - 1; i >= 0; i--) {
            products[i] *= temp;
            temp *= originalArray[i];
        }

        return products;
    }
}
