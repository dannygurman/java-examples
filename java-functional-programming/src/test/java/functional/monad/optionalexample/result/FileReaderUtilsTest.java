package functional.monad.optionalexample.result;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class FileReaderUtilsTest {

    @Test
    public void testBusinessOperation() {

        Result<Double>  result = FileReaderUtils.businessOperation("file1" , "file2");
        double expectedResults =(double) ((5-3) +3)/2;

        assertFalse(result.isError());
        assertThat(result.getValue(), is(expectedResults));

        result = FileReaderUtils.businessOperation(FileReaderUtils.ERROR_FILENAME , "file2");
        assertTrue(result.isError());

        result = FileReaderUtils.businessOperation(  "file1" , FileReaderUtils.ERROR_FILENAME);
        assertTrue(result.isError());
    }
}
