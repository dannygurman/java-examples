package concurrency.thread.threadsafe.ex1;

//However, if two threads call the add() method simultaneously on different instances then it does NOT lead to race condition
// Now the two threads have each their own instance of NotThreadSafe so their calls to
// the add method doesn't interfere with each other.
// The code does not have race condition anymore.
// So, even if an object is not thread safe it can still be used in a way that does NOT lead to race condition.

public class NoRaceConditionExample {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new MyRunnable(new NotThreadSafe()));
        t1.start();
        Thread t2 =  new Thread(new MyRunnable(new NotThreadSafe()));
        t2.start();

        t1.join();
        t2.join();

    }

}
