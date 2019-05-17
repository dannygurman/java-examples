package examples.reactorcore;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class FluxSubscriberOnNextConsumerTest {
    @Test
    public void givenFlux_whenSubscribing_thenStream() throws InterruptedException {
        //In order for an application to be reactive, the first thing it must be able to do is to produce a stream of data.
        // Without this data, we would not have anything to react to, which is why this is a logical first step.

        // Reactive Core gives us two data types that enable us to do this - Flux and Mono
        // Both a Flux and Mono are implementations of the Reactive Streams Publisher interface

        List<Integer> elements = new ArrayList<>();

        Function<Integer , Integer> mapper = i -> {
            System.out.println(i + ":" + Thread.currentThread());
            return i * 2;
        };


        Consumer<Integer> onNextConsumer = elements::add;// boolean add(E e);

        // Flux. It’s a stream which can emit 0..n elements
        //Using use the subscribe() method to collect all the elements in a stream
        Flux.just(1, 2, 3, 4)
                .log()
                .map(mapper)
                .subscribe(onNextConsumer);

        assertThat(elements).containsExactly(2, 4, 6, 8);

       /* Output:
        1:Thread[main,5,main]
        2:Thread[main,5,main]
        3:Thread[main,5,main]
        4:Thread[main,5,main]
        Sep 24, 2018 4:24:40 PM reactor.util.Loggers$JdkLogger info
        INFO: | onSubscribe([Synchronous Fuseable] FluxArray.ArraySubscription)
        Sep 24, 2018 4:24:40 PM reactor.util.Loggers$JdkLogger info
        INFO: | request(unbounded)
        Sep 24, 2018 4:24:40 PM reactor.util.Loggers$JdkLogger info
        INFO: | onNext(1)
        Sep 24, 2018 4:24:40 PM reactor.util.Loggers$JdkLogger info
        INFO: | onNext(2)
        Sep 24, 2018 4:24:40 PM reactor.util.Loggers$JdkLogger info
        INFO: | onNext(3)
        Sep 24, 2018 4:24:40 PM reactor.util.Loggers$JdkLogger info
        INFO: | onNext(4)
        Sep 24, 2018 4:24:40 PM reactor.util.Loggers$JdkLogger info
        INFO: | onComplete()*/

        // onSubscribe() – This is called when we subscribe to our stream

        //  request(unbounded) – When we call subscribe, behind the scenes we are creating a Subscription.
        // This subscription requests elements from the stream.
        // In this case, it defaults to unbounded, meaning it requests every single element available

        // onNext() – This is called on every single element

        // onComplete() – This is called last, after receiving the last element.

        // There’s actually a onError() as well, which would be called if there is an exception,
        // but in this case, there isn’t.

        //This is the flow laid out in the Subscriber interface as part of the Reactive Streams Specification
        //  and in reality, that’s what’s been instantiated behind the scenes in our call to onSubscribe()
    }

}
