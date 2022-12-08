package me.miroslavst.hashtable;

import me.miroslavst.linkedlist.LinkedList;

import java.io.*;

public class AdvancedHashTable<T> implements HashTable<T> {

    private final int BUCKETS_COUNT = 1024;

    private final LinkedList<HashTableEntry<T>>[] buckets;

    private final File file;

    private FileInputStream fileInputStream;

    private final FileOutputStream fileOutputStream;

    public AdvancedHashTable() throws IOException {
        file = new File("file.txt");
        file.createNewFile();
        buckets = new LinkedList[BUCKETS_COUNT];
        fileOutputStream = new FileOutputStream("file.txt", true);
    }

    @Override
    public void put(String key, T value) {
        int targetIndex = hashFunction(key);

        if (key.contains(":")) {
            throw new IllegalArgumentException("Keys are not allowed to have ':' symbol");
        }

        if (buckets[targetIndex] == null) {
            buckets[targetIndex] = new LinkedList<>();
        }

        buckets[targetIndex].remove((currentItem, index) -> currentItem.getKey().equals(key));
        buckets[targetIndex].add(new HashTableEntry<>(key, value));
    }

    @Override
    public T get(String key) {
        int targetIndex = hashFunction(key);

        if (buckets[targetIndex] == null) {
            return null;
        }

        HashTableEntry<T> result = buckets[targetIndex].find((currentItem, index) -> currentItem.getKey().equals(key));
        return result == null ? null : result.getValue();
    }

    @Override
    public boolean contains(String key) {
        return get(key) != null;
    }

    @Override
    public boolean remove(String key) {
        int targetIndex = hashFunction(key);
        return buckets[targetIndex].remove((currentItem, index) -> currentItem.getKey().equals(key));
    }

    public void save() throws Exception {
        if (fileInputStream == null) {
            fileInputStream = new FileInputStream("file.txt");
        }

        for (LinkedList<HashTableEntry<T>> linkedList : buckets) {
            if (linkedList != null) {
                linkedList.forEach((currentItem, index) -> {
                    try {
                        fileOutputStream.write(currentItem.toString().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return false;
                });
            }
        }
    }

    public void read(Class<T> targetValueType) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] args = line.split(":");
            String key = args[0];
            T value = targetValueType.getConstructor(String.class).newInstance(args[1]);
            put(key, value);
        }
    }

    private int hashFunction(String key) {
        int result = key.hashCode() % BUCKETS_COUNT;

        if (result < 0) {
            result = -result;
        }

        return result;
    }
}
