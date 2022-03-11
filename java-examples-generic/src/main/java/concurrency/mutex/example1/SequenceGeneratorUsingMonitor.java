package concurrency.mutex.example1;

import com.google.common.util.concurrent.Monitor;

//So far, we've seen the options to implement mutex using features provided by Java.

//However, the Monitor class of Google's Guava library is a better alternative to the ReentrantLock class.
// As per its documentation, code using Monitor is more readable and less error-prone than the code using ReentrantLock.

public class SequenceGeneratorUsingMonitor extends SequenceGenerator {

    private Monitor mutex = new Monitor();

    @Override
    public int getNextSequence() {
        mutex.enter();
        try {
            return super.getNextSequence();
        } finally {
            mutex.leave();
        }
    }

}