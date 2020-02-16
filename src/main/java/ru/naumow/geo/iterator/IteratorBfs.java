package ru.naumow.geo.iterator;

import ru.naumow.geo.GeoNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class IteratorBfs implements Iterator<GeoNode> {

    private Queue<GeoNode> queue = new LinkedList<>();

    public IteratorBfs(GeoNode root) {
        queue.add(root);
    }

    @Override
    public GeoNode next() {
        GeoNode node = queue.remove();
        queue.addAll(node.getChildren());
        return node;
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

}
