package examples.rxjava.hotObservable.backpressure;

import examples.rxjava.hotObservable.HotObservableExampleAbs;
import examples.rxjava.utils.ComputeFunction;
import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

//Whenever the downstream Observer is not ready to receive an element,
// we can use an onBackpressureDrop() method to drop that element from the sequence.
//
//We can think of that method as an onBackpressureBuffer() method with a capacity of a buffer set to zero
// with a strategy ON_OVERFLOW_DROP_LATEST.

public class HotObservableOnBackpressureDropExample extends HotObservableExampleAbs {

    static long numOfSleepSeconds =120;


    public static void main(String[] args) throws InterruptedException {

        Action onOverflow = () -> {};
        Consumer<Integer> onNext =  ComputeFunction::compute;


        Flowable.range(1, 1_000_000).onBackpressureDrop().
                observeOn(Schedulers.computation())
                .subscribe(onNext, Throwable::printStackTrace);

        // We’ve set the thread to sleep to prevent the main function from returning immediately.
        Thread.sleep(numOfSleepSeconds * 1000);

    }



}
