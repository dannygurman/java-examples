package concurrency.completableFuture.ex6;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * You can use thenApply() method to process and transform the result of a CompletableFuture when it arrives.
 * It takes a Function<T,R> as an argument. Function<T,R> is a simple functional interface representing a function that accepts an argument
 * of type T and produces a result of type R
 */
public class ThenApplySync {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Supplier<String> whatsYourNameSupplier  = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev"; //PUT BREAKPOINT HERE FOR UNDERSTANDING
        };

        Function <String , String > helloFunction = name ->  "Hello " + name;

        Function <String , String > welcomeFunction = name ->  "Welcome " + name;

        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(whatsYourNameSupplier);



        // Attach a callback to the Future using thenApply()
        //when apply called after ask is completed
        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(helloFunction/*PUT BREAKPOINT HERE FOR UNDERSTANDING*/);



       // You can also write a sequence of transformations on the CompletableFuture by attaching a series of thenApply() callback methods.
        // The result of one thenApply() method is passed to the next in the series -
        CompletableFuture<String> welcomeTextFuture = greetingFuture.thenApply(welcomeFunction /*PUT BREAKPOINT HERE FOR UNDERSTANDING*/);

        // Block and get the result of the future.
        System.out.println(welcomeTextFuture.get()); // Hello Rajeev
    }




}
