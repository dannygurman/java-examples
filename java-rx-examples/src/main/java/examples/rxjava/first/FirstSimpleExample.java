package examples.rxjava.first;

import examples.rxjava.ObservableExampleAbs;
import io.reactivex.Maybe;
import io.reactivex.Observable;


public class FirstSimpleExample extends ObservableExampleAbs {

    public static void main(String[] args) {
        System.out.println("------First-----------");
        //First -  Returns an Observable that emits only the very first item emitted by the source Observable,
        // or notifies of an NoSuchElementException if the source Observable is empty.
        Maybe<String> maybe = Observable.fromArray(letters).firstElement ();

        maybe.subscribe(onNextStringPrintln);
    }
    //------First-----------
  //  a
}

