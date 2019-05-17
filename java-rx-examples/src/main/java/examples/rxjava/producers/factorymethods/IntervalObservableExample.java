package examples.rxjava.producers.factorymethods;


import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import java.util.concurrent.TimeUnit;


public class IntervalObservableExample {

    public static void main(String[] args) throws Exception{
        int numOfSleepSeconds = 10;
        long intervalInSeconds = 1;

        //Observable.interval() emits constant sequences of integers in ascending
        // order which are evenly spaced by the interval specified

        //Emits 0 to numOfSleepSeconds-1 (9) each second.
        Observable intervalObservable = Observable.interval(intervalInSeconds, TimeUnit.SECONDS);

        Consumer<Long> onNext = System.out::println;
        intervalObservable.subscribe(onNext);

        System.out.println("Sleeping for " + numOfSleepSeconds + " seconds");

        //Emits 0 to 4 each second.
       // We’ve set the thread to sleep to prevent the main function from returning immediately.
        Thread.sleep(numOfSleepSeconds * 1000);


        /*Sleeping for 10 seconds
        0
        1
        2
        3
        4
        5
        6
        7
        8
        9*/
    }
}
