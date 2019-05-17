package algorithms.cache.lru;

class Node {
    //key in map
    int key;

    int value;
    Node pre;

    Node next;

    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}