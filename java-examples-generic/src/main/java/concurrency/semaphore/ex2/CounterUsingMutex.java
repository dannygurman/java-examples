package concurrency.semaphore.ex2;

import java.util.concurrent.Semaphore;

class CounterUsingMutex {

    private final Semaphore mutex;
    private int count;

    CounterUsingMutex() {
        //Mutex-semaphore when 1 slot
        mutex = new Semaphore(1);
        count = 0;
    }

    void increase() throws InterruptedException {
        printWrapMsg("increase - before acquire");
        mutex.acquire();
        printWrapMsg("increase - after acquire");
        this.count = this.count + 1;
        Thread.sleep(1000);
        mutex.release();
        printWrapMsg("increase - after release");

    }

    int getCount() {
        return this.count;
    }

    boolean hasQueuedThreads() {
        return mutex.hasQueuedThreads();
    }

    private int getQueueLength() {
        return mutex.getQueueLength();
    }

    int availablePermits() {
        return mutex.availablePermits();
    }
    private void printWrapMsg(String inMsg){
        String  name= Thread.currentThread().getName();
    System.out.println("name:" + name+ "  availablePermits" + availablePermits() +
              " getQueueLength:"+getQueueLength() + " " + inMsg);
    }

}
