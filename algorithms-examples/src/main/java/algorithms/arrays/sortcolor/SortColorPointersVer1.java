package algorithms.arrays.sortcolor;

import algorithms.utils.AlgorithmUtils;

public class SortColorPointersVer1 {

    //one - begin of ones 1111

    //begin of two2 11111
    //in every step - the sub array before is already sorted (001122)

    //
    public static void sortColors(int [] A) {
        int one = 0;//beginning of ones
        int two = 0; //beginning of two
        for (int i = 0; i < A.length ; i ++){
            if (A[i] == 0) {
                //if current value 0 -
                //move to this location (end) the start of 2
                //move to start of 2 - start of one
                //move to start of 1 - o
                //increasing start of 1 and 2
                A [i] = A [two];
                A [two] = A [one];
                A [one] = 0;
                one ++;
                two ++;
            }  else if (A[i] == 1) {
                //if value 1 -
                //move to this location (end) the start of 2
                //move to start of 2 - start of one
                A [i] = A [two];
                A [two] = 1;
                two ++;
            } else {
                //A[i] = 2
                continue; //not required - just to make answer clear
            }

            System.out.println("i="+ i) ;
            AlgorithmUtils.printArray(A , 0 , i);
        }

    }
}
