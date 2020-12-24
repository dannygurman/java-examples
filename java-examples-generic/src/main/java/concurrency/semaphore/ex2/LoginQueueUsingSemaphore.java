package concurrency.semaphore.ex2;

import java.util.concurrent.Semaphore;

class LoginQueueUsingSemaphore {

    private final Semaphore semaphore;

    LoginQueueUsingSemaphore(int slotLimit) {
        semaphore = new Semaphore(slotLimit);
    }

    boolean tryLogin() {
        printWrapMsg("tryLogin before acquire");
        boolean acquire =  semaphore.tryAcquire();
        printWrapMsg("tryLogin after acquire-acquired:" + acquire);
        return acquire;
    }

    void logout() {
        printWrapMsg("logout before release");
        semaphore.release();
        printWrapMsg("logout after release");
    }

    int availableSlots() {
        return semaphore.availablePermits();
    }


    private void printWrapMsg(String inMsg){
        String  name= Thread.currentThread().getName();
        System.out.println("name:" + name+ " available " +":"+availableSlots() + " " + inMsg);
    }
}
