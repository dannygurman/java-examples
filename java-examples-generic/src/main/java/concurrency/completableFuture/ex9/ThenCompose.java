package concurrency.completableFuture.ex9;

import concurrency.completableFuture.common.CreditRatingService;
import concurrency.completableFuture.common.User;
import concurrency.completableFuture.common.UserService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

public class ThenCompose {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThenCompose thenComposeTest = new ThenCompose();
        String userId = "a";

        CompletableFuture<User> userDetailsFuture = thenComposeTest.getUserDetailFuture(userId);

        Function<User, CompletableFuture<Double>> functionGetCreditCratingFuture = user -> thenComposeTest.getCreditRatingFuture(user);



        CompletableFuture<CompletableFuture<Double>> result = userDetailsFuture.thenApply(functionGetCreditCratingFuture);

        //In earlier examples, the Supplier function passed to thenApply() callback would return a simple
        // value but in this case, it is returning a CompletableFuture.
        // Therefore, the final result in the above case is a nested CompletableFuture.


        // If you want the final result to be a top-level Future, use thenCompose() method instead
        CompletableFuture<Double> result2 = userDetailsFuture.thenCompose(functionGetCreditCratingFuture);

        //So, Rule of thumb here -
        // If your callback function returns a CompletableFuture, and you want a flattened
        // result from the CompletableFuture chain (which in most cases you would), then use thenCompose().

        System.out.println("rating:" + result2.get());
        // Result:rating:4.0
    }



    public CompletableFuture<User> getUserDetailFuture(String userId) {
        Supplier <User>userDetailsSupplier = () ->  UserService.getUserDetails(userId);
        return CompletableFuture.supplyAsync(userDetailsSupplier);
    }

    CompletableFuture<Double> getCreditRatingFuture(User user){
        Supplier <Double> creditRatingSupplier = () ->   CreditRatingService.getCreditRating(user);
        return CompletableFuture.supplyAsync(creditRatingSupplier);
    }

}
