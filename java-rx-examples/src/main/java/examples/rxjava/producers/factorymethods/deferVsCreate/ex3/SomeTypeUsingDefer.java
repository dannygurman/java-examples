package examples.rxjava.producers.factorymethods.deferVsCreate.ex3;

import examples.rxjava.producers.factorymethods.deferVsCreate.SomeType;
import io.reactivex.Observable;

import java.util.concurrent.Callable;


public class SomeTypeUsingDefer extends SomeType {

    public Observable<String> valueObservable() {
        //Callable:V call() throws Exception;
        Callable<Observable<String>> observableFactory =() ->  Observable.just(value);

        return Observable.defer(observableFactory);
    }
}
