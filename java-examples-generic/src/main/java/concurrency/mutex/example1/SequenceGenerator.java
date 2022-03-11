package concurrency.mutex.example1;

public class SequenceGenerator {

    private int currentValue = 0;

    //Multithreaded Unsafe
    public int getNextSequence() {
        currentValue = currentValue + 1;
        return currentValue;
    }

}