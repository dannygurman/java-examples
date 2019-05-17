package examples.rxjava.producers.factorymethods;

import examples.rxjava.ObservableExampleAbs;
import io.reactivex.Observable;

public class ObservableRangeExample extends ObservableExampleAbs {

    public static void main(String[] args) {


        //Observable.range(start,n) is used to emit n number of values starting from and inclusive of start
        Observable<Integer> rangeObservable = Observable.range(2,5);

        rangeObservable.subscribe(intObserver); //emits 2,3,4,5,6
    }
 /*   onNext: 2
    onNext: 3
    onNext: 4
    onNext: 5
    onNext: 6
    onCompleted*/

}
