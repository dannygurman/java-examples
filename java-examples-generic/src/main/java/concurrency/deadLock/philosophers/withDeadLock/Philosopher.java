package concurrency.deadLock.philosophers.withDeadLock;

public class Philosopher extends Thread {
    /**
     * Running the above code may lead to a deadlock if all the philosophers have a left chopstick
     * and are waiting for the right one.
     */
    private int bite = 20;

    private Chopstick left;
    private Chopstick right;

    public Philosopher(Chopstick left, Chopstick right) {
        this.left = left;
        this.right = right;
    }

    public void eat () {
        pickUp();
        chew();
        putDown();
    }

    public void pickUp(){
        left.pickUp();
        right.pickUp();
    }

    public void putDown(){
        left.putDown();
        right.putDown();
    }

    public void chew() { }
}
