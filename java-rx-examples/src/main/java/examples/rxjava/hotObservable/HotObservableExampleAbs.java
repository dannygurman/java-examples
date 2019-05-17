package examples.rxjava.hotObservable;

import io.reactivex.subjects.PublishSubject;

import java.util.function.IntConsumer;

public abstract class HotObservableExampleAbs {

    protected  static IntConsumer getIntConsumer(PublishSubject<Integer> source) {
        IntConsumer intConsumer = i -> {
            if ((i % 100000) == 0) {
                System.out.println(i);
            }
            source.onNext(i);
        };

        return intConsumer;
    }
}
