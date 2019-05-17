package examples.rxjava.proccesor;


import io.reactivex.processors.BehaviorProcessor;

/*
Observable:
 Assume that a professor is an observable.
The professor teaches about some topic.

Observer:
Assume that a student is an observer.
The student observes the topic being taught by the professor.

Behavior Subject
It emits the most recently emitted item and all the subsequent items of the
source Observable when an observer subscribes to it.

Here, if a student entered late into the classroom,
he wants to listen the most recent things(not from the beginning) being taught by the professor so that he gets the idea of the context.
 So, here we will use Behavior.
 */
public class BehaviorProcessorExample extends ProcessorExample {

    public static void main(String[] args) {
        BehaviorProcessor<Integer> source = BehaviorProcessor.create();


        // It will get 1, 2, 3, 4 and onComplete
        source.subscribe(getFirstSubscriber());
        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
// It will get 3(last emitted)and 4(subsequent item) and onComplete
        source.subscribe(getSecondSubscriber());
        source.onNext(4);

        source.onComplete();
    }

    /*Subscriber 1 : 1
    Subscriber 1 : 2
    Subscriber 1 : 3
    Subscriber 2 : 3
    Subscriber 1 : 4
    Subscriber 2 : 4
    Subscriber1 :  completed
    Subscriber2 :  completed*/
}
