package examples.rxjava.producers.factorymethods;

import examples.rxjava.ObservableExampleAbs;
import io.reactivex.Observable;


import java.util.ArrayList;
import java.util.List;

public class ObservableFromExample extends ObservableExampleAbs {


    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        //The from method dissolves the list/array and emits each value one at a time.
        Observable<Integer> observable = Observable.fromIterable(numbers);


        observable.subscribe(intObserver);

    }
}


//Following is printed in the log console.
//onNext: 1
//onNext: 2
//onNext: 3
//onCompleted


