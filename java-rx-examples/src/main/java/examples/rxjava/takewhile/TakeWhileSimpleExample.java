package examples.rxjava.takewhile;

import examples.rxjava.ObservableExampleAbs;

import io.reactivex.Observable;


import java.util.function.Predicate;

public class TakeWhileSimpleExample  extends ObservableExampleAbs {

    public static void main(String[] args){
        System.out.println("-------TakeWhile-----------");

        io.reactivex.functions.Predicate<Integer> predicateLowerThen5 = i -> i < 5;

        Observable.fromArray(numbers)
                .takeWhile(predicateLowerThen5)
                .subscribe(onNextIntegerPrintln);
    }
    /*-------TakeWhile-----------
            0
            1
            2
            3
            4*/
}
