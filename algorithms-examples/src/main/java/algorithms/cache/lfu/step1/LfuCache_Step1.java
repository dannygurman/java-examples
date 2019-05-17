package algorithms.cache.lfu.step1;

import java.util.HashMap;
import java.util.Map;

/*Step 1: Store values and access frequencies
        We need 2 things to start with

        Map to store key-value pairs
        Map to store counts/frequency of access
        Now insert and access operations are O(1) i.e. they perform in constant time.*/



public class LfuCache_Step1 {

    //Key is cache index and value is the cache value
    private Map<Integer, Integer> values = new HashMap<>();

    //Key is cache index and value frequencies - num of times that key was inserted (put) or fetched (get)

    private Map<Integer, Integer> frequencies = new HashMap<>();

    private final int MAX;

    public LfuCache_Step1(int capacity) {
        MAX = capacity;
    }

    public int get(int key) {
        if ( ! values.containsKey(key)) {
            //value not found
            return -1;
        }
        //Increase frequencies by 1
        increaseFrequencies( key);

        return values.get(key);
    }

    public void set(int key, int value) {
        if ( ! values.containsKey(key)) {
            //not exist yet
            // TODO: eviction code
            // set initial access count as 1
            frequencies.put(key, 1);
        } else {
            increaseFrequencies( key);
        }

        values.put(key, value);

    }

    private void increaseFrequencies(int key){
        int currentValue = frequencies.get(key) ;
        frequencies.put(key, currentValue + 1);
    }
}