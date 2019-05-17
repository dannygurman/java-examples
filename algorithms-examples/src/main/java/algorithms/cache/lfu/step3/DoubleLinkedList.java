package algorithms.cache.lfu.step3;

// Could be private class in LFUCache

public class DoubleLinkedList {
    private int n;
    private Node head;
    private Node tail;

    public void add(Node node) {
        if (head == null) {
            //list empty
            head = node;
        } else {
            //adding after tail
            tail.next = node;
            node.prev = tail;
        }
        //tail is the new node
        tail = node;
        n++;
    }

    public void remove(Node node) {

        if (node.next == null) {
            //node was tail - previous necome tail
            tail = node.prev;
        } else {
            //connecting next node to prev
            node.next.prev = node.prev;
        }

        if (head.key == node.key) {
            //node is head  = next become previous
            head = node.next;
        } else {
            //connecting previous to next
            node.prev.next = node.next;
        }

        n--;
    }

    public Node head() {
        return head;
    }

    public int size() {
        return n;
    }
}

