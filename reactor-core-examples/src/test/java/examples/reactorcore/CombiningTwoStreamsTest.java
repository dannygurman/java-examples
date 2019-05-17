package examples.reactorcore;

import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

//Example of  combining another stream with current using zip() function
//Creating another Flux which keeps incrementing by one and streaming it together with  original one
public class CombiningTwoStreamsTest {

    @Test
    public void givenFlux_whenZipping_thenCombine() {
        List<String> elements = new ArrayList<>();

        Publisher<Integer> source2 = Flux.range(0, Integer.MAX_VALUE).log();//keeps incrementing by one

        BiFunction<Integer , Integer , String > combinator = (one, two) -> String.format("First Flux: %d, Second Flux: %d", one, two);

        Consumer<String> onNextConsumer = elements::add;

        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> i * 2)
                .zipWith(source2, combinator )
                .subscribe(onNextConsumer);

        assertThat(elements).containsExactly(
                "First Flux: 2, Second Flux: 0",
                "First Flux: 4, Second Flux: 1",
                "First Flux: 6, Second Flux: 2",
                "First Flux: 8, Second Flux: 3");
    }
}
