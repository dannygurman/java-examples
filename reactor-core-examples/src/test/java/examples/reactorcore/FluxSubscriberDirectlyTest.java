package examples.reactorcore;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class FluxSubscriberDirectlyTest {

    @Test
    public void givenFlux_whenSubscribing_thenStream() throws InterruptedException {

        List<Integer> elements = new ArrayList<>();


        //provide a Subscriber interface directly:
        Flux.just(1, 2, 3, 4)
                .log()
                .subscribe(new Subscriber<Integer>() {//Anonymous subscriber
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        elements.add(integer);
                    }

                    @Override
                    public void onError(Throwable t) {}

                    @Override
                    public void onComplete() {}
                });


        assertThat(elements).containsExactly(1 ,2 , 3 ,4 );
    }

}
