package algorithms.cache.lru;

import java.util.HashMap;
import java.util.Iterator;

/*

        Analysis

        The key to solve this problem is using a double linked list
        which enables us to quickly move nodes.

        The LRU cache is a hash table of keys and double linked nodes.
        The hash table makes the time of get() to be O(1).

        The
        list of double linked nodes make the nodes adding/removal operations O(1).
        */

public class  LruCacheImpl  implements  LRUCache {
    int capacity;
    HashMap<Integer, Node> map = new HashMap<Integer, Node>();
    Node head=null;
    Node end=null;

    public LruCacheImpl(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            Node n = map.get(key);

            //removing searched node from its current position in the double linked list
            remove(n);

            //set to head (recently used)
            setHead(n);

            return n.value;
        }

        return -1;
    }

    public void set(int key, int value) {

        if(map.containsKey(key)){
            //If found in map
            Node old = map.get(key);
            //replace value
            old.value = value;

            //removing searched node from its current position in the double linked list
            remove(old);

            //set to head (recently used)
            setHead(old);
        }else{
            //Not existing - creating new
            Node created = new Node(key, value);
            if(map.size() >= capacity) {
                //Reach max capacity

                //removing first from map the least used in the tail (end) of the linked list(using key saved in node)
                map.remove(end.key);
                //removing also from linked list
                remove(end);
            }
            //adding new node to head of the linked list
            setHead(created);

            //adding to map
            map.put(key, created);
        }

    }


    public  void printLinkedList () {
        Iterator<Integer> iterator = iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    private void remove(Node n){
        if(n.pre!=null){
            n.pre.next = n.next;
        }else{
            //n is current head
            head = n.next;
        }

        if(n.next!=null){
            n.next.pre = n.pre;
        }else{
            // n is current end
            end = n.pre;
        }

    }



    private void setHead(Node n){
        n.next = head;
        n.pre = null;

        if(head!=null)
            head.pre = n;

        head = n;

        if(end ==null)
            end = head;
    }

    private Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    Integer data = current.value;
                    current = current.next;
                    return data;
                }
                return null;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove not implemented.");
            }

        };

    }



}
