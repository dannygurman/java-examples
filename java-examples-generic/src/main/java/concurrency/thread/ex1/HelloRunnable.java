package concurrency.thread.ex1;

/** This  idiom, 
 * which employs a Runnable object, is more general,
 *  because the Runnable object can subclass a class other than Thread.
  */
public class HelloRunnable implements Runnable {

    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new Thread(new HelloRunnable())).start();
    }

}
