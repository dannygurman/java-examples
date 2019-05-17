package concurrency.completableFuture.ex12;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

//CompletableFuture.anyOf() as the name suggests, returns a new CompletableFuture
// which is completed when any of the given CompletableFutures complete, with the same result.

//in the this example, the anyOfFuture is completed when any of the three CompletableFutures complete.

// Since future2 has the least amount of sleep time, it will complete first, and the final result will be - Result of Future 2.

//CompletableFuture.anyOf() takes a varargs of Futures and returns CompletableFuture<Object>.
// The problem with CompletableFuture.anyOf() is that if you have CompletableFutures that return results of different types, then you won’t know
// the type of your final CompletableFuture.

public class CompletableFutureAnyOf {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = getStringFuture ("1" , 2);
        CompletableFuture<String> future2 = getStringFuture ("2" , 1);
        CompletableFuture<String> future3 = getStringFuture ("3" , 3);

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2, future3);

        System.out.println(anyOfFuture.get());
        // Result of Future 2

    }


    public static CompletableFuture<String> getStringFuture (String id , long timeoutSeconds) {
        Supplier <String> stringSupplier = () -> {
            try {
                TimeUnit.SECONDS.sleep(timeoutSeconds);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future " + id;
        };

        return CompletableFuture.supplyAsync(stringSupplier);

    }
}
