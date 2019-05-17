package concurrency.completableFuture.ex2;

import org.junit.Assert;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

//https://www.callicoder.com/java-8-completablefuture-tutorial/ - 1. The trivial example
public class TrivalCompleteExample {

    public static void main(String[] args)   {
     String returnValue = "Future's Result";
        CompletableFuture<String> completableFuture = new CompletableFuture<String>();

        //If not calling complete - will wait forever cause not complete !!!
       completableFuture.complete(returnValue);
        try {
            String result = completableFuture.get();
            Assert.assertEquals(returnValue, result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
