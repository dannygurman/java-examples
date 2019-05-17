package examples.rxjava.producers.maybe;

import io.reactivex.Maybe;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;


//maybe producer example
public class MaybeExample {

    public static void main(String[] args) {

        int defaultValue = 2; //5
        Function<Integer, Integer> mapper = v -> v + 1;
        Predicate <Integer> filter = v -> v == 1;

        Maybe <Integer> maybe =  Maybe.just(1).map(mapper).filter(filter).defaultIfEmpty(defaultValue);

        TestObserver<Integer> testObserver= maybe.test();

        testObserver.assertResult(2);

    }
}
