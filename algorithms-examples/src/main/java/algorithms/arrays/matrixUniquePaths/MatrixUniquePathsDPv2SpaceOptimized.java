package algorithms.arrays.matrixUniquePaths;

//https://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/

//MatrixUniquePathsDPv1 solution is more intuitive but we can also reduce the space by O(n); where n is column size
public class MatrixUniquePathsDPv2SpaceOptimized {

    // Driver program to test above function
    public static void main(String args[])        {
        System.out.println(numberOfPaths(3, 3));//except - 6
    }

    // Returns count of possible paths to reach
    // cell at row number m and column number n from
    //  the top-most-left-most  cell (cell at 1, 1)
    static int numberOfPaths(int numOfRows, int numOfCols){

        //m - number of rows
        // Create a 1D array to store results of sub-problems
        //dp - dynamic programming.
        // numOfCols - number of columns (number of paths to get to column i - latest row)
        int[] dp = new int[numOfCols];
        dp[0] = 1;

        //iterate over rows
        for (int i = 0; i < numOfRows; i++) {

            //adding over column - for specific row
            for (int j = 1; j < numOfCols; j++) {
                //new value is the number of paths to get to same column in row above (dp[j]) +
                // number of paths to get to previous column in column below in same row (dp [j-1])
                dp[j] += dp[j - 1];
            }
        }

        //return last column (and also last row)
        return dp[numOfCols - 1];
    }


}

