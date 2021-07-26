package algorithms.arrays.eggDrop;


class EggDrop_Recursion {

    /* Function to get minimum number of
    trials needed in worst case with n
    eggs and k floors */
    static int eggDropMinimalTrails(int n_eggs, int k_floors) {
        // If there are no floors, then
        // no trials needed. OR if there
        // is one floor, one trial needed.
        if (k_floors == 1 || k_floors == 0)
            return k_floors;

        // We need k trials for one egg
        // and k floors
        if (n_eggs == 1)
            return k_floors;

        int min = Integer.MAX_VALUE;
        int floor_id, res;

        // Consider all droppings from
        //1st floor to kth floor and
        // return the minimum of these
        // values plus 1.
        for (floor_id = 1; floor_id <= k_floors; floor_id++) {
            //in case egg broken - we have left with minus 1 egg - checking lower floor
            int min_trails_lower_flower_minus_egg = eggDropMinimalTrails(n_eggs - 1, floor_id - 1);

            //in case egg was NOT  broken - we have left with all eggs - checking upper floor
            //The floors we want to test aren't important;
            //in fact, the number of remaining floors is what matters.
            //For example, testing the floors between 1 and 20 (both 1 and 20 included)
            //would require the same number
            // of drops to test the floors between 21 and 40 (after knowing that we can drop from floor 20 and the egg will not be broken)
            //, or between 81 and 100. In all
            // three situations, we tested 20 floors
            int min_trails_upper_flower = eggDropMinimalTrails(n_eggs, k_floors - floor_id);

            res = Math.max(min_trails_lower_flower_minus_egg,min_trails_upper_flower);
            if (res < min)
                //updae minimum
                min = res;
        }

        return min + 1;//Adding this drop
    }

    // Driver code
    public static void main(String args[]) {
        int n_eggs = 2, k_floors = 100;
        System.out.print("Minimum number of "
                + "trials in worst case with "
                + n_eggs + " eggs and " + k_floors
                + " floors is " + eggDropMinimalTrails(n_eggs, k_floors));
    }
    // This code is contributed by Ryuga.
}



