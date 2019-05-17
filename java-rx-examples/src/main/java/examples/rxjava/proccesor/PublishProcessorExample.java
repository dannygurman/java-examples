package examples.rxjava.proccesor;

/*
Observable:
 Assume that a professor is an observable.
The professor teaches about some topic.

        Observer:
        Assume that a student is an observer.
        The student observes the topic being taught by the professor.

        Publish Subject/processor
        It emits all the subsequent items of the source Observable at the time of subscription.

        Here, if a student entered late into the classroom,
        he just wants to listen from that point of time when he entered the classroom.
        So, Publish will be the best for this use-case.
*/

import io.reactivex.processors.PublishProcessor;


public class PublishProcessorExample extends ProcessorExample {

    public static void main(String[] args) {

        PublishProcessor<Integer> source = PublishProcessor.create();

        // It will get 1, 2, 3, 4 and onComplete
        source.subscribe(getFirstSubscriber());

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

     // It will get 4 and onComplete for second observer also.
        source.subscribe(getSecondSubscriber());

        source.onNext(4);

       source.onComplete();
    }

    /*Subscriber 1 : 1
    Subscriber 1 : 2
    Subscriber 1 : 3
    Subscriber 1 : 4
    Subscriber 2 : 4
    Subscriber1 :  completed
    Subscriber2 :  completed*/
}
