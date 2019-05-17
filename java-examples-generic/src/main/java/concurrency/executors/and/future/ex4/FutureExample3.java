package concurrency.executors.and.future.ex4;

import concurrency.executors.and.future.FutureExampleAbs;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureExample3 extends FutureExampleAbs {

    @Test
    public void testShowSearch() throws InterruptedException {
        String textToSearch = "BLA";
        showSearch(textToSearch);
    }


    public void showSearch(final String target) throws InterruptedException {

        Callable<String> searchCallable= () -> searcher.search(target);

       // The FutureTask class is an implementation of Future that implements Runnable,
        // and so may be executed by an Executor.
        // For example, the  construction with submit in Example2 could be replaced by:
        FutureTask<String> future =  new FutureTask<>(searchCallable);

        executor.execute(future);

        while(!future.isDone()) {
            System.out.println("searching - not done yet...");
            Thread.sleep(1000);
        }

       try {
            //Future is now finished
            displayText(future.get()); // use future
        } catch (ExecutionException ex) { cleanup(); return; }
    }
}
