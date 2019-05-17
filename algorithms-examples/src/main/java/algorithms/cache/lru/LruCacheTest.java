package algorithms.cache.lru;

public class LruCacheTest {

    public static void main(String[] args) {
        LRUCache lruCache = new LruCacheImpl(5);
        lruCache.set(4 ,123);
        lruCache.set(11 , 111);
        lruCache.set(2 , 102);
        lruCache.set(3 , 103);
        lruCache.set(4 , 104);
        lruCache.set(5 , 105);
        lruCache.set(6 , 106);
        lruCache.set(7 , 107);
        //11 not exist because when capacity exxecd we remove it
        lruCache.get(11);

        lruCache.get(4);

        lruCache.printLinkedList();
    }
}
