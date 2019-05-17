package consumerProducer.ex1;

//This class is doing the most simplest of things that it can do – adding an integer to the broker.
//
// Some key areas to note are:
//1. There is a property on Broker (continueProducing) which is updated in the end by the producer
// when its done producing.

// This is also known as the “final” or “poison” entry.
// This is used by the consumers to know that there are no more data coming up

//2. UsingThread.sleep to simulate that some producers may take more time to produce the data.
// You can tweak this value and see the consumers act

public class Producer implements Runnable {

    private Broker broker;


    public Producer(Broker broker)    {
        this.broker = broker;
    }


    @Override
    public void run() {
        long sleepTimeBetweenProduce = 2000;//simulating produce time
        Integer intsToProduces = 5;

        try  {
            for ( Integer i = 1; i <= intsToProduces + 1; ++i ) {
                System.out.println("Producer produced: " + i);
                Thread.sleep(sleepTimeBetweenProduce);
                broker.put(i);
            }

            //Producing ended
            this.broker.setContinueProducing( Boolean.FALSE);

            System.out.println("Producer finished its job; terminating.");

        }  catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }




}
