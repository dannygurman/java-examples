package examples.rxjava.hotObservable.bufferingAndWindow;

import examples.rxjava.utils.ComputeFunction;
import examples.rxjava.hotObservable.HotObservableExampleAbs;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

//Window is similar to Buffer, but rather than emitting packets of items from the source Observable,
// it emits Observables, each one of which emits a subset of items from the source Observable and then
// terminates with an onCompleted notification.

// In the terminology of the Window operator, when a window “opens,” this means that a new Observable is emitted and that
// Observable will begin emitting items emitted by the source Observable.
//
// When a window “closes,” this means that the emitted Observable stops emitting items from the
// source Observable and terminates with an onCompleted notification to its observers.

public class HotObservableWindowBatch extends HotObservableExampleAbs {


    static long numOfSleepSeconds =120;

    static long windowSise =500;


        public static void main(String[] args) throws InterruptedException {
            PublishSubject<Integer> source = PublishSubject.<Integer>create();

            Consumer<Observable<Integer>> windowComputeOnNext = intObservable -> {
                Single<List<Integer>> listSingle =  intObservable.toList();
                listSingle.subscribe(ComputeFunction::computeList);

            };

            source.window(windowSise)
                    .observeOn(Schedulers.computation())
                    .subscribe(windowComputeOnNext,
                            Throwable::printStackTrace);


            IntConsumer intConsumer = getIntConsumer( source);
            IntStream.range(1, 1_000_000).forEach(intConsumer );

            // We’ve set the thread to sleep to prevent the main function from returning immediately.
            Thread.sleep(numOfSleepSeconds * 1000);
        }


    }

