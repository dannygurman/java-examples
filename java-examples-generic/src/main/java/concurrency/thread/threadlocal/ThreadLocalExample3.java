package concurrency.thread.threadlocal;

import static java.lang.Thread.sleep;

 //Both threads execute the run() method, and thus sets different values on the ThreadLocal instance.
// //If the access to the set() call had been synchronized, and it had NOT been a ThreadLocal object, the second thread would
// //have overridden the value set by the first thread.

//However, since it is a ThreadLocal object then the two threads cannot see each
// other's values. Thus, they set and get different values.

public class ThreadLocalExample3  {

    public static  class MyRunnable implements Runnable {
        private ThreadLocal<Integer> threadLocal =   new ThreadLocal<Integer>();
        @Override
        public void run() {

            System.out.println("1:" + threadLocal.get());

            threadLocal.set( (int) (Math.random() * 100D) );

            try {
                sleep(2000);
            } catch (InterruptedException e) {
            }

            System.out.println("2:" + threadLocal.get());
        }
    }


    public static void main(String[] args) throws InterruptedException{
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
       // sleep (4000);
        thread2.start();

        thread1.join(); //wait for thread 1 to terminate
        thread2.join(); //wait for thread 2 to terminate
    }

}
