public class Main {
    public static void main(String[] args) {
        MultilevelCacheSystem cacheSystem = new MultilevelCacheSystem();

        // Add cache levels
        cacheSystem.addCacheLevel(3, "LRU");  // L1 cache
        cacheSystem.addCacheLevel(2, "LFU");  // L2 cache

        // Insert key-value pairs
        cacheSystem.put("A", "1");
        cacheSystem.put("B", "2");
        cacheSystem.put("C", "3");

        // Display cache contents
        System.out.println("After initial insertions:");
        cacheSystem.displayCache();

        // Retrieve key "A" (should be found in L1)
        System.out.println("Get A: " + cacheSystem.get("A"));

        // Insert more data and check for eviction
        cacheSystem.put("D", "4");  // L1 cache is full, evict least recently used
        System.out.println("After inserting D (with eviction):");
        cacheSystem.displayCache();

        // Retrieve key "C" (promote from L2 to L1)
        System.out.println("Get C: " + cacheSystem.get("C"));

        // Final cache state
        System.out.println("Final cache state:");
        cacheSystem.displayCache();
    }
}
