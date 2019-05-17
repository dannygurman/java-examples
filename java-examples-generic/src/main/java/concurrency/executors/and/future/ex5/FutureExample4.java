package concurrency.executors.and.future.ex5;

import concurrency.executors.and.future.FutureExampleAbs;
import org.junit.Test;

import java.util.concurrent.*;

public class FutureExample4 extends FutureExampleAbs {

    @Test
    public void testShowSearch() throws InterruptedException ,  TimeoutException {
        String textToSearch = "BLA";
        showSearch(textToSearch);
    }


    public void showSearch(final String target) throws InterruptedException {

        Callable<String> searchCallable= () -> searcher.search(target);

        Future<String> future   = executor.submit(searchCallable);

        displayOtherThings(); // do other things while searching
        try {
            //Blocked with timeout-  Waits if necessary for the computation to complete, and then
            //  retrieves its result.

            long timeoutInSeconds = 3;
            String result = "NA";
            try {
                result = future.get(timeoutInSeconds, TimeUnit.SECONDS);
            } catch (TimeoutException timeOutException) {
                System.out.println ("TimeoutException occurred" + timeOutException);
            }

            System.out.println ("is done:"+ future.isDone());
            System.out.println ("is canceled:"+ future.isCancelled());


            displayText(result); // use future
        } catch (ExecutionException ex) {
            cleanup();
        } return;
    }

    //Output:
  /*  Dp Other things
    And other things..
    TimeoutException occurredjava.util.concurrent.TimeoutException
    is done:false
    is canceled:false
    NA*/

}
