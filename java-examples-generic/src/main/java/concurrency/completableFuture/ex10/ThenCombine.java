package concurrency.completableFuture.ex10;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Supplier;

//https://www.callicoder.com/java-8-completablefuture-tutorial/#future-vs-completablefuture

//While thenCompose() is used to combine two Futures where one future is dependent on the other,
// thenCombine() is used when you want two Futures to run independently and do something after both are complete.

//The callback function passed to thenCombine() will be called when both the Futures are complete.

public class ThenCombine {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        Supplier <Double> weightSupplier = () ->  {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        };

        Supplier <Double> heightSupplier = () ->  {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 177.8;
        };

        //Represents a function that accepts two arguments and produces a result.
        BiFunction <Double , Double ,Double >  bmiFunction = (weightInKg, heightInCm) -> {
            Double heightInMeter = heightInCm / 100;
            return weightInKg / (heightInMeter * heightInMeter);
        };

        System.out.println("Retrieving weight.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(weightSupplier);

        System.out.println("Retrieving height.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(heightSupplier);

        System.out.println("Calculating BMI.");
        CompletableFuture<Double> combinedFuture = weightInKgFuture.thenCombine(heightInCmFuture, bmiFunction);

        System.out.println("Your BMI is - " + combinedFuture.get());
        //Your BMI is - 20.56126561232714
    }

}
