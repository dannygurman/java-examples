package examples.rxjava.using;


import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import java.util.concurrent.Callable;

public class UsingExample {


    public static void main(String[] args) {


        //Generate String
        Callable<String> resourceFactory =  () -> {
            String resource = "MyResource";
            System.out.println("Leased: " + resource);
            return resource;
        };


        //Generating observable of char stream from input string
        Function< String, Observable<Character> > observableFactory = str -> {

            ObservableOnSubscribe<Character> charOnSubscribe = observer -> {
                for (Character c : str.toCharArray()) {
                    observer.onNext(c);
                }
                observer.onComplete();
            };

            return Observable.create(charOnSubscribe);
        };


        //A one-argument action
        Consumer<String> disposeAction = resource -> System.out.println("Disposed: " + resource);


        //Creating observable - with using
        Observable<Character> valuesObservable = Observable.using(resourceFactory ,observableFactory ,disposeAction);


        //Subscibing
        Consumer<Character> charOnNext = c -> System.out.println("char:" + c);
        Consumer <Throwable> onError= throwable -> System.out.println("Error " + throwable.getCause());

        valuesObservable.subscribe(charOnNext,  onError );
    }

//Output:
/*
    Leased: MyResource
    char:M
    char:y
    char:R
    char:e
    char:s
    char:o
    char:u
    char:r
    char:c
    char:e
    Disposed: MyResource*/
}
