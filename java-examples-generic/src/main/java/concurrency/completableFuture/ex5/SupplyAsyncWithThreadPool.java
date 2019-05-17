package concurrency.completableFuture.ex5;

//https://www.callicoder.com/java-8-completablefuture-tutorial/ - 3. Run a task asynchronously and return the result using supplyAsync() -
//You might be wondering that - Well, I know that the runAsync() and supplyAsync() methods execute their tasks in a separate thread.
// But, we never created a thread right?

//Yes! CompletableFuture executes these tasks in a thread obtained from the global ForkJoinPool.commonPool().

//But hey, you can also create a Thread Pool and pass it to runAsync() and supplyAsync() methods to let them execute
// //their tasks in a thread obtained from your thread pool.

// All the methods in the CompletableFuture API has two variants - One which accepts an Executor as an argument and one which does NOT

import java.util.concurrent.*;
import java.util.function.Supplier;

public class SupplyAsyncWithThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Executor threadPool = Executors.newFixedThreadPool(10);


        Supplier<String> supplier = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        };

        //Call with executor
        CompletableFuture<String> future = CompletableFuture.supplyAsync(supplier , threadPool);

        // Block and get the result of the Future
        String result = future.get();
        System.out.println(result);
    }
}
