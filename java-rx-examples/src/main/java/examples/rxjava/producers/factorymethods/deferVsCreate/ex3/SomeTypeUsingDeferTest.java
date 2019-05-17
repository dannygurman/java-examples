package examples.rxjava.producers.factorymethods.deferVsCreate.ex3;



//https://blog.danlew.net/2015/07/23/deferring-observable-code-until-subscription-in-rxjava/

//defer(...) accepts Factory function that returns Observable(Subject, etc...),
// wraps it with OnSubscribeDefer and creates Observable
// only when subscriber subscribes, new Observable for every subscriber.


import io.reactivex.Observable;

public class SomeTypeUsingDeferTest {

    public static void main(String[] args) {
        SomeTypeUsingDefer instance = new SomeTypeUsingDefer();
        Observable<String> value = instance.valueObservable();
        instance.setValue("Some Value");
        value.subscribe(System.out::println)  ;
    }

    //Result:Some Value

    //All I did was wrap the original code with defer(), but now the behavior is what I want.
    // None of the code inside of defer() is executed until subscription.
    // We only call Observable.just() when someone requests the data.

   // The only downside to defer() is that it creates a new Observable each time you get a subscriber.
    // create() can use the same function for each subscriber, so it's more efficient.

}
