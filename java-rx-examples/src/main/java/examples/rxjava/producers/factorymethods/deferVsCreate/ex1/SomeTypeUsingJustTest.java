package examples.rxjava.producers.factorymethods.deferVsCreate.ex1;


import io.reactivex.Observable;

public class SomeTypeUsingJustTest {

    public static void main(String[] args) {
        SomeTypeUsingJust instance = new SomeTypeUsingJust();
        Observable<String> valueObservable = instance.valueObservable();
        instance.setValue("Some Value");

        valueObservable.subscribe(System.out::println)  ;
    }

    //Return null (not some Value!! as expected )
    // since value had yet to be initialized when Observable.just() is called.

    //just(), from(), and other Observable factorymethods tools store the value of data when created, not when subscribed
}
