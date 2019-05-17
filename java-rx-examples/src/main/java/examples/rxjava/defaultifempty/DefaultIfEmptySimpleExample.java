package examples.rxjava.defaultifempty;

import examples.rxjava.ObservableExampleAbs;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


public class DefaultIfEmptySimpleExample extends ObservableExampleAbs {

    public static void main(String[] args) {
        System.out.println("------DefaultIfEmpty------------");

        Consumer <Object>  onNextPrintln= System.out::println;

        String defaultValue = "Observable is empty";

        Observable.empty()
                .defaultIfEmpty(defaultValue)
                .subscribe(onNextPrintln);

    }


}
