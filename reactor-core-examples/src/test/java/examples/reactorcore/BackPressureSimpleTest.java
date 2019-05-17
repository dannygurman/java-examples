package examples.reactorcore;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class BackPressureSimpleTest {

    //Back pressure is when a downstream can tell an upstream to send
    // it fewer data in order to prevent it from being overwhelmed.

    private  long numOfElementsToRequest = 2;

    @Test
    public void givenFlux_whenApplyingBackPressure_thenPushElementsInBatches() throws InterruptedException {


        List<Integer> elements = new ArrayList<>();

        Function<Integer , Integer> mapper = i ->  i * 2;

        Subscriber subscriber = new Subscriber<Integer>() {
            private Subscription subscription;
            int onNextAmount;

            @Override
            public void onSubscribe(final Subscription subscription) {
                this.subscription = subscription;
                subscription.request(numOfElementsToRequest);
            }

            @Override
            public void onNext(final Integer integer) {
                elements.add(integer);
                onNextAmount++;
                if ( isMultipleOfNumToRequest (onNextAmount) ) {
                    subscription.request(numOfElementsToRequest);
                }
            }

            @Override
            public void onError(final Throwable t) {
            }

            @Override
            public void onComplete() {
               // int ham = 2;
            }
        };



        Flux.just(1, 2, 3, 4)
                .log()
                .map(mapper)
                //  .onBackpressureBuffer()
                .subscribe(subscriber);



        assertThat(elements).containsExactly(2, 4, 6, 8);
    }

    private boolean  isMultipleOfNumToRequest  ( int onNextAmount) {
        return (onNextAmount % numOfElementsToRequest == 0);
    }

    /*Output:
    INFO: | onSubscribe([Synchronous Fuseable] FluxArray.ArraySubscription)
    Sep 24, 2018 5:06:15 PM reactor.util.Loggers$JdkLogger info
    INFO: | request(2)
    Sep 24, 2018 5:06:15 PM reactor.util.Loggers$JdkLogger info
    INFO: | onNext(1)
    Sep 24, 2018 5:06:15 PM reactor.util.Loggers$JdkLogger info
    INFO: | onNext(2)
    Sep 24, 2018 5:06:15 PM reactor.util.Loggers$JdkLogger info
    INFO: | request(2)
    Sep 24, 2018 5:06:15 PM reactor.util.Loggers$JdkLogger info
    INFO: | onNext(3)
    Sep 24, 2018 5:06:15 PM reactor.util.Loggers$JdkLogger info
    INFO: | onNext(4)
    Sep 24, 2018 5:06:15 PM reactor.util.Loggers$JdkLogger info
    INFO: | request(2)
    Sep 24, 2018 5:06:15 PM reactor.util.Loggers$JdkLogger info
    INFO: | onComplete()*/
}
