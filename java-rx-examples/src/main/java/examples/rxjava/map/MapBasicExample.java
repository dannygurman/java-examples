package examples.rxjava.map;

import examples.rxjava.ObservableExampleAbs;
import io.reactivex.Observable;


public class MapBasicExample extends ObservableExampleAbs {

    public static void main(String[] args) {

        System.out.println();
        System.out.println("-------Map-----------");
        Observable.fromArray(letters)
                .map(upperCaseFunction)
                .subscribe(onNextStringPrintln);
    }
}
