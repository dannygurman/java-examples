package examples.rxjava.producers.factorymethods.deferVsCreate.ex2;


import io.reactivex.Observable;

public class SomeTypeUsingCreateTest {

    public static void main(String[] args) {
        SomeTypeUsingCreate instance = new SomeTypeUsingCreate();
        Observable<String> valueObservable = instance.valueObservable();
        instance.setValue("Some Value");

        valueObservable.subscribe(System.out::println)  ;
    }

    //Result - some value
    //Observable.create() allows you to control the sequence precisely for each subscriber:

    //Now valueObservable() will emit the current value when subscribed.

    // It's roughly equivalent to what Observable.just() does,
    // except it retrieves value on SUBSCRIPTION (not factorymethods).

    //The only issue here is that I have been wary of writing custom operators
    //operators are tricky to write correctly

    //While the above code works now, how do I know it will always work for future versions of RxJava?
    //And how do I know I've safely covered all my bases, like backpressure and unsubscription? I

    // As such, I've tried avoiding custom operators unless necessary.


}
