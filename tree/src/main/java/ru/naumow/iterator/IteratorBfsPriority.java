package ru.naumow.iterator;

import ru.naumow.tree.Node;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class IteratorBfsPriority implements Iterator<Node> {

    private Queue<Node> upper = newQueue();
    private Queue<Node> lower = newQueue();

    public IteratorBfsPriority(Node root) {
        upper.add(root);
    }

    @Override
    public Node next() {
        Node node = upper.poll();
        lower.addAll(node.getChildren());
        return node;
    }

    @Override
    public boolean hasNext() {
        if (upper.isEmpty()) {
            upper = lower;
            lower = newQueue();
        }

        return !upper.isEmpty();
    }

    private PriorityQueue<Node> newQueue() {
        return new PriorityQueue<>(Comparator.comparing(Node::getPriority).reversed());
    }

}
