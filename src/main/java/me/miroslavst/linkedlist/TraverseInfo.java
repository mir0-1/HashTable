package me.miroslavst.linkedlist;

public class TraverseInfo<T> {

    private final LinkedListNode<T> prevNode;

    private final LinkedListNode<T> currentNode;

    private final boolean executeSuccessful;

    public TraverseInfo(LinkedListNode<T> prevNode, LinkedListNode<T> currentNode, boolean executeSuccessful) {
        this.prevNode = prevNode;
        this.currentNode = currentNode;
        this.executeSuccessful = executeSuccessful;
    }

    public LinkedListNode<T> getCurrentNode() {
        return currentNode;
    }

    public LinkedListNode<T> getPrevNode() {
        return prevNode;
    }

    public boolean wasExecuteSuccessful() {
        return executeSuccessful;
    }
}
