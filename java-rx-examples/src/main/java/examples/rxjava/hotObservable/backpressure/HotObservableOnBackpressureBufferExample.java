package examples.rxjava.hotObservable.backpressure;

import examples.rxjava.hotObservable.HotObservableExampleAbs;
import examples.rxjava.utils.ComputeFunction;
import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/*There are 4 types of actions that can be executed when the buffer fills up:
        ERROR – this is the default behavior signaling a BufferOverflowException when the buffer is full
        DROP_LATEST – if an overflow would happen, the current value will be simply ignored and only the old values will be delivered once the downstream Observer requests
        DROP_OLDEST – drops the oldest element in the buffer and adds the current value to it
        */
public class HotObservableOnBackpressureBufferExample extends HotObservableExampleAbs {
    static long numOfSleepSeconds =120;

    static int bufferCapacity = 16;

    public static void main(String[] args) throws InterruptedException {

        PublishSubject<Integer> source = PublishSubject.<Integer>create();

        Action onOverflow = () -> {};
        Consumer<Integer> onNext =  ComputeFunction::compute;
        Consumer< Throwable> onError = e -> System.out.println(e.getMessage());

        source.toFlowable(BackpressureStrategy.DROP).
                onBackpressureBuffer(bufferCapacity, onOverflow, BackpressureOverflowStrategy.DROP_OLDEST)
                .observeOn(Schedulers.computation())
                .subscribe(onNext, onError);

        IntConsumer intConsumer = getIntConsumer( source);
        IntStream.range(1, 1_000_000_000).forEach(intConsumer );

        // We’ve set the thread to sleep to prevent the main function from returning immediately.
        Thread.sleep(numOfSleepSeconds * 1000);
    }

}
