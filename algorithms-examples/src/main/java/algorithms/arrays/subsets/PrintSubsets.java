package algorithms.arrays.subsets;

public class PrintSubsets {

    // Driver code
    public static void main(String[] args)    {
        char set[] = {'a', 'b', 'c'};//Set - no equal  members
        printSubsets(set);
       /* Out:
        { }
        { a } 001
        { b } 010
        { a b }   //-011
        { c } //100
        { a c } // 101
        { b c }   // 110
        { a b c } //11*/
    }

    // Print all subsets of given set[]
    static void printSubsets(char set[])    {
        int n = set.length;

        // Run a loop for printing all 2^n
        // subsets one by obe

        //Bit  wise shit -The signed left shift operator "<<" shifts a bit pattern to the left
        //1<<n = 2^n
        //1<<3 = 2^3=8  1 -> 1000

       // n=3 : 1<<n = 8
        for (int subsetIndex = 0; subsetIndex < (1<<n); subsetIndex++) {
            System.out.println("subset number subsetIndex:" + subsetIndex + "Binary:"+ Integer.toBinaryString(subsetIndex));
            System.out.print("{ ");

            // Print current subset
            for (int charIndex = 0; charIndex < n; charIndex++) {
                if (indexInTheSubset(subsetIndex, charIndex )) {
                    System.out.print(set[charIndex] + " ");
                }
            }
            System.out.println("}");
        }


    }

    // (1<<j) [2^j] is a number with jth bit 1
    // Like:
    //  1<<1 = 10
    // 1<<2 100
    // 1<<3 1000

    //c b a
    // so when we 'and' them with the
    // subset number we get which numbers
    // are present in the subset and which
    // are not
    private static boolean  indexInTheSubset (int subset , int index ) {
        return  ((subset & (1 << index) ) > 0) ;
    }
}
