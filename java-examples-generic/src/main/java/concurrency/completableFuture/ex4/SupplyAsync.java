package concurrency.completableFuture.ex4;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

//https://www.callicoder.com/java-8-completablefuture-tutorial/ - 3. Run a task asynchronously and return the result using supplyAsync() -

//A Supplier<T> is a simple functional interface which represents a supplier of results.
// /It has a single get() method where you can write your background task and return the result.

public class SupplyAsync {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Run a task specified by a Supplier object asynchronously

        Supplier <String> supplier = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        };

        CompletableFuture<String> future = CompletableFuture.supplyAsync(supplier);

        // Block and get the result of the Future
        String result = future.get();
        System.out.println(result);

    }
}
