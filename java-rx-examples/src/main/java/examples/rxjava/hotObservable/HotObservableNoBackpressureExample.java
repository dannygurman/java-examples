package examples.rxjava.hotObservable;

import examples.rxjava.hotObservable.HotObservableExampleAbs;
import examples.rxjava.utils.ComputeFunction;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

//Let’s consider an example of hot Observable, that is producing a 1 million items to an end consumer that is processing those items.
//When a compute() method in the Observer takes some time to process every item,
//  the Observable is starting to fill up a memory with items, causing a program to fail:

//Running that program will fail with a MissingBackpressureException because
// we didn’t define a way of handling overproducing Observable.

public class HotObservableNoBackpressureExample extends HotObservableExampleAbs {


    static long numOfSleepSeconds =120;

    public static void main(String[] args) throws InterruptedException {

        PublishSubject<Integer> source = PublishSubject.<Integer>create();

        source.observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute,
                        Throwable::printStackTrace);



        IntConsumer intConsumer = getIntConsumer( source);
        IntStream.range(1, 1_000_000).forEach(intConsumer );


        // We’ve set the thread to sleep to prevent the main function from returning immediately.
        Thread.sleep(numOfSleepSeconds * 1000);
    }


}
