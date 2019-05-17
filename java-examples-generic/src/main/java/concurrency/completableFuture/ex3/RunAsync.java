package concurrency.completableFuture.ex3;

//https://www.callicoder.com/java-8-completablefuture-tutorial/ - 2. Running asynchronous computation using runAsync()

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class RunAsync {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            // Simulate a long-running Job
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("I'll run in a separate thread than the main thread.");
        };


        CompletableFuture<Void> future = CompletableFuture.runAsync(runnable);

        // Block and wait for the future to complete
        try {
            System.out.println("Before get");
            future.get();
            System.out.println("After get");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
