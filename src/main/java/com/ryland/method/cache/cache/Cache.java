package com.ryland.method.cache.cache;

public interface Cache<K, V> {

    void put(K k, V v);

    void update(K k, V v);

    void delete(K k);

    V get(K k);

}
