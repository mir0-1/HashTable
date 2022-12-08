package me.miroslavst.hashtable;

class HashTableEntry<T> {

    private final String key;

    private final T value;

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public HashTableEntry(String key, T value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + ':' + value + '\n';
    }
}
