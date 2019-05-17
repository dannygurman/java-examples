package algorithms.cache.lfu.step3;

// Could be private class in LFUCache
public class Node  {
   //cache key  key
    protected int key;

    //cache value
    protected int value;

    protected Node next;
    protected Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int key() {
            return key;
        }

        public int value() {
            return value;
        }
}
