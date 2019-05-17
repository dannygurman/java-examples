package algorithms.cache.lfu.step3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LFUCache {

    private Map<Integer, Node> values = new HashMap<>();

    private Map<Integer, Integer> counts = new HashMap<>();

    private TreeMap<Integer, DoubleLinkedList> frequencies = new TreeMap<>();
    private final int MAX_CAPACITY;

    public LFUCache(int capacity) {
        MAX_CAPACITY = capacity;
    }

    public int get(int key) {
        if (!values.containsKey(key)) {
            //value not found
            return -1;
        }

        Node node = values.get(key);

        // Move item from one frequency list to next. O(1) this time.
        int frequency = counts.get(key);

        DoubleLinkedList matchingItemsToFrequencyList =  frequencies.get(frequency);
        //Node contains next in prev - remove on O(1)
        matchingItemsToFrequencyList.remove(node);

        removeIfListEmpty(frequency);

        int newFrequency = frequency + 1;//Increased in 1

        DoubleLinkedList keyListOfNewFrequency = frequencies.computeIfAbsent(newFrequency, k -> new DoubleLinkedList());

        keyListOfNewFrequency.add(node);


        counts.put(key, newFrequency);

        return values.get(key).value;
    }

    public void set(int key, int value) {
        if ( ! values.containsKey(key) ) {

            Node node = new Node(key, value);

            if (values.size() == MAX_CAPACITY) {
                //eviction
                int lowestCount = frequencies.firstKey();   // smallest frequency
                DoubleLinkedList matchingItemsToFrequencyList =  frequencies.get(lowestCount);
                Node nodeToDelete = matchingItemsToFrequencyList.head(); // first item (LRU -least recently used)
                matchingItemsToFrequencyList.remove(nodeToDelete);

                int keyToDelete = nodeToDelete.key();
                removeIfListEmpty(lowestCount);

                values.remove(keyToDelete);
                counts.remove(keyToDelete);
            }

            values.put(key, node);
            counts.put(key, 1);

            // starting frequency = 1
            DoubleLinkedList keyListOd_frequency_1 = frequencies.computeIfAbsent(1, k -> new DoubleLinkedList());
            keyListOd_frequency_1.add(node);
        }

        //TODO - if value contain key
    }

    private void removeIfListEmpty(int frequency) {
        if (frequencies.get(frequency).size() == 0) {
            frequencies.remove(frequency);  // remove from map if list is empty
        }
    }



}