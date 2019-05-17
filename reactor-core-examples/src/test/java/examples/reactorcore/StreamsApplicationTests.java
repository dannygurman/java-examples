package examples.reactorcore;

import examples.reactorcore.ex2.MessageConsumer;
import examples.reactorcore.ex2.MessageProducer;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.core.publisher.WorkQueueProcessor;
import reactor.util.concurrent.WaitStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class StreamsApplicationTests {

    private List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );
    @Test
    public void messageProducer() {
        double consumptionRate = 0.5;
        MessageProducer messageProducer = new MessageProducer(consumptionRate);
        messageProducer.getStream().subscribe(message -> System.out.print("Consumed:" + message +".\n"));
        messageProducer.produce();
    }

    @Test
    public void messageConsumer() {
        double consumptionRate = 0.5;
        Flux<String> manyLetters = Flux.fromIterable(words);

        MessageConsumer messageConsumer = new MessageConsumer(consumptionRate);
        messageConsumer.consume(manyLetters);
    }

    @Test
    public void thePowerOfApi() {

        double productionRate = 0.5;
        double consumptionRate = 0.5;

        MessageProducer messageProducer = new MessageProducer(productionRate);
        MessageConsumer messageConsumer = new MessageConsumer(consumptionRate);

        Flux<String> stream = messageProducer.getStream();

        Function<String, Mono <String>> messageMapper = msg -> Mono.just("How easy it is " + msg);

        Flux<String> flatStream = stream.flatMap(messageMapper);

        messageConsumer.consume(flatStream);

        messageProducer.produce();
    }

    @Test
    public void thePowerOfApiFilter() {

        double productionRate = 0.5;
        double consumptionRate = 0.5;

        MessageProducer messageProducer = new MessageProducer(productionRate);
        MessageConsumer messageConsumer = new MessageConsumer(consumptionRate);

        Flux<String> stream = messageProducer.getStream();


        Function<String, Mono <String>> messageMapper = msg -> Mono.just("How easy it is " + msg);
        Predicate<String> endWith2Filter = msg -> msg.endsWith("2");

        Flux<String>  streamFilteredMapped = stream.
                filter(endWith2Filter).
                flatMap(messageMapper);

        messageConsumer.consume(streamFilteredMapped);

        messageProducer.produce();
    }

    @Test
    public void producerMultipleConsumer() {

        double productionRate = 0.5;
        double consumptionRate1 = 0.5;
        double consumptionRate2 = 0.5;

        MessageProducer messageProducer = new MessageProducer(productionRate);

        MessageConsumer messageConsumer0 = new MessageConsumer(consumptionRate1 , "consumer 1");
        MessageConsumer messageConsumer1 = new MessageConsumer(consumptionRate2 , "consumer 2");

        Flux<String> stream = messageProducer.getStream();

        messageConsumer0.consume(stream);
        messageConsumer1.consume(stream);

        messageProducer.produce();
    }

    @Test
    public void letsGoParallel() {

        double productionRate = 0.5;//40;
        double consumptionRate = 0.5;

        int bufferSize = 8;//128;

        int parallelWorkers = 1;

        letsGoParallelInternal( parallelWorkers ,  productionRate ,  consumptionRate ,  bufferSize );
    }


    @Test
    public void letsGoCrazyParallel() {
        int parallelWorkers = 3;

        double productionRate = 10;
        double consumptionRate = 0.5;

        int bufferSize = 8;

        letsGoParallelInternal( parallelWorkers ,  productionRate ,  consumptionRate ,  bufferSize );
    }


    private void letsGoParallelInternal(int parallelWorkers , double productionRate , double consumptionRate , int bufferSize ) {

        //WaitStrategy - Strategy employed to wait for specific LongSupplier values with various spinning strategies.

        //Blocking strategy that uses a lock and condition variable for consumer waiting on a barrier.
        // This strategy can be used when throughput and low-latency are not as important as CPU resource.
        WaitStrategy waitStrategy = WaitStrategy.blocking();

        // autoCancel - automatically cancel  Default value is true.
        //By default, if all of its subscribers are cancelled (which basically means they have all un-subscribed),
        // it will clear its internal buffer and stop accepting new subscribers
        boolean autoCancel = false;

        MessageProducer messageProducer = new MessageProducer(productionRate);
        MessageConsumer messageConsumer = new MessageConsumer(consumptionRate);

        WorkQueueProcessor<String> bufferStream = WorkQueueProcessor.<String>builder().name("BufferStream").bufferSize(bufferSize).waitStrategy(waitStrategy).autoCancel(autoCancel).build();


        FluxSink<String> stringBlockingSink = bufferStream.sink();

        Consumer <String> msgConsumer =  msg -> stringBlockingSink.next(msg);

        Consumer <Throwable> errorConsumer =  e -> System.out.println("Error:" + e.getCause());

        messageProducer.getStream().subscribe(msgConsumer , errorConsumer);


        messageProducer.getStream().subscribe(msgConsumer);

        for (int i = 0; i < parallelWorkers; i++) {
            messageConsumer.consume(bufferStream);
        }

        messageProducer.produce();
    }


    @Test
    public void letsGoReallyFast() throws InterruptedException {

        int parallelProducers = 10;
        int parallelConsumers = 10;
        int messagesPerProducer = 1_000_000;

        int bufferSize = 4096;

        double consumptionRate = 0.5;
        double productionRate = 10;

        WaitStrategy waitStrategy = WaitStrategy.blocking();
        boolean autoCancel = false;

        WorkQueueProcessor<String> bufferStream = WorkQueueProcessor.<String>builder().name("BufferStream").bufferSize(bufferSize).waitStrategy(waitStrategy).autoCancel(autoCancel).build();

        //try-with-resources
        try(TimeMeasurement tm = new TimeMeasurement()){
            List<MessageConsumer> allConsumers = new ArrayList<>();
            for (int i = 0; i < parallelConsumers; i++) {
                MessageConsumer messageConsumer = new MessageConsumer(consumptionRate);
                messageConsumer.performanceConsume(bufferStream);
                allConsumers.add(messageConsumer);
            }


            FluxSink<String> stringBlockingSink = bufferStream.sink();

            for (int i = 0; i < parallelProducers; i++) {
                Runnable runnableProducer = createRunnableProducer(productionRate ,stringBlockingSink, messagesPerProducer);
                Thread producer = new Thread(runnableProducer);
                producer.start();
            }

            long sleepInMillis = 1000;
            while ( totallyConsumed(allConsumers) != parallelProducers * messagesPerProducer) {
                Thread.sleep(sleepInMillis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Runnable createRunnableProducer(double productionRate ,final  FluxSink<String> stringBlockingSink , int amountOfMessagesToCreate) {
        return () -> {
            System.out.println("Performance producer started " + Thread.currentThread().getName() );
            MessageProducer producer = new MessageProducer(productionRate);
            Consumer <String> msgConsumer =  msg -> stringBlockingSink.next(msg);
            producer.getStream().subscribe(msgConsumer);
            producer.performanceProduce(amountOfMessagesToCreate);
            System.out.println("Performance producer finished "+ Thread.currentThread().getName());
        };
    }


    private int totallyConsumed(List<MessageConsumer> consumers) {
        int result = 0;
        for (MessageConsumer consumer : consumers) {
            result += consumer.getConsumed();
        }
        System.out.println("totallyConsumed = " + result);
        return result;
    }
}
