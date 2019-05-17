package examples.rxjava.proccesor;



/*
Observable:
 Assume that a professor is an observable.
The professor teaches about some topic.

Observer:
Assume that a student is an observer.
The student observes the topic being taught by the professor.

 Replay Subject
It emits all the items of the source Observable, regardless of when the subscriber subscribes.

Here, if a student entered late into the classroom,
he wants to listen from the beginning.
 So, here we will use Replay to achieve this
*/

import io.reactivex.processors.ReplayProcessor;

public class ReplayProcessorExample extends ProcessorExample {

    public static void main(String[] args) {
        ReplayProcessor<Integer> source = ReplayProcessor.create();
         // It will get 1, 2, 3, 4

        source.subscribe(getFirstSubscriber());//Processor is Publisher so he has subscribe

        source.onNext(1);//Calling on next in Subscriber - Processor(source) is also subscriber
        source.onNext(2);
        source.onNext(3);
        source.onNext(4);

        source.onComplete();
// It will also get 1, 2, 3, 4 as we have used replay Subject

        source.subscribe(getSecondSubscriber());
    }

 /*   Subscriber 1 : 1
    Subscriber 1 : 2
    Subscriber 1 : 3
    Subscriber 1 : 4
    Subscriber1 :  completed
    Subscriber 2 : 1
    Subscriber 2 : 2
    Subscriber 2 : 3
    Subscriber 2 : 4
    Subscriber2 :  completed*/
}
