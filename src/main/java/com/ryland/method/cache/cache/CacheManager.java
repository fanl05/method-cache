package com.ryland.method.cache.cache;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager {

    public static final Object[] EMPTY_PARAMETER = new Object[0];

    private static final Map<String, Cache<Object[], Object>> CACHES = new ConcurrentHashMap<>();

    public static Object get(String methodName, Object[] methodParameters) {
        Cache<Object[], Object> cache = CACHES.get(methodName);
        if (Objects.isNull(cache)) {
            return null;
        }
        Object returnValue = cache.get(methodParameters);
        return Objects.isNull(returnValue) ? null : returnValue;
    }

    public static void put(String methodName, Object[] methodParameters, Object returnValue) {
        Cache<Object[], Object> cache = CACHES.get(methodName);
        if (Objects.isNull(cache)) {
            cache = new CacheMapImpl();
        }
        cache.put(methodParameters, returnValue);
        CACHES.put(methodName, cache);
    }

}
