package me.miroslavst.linkedlist;

public class LinkedList<T> {

    private LinkedListNode<T> linkedListHead;

    private int size = 0;


    public void add(T value) {
        if (linkedListHead == null) {
            linkedListHead = new LinkedListNode<>(value);
            size++;
            return;
        }

        LinkedListNode<T> traverser = getLastNode();
        traverser.setNext(new LinkedListNode<>(value));
        size++;
    }

    public T find(DataIterateFunction<T> dataIterateFunction) {
        TraverseInfo<T> traverseInfo = traverseAll((currentItem, index) -> dataIterateFunction.execute(currentItem.getValue(), index));

        if (traverseInfo.wasExecuteSuccessful()) {
            return traverseInfo.getCurrentNode().getValue();
        }

        return null;
    }

    public boolean remove(DataIterateFunction<T> dataIterateFunction) {
        TraverseInfo<T> traverseInfo = traverseAll(((currentItem, index) -> dataIterateFunction.execute(currentItem.getValue(), index)));

        if (traverseInfo.wasExecuteSuccessful()) {
            LinkedListNode<T> prevNode = traverseInfo.getPrevNode();
            LinkedListNode<T> currentNode = traverseInfo.getCurrentNode();
            LinkedListNode<T> nextNode = (currentNode == null ? null : currentNode.getNext());

            if (prevNode == null) {
                linkedListHead = linkedListHead.getNext();
                size--;
                return true;
            }

            prevNode.setNext(nextNode);
            size--;
            return true;
        }

        return false;
    }

    public T forEach(DataIterateFunction<T> dataIterateFunction) {
        TraverseInfo<T> traverseInfo = traverseAll((currentItem, index) -> dataIterateFunction.execute(currentItem.getValue(), index));

        if (traverseInfo.wasExecuteSuccessful()) {
            return traverseInfo.getCurrentNode().getValue();
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        traverseAll((linkedListNode, index) -> {result.append(linkedListNode.toString()).append(", "); return false;});

        return result.delete(result.length()-2, result.length()).toString();
    }

    private LinkedListNode<T> getLastNode() {
        TraverseInfo<T> traverseInfo = traverseAll((currentItem, index) -> index == getSize()-1);

        if (traverseInfo.wasExecuteSuccessful()) {
            return traverseInfo.getCurrentNode();
        }

        return linkedListHead;
    }

    private TraverseInfo<T> traverseAll(NodeIterateFunction<T> iterateFunction) {
        LinkedListNode<T> traverser = linkedListHead;
        LinkedListNode<T> prevTraverser = null;
        int index = 0;

        while (traverser != null) {
            if (iterateFunction.execute(traverser, index)) {
                return new TraverseInfo<>(prevTraverser, traverser, true);
            }

            prevTraverser = traverser;
            traverser = traverser.getNext();
            index++;
        }

        return new TraverseInfo<>(prevTraverser, null, false);
    }

    public int getSize() {
        return size;
    }
}
