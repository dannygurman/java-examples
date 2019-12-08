package algorithms.arrays.eggDrop;

// A Dynamic Programming based Java Program for the Egg Dropping Puzzle
//Time Complexity: O(nk^2)
//Auxiliary Space: O(nk)
class EggDrop_DynamicPrograming {
    // A utility function to get maximum of two integers
    static int max(int a, int b) { return (a > b) ? a : b; }

    /* Function to get minimum number of trials needed in worst
    case with n eggs and k floors */
    static int eggDrop(int n_eggs, int k_floors)
    {
		/* A 2D table where entery eggFloor[i][j] will represent minimum
	number of trials needed for i eggs and j floors. */
        int eggFloor[][] = new int[n_eggs + 1][k_floors + 1];
        int res;
        int egg_count, floor_id, x;

        // We need one trial for one floor and0 trials for 0 floors
        for (egg_count = 1; egg_count <= n_eggs; egg_count++) {
            eggFloor[egg_count][1] = 1;
            eggFloor[egg_count][0] = 0;
        }

        // We always need j trials for one egg and j floors.
        for (floor_id = 1; floor_id <= k_floors; floor_id++)
            eggFloor[1][floor_id] = floor_id;

        // Fill rest of the entries in table using optimal substructure
        // property
        for (egg_count = 2; egg_count <= n_eggs; egg_count++) {
            for (floor_id = 2; floor_id <= k_floors; floor_id++) {
                eggFloor[egg_count][floor_id] = Integer.MAX_VALUE;
                for (x = 1; x <= floor_id; x++) {
                    //in case egg broken - we have left with minus 1 egg - checking lower floor
                    int min_trails_lower_flower_minus_egg = eggFloor[egg_count - 1][x - 1];

                    //in case egg was NOT  broken - we have left with all eggs - checking upper floor
                    int min_trails_upper_flower =  eggFloor[egg_count][floor_id - x];
                    res = 1 + max(min_trails_lower_flower_minus_egg, min_trails_upper_flower);
                    if (res < eggFloor[egg_count][floor_id])
                        eggFloor[egg_count][floor_id] = res;
                }
            }
        }

        // eggFloor[n][k] holds the result
        return eggFloor[n_eggs][k_floors];
    }

    /* Driver program to test to pront printDups*/
    public static void main(String args[])
    {
        int n = 2, k = 10;
        System.out.println("Minimum number of trials in "+
                "worst case with "
                + n + " eggs and " + k + " floors is " + eggDrop(n, k));
    }
}
