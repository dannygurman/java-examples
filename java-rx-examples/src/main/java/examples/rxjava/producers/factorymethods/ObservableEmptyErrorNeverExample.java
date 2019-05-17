package examples.rxjava.producers.factorymethods;

import examples.rxjava.ObservableExampleAbs;
import io.reactivex.Observable;


public class ObservableEmptyErrorNeverExample extends ObservableExampleAbs {

    public static void main(String[] args) {

       // Observable.empty() creates an empty observable that emits nothing. It just completes.
       System.out.println("emptyObservable.subscribe:");
        Observable emptyObservable = Observable.empty();
        emptyObservable.subscribe(intObserver);
        //onCompleted

      // Observable.error() creates an error. The onError() of all the subscribers would be called.
        System.out.println("errorObservable.subscribe:");
        Observable errorObservable = Observable.error(new Exception("Error message"));
        errorObservable.subscribe(intObserver);
        //onError. Message:Error messageonCompleted

         // Observable.never() does nothing. Neither emits a complete nor an error
        System.out.println("neverObservable.subscribe:");
        Observable neverObservable = Observable.never();
        neverObservable.subscribe(intObserver);
        //Nothing
    }
}
