package functional.monad.optionalexample.counter;

import java.util.Optional;

public abstract class CounterRepo {


    public abstract Optional<Counter> getThisMonthCounts();

    public abstract Optional<Counter> getPreviousMonthCounts();


}