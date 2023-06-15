package concurrency.completableFuture.ex7;


//All the callback methods provided by CompletableFuture have two async variants -
//
//// thenApply() variants
//<U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
//<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
//<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)

//thenRun
//thenRunAsync


//thenAccept
//thenAcceptAsunc


//These async callback variations help you further parallelize your computations by executing the callback tasks in a separate thread.

//For  'regular thenApply (not sync) -CompletableFuture.supplyAsync(()-> some_task).thenApply(result->do_something) :

//It executed in the same thread where the supplyAsync() task is executed
//    or in the main thread If the supplyAsync() task completes immediately (no sleep)


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;

public class ThenApplyAsync {
    public static void main(String[] args) {

        //To have more control over the thread that executes the callback task, you can use async callbacks.

        // If you use thenApplyAsync() callback, then it will be executed
        // in a different thread obtained from ForkJoinPool.commonPool() -

        Supplier <String>  supplySomething = () -> "Some Result";
        Function<String , String> doProcess = result -> "Processed Result" + result;

        CompletableFuture.supplyAsync(supplySomething).thenApplyAsync(doProcess/* Executed in a different thread from ForkJoinPool.commonPool() */);

        // Moreover, If you pass an Executor to the thenApplyAsync() callback then the task will be executed in a thread obtained
        // from the Executor’s thread pool.

        Executor executor = Executors.newFixedThreadPool(2);

        CompletableFuture.supplyAsync(supplySomething).
                thenApplyAsync(doProcess/* Executed in a thread obtained from the executor*/ , executor);

    }



}
