package algorithms.arrays.matrixUniquePaths;


//Now consider if some obstacles are added  to the grid.
//An obstacle e marked as 1 , and empty space -  0

//Example 3x3 grid:
/*[
[ 0 ,0 ,0 ],
[ 0 ,1 ,0 ],
[ 0 ,0 ,0 ]

  The total number of unique paths is 2

        ]*/


public class MatrixUniquePathsDPv3WithObstacles {

    // Driver program to test above function
    public static void main(String args[])        {
        int [][] matrix = { { 0 , 0 ,0} , {0 ,1 ,0} , {0 , 0 , 0}};
        System.out.println(numberOfUniquePathsWithObstacles(matrix));
    }

    private static int numberOfUniquePathsWithObstacles(int[][] matrix) {
        int numOfCols = matrix[0].length;
        int[] dp = new int[numOfCols];//dp - dynamic programming. numOfCols - number of columns (number of paths to get to column i - latest row)
        dp[0] = 1;

        //iterating on rows
        for (int [] row : matrix) {
            //iterating over columns
            for (int j = 0; j < numOfCols; j++) {
                //Handling obstacles - setting num of path to this cell as o
                if (row[j] == 1){
                    dp[j] = 0;
                    //if not last row and not obstacle
                } else if (j > 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        //return last column (and also last row)
        return dp[numOfCols - 1];

    }

}
