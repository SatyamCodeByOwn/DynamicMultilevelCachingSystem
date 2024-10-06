import java.util.*;

public class CacheLevel {
    private int size;
    private Map<String, String> cacheData;
    private String evictionPolicy;
    private LinkedHashMap<String, String> lruCache;
    private Map<String, Integer> accessFrequency;

    public CacheLevel(int size, String evictionPolicy) {
        this.size = size;
        this.evictionPolicy = evictionPolicy;
        this.cacheData = new LinkedHashMap<>();
        
        if (evictionPolicy.equals("LRU")) {
            this.lruCache = new LinkedHashMap<>(size, 0.75f, true);
        } else if (evictionPolicy.equals("LFU")) {
            this.accessFrequency = new HashMap<>();
        }
    }

    public String get(String key) {
        if (cacheData.containsKey(key)) {
            if (evictionPolicy.equals("LFU")) {
                accessFrequency.put(key, accessFrequency.getOrDefault(key, 0) + 1);
            }
            return cacheData.get(key);
        }
        return null;
    }

    public void put(String key, String value) {
        if (cacheData.size() >= size) {
            evict(); // Evict an element if cache is full
        }
        cacheData.put(key, value);
        if (evictionPolicy.equals("LRU")) {
            lruCache.put(key, value); // Maintain LRU order
        } else if (evictionPolicy.equals("LFU")) {
            accessFrequency.put(key, 1); // Initialize access frequency
        }
    }

    private void evict() {
        if (evictionPolicy.equals("LRU")) {
            String oldestKey = lruCache.keySet().iterator().next(); // Oldest key in LRU
            lruCache.remove(oldestKey);
            cacheData.remove(oldestKey); // Remove from main cache
        } else if (evictionPolicy.equals("LFU")) {
            String leastFrequentKey = Collections.min(accessFrequency.entrySet(), Map.Entry.comparingByValue()).getKey();
            accessFrequency.remove(leastFrequentKey);
            cacheData.remove(leastFrequentKey); // Remove from main cache
        }
    }

    public void display() {
        System.out.println(cacheData);
    }
}
