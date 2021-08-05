package consumerProducer.ex1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Broker {

    //Using ArrayBlockingQueue as the data holder
    //ArrayBlockingQueue  - A bounded blocking queue backed by an array
    //the producers are going to place the data in the queue and the consumers will fetch from the queue in FIFO format
    //But, if the producers are slow, the consumers will wait for data to come in and if the array is full, the producers will wait for it to free.

    int capacity = 100;
    public ArrayBlockingQueue <Integer> queue = new ArrayBlockingQueue(capacity);

    private  Boolean continueProducing = Boolean.TRUE;

    public void put(Integer data) throws InterruptedException    {
        //Inserts the specified element at the tail of this queue, waiting for space to become available if the queue is full.
        this.queue.put(data);
    }

    public Integer get() throws InterruptedException   {
        //poll - Retrieves and removes the head of this queue, waiting up to the specified wait time if necessary for an element to become available
        //timeout - how long to wait before giving up, in units of unit
       //return - he head of this queue, or null if the specified waiting time elapses before an element is available
        long timeoutInSec = 1;
       return this.queue.poll(timeoutInSec, TimeUnit.SECONDS);
    }

    public Boolean getContinueProducing() {
        return continueProducing;
    }

    public void setContinueProducing(Boolean continueProducing) {
        this.continueProducing = continueProducing;
    }
}
