package examples.rxjava.hotObservable;

import examples.rxjava.utils.ComputeFunction;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/*  When we start our program, items will be computed by Observer lazily and will be requested in a pull fashion.
  The Schedulers.computation() method means that we want to run our Observer within a computation thread pool in RxJava.*/
public class ColdObservableExample  {

    static long numOfSleepSeconds =10;

    public static void main(String[] args) throws InterruptedException {

        Observable.range(1, 1_000_000)
                .observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute);

        // We’ve set the thread to sleep to prevent the main function from returning immediately.
        Thread.sleep(numOfSleepSeconds * 1000);
    }
}
