package consumerProducer.ex3;

public class Consumer extends Thread {
    private CubbyHole cubbyhole;
    private int consumerId;

    public Consumer(CubbyHole c, int consumerId) {
        cubbyhole = c;
        this.consumerId = consumerId;
    }
    public void run() {
        int value ;
        for (int i = 0; i < 10; i++) {
            value = cubbyhole.get();
            System.out.println("Consumer #" + this.consumerId + " got: " + value);
        }
    }

}
