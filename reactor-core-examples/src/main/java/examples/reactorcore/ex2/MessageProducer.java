package examples.reactorcore.ex2;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import org.apache.log4j.Logger;
import reactor.core.publisher.FluxSink;

public class MessageProducer {

    private double productionRate;
    private EmitterProcessor<String> emitter;

    public MessageProducer(double productionRate) {
        this.productionRate = productionRate;
        emitter = EmitterProcessor.create();
    }

    public Flux<String> getStream() {
        return this.emitter;
    }

    public void produce() {

        long sleepTimeOut = Math.round(1000d / productionRate);
        int counter = 0;

       // FluxSink - Wrapper API around a downstream Subscriber for emitting any number of next signals followed by zero or one onError/onComplete.
        FluxSink<String> sink = emitter.sink();
        while (true) {
            try {
                Thread.sleep(sleepTimeOut);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String message = "Message" + counter;
            sink.next(message);
            System.out.println(message + " sent (produce method) Thread:" + Thread.currentThread().getName() );
            counter++;
        }
    }

    public void performanceProduce( int amount) {

        FluxSink<String> sink = emitter.sink();

        for (int i = 0; i < amount; i++) {
            String message = "Message" + i;
            sink.next(message);
            if ( (i%100000) == 0)
            System.out.println(message + " sent (performanceProduce) Thread:" + Thread.currentThread().getName() );
        }
        System.out.println("Performance run completed");
    }

}
