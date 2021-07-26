package algorithms.arrays.matrixUniquePaths;


//The time complexity of above recursive solution is exponential. There are many overlapping subproblem
public class MatrixUniquePathsRecursion {

    public static void main(String args[])    {
        System.out.println(numberOfPaths(3, 3));
    }

    // Returns count of possible paths to reach
    // cell at row number 'row' and column number 'column'
    // from the topmost-leftmost cell (cell at 1, 1)
    //you can either move only to right or down
    static int  numberOfPaths(int row, int column)
    {
        // If either given row number is first or
        // given column number is first
        //There is one way - down till end  OR right till end
        if (row == 1 || column == 1) {
            return 1;
        }

        // If diagonal movements are allowed then  the last addition is required.
        return  numberOfPaths(row-1, column) + numberOfPaths(row, column-1);
        // + numberOfPaths(row-1,column-1);
    }

}
