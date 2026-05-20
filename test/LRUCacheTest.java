import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LRUCacheTest {

    LRUCache cache;

    @BeforeEach
    void setUp() {
        cache = new LRUCache(2); // fresh cache before each test
    }

    @Test
    void testGetOnEmptyCacheReturnsMinusOne() {
        assertEquals(-1, cache.get(1));
    }

    @Test
    void testPutAndGet() {
        cache.put(1, 10);
        assertEquals(10, cache.get(1));
    }

    @Test
    void testEvictsLRUWhenFull() {
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3); // should evict key 1
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(3, cache.get(3));
    }

    @Test
    void testGetPromotesToMRU() {
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);    // promotes 1 to MRU
        cache.put(3, 3); // should evict 2, not 1
        assertEquals(1, cache.get(1));
        assertEquals(-1, cache.get(2));
    }

    @Test
    void testDuplicateKeyUpdatesValue() {
        cache.put(1, 10);
        cache.put(1, 99); // update value
        assertEquals(99, cache.get(1));
    }

    @Test
    void testCacheSizeStaysWithinLimit() {
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        // only 2 items should remain
        int count = 0;
        if (cache.get(1) != -1) count++;
        if (cache.get(2) != -1) count++;
        if (cache.get(3) != -1) count++;
        if (cache.get(4) != -1) count++;
        assertEquals(2, count);
    }
}