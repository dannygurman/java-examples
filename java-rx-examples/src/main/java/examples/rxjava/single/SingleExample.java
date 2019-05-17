package examples.rxjava.single;




/*
A Single is something like an Observable, but instead of emitting a series of values ( anywhere from none at all to an infinite number )
it always either emits one value or an error notification.

//We know that onComplete will follow as soon as onNext happens so why can’t we combine them?

Single implements almost all of the operators you’d often use on an Observable — map, flatMap , filter, zip — they’re all there .
 the only difference being they return or work with Singles instead of Observables*/

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;


public class SingleExample {

    public static void main(String[] args) {

        Consumer<String> onSuccess = System.out::print;
        Consumer<Throwable> onError = e -> {throw new RuntimeException(e.getMessage());};

        Observable.just("Hello");

        Single<String> single = Single.just("Hello").doOnSuccess(onSuccess).doOnError(onError);


        single.subscribe();
    }
}
