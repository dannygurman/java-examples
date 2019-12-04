package concurrency.deadLock.philosophers.safe;

import concurrency.deadLock.philosophers.safe.Chopstick;

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

    public void eat() {
        if (pickup()) {
            chew();
            putDown();
        }
    }

    public boolean pickup() {
        /* attempt to pick up */
        if (! left.pickUp()) {
            return false;
        }
        if (!right.pickUp()) {
            left.putDown();
            return false;
        }
        return true;
    }


    public void putDown(){
        left.putDown();
        right.putDown();
    }

    public void chew() { }
}
