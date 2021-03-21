package concurrency.ConcurrentCollections.conHashMap;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

    /**
     * The underlined data structure for ConcurrentHashMap is Hashtable.
     *
     *  ConcurrentHashMap class is thread-safe i.e. multiple thread can operate on a single object
     *  without any complications.
     *
     * At a time any number of threads are applicable for read operation without locking the ConcurrentHashMap object
     * which is not there in HashMap.
     *
     * In ConcurrentHashMap, the Object is divided into number of segments according to the concurrency level.
     * Default concurrency-level of ConcurrentHashMap is 16.
     *
     * In ConcurrentHashMap, at a time any number of threads can perform retrieval operation
     * BUT for updation in object, thread must lock the particular segment in which thread want to operate.
     * This type of locking mechanism is known as Segment locking or bucket locking.
     * Hence at a time 16 updation operations can be performed by threads.
     *
     * null insertion is NOT possible in ConcurrentHashMap as key or value.
     *
     * Load-Factor: It's a threshold, used to control resizing.
     *
     *  Concurrency-Level - Defines the number which is an estimated number of concurrently updating threads.
     *  The implementation performs internal sizing to try to accommodate this  many threads.
     *
     *  #Initial Capacity: The implementation performs internal sizing to accommodate these many elements.
     *
     *
     *
     *
     * @param args
     */
    public static void main(String[] args)
    {
        ConcurrentHashMap m = new ConcurrentHashMap();
        m.put(100, "Hello");
        m.put(101, "Geeks");
        m.put(102, "Geeks");

        // Here  Hello will not be added because 101 key
        // is already present in ConcurrentHashMap object
        m.putIfAbsent(101, "Hello");

        // We can remove entry because 101 key
        // is associated with For value
        m.remove(101, "Geeks");

        // Now we can add Hello
        m.putIfAbsent(103, "Hello");

        // We cant replace Hello with For
        m.replace(101, "Hello", "For");
        System.out.println(m);
    }
}

