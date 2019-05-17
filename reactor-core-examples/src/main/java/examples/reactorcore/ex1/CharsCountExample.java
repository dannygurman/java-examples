package examples.reactorcore.ex1;

import org.assertj.core.groups.Tuple;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Comparator;
import java.util.function.Function;

public class CharsCountExample {


    public static void main(String[] args) {

        // Flux<String> letterFlux = createLettersFlux();
        //letterFlux.subscribe(System.out::println);

        System.out.println("lettersCountVer1");
        lettersCountVer1();

        System.out.println("lettersCountVer2");
        lettersCountVer2();


    }
    public static  void lettersCountVer1() {

        Function< GroupedFlux <String , String> ,  Publisher <Tuple2<String , Long>>  > groupedFluxToTuplePublisherMapper =
                group -> Mono.just( getToupleFromLetterCountGroupedFlux (group) );

        lettersCountInternal( groupedFluxToTuplePublisherMapper );
    }

    private static Tuple2<String , Long> getToupleFromLetterCountGroupedFlux (GroupedFlux <String , String>  letterCountGroupdFlux ) {
        String charName = letterCountGroupdFlux.key();
        // count()- Counts the number of values in this Flux.
        Mono <Long> countMono = letterCountGroupdFlux.count();

        //Mono bluck - Subscribe to this Mono and block indefinitely until a next signal is received.
        // Returns that value, or null if the Mono completes empty
        Long count = countMono.block();
        return Tuples.of(charName, count);
    }


    public static  void lettersCountVer2() {

        Function< GroupedFlux <String , String> ,  Publisher <Tuple2<String , Long>> > groupedFluxToTuplePublisherMapper =
                group -> {
                    Mono <String> groupKeyMono = Mono.just(group.key());
                    Mono <Long> groupCountMono =  group.count();
                    //Zip two sources together, that is to say wait for all the sources to emit one element and combine these elements once into a Tuple2.
                    return Mono.zip( groupKeyMono , groupCountMono);
                };

        lettersCountInternal( groupedFluxToTuplePublisherMapper );
    }

    public static  void lettersCountInternal(Function <GroupedFlux <String , String> , Publisher <Tuple2<String , Long>> > groupedFluxToTuplePublisherMapper ) {

        Function<Tuple2<String , Long>, String> keyCountTupleToStringMapper = keyAndCount -> keyAndCount.getT1() + " => " + keyAndCount.getT2() + "; ";

        //GroupedFlux - Represents a sequence of events which has an associated key.
        Flux <GroupedFlux <String , String> > sortedGroupedLettersFlux  = createSortedGroupedLettersFlux();

        //A Tuples is an immutable Collection of objects, each of which can be of an arbitrary type.
        // Tuple2  - A tuple that holds two non-null values
        Flux <Tuple2<String , Long>> sortedGroupedLettersTupleFlux = sortedGroupedLettersFlux.flatMap(groupedFluxToTuplePublisherMapper);

        Flux <String> charsCountStringFlux = sortedGroupedLettersTupleFlux.map(keyCountTupleToStringMapper);

        //Subscribe
        charsCountStringFlux.subscribe(System.out::println);
    }

    public static   Flux <GroupedFlux <String , String> > createSortedGroupedLettersFlux() {

        Function <String , String>  keyMapper = s -> s;

        Comparator<GroupedFlux<String , String>> sortFunction = (o1, o2) -> o1.key().compareTo(o2.key());

        Flux<String> lettersFlux =  createLettersFlux();

        return  lettersFlux.groupBy(keyMapper).
                sort(sortFunction);

        //sortedGroupedLettersFlux.subscribe(groupedFlux -> System.out.println(groupedFlux.key()));

    }


    public static  Flux<String> createLettersFlux() {

        Function <String , String> upperCaseMApper = String::toUpperCase;
        Function<String,  Flux<String>> StringToCharsFluxMapper = string -> Flux.fromArray(string.split(""));

        Comparator<GroupedFlux<String , String>> sortFunction = (o1, o2) -> o1.key().compareTo(o2.key());

        return Flux.just("alpha", "beta", "charlie")
                .map(upperCaseMApper)
                .flatMap(StringToCharsFluxMapper);

    }

/*    Ver 1:
            Flux.just("alpha", "beta", "charlie")
            .map(String::toUpperCase)
        .flatMap(s -> Flux.fromArray(s.split("")))
            .groupBy(String::toString)
        .sort((o1,o2) -> o1.key().compareTo(o2.key()))
            .flatMap(group -> Mono.just(Tuples.of(group.key(), group.count().block())))
            .map(keyAndCount -> keyAndCount.getT1() + " => " + keyAndCount.getT2() + "; ")


    Ver2:
            Flux.just("alpha", "beta", "charlie")
            .map(String::toUpperCase)
    .flatMap(s -> Flux.fromArray(s.split("")))
            .groupBy(String::toString)
    .sort((o1,o2) -> o1.key().compareTo(o2.key()))
            .flatMap(group -> Mono.zip(Mono.just(group.key()), group.count()))
            .map(keyAndCount -> keyAndCount.getT1() + " => " + keyAndCount.getT2() + "; ")*/



}
