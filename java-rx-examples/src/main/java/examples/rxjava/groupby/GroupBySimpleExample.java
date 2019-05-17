package examples.rxjava.groupby;

import examples.rxjava.ObservableExampleAbs;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;


/*RESULT:
EVEN : 0
        ODD : 1
        EVEN : 2
        ODD : 3
        EVEN : 4
        ODD : 5
        EVEN : 6
        ODD : 7
        EVEN : 8
        ODD : 9*/
public class GroupBySimpleExample extends ObservableExampleAbs {

    public static void main(String[] args) {
        System.out.println("------GroubBy------------");

        Function<Integer, String> keySelector = i -> (0 == (i % 2) ? "EVEN" : "ODD");

        Consumer<GroupedObservable<String, Integer>> groupedObservableOnNext  = (group) -> {
         String groupKey = group.getKey();

            Consumer <Integer> numberOnNext = (number) -> System.out.println(groupKey + " : " + number);

           group.subscribe(numberOnNext);
       };

        Observable.fromArray(numbers)
                .groupBy(keySelector)
                .subscribe(groupedObservableOnNext);
    }

}
/*------GroubBy------------
        EVEN : 0
        ODD : 1
        EVEN : 2
        ODD : 3
        EVEN : 4
        ODD : 5
        EVEN : 6
        ODD : 7
        EVEN : 8
        ODD : 9*/
