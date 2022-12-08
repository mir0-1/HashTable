package me.miroslavst.linkedlist;

interface NodeIterateFunction<T> {

    boolean execute(LinkedListNode<T> currentItem, int index);

}
