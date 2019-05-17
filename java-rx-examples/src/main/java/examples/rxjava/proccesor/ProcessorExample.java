package examples.rxjava.proccesor;


public abstract class ProcessorExample {

    protected static SomeFlowableSubscriber<Integer> getFirstSubscriber() {
        return new SomeFlowableSubscriber<Integer>( "1");

    }

    protected static SomeFlowableSubscriber<Integer> getSecondSubscriber() {
        return new SomeFlowableSubscriber<Integer>("2");
    }
}
