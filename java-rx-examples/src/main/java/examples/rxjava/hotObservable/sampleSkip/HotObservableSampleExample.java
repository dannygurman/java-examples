package examples.rxjava.hotObservable.sampleSkip;

import examples.rxjava.hotObservable.HotObservableExampleAbs;
import examples.rxjava.utils.ComputeFunction;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

//If some of the values produced by Observable can be safely ignored, we can use the sampling within a
// specific time and throttling operators.

//The methods sample() and throttleFirst() are taking duration as a parameter:
//We specified that strategy of skipping elements will be a sample() method.
// We want a sample of a sequence from 100 milliseconds duration.
// That element will be emitted to the Observer.
public class HotObservableSampleExample extends HotObservableExampleAbs {

    static long numOfSleepSeconds =15;

    static long period = 100;

    public static void main(String[] args)  throws InterruptedException  {

        PublishSubject<Integer> source = PublishSubject.<Integer>create();

        source.sample(period, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute, Throwable::printStackTrace);

        IntConsumer intConsumer = getIntConsumer( source);
        IntStream.range(1, 1_000_000).forEach(intConsumer );

        // We’ve set the thread to sleep to prevent the main function from returning immediately.
        Thread.sleep(numOfSleepSeconds * 1000);
    }

}
