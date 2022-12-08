package me.miroslavst.linkedlist;

import java.util.Objects;

class LinkedListNode<T> {

    private T value;

    private LinkedListNode<T> next;


    public LinkedListNode() {}

    public LinkedListNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
