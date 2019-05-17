package concurrency.completableFuture.ex13;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

//The exceptionally() callback gives you a chance to recover from errors generated from the original Future.
// You can log the exception here and return a default value.

// Note that, the error will NOT be propagated further in the callback chain if you handle it once.

public class CompleteFutureExceptionally {

        public static void main(String[] args) throws ExecutionException, InterruptedException {
        Integer age = -1;

        Supplier<String> supplyMaturity = () -> {
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if (age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        };


        Function< Throwable , String > handleExceptionFunction  = ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        };


        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(supplyMaturity).exceptionally(handleExceptionFunction);


        System.out.println("Maturity : " + maturityFuture.get());
    }
}
