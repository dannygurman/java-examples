package algorithms.arrays.matrixUniquePaths;


//Solution using dynamic programming (DP)
//Like other typical Dynamic Programming(DP) problems, recomputations of same subproblems
// can be avoided by constructing a temporary array count[][] in bottom up manner using the  recursive formula.

//https://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/

//Time complexity  is O(mn).
//The space complexity of the above solution is O(mn)

public class MatrixUniquePathsDPv1 {

    // Driver program to test above function
    public static void main(String args[]) {
        System.out.println(numberOfPaths(3, 3));
    }


    // Returns count of possible paths to reach
    // cell at row number m and column number n from
    //  the top-most-left-most  cell (cell at 1, 1)
    static int numberOfPaths(int m, int n)    {

        // Create a 2D table to store results of sub problems
        int count[][] = new int[m][n];

        // Count of paths to reach any cell in first COLUMN is 1
        for (int i = 0; i < m; i++) {
            count[i][0] = 1;
        }

        // Count of paths to reach any cell in first ROW is 1
        for (int j = 0; j < n; j++) {
            count[0][j] = 1;
        }
        // Calculate count of paths for other cells in bottom-up manner using  the recursive solution
        for (int i = 1; i < m; i++)  {
            for (int j = 1; j < n; j++) {
                // By uncommenting the last part the code calculates the total possible paths if the DIAGONAL Movements are allowed

                count[i][j] = count[i - 1][j] + count[i][j - 1]; //+ count[i-1][j-1];
            }

        }
        return count[m-1][n-1];
    }
}
