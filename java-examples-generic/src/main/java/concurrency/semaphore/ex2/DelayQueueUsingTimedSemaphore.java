package concurrency.semaphore.ex2;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.concurrent.TimedSemaphore;

class DelayQueueUsingTimedSemaphore {

    private final TimedSemaphore semaphore;

    DelayQueueUsingTimedSemaphore(long period, int slotLimit) {
        semaphore = new TimedSemaphore(period, TimeUnit.SECONDS, slotLimit);
    }

    boolean tryAdd() {
        printWrapMsg("tryAdd before acquire");
        boolean acquire =  semaphore.tryAcquire();
        printWrapMsg("tryAdd after acquire");
        return acquire;
    }

    int availableSlots() {
        return semaphore.getAvailablePermits();
    }

    private void printWrapMsg(String inMsg){
        String  name= Thread.currentThread().getName();
        System.out.println("name:" + name+ " available " +":"+availableSlots() + " " + inMsg);
    }
}
