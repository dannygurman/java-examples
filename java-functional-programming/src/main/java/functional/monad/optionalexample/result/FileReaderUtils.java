package functional.monad.optionalexample.result;

public class FileReaderUtils {

    public static final String ERROR_FILENAME = "error";
    public static Result<Integer> readIntFromFile(String file) {
        if(ERROR_FILENAME.equals(file)) {
            return Result.error("File does not exist");
        }

        return Result.ok(3);
    }

    public static Result<Integer> adjustValue(Integer value) {
        if (value > 5) {
            Result.error("Value " + value + " should no be greater than 5");
        }

        return Result.ok(5 - value);
    }

    public static Double calculateAverage(Integer val1, Integer val2) {
        return (val1 + val2)/2d;
    }

    /**
     * Read ints from two algorithems.files, Adjust the first one and then calculate average.
     * Returns a Result wrapping the positive outcome or any error.
     */
    public static Result<Double> businessOperation(String fileName1, String fileName2) {
        Result<Integer> adjustedValueFromFile1 = readIntFromFile(fileName1).flatMap(FileReaderUtils::adjustValue);

        return adjustedValueFromFile1.flatMap( val1 ->
                readIntFromFile(fileName2).flatMap( val2 ->
                        Result.ok(calculateAverage(val1, val2))
                ));
        //((5-3)+3)/2
    }

}