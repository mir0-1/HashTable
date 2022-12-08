package me.miroslavst.hashtable;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BasicHashTable<T> implements HashTable<T> {

    private Map<String, T> internalMap = new HashMap<>();

    private ObjectInputStream objectInputStream = null;

    private ObjectOutputStream objectOutputStream = null;

    private final String filename;


    public BasicHashTable(String filename) throws IOException {
        this.filename = filename;
    }

    public BasicHashTable() throws IOException {
        this("map.txt");
    }

    @Override
    public void put(String key, T value) {
        internalMap.put(key, (T) value);
    }

    @Override
    public T get(String key) {
        return internalMap.get(key);
    }

    @Override
    public boolean contains(String key) {
        return internalMap.containsKey(key);
    }

    @Override
    public boolean remove(String key) {
        return internalMap.remove(key) != null;
    }

    public void save() throws IOException {
        if (objectOutputStream == null) {
            FileOutputStream fileOutputStream = new FileOutputStream(filename, true);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        }

        objectOutputStream.writeObject(internalMap);
    }

    @SuppressWarnings("unchecked")
    public void read() throws IOException, ClassNotFoundException {
        if (objectInputStream == null) {
            FileInputStream fileInputStream = new FileInputStream(filename);
            objectInputStream = new ObjectInputStream(fileInputStream);
        }

        internalMap = (Map<String, T>) objectInputStream.readObject();
    }

    @Override
    public String toString() {
        return internalMap.toString();
    }
}
