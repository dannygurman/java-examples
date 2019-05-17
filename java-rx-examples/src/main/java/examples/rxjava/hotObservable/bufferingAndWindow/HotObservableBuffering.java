package examples.rxjava.hotObservable.bufferingAndWindow;

//Buffering Overproducing Observable -
//The first way to handle overproducing Observable is to define some kind of a buffer for
// elements that cannot be processed by an Observer.

//Defining a buffer with a size of 1024 will give an Observer some time to catch up to an overproducing source.
// The buffer will store items that were not yet processed.

//Note however that generally, this may be only a temporary fix as the
// overflow can still happen if the source overproduces the predicted buffer size.


import examples.rxjava.utils.ComputeFunction;
import examples.rxjava.hotObservable.HotObservableExampleAbs;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class HotObservableBuffering extends HotObservableExampleAbs {

    static long numOfSleepSeconds =120;

    static int bufferSize = 1024;

    public static void main(String[] args) throws InterruptedException {
        PublishSubject<Integer> source = PublishSubject.<Integer>create();

        source.buffer(bufferSize)
                .observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::computeList,
                        Throwable::printStackTrace);


        IntConsumer intConsumer = getIntConsumer( source);
        IntStream.range(1, 1_000_000).forEach(intConsumer );

        // We’ve set the thread to sleep to prevent the main function from returning immediately.
        Thread.sleep(numOfSleepSeconds * 1000);
    }
}
