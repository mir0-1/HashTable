package me.miroslavst.hashtable;


public interface HashTable<T> {

    void put(String key, T value);

    T get(String key);

    boolean contains(String key);

    boolean remove(String key);

}
