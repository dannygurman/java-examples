package consumerProducer.ex1;

//This is  a simple class that reads the Integer and prints it on the console.
// However, key points to note are:

// 1. The loop to process data is an endless loop, that runs on two conditions –
// until the producer is producing and there is some data with the broker

//2. Again, the Thread.sleep is used to create effective and different scenarios

public class Consumer implements Runnable {

    private String name;
    private Broker broker;


    public Consumer(String name, Broker broker)   {
        this.name = name;
        this.broker = broker;
    }

    @Override
    public void run()     {
        long sleepTimeMillis = 1000;
        try  {
            Integer data = broker.get();
            while (broker.getContinueProducing() || data != null)  {
                Thread.sleep(sleepTimeMillis);
                System.out.println("Consumer " + this.name + " processed data from broker: " + data);
                data = broker.get();
            }

            System.out.println("Consumer " + this.name + " finished its job; terminating.");
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }


}