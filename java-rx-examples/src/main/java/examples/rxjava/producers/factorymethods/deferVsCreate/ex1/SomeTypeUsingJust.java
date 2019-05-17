package examples.rxjava.producers.factorymethods.deferVsCreate.ex1;

import examples.rxjava.producers.factorymethods.deferVsCreate.SomeType;
import io.reactivex.Observable;


public class SomeTypeUsingJust extends SomeType {

    public Observable<String> valueObservable() {
        return Observable.just(value);
    }
}
