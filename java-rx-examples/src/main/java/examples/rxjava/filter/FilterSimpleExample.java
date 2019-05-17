package examples.rxjava.filter;

import examples.rxjava.ObservableExampleAbs;
import io.reactivex.Observable;
import io.reactivex.functions.Predicate;


public class FilterSimpleExample extends ObservableExampleAbs {

    public static void main(String[] args) {
        System.out.println("-------Filter-----------");

        Predicate<Integer> oddPredicate = i -> (i % 2 == 1);

        Observable.fromArray(numbers)
                .filter(oddPredicate)
                .subscribe(onNextIntegerPrintln);

      /*  -------Filter-----------
                1
        3
        5
        7
        9*/
    }
}
