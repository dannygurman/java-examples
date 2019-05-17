package concurrency.thread.ex5;

public class ThreadNamesExample {

    public static void main(String[] args) {
        Thread thread = new Thread("New Thread") {
            public void run(){
                System.out.println("run by: " + getName());
            }
        };


        thread.start();
        System.out.println(thread.getName());

        Runnable myRunnable1 = () -> System.out.println("MyRunnable running");

        Thread thread2 = new Thread(myRunnable1, "New Thread 2");

        thread2.start();
        System.out.println(thread2.getName());

        System.out.println("Current Thread: " + Thread.currentThread().getName());

    }
}
