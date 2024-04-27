package com.aeswibon.client.cache;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    public static final ConcurrentHashMap<Object, StorageData> cache = new ConcurrentHashMap<>();

    public static String get(Object key) {
        StorageData storageData = cache.get(key);
        if (storageData == null) {
            return null;
        }
        if (storageData.expirationTime().isBefore(Instant.now())) {
            cache.remove(key);
            return null;
        }
        return storageData.value();
    }

    public static void set(Object key, String value) {
        cache.put(key, new StorageData(value, Instant.MAX));
    }

    public static void set(Object key, String value, long expirationTime) {
        cache.put(key, new StorageData(value, Instant.now().plusMillis(expirationTime)));
    }
}
