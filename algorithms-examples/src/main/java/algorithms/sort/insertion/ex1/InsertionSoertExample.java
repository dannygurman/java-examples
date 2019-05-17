package algorithms.sort.insertion.ex1;

/**
 * Created by dannyg on 1/8/2018.
 */
// Java program for implementation of Insertion Sort
class InsertionSoertExample
{
    /*Function to sort array using insertion sort*/
    void sort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            //saving the key (value of i)
            int key = arr[i];
            System.out.println("index " + i +" key" + key);

            int j = i-1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead (right)
               of their current position */
            while (j >= 0 && arr[j] > key) {
                System.out.print("j=" + j+ " ");
                arr[j+1] = arr[j];
                j--;
                System.out.print("array in sub iteration end:");
                printArray(arr);
            }
        //put key in its new place
            arr[j+1] = key;
            System.out.print("array in iteration end:");
            printArray(arr);
            System.out.println("\n--");
        }
    }

    /* A utility function to print array of size n*/
    static void printArray(int arr[])
    {
        int n = arr.length;

        for (int i=0; i < n; ++i)
            System.out.print(arr[i] + " ");

        System.out.println();
    }

    // Driver method
    public static void main(String args[])
    {
        int arr[] = {12, 11, 13, 5, 6};
        System.out.println("Before sort:");
        printArray(arr);

        InsertionSoertExample ob = new InsertionSoertExample();
        ob.sort(arr);


       /*// Output:
        Before sort:
        12 11 13 5 6

        index 1 key11
        j=0 array in sub iteration end:12 12 13 5 6
        array in iteration end:11 12 13 5 6

         --
        index 2 key13
        array in iteration end:11 12 13 5 6

         --
        index 3 key5
        j=2 array in sub iteration end:11 12 13 13 6
        j=1 array in sub iteration end:11 12 12 13 6
        j=0 array in sub iteration end:11 11 12 13 6
        array in iteration end:5 11 12 13 6

        --
        index 4 key6
        j=3 array in sub iteration end:5 11 12 13 13
        j=2 array in sub iteration end:5 11 12 12 13
        j=1 array in sub iteration end:5 11 11 12 13
        array in iteration end:5 6 11 12 13
        */


    }
} /* This code is contributed by Rajat Mishra. */