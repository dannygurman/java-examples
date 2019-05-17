package examples.reactorcore.ex2;

import org.apache.log4j.Logger;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

//Created by OlegG on 2/2/2017.
public class MessageConsumer {

    private  String name;
    private AtomicInteger consumed;
    private double consumptionRate;

    public MessageConsumer(double consumptionRate , String name) {
        this.consumptionRate = consumptionRate;
        this.consumed = new AtomicInteger(0);
        this.name = name;
    }

    public MessageConsumer(double consumptionRate) {
        this(consumptionRate , "CONSUMER");
    }


    public void consume(Flux<String> flux){
        flux.subscribe(this::innerConsume);
    }

    private void innerConsume(String s) {
        long sleepTimeOut = Math.round(1000d / consumptionRate);
        try {
            Thread.sleep(sleepTimeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(s + "  consumed (innerConsume in " + name + " Thread:" + Thread.currentThread().getName() + " ) ");
    }

    public void performanceConsume(Flux<String> input){
        input.subscribe(this::innerFastConsume);
    }

    private void innerFastConsume(String s) {
        this.consumed.incrementAndGet();
        if (s.contains("00000")) {
            System.out.println(s + "  consumed (innerFastConsume in " + name + " Thread:" + Thread.currentThread().getName() + " ) ");
        }
    }

    public int getConsumed(){
        return this.consumed.get();
    }
}
