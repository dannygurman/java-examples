package concurrency.atomicReference;

//The AtomicReference class provides an object reference variable which can be read and written atomically.
// By atomic is meant that multiple threads attempting to change the same AtomicReference
// (e.g. with a compare-and-swap operation)
// will NOT  make the AtomicReference end up in an inconsistent state.
// AtomicReference even has an advanced compareAndSet() method which lets you compare the reference
// to an expected value (reference) and if they are equal, set a new reference inside the AtomicReference object.

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample {

    private static String message = "hello";
    private static AtomicReference<String> atomicReference;

    public static void main(String[] args) {
        atomicReference = new AtomicReference<>(message);

        Runnable runnable = () -> {
            atomicReference.compareAndSet(message, "New Val");
        };

        Thread thread1 = new Thread(runnable, "Thread 1");

        thread1.start();

        System.out.println("Message is: " + message);
        System.out.println("Atomic Reference of Message is: " + atomicReference.get());

    }

}
