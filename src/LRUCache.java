import java.util.HashMap;

public class LRUCache {

    public static class Node {

        int limit;

        Node prev;
        Node next;

        int key = -1;
        int val = -1;

        Node(int k, int v) {
            key = k;
            val = v;
            prev = next = null;

        }

    }

    void addNode(Node n) {

        if (map.containsKey(n.key) ) {
            deleteNode(map.get(n.key));
        }
       else if( map.size() == limit){
            deleteNode(tail.prev);
        }
        Node oldPrev = head.next;
        n.prev = head;
        head.next = n;
        n.next = oldPrev;
        oldPrev.prev = n;
        map.put(n.key, n);
    }

    public void deleteNode(Node returnNode) {

        Node oldHead = returnNode.prev;
        Node oldTail = returnNode.next;
        oldHead.next = oldTail;
        oldTail.prev = oldHead;
        map.remove(returnNode.key);


    }

    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);
    int limit;
    HashMap<Integer, Node> map = new HashMap();


    public LRUCache(int capacity) {
        this.head.next = tail;
        this.tail.prev = head;
        limit = capacity;
    }

    public int get(int key) {
        int returnNodeVal = -1;
        Node returnNode = new Node(-1, -1);

        if (map.containsKey(key)) {
            returnNode = map.get(key);
            returnNodeVal = map.get(key).val;
            deleteNode(returnNode);
            addNode(returnNode);
        }
        return returnNodeVal;
    }

    public void put(int key, int value) {
        Node n = new Node(key, value);
        addNode(n);
    }
}
