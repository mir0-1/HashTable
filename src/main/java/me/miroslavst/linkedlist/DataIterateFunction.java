package me.miroslavst.linkedlist;

public interface DataIterateFunction<T> {

    boolean execute(T currentItem, int index);

}
