package examples.rxjava.proccesor;


import io.reactivex.FlowableSubscriber;
import org.reactivestreams.Subscription;

public class SomeFlowableSubscriber<T> implements FlowableSubscriber<T> {

   private String id;
   private Subscription subscription;

    public SomeFlowableSubscriber(String id) {
        this.id = id;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
     System.out.println("Subscriber " + id + " onSubscribe");
     this.subscription = subscription;
    //this ensures that the data will flow properly when onNext is called.
     //No events will be sent by a Publisher until demand is signaled via this method.
     subscription.request(1);
    }


    @Override
    public void onNext(T value) {
        System.out.println("Subscriber " + id + " onNext: "  + value);
       // n (1 here)- the strictly positive number of elements to requests to the upstream Publisher
        subscription.request(1);
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("error");
    }

    @Override
    public void onComplete() {
        System.out.println("Subscriber" + id + " :  completed");
    }


}
