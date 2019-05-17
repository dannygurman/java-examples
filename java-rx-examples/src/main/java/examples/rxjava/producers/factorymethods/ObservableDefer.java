package examples.rxjava.producers.factorymethods;

import examples.rxjava.ObservableExampleAbs;
import io.reactivex.Observable;

import java.util.concurrent.Callable;


public class ObservableDefer  extends ObservableExampleAbs {

    public static void main(String[] args) {
        Callable<Observable<Integer>> observableFactory = () -> Observable.just(1, 2, 3);

        Observable<Integer> deferObservable = Observable.defer(observableFactory);

        //The Observable.defer() is similar to create() except that it postpones the actual factorymethods until an Observer subscribes

        //From Jdoc: Returns an Observable that calls an Observable factory to create an Observable for each new Observer that subscribes.
        // That is, for each subscriber, the actual Observable that subscriber observes is determined by the factory function.
        deferObservable.subscribe(intObserver);
    }

   /*onNext: 1
    onNext: 2
    onNext: 3
    onCompleted*/
}
