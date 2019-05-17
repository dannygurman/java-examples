package consumerProducer.ex3;
//Broker
public class CubbyHole {

    //one place content
    private int contents;

    private boolean available = false;

    public synchronized int get() {
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        available = false;

        notifyAll();

        return contents;
    }

    public synchronized void put(int value) {
        while (available == true) {
            try {
                //wait for place to put in
                wait();
            } catch (InterruptedException e) { }
        }
        contents = value;

        available = true;

        notifyAll();
    }
}
