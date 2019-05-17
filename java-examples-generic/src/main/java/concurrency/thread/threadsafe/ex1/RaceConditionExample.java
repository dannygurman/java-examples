package concurrency.thread.threadsafe.ex1;

//http://tutorials.jenkov.com/java-concurrency/thread-safety.html

//Notice how the two MyRunnable instances share the same NotThreadSafe instance.
// //Therefore, when they call the add() method on the NotThreadSafe instance
// it leads to race condition.

public class RaceConditionExample {
    public static void main(String[] args) throws InterruptedException {
        NotThreadSafe sharedInstance = new NotThreadSafe();

        Thread t1 = new Thread(new MyRunnable(sharedInstance));
        t1.start();
        Thread t2 =  new Thread(new MyRunnable(sharedInstance));
        t2.start();

        t1.join();
        t2.join();

        System.out.println(sharedInstance.toString());
    }


}
