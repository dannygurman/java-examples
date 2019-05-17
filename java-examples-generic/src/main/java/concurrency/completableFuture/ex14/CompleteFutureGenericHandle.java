package concurrency.completableFuture.ex14;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Supplier;


//The API also provides a more generic method - handle() to recover from exceptions.
// It is called whether or not an exception occurs.

//If an exception occurs, then the res argument will be null, otherwise, the ex argument will be null.

public class CompleteFutureGenericHandle {

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


        BiFunction< String , Throwable , String > handleExceptionFunction =(res, ex) -> {
            if(ex != null) {
                System.out.println("Oops! We have an exception - " + ex.getMessage());
                return "Unknown!";
            }
            return res;
        };


        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(supplyMaturity).handle(handleExceptionFunction);


        System.out.println("Maturity : " + maturityFuture.get());
        //Oops! We have an exception - java.lang.IllegalArgumentException: Age can not be negative
        //Maturity : Unknown!
    }
}
