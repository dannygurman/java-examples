package com.baeldung.rxjava;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

public class RxJavaBackpressureLongRunningUnitTest {

    @Test
    public void givenColdObservable_shouldNotThrowException() {
    // given
        // when
        TestObserver<Integer> testObserver =Observable.range(1, 1_000_000).observeOn(Schedulers.computation()).test();

        // then
        testObserver.awaitTerminalEvent();
        assertTrue(testObserver.errors().size() == 0);
    }

    @Test
    public void givenHotObservable_whenBackpressureNotDefined_shouldTrowException() {
        // given
        PublishSubject<Integer> source = PublishSubject.create();

        TestObserver<Integer> testObserver = source.observeOn(Schedulers.computation()).test();

        // when
        IntStream.range(0, 1_000_000).forEach(source::onNext);

        // then
        testObserver.awaitTerminalEvent();
        testObserver.assertError(MissingBackpressureException.class);
    }

    @Test
    public void givenHotObservable_whenWindowIsDefined_shouldNotThrowException() {
        // given

        PublishSubject<Integer> source = PublishSubject.create();

        // when
        TestObserver<Observable<Integer>> testObserver = source.window(500).observeOn(Schedulers.computation()).test();

        IntStream.range(0, 1_000).forEach(source::onNext);

        // then
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);
        assertTrue(testObserver.errors().size() == 0);
    }

    @Test
    public void givenHotObservable_whenBufferIsDefined_shouldNotThrowException() {
        // given
        PublishSubject<Integer> source = PublishSubject.create();

        // when
        TestObserver<List<Integer>> testObserver =  source.buffer(1024).observeOn(Schedulers.computation()).test();

        IntStream.range(0, 1_000).forEach(source::onNext);

        // then
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);
        assertTrue(testObserver.errors().size() == 0);
    }

    @Test
    public void givenHotObservable_whenSkippingOperationIsDefined_shouldNotThrowException() {
        // given
           PublishSubject<Integer> source = PublishSubject.create();

        // when
        TestObserver<Integer> testObserver = source.sample(100, TimeUnit.MILLISECONDS)
                // .throttleFirst(100, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.computation()).test();

        IntStream.range(0, 1_000).forEach(source::onNext);

        // then
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);
        assertTrue(testObserver.errors().size() == 0);
    }

    @Test
    public void givenHotObservable_whenOnBackpressureBufferDefined_shouldNotThrowException() {
        // given

        // when
        TestSubscriber <Integer> testSubscriber = Flowable.range(1, 1_000_000).onBackpressureBuffer(16, () -> {
        }, BackpressureOverflowStrategy.DROP_OLDEST).observeOn(Schedulers.computation()).test();

        // then
        testSubscriber.awaitTerminalEvent(2, TimeUnit.SECONDS);
        assertTrue(testSubscriber.errorCount() == 0);
    }

    @Test
    public void givenHotObservable_whenOnBackpressureDropDefined_shouldNotThrowException() {
        // given

        // when
        TestSubscriber <Integer> testSubscriber = Flowable.range(1, 1_000_000).onBackpressureDrop().
                observeOn(Schedulers.computation())
                .test();

        // then
        testSubscriber.awaitTerminalEvent(2, TimeUnit.SECONDS);
        assertTrue(testSubscriber.errorCount() == 0);
    }
}
