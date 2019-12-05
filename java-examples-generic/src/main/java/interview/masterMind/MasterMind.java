package interview.masterMind;

public class MasterMind {


    public static int MAX_COLORS = 4;

    public int code(char c) {
        switch (c) {
            case 'B':
                return 0;
            case 'G':
                return 1;
            case 'R':
                return 2;
            case 'Y':
                return 3;
            default:
                return -1;
        }
    }

    public Result estimate(String guess, String solution) {
        if (guess.length() != solution.length()) return null;

        Result res = new Result();
        int[] frequencies = new int[MAX_COLORS];

        /* Compute hits and build frequency table */
        for (int i = 0; i < guess.length(); i++) {
            char solution_char = solution.charAt(i);
            if (guess.charAt(i) == solution_char) {
                res.hits++;
            } else {
/* Only increment the frequency table (which will be used
* for pseudo-hits) if it's not a hit. If it'is a hit, the
 * slot has already been "used." */
                int code = code(solution_char);
                frequencies[code]++;
            }
        }

        /* Compute pseudo-hits */
        for (int i = 0; i < guess.length(); i++) {
            int guess_char_code = code(guess.charAt(i));
            if (guess_char_code >= 0 && frequencies[guess_char_code] > 0 &&
                    guess.charAt(i) != solution.charAt(i)) {
                res.pseudoHits++;
                frequencies[guess_char_code]--;
            }
        }
        return res;
    }
}
