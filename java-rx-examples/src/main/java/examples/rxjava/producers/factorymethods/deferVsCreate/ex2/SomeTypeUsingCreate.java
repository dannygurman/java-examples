package examples.rxjava.producers.factorymethods.deferVsCreate.ex2;

import examples.rxjava.producers.factorymethods.deferVsCreate.SomeType;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;


public class SomeTypeUsingCreate extends SomeType {



    public Observable<String> valueObservable() {
        ObservableOnSubscribe<String> source = subscriber -> {//subscribe(@NonNull ObservableEmitter<T> var1)
            subscriber.onNext(value);
            subscriber.onComplete();
        };

        return Observable.create(source);

    }

}
