package concurrency.thread.ex1;

/** This idiom is easier to use in simple applications,
 *  but is limited by the fact that your task class
 *   must be a descendant of Thread
 *
 */
public class HelloThread extends Thread {

    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new HelloThread()).start();
    }

}
