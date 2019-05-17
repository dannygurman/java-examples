package concurrency.completableFuture.ex8;

import concurrency.completableFuture.common.Product;
import concurrency.completableFuture.common.ProductService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Supplier;

//https://www.callicoder.com/java-8-completablefuture-tutorial/

//If you do not want to return anything from your callback function and just want to run some piece of code after the completion of the Future,
// then you can use thenAccept() and thenRun() methods.
// These methods are consumers and are often used as the last callback in the callback chain.




public class ThenAccept {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // CompletableFuture.thenAccept() takes a Consumer<T> and returns CompletableFuture<Void>.
        // It has access to the result of the CompletableFuture on which it is attached.

        Supplier<Product> productSupplier = () -> ProductService.getProductDetail();

        Consumer<Product> productConsumer = product -> System.out.println("Got product detail from remote service " + product.getName());

        CompletableFuture<Void> completableFutureProductDetails = CompletableFuture.supplyAsync(productSupplier).thenAccept(productConsumer);

        completableFutureProductDetails.get();


        // While thenAccept() has access to the result of the CompletableFuture on which it is attached,
        // thenRun() does not even have access to the Future’s result.
        // It takes a Runnable and returns CompletableFuture<Void> -

        Supplier<Integer> zeroSupplier = () -> {return 0;/* Run some computation} */ };
        Runnable runnableAction = () -> { /* Computation Finished.*/};
         CompletableFuture.supplyAsync(zeroSupplier).thenRun(runnableAction).get();

    }

}
