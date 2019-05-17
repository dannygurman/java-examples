package functional.monad.optionalexample.result;

import static functional.monad.optionalexample.result.FileReaderUtils.calculateAverage;
import static functional.monad.optionalexample.result.FileReaderUtils.readIntFromFile;

public class ResultExample2 {

    /**
     * Read ints from two algorithems.files, Adjust the first one and then calculate average.
     * Returns a Result wrapping the positive outcome or any error.
     */
    public Result<Double> businessOperation(String fileName, String fileName2) {

        Result<Integer> adjustedValue = readIntFromFile(fileName)
                .flatMap(FileReaderUtils::adjustValue);

        return
                adjustedValue.flatMap( val1 ->
                        readIntFromFile(fileName2).flatMap( val2 ->
                                Result.ok(calculateAverage(val1, val2))
                        ));
    }

}