package lambdaAndStream.mapReduce;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;


//Demo mapReduce + pitfalls - using  reduction and non-associative reduction +//reduction that has no identity element
//Red - reduction
//Associative : Red (a , Red (b , c)) = Red (Red (a , b) , c )
public class ReductionExamplesJdk7 {

    public static void main(String[] args) {

        List<Integer> allInts = Arrays.asList(0 ,1 , 2 , 3 , 4 , 5,  6 , 7 , 8 , 9);
        List<Integer> ints1 = Arrays.asList(0 ,1 , 2 , 3 , 4);
        List<Integer> ints2 = Arrays.asList(5,  6 , 7 , 8 , 9);

        //Sum is associative
        BinaryOperator <Integer>  sumBinaryOperator  =  (i1 ,i2) -> i1 + i2 ;
        int identity = 0;
        checkReduction ("(i1 ,i2) -> i1 + i2" , allInts , ints1 ,  ints2  , identity , sumBinaryOperator);

        //Max is associative - but with no identity
        //Using 0 on negative number will results error !!!!!!
        BinaryOperator <Integer>  maxBinaryOperator  =  (i1 ,i2) -> Integer.max(i1 , i2);
        checkReduction ("(i1 ,i2) -> Integer.max(i1 , i2)" , allInts , ints1 ,  ints2  , identity , maxBinaryOperator);

        //Not associative
        BinaryOperator <Integer>  multipleOfSumBinaryOperator  =  (i1 ,i2) -> (i1 + i2) * (i1 + i2);
        checkReduction ("(i1 ,i2) -> (i1 + i2) * (i1 + i2)" , allInts , ints1 ,  ints2  , identity , multipleOfSumBinaryOperator);

        //Associative operation
        BinaryOperator <Integer>  firstElementBinaryOperator  =  (i1 ,i2) -> i1;
        checkReduction ("(i1 ,i2) -> i1;" , allInts , ints1 ,  ints2  , identity , firstElementBinaryOperator);

        //Not associative operation
        BinaryOperator <Integer>  sumDivide2BinaryOperator  =  (i1 ,i2) -> (i1 + i2) / 2;
        checkReduction (" (i1 ,i2) -> (i1 + i2) / 2;" , allInts , ints1 ,  ints2  , identity , sumDivide2BinaryOperator);

    }


    private static  void checkReduction (String operatorDescription , List<Integer> allValues , List<Integer> partialValues1 , List<Integer> partialValues2  ,  int identity , BinaryOperator <Integer> binaryOperator ) {
        System.out.println("\nCheck reduction using operator  :" + operatorDescription + ".    identity:" + identity);

        int sumReductionResults = reduce (allValues , identity , binaryOperator);
        System.out.println("Reduction Results on  array  :" + sumReductionResults);

        //Simulating parallel computation.

        int reductionResultsPartial1 = reduce (partialValues1 , identity , binaryOperator);
        int reductionResultsPartial2 = reduce (partialValues2 , identity , binaryOperator);
        int reductionResultsParallelSimulate =  reduce (Arrays.asList(reductionResultsPartial1 , reductionResultsPartial2) , identity , binaryOperator);
        System.out.println("Reduction Results on  array - parallel  :" + reductionResultsParallelSimulate);

        System.out.println("Result match: :" + (sumReductionResults == reductionResultsParallelSimulate));
    }


    private static int reduce(List<Integer> values , int valueIfEmpty , BinaryOperator <Integer> reduction) {
        int result = valueIfEmpty;
        for (int value : values){
            result = reduction.apply(result , value);
        }
        return result;
    }
}
