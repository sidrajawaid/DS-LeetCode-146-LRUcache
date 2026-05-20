# LRU Cache — Java Implementation

A custom implementation of a **Least Recently Used (LRU) Cache** in Java, built from scratch using a doubly linked list and a HashMap — without relying on Java's built-in `LinkedHashMap`.

---

## What is an LRU Cache?

An LRU Cache is a data structure that holds a fixed number of items. When the cache is full and a new item needs to be added, it evicts the **least recently used** item to make room. This makes it ideal for scenarios where you want fast access to frequently used data while automatically discarding data that hasn't been used in a while.

**Real world use case:** In Android development, LRU Cache is used to store decoded `Bitmap` images in memory so that images already seen by the user load instantly instead of being re-downloaded from the network on every scroll.

---

## How It Works

The implementation combines two data structures:

- **Doubly Linked List** — maintains the order of usage. The most recently used item sits at the head, the least recently used sits at the tail.
- **HashMap** — maps each key to its corresponding node in the linked list for O(1) lookup.

Together they give O(1) time complexity for both `get()` and `put()` operations.

### get(key)
1. Check if key exists in the HashMap
2. If found → move node to head (mark as most recently used) → return value
3. If not found → return -1 (cache miss)

### put(key, value)
1. If key already exists → delete old node, insert updated node at head
2. If cache is full → evict node at tail (least recently used), insert new node at head
3. Otherwise → insert new node at head

---

## Complexity

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|-----------------|
| `get()`   | O(1)           | O(n)            |
| `put()`   | O(1)           | O(n)            |

---

## Project Structure

```
src/
├── LRUCache.java       # Core LRU Cache implementation
└── Main.java           # Driver code with sample usage
test/
└── LRUCacheTest.java   # JUnit 5 unit tests
```

---

## Sample Usage

```java
LRUCache cache = new LRUCache(2); // capacity = 2

cache.put(1, 1);   // cache: {1=1}
cache.put(2, 2);   // cache: {1=1, 2=2}
cache.get(1);      // returns 1, promotes key 1 to MRU
cache.put(3, 3);   // evicts key 2 (LRU), cache: {1=1, 3=3}
cache.get(2);      // returns -1 (evicted)
cache.get(3);      // returns 3
```

---

## How to Run

**Clone the repo**
```bash
git clone https://github.com/yourusername/lru-cache.git
cd lru-cache
```

**Compile and run**
```bash
javac src/LRUCache.java src/Main.java
java -cp src Main
```

---

## How to Run Tests

Open the project in **IntelliJ IDEA**, right click `LRUCacheTest.java` and select **Run LRUCacheTest**.

Make sure JUnit 5 is added as a library under **File → Project Structure → Libraries**.

---

## Key Design Decisions

**Why not use LinkedHashMap?**
Java's `LinkedHashMap` can replicate LRU behavior with a single line, but implementing it manually demonstrates a deeper understanding of how the underlying data structure actually works — which is what this project is about.

**Why a doubly linked list over a singly linked list?**
Deleting a node from the middle of the list requires access to the previous node. A doubly linked list gives O(1) deletion without traversal, whereas a singly linked list would require O(n) traversal to find the previous node.

**Why sentinel head and tail nodes?**
Using dummy head and tail nodes eliminates null checks during insertion and deletion, making the code cleaner and less error-prone.

---

## Test Cases

| Test | Description |
|------|-------------|
| `testGetOnEmptyCacheReturnsMinusOne` | get() on empty cache returns -1 |
| `testPutAndGet` | Basic put and get works correctly |
| `testEvictsLRUWhenFull` | Least recently used item is evicted when cache is full |
| `testGetPromotesToMRU` | get() promotes accessed node to most recently used |
| `testDuplicateKeyUpdatesValue` | put() with existing key updates value correctly |
| `testCacheSizeStaysWithinLimit` | Cache never exceeds its capacity |

---

## Author

Built as a core DSA implementation to understand caching mechanisms used in real world Android development.
