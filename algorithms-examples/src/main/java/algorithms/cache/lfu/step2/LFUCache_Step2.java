package algorithms.cache.lfu.step2;

import java.util.*;

public class LFUCache_Step2 {

    //Key is cache index and value is the cache value
    private Map<Integer, Integer> values = new HashMap<>();

    //Key is cache index and value is the number of usage (like the frequencies map  in LFUCache_Step1)
    private Map<Integer, Integer> counts = new HashMap<>();

    // TreeMap in general: The map is sorted according to the natural ordering of its KEYS, or by a
    // Comparator provided at map creation time(depending on which constructor is used)
    //Key - frequency of usage
    //Value - list of cache keys (first item is least recently used , the end one is most recently used)
    //Note - linked list implementation will be used
    private TreeMap<Integer, List<Integer>> frequencies = new TreeMap<>();

    private final int MAX_CAPACITY;


    public LFUCache_Step2(int capacity) {
        MAX_CAPACITY = capacity;
    }

    public int get(int key) {
        if (!values.containsKey(key)) {
            //value not found
            return -1;
        }

        // Move item from one frequency list to next.
        // NOT O(1)!! due to list iteration.

        //First finding matching frequency
        int frequency = counts.get(key);

        List matchingItemsToFrequencyList =  frequencies.get(frequency);

        //In general list remove - Removes the first occurrence of the specified element from this list - mot O(1)
        //removing the key from the list
        matchingItemsToFrequencyList.remove(new Integer(key));

        if (matchingItemsToFrequencyList.size() == 0) {
            // remove from frequencies map if list is empty after deletion
            frequencies.remove(frequency);
        }
        int newFrequency = frequency + 1;//Increased in 1

        //Reminder - computeIfAbsent return the current (existing or computed) value associated with the specified key, or null if the computed value is null.
        //If there is new list for frequency - creating new one
        List keyListOfNewFrequency =  frequencies.computeIfAbsent(newFrequency, k -> new LinkedList<>());
        //Adding the the requency array
        keyListOfNewFrequency.add(key);

        //add to /update  count
        counts.put(key, newFrequency);

        //return value
        return values.get(key);
    }

    public void set(int key, int value) {
        if ( ! values.containsKey(key)) {
            //no value for key - adding new value
            if (values.size() == MAX_CAPACITY) {
                //Reaching max capacity -Need to delete item with lowest frequency and Lease recently used
                // Get first item from 'list of smallest frequency'
                int lowestCount = frequencies.firstKey();

                List <Integer> matchingItemsToFrequencyList = frequencies.get(lowestCount);
                //remove the first elment (Leaset recently used)
                //The remove return the deleted item
                int keyToDelete = matchingItemsToFrequencyList.remove(0);

                if (frequencies.get(lowestCount).size() == 0) {
                    frequencies.remove(lowestCount); // remove from map if list is empty
                }

                //remove key from vakues and count map
                values.remove(keyToDelete);
                counts.remove(keyToDelete);
            }

            //Adding new value to values and to count (count of 1 - for addition
            values.put(key, value);
            counts.put(key, 1);

            //Reminder - computeIfAbsent return the current (existing or computed) value associated with the specified key, or null if the computed value is null.
            //Adding to frequency "1

            List keyListOd_frequency_1 = frequencies.computeIfAbsent(1, k -> new LinkedList<>());
            keyListOd_frequency_1.add(key); // starting frequency = 1
        }
        //TODO - if value contain key
    }
}

