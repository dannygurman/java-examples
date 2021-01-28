package examples.rxjava.producers.factorymethods;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

public class CreateObservableExample {


    public static void main(String[] args) {

        System.out.println("Creating observableOnSubscribe using OnSubscribe Lambda ");

        //ObservableOnSubscribe - functional interface with
        //void subscribe(@NonNull ObservableEmitter<T> var1)

        ObservableOnSubscribe<Integer> observableOnSubscribe = subscriber -> {
            //Code to pass the data to the subscribers and observers goes here.}
            System.out.println("Started emitting");

            System.out.println("Emitting 1st");
            subscriber.onNext(1);

            System.out.println("Emitting 2nd");
            subscriber.onNext(2);
            System.out.println("Emitting onComplete");
            subscriber.onComplete();
        };


        Observable<Integer> myObservable = Observable.create(observableOnSubscribe);

    }


}
