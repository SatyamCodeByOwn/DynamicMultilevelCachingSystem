import java.util.*;

public class MultilevelCacheSystem {
    private List<CacheLevel> cacheLevels;

    public MultilevelCacheSystem() {
        this.cacheLevels = new ArrayList<>();
    }

    public void addCacheLevel(int size, String evictionPolicy) {
        CacheLevel newLevel = new CacheLevel(size, evictionPolicy);
        cacheLevels.add(newLevel);
    }

    public String get(String key) {
        for (CacheLevel cacheLevel : cacheLevels) {
            String value = cacheLevel.get(key);
            if (value != null) {
                promoteDataToTopCache(key, value);
                return value;
            }
        }
        return null; // Cache miss
    }

    public void put(String key, String value) {
        CacheLevel l1Cache = cacheLevels.get(0);
        l1Cache.put(key, value);
    }

    private void promoteDataToTopCache(String key, String value) {
        CacheLevel l1Cache = cacheLevels.get(0);
        l1Cache.put(key, value);
    }

    public void removeCacheLevel(int levelIndex) {
        if (levelIndex < cacheLevels.size()) {
            cacheLevels.remove(levelIndex);
        }
    }

    public void displayCache() {
        for (int i = 0; i < cacheLevels.size(); i++) {
            System.out.print("L" + (i + 1) + " Cache: ");
            cacheLevels.get(i).display();
        }
    }
}
