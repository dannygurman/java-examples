package algorithms.arrays.productsArray;

import org.junit.Assert;
import org.junit.Test;

public class ProductsArrayTest {


    @Test
    public void test_ProductsArray_Method1() {
        ProductsArrayGenerator productsArrayGenerator = new ProductsArrayGeneratorMethod1();
        testProductArrayInternal(productsArrayGenerator);
    }

    @Test
    public void test_ProductsArray_Method2() {
        ProductsArrayGenerator productsArrayGenerator = new ProductsArrayGeneratorMethod2();
        testProductArrayInternal(productsArrayGenerator);
    }

    public void testProductArrayInternal(ProductsArrayGenerator productsArrayGenerator) {
        int originalArray[] = {10, 3, 5, 6, 2};
        int[] expectedProductsArray = {180, 600, 360, 300, 900};
        int[] foundProductsArray = productsArrayGenerator.createProductsArray(originalArray);
        Assert.assertArrayEquals(expectedProductsArray, foundProductsArray);
    }
}

