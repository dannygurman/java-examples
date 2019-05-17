package examples.rxjava.producers.factorymethods;




import examples.rxjava.ObservableExampleAbs;
import io.reactivex.Observable;


public class ObservableJustExample  extends ObservableExampleAbs {

    public static void main(String[] args) {


       //The just emits whatever is present inside the just function.
        // It can take between 2 to 9 parameters.

        // If you pass a List/Array in just() it’ll emit the List/Array only.

        Observable<Integer> justObservable = Observable.just(4,4,6);

        justObservable.subscribe(intObserver);


    }
}
//Prints :
//onNext: 4
//onNext: 4
//onNext: 6
//onNext: null
//onCompleted


