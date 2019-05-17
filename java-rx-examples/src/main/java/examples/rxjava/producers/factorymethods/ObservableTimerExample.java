package examples.rxjava.producers.factorymethods;



import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableTimerExample {

    public static void main(String[] args) throws  Exception {
        int numOfSleepSeconds = 5;
        long delayInSeconds = 2;

        //Observable.timer() emits value only after a certain time/delay.
        //It emits only a single value (0L) after the delay
        Observable<Long> intervalObservable = Observable.timer(delayInSeconds, TimeUnit.SECONDS);

        intervalObservable.subscribe(System.out::println);
        Thread.sleep(numOfSleepSeconds * 1000);

//Prints
//0

    }
}
