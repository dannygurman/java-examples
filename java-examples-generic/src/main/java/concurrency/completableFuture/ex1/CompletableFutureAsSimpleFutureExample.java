package concurrency.completableFuture.ex1;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//http://www.baeldung.com/java-completablefuture
public class CompletableFutureAsSimpleFutureExample  {

    @Test
    public  void simpleFutureExample1() throws Exception {
        CompletableFuture<String> completableFuture = calculateAsync();
        //Waits if necessary for this future to complete, and then return result (blocking?)
        String result = completableFuture.get();
        Assert.assertEquals("Hello", result);
    }


    public  CompletableFuture<String> calculateAsync() {

        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        //Callable and not runnable - as run() not returning exception and call those
        Callable callable = () -> {
            Thread.sleep(15000);
            //If not already completed, sets the value returned by  get() and related methods to the given value.
            completableFuture.complete("Hello");
            return null;
        };

        Executors.newCachedThreadPool().submit( callable);

        return completableFuture;
    }
}
