package algorithms.sort.bubble.ex1;

/**
 * Created by dannyg on 1/8/2018.
 *
 * Bubble sort has worst-case and average complexity both О(n2), where n is the number of items being sorted.
 *
 * The only significant advantage that bubble sort has over most other implementations, even quicksort,
 * but not insertion sort, is that the ability to detect that the list is sorted efficiently is built into the algorithm.
 * When the list is already sorted (best-case), the complexity of bubble sort is only O(n).
 *
 * .
 */
public class BubbleSortExample {

    static void bubbleSort(int[] arr) {
        int n = arr.length;


        for(int i=0; i < n; i++){

            for(int j=0; j < (n - i -1); j++){

                if(arr[j] > arr[j +1]){
                    //swap elements
                    swap (arr , j  , j + 1);
                }

            }
        }
    }

    private static void swap (int arr[] , int x , int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }


    public static void main(String[] args) {
        int arr[] ={3,60,35,2,45,320,5};

        System.out.println("Array Before Bubble Sort");
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        bubbleSort(arr);//sorting array elements using bubble sort

        System.out.println("Array After Bubble Sort");
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
       // Array Before Bubble Sort
       // 3 60 35 2 45 320 5

       // Array After Bubble Sort
        //2 3 5 35 45 60 320


    }
}
