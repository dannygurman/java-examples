package algorithms.hash.ex1;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.Map;



/**
 * Created by dannyg on 1/10/2018.
 *
 * O(N^2) to build the hash +
 * O(N) * O(1) to go over the array and search for the opposite twosum
 * = O(N^2) time,
 * O(N^2) space for the hash
 *
 */
public class HashSolution {

    public static void main(String[] args) {
        int arr [] = NumArrayUtils.generateNumbers();
        find (arr , true);
    }

    private static class IntPair {
        public IntPair(int first, int seconds) {
            this.first = first;
            this.seconds = seconds;
        }

        int first;
        int seconds;

    }

    private static  void find(int[] arr , boolean stopOnFirstFind) {
        Multimap  <Integer , IntPair > sumMap = generateSumMap (  arr );
        //printMap ( sumMap , arr);

        boolean found = false;
        int tripleIndex = 1;
        for (int i=0; i< arr.length ; i++ ) {
            int keyVal =  - arr[i] ;//The negative value of the value in index i
            if (sumMap.containsKey(keyVal)); {
                Collection <IntPair> pairs = sumMap.get(keyVal);
                for (IntPair pair : pairs) {
                    //Pairs found matching the key
                    int j = pair.first;
                    int k = pair.seconds;
                    if ( i == j || i == k) {
                        continue;//skip - if one of the foind index same as current checked index
                    }
                    //Found match in map - printing
                    NumArrayUtils.printTriple(tripleIndex, arr, i, j, k);
                    tripleIndex++;//Increasing num of founds triples -//relevant If need to found more then one and pring indexes

                    if (stopOnFirstFind) {
                        return;
                    }
                }
            }
        }



    }


    //This MULTY map contains all sums of all pairs - size ^N2
    private static   Multimap  <Integer , IntPair > generateSumMap ( int[] arr ) {
        Multimap <Integer, IntPair> sumMap = ArrayListMultimap.create();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int sum = arr[i] + arr[j];
                sumMap.put(sum, new IntPair(i, j));
            }
        }
        System.out.println("Array length:"+arr.length +" Map size "+sumMap.size());
        printMap(sumMap, arr);
        return sumMap;
    }



    private static void printMap (Multimap <Integer , IntPair > map , int[] arr) {
        for ( Map.Entry <Integer , IntPair > entry : map.entries())  {
            int sum = entry.getKey();
            IntPair intPair =  entry.getValue();
            int firstIndex = intPair.first;
            int secondsIndex = intPair.seconds;
            System.out.println("twosum :" + sum + " arr[firstIndex] " + arr[firstIndex]  + " arr[secondsIndex] " + arr[secondsIndex]  );
        }
    }


}
