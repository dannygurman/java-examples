package examples.rxjava.proccesor;


import io.reactivex.processors.AsyncProcessor;

/*
Observable:
 Assume that a professor is an observable.
The professor teaches about some topic.

Observer:
Assume that a student is an observer.
The student observes the topic being taught by the professor.

Async Subject
t only emits the last value of the source Observable(and only the last value)
only after that source Observable completes.

Here, if a student entered at any point of time into the classroom,
and he wants to listen only about the last thing(and only the last thing) being taught,
 after class is over. So, here we will use Async.
 */
public class AsyncProcessorExample extends ProcessorExample {

    public static void main(String[] args) {
        AsyncProcessor<Integer> source = AsyncProcessor.create();
// It will get only 4 and onComplete
        source.subscribe(getFirstSubscriber());
        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

// It will also get only get 4 and onComplete
        source.subscribe(getSecondSubscriber());
        source.onNext(4);
        source.onComplete();
    }

    /*Subscriber 1 : 4
    Subscriber1 :  completed
    Subscriber 2 : 4
    Subscriber2 :  completed*/
}
