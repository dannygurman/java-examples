package functional.monad.optionalexample.counter;

import java.util.Optional;
import java.util.function.Function;

//https://medium.com/@afcastano/monads-for-java-developers-part-1-the-optional-monad-aa6e797b8a6e
public class TotalCountsCalculator {

    private CounterRepo repo;

    public TotalCountsCalculator(CounterRepo repo) {
        this.repo = repo;
    }

    //Verbose version - see totalColourShortVersion below
    public Optional<Integer> totalColour() {

        Function<Counter, Optional<Integer>> counterToColourMapper = Counter::colour; //counter -> counter.colour();

        Optional<Counter> thisMountCount = repo.getThisMonthCounts();

        Optional<Integer> thisMountColourCount = thisMountCount.flatMap(counterToColourMapper);

        Function<Integer, Optional<Integer>> fetchPreviousMountAndAddMapper = colour1 -> {

            Optional<Counter> previousMonthCounts = repo.getPreviousMonthCounts();
            Optional<Integer> previousMountColourCount = previousMonthCounts.flatMap(counterToColourMapper);

            Function<Integer, Optional<Integer>> addToColour1Mapper = colour2 -> Optional.of(colour1 + colour2);
            return previousMountColourCount.flatMap(addToColour1Mapper);

        };

        Optional<Integer> totalColour = thisMountColourCount.flatMap(fetchPreviousMountAndAddMapper);

        return totalColour;
    }

    public Optional<Integer> totalColourShortVersion() {

        return
                repo.getThisMonthCounts().flatMap(Counter::colour).flatMap(colour1 ->
                        repo.getPreviousMonthCounts().flatMap(Counter::colour).flatMap(colour2 ->

                                Optional.of(colour1 + colour2)
                        ));
    }

}
