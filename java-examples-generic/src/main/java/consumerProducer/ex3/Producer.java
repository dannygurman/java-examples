package consumerProducer.ex3;

public class Producer  extends Thread {
    private CubbyHole cubbyhole;
    private int producerID;


    public Producer(CubbyHole c, int producerId) {
        cubbyhole = c;
        this.producerID = producerId;
    }
    public void run() {
        for (int i = 0; i < 10; i++) {

            cubbyhole.put(i);

            System.out.println("Producer #" + this.producerID + " put: " + i);
            try {
                int sleepInMillis =  (int)(Math.random() * 1000);
                System.out.println("Producer #" + this.producerID + " sleep for: " + sleepInMillis + "millis");
                sleep(sleepInMillis);
            } catch (InterruptedException e) { }
        }
    }
}
