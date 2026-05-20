//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        LRUCache cac=new LRUCache(2);
        cac.put(1,1);
        cac.put(1, 1); // cache is {1=1}
        cac.put(2, 2); // cache is {1=1, 2=2}
        cac.get(1);    // return 1
        cac.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        cac.get(2);    // returns -1 (not found)
        cac.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        cac.get(1);    // return -1 (not found)
        cac.get(3);    // return 3
        cac.get(4);
    }
}