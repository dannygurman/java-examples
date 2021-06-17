package consumerProducer.ex1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//The solution is what we see everyday in many places – like the cinema hall queue, Petrol Pumps etc.
// There are so many people who come in to book a ticket and based on how many people come in,
// the more people are added to issue tickets.

//his is the first class where the contract between the producers and consumers are managed.
// The controller will setup 1 thread for the Producer and 2 threads for the consumer.
// Based on the needs we can create as many threads as we need; and even can even read the
// data from a properties or do some dynamic magic.
//
// For now, we will keep this simple.


public class ProducerConsumerController {

    public static void main(String[] args) {
        int numOfThreads = 3;
        try {
            Broker broker = new Broker();
            ExecutorService threadPool = Executors.newFixedThreadPool(numOfThreads);

            //Execute - not returning future
            threadPool.execute(new Consumer("consumer1", broker));
            threadPool.execute(new Consumer("consumer2", broker));

            //When submitting Runnable , and not callable - the Future get will return null upon success
            Future producerStatus = threadPool.submit(new Producer(broker));

            /// this will wait for the producer to finish its execution.
            System.out.println("Calling producer Status get");
            producerStatus.get();
            System.out.println("After producer Status get");

            //shut down - Initiates an orderly shutdown in which previously submitted tasks are executed,
            //but no new tasks will be accepted.
            //Invocation has no additional effect if already shut down.
            threadPool.shutdown();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

}
