package algorithms.plusone;

//Given non negative integer represented as a non empty array of digits  -
// implement plus one (addition 1) to an integer

import algorithms.utils.AlgorithmUtils;

public class PlusOne {
    public static void main(String[] args) {
        int [] digits =  { 1 , 9 ,9 , 9 };// { 9 , 9 ,9 , 9 };
        int  [] result = plusOne(digits);
        AlgorithmUtils.printArray(result);
    }

    public static int [] plusOne (int [] digits) {
        int n = digits.length;
        for  (int i = n - 1 ; i >= 0 ; i-- ){
            if ( digits[i] < 9 ) {
                //digit below 9 - just Increasing digit and returning
                digits [i]++;
                return digits;
            }
            //else
            digits [i] = 0; //setting current digit to 0 and adding 1 to next digit (by moving to next iteration
        }
        //if we got here we need to add digit  000000 n tims + 1
        int [] newNumber = new int [ n + 1];
        newNumber [0] = 1 ; //first digit is 1
        return  newNumber;

    }
}
