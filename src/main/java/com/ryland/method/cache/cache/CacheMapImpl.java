package com.ryland.method.cache.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheMapImpl implements Cache<Object[], Object> {

    private final Map<Object[], Object> cache = new ConcurrentHashMap<>();

    @Override
    public void put(Object[] objects, Object o) {
        cache.put(objects, o);
    }

    @Override
    public void update(Object[] objects, Object o) {

    }

    @Override
    public void delete(Object[] objects) {

    }

    @Override
    public Object get(Object[] parameters) {
        for (Object[] cachedParameters : cache.keySet()) {
            if (compareParameters(parameters, cachedParameters)) {
                return cache.get(cachedParameters);
            }
        }
        return null;
    }

    private boolean compareParameters(Object[] parameters, Object[] cachedParameters) {
        if (parameters.length != cachedParameters.length) {
            return false;
        }
        for (int i = 0; i < parameters.length; i++) {
            if (cachedParameters[i].getClass() != parameters[i].getClass()) {
                return false;
            }
            if (!cachedParameters[i].equals(parameters[i])) {
                return false;
            }
        }

        return true;
    }
}
