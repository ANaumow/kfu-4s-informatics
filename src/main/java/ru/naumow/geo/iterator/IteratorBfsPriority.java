package ru.naumow.geo.iterator;

import ru.naumow.geo.GeoNode;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Supplier;

public class IteratorBfsPriority implements Iterator<GeoNode> {

    private Queue<GeoNode> upper = newQueue();
    private Queue<GeoNode> lower = newQueue();

    public IteratorBfsPriority(GeoNode root) {
        upper.add(root);
    }

    @Override
    public GeoNode next() {
        GeoNode node = upper.poll();
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

    private PriorityQueue<GeoNode> newQueue() {
        return new PriorityQueue<>(Comparator.comparing(GeoNode::getPriority).reversed());
    }

}
