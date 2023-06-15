package concurrency.executors.and.future.ex3;

import concurrency.executors.and.future.FutureExampleAbs;
import org.junit.Test;

import java.util.concurrent.*;

public class FutureExample2 extends FutureExampleAbs {


    @Test
    public void testShowSearch() throws InterruptedException {
        String textToSearch = "BLA";
        showSearch(textToSearch);
    }


    public void showSearch(final String target) throws InterruptedException {
        Callable <String> searchCallable= () -> searcher.search(target);

        Future<String> future   = executor.submit(searchCallable);

        displayOtherThings(); // do other things while searching
        try {
            //Blocked -  Waits if necessary for the computation to complete, and then
            //     * retrieves its result.
            displayText(future.get()); // use future

        } catch (ExecutionException ex) { cleanup(); return; }
    }



}
